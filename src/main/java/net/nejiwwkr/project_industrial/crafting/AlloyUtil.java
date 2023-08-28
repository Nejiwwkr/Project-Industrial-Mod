package net.nejiwwkr.project_industrial.crafting;

import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.text.LiteralText;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Formatting;
import net.minecraft.world.World;
import net.nejiwwkr.project_industrial.crafting.alloy.MetalType;
import net.nejiwwkr.project_industrial.crafting.alloy.Usage;
import net.nejiwwkr.project_industrial.item.abstract_mod_item.InstructType;
import net.nejiwwkr.project_industrial.item.abstract_mod_item.Instructed;
import net.nejiwwkr.project_industrial.item.alloy.AlloyIngotItem;
import net.nejiwwkr.project_industrial.util.C;
import org.jetbrains.annotations.Nullable;

import java.util.*;
import java.util.stream.Stream;

public class AlloyUtil {
    public static HashMap<MetalType, Usage> monoMap = new HashMap<>();
    public static HashMap<LinkedHashSet<MetalType>, Usage> diMap = new HashMap<>();
    public static HashMap<LinkedHashSet<MetalType>, Usage> triMap = new HashMap<>();
    public static HashMap<LinkedHashSet<MetalType>, Usage> tetraMap = new HashMap<>();

    static {
        monoMap.put(MetalType.IRON, new Usage(null, formMonoKey(MetalType.IRON)));
    }
    public static Usage[] getUsage(ItemStack stack) {
        if (Stream.<Class<?>>of(AlloyIngotItem.class).noneMatch(x -> x.isInstance(stack.getItem()))) {
            throw new IllegalArgumentException("item isn't alloy");
        }
        var nbt = stack.getOrCreateNbt();
        var main = MetalType.getTypeByLiteral(nbt.getString("main_ingredient"));
        var side = Stream.of(0,1,2,3)
                         .map(k -> MetalType.getTypeByLiteral(nbt.getString("side_ingredient") + k))
                         .filter(Objects::nonNull)
                         .toArray(MetalType[]::new);

        Usage[] res = new Usage[4];
        res[0] = monoMap.get(main);




        //TODO combination
        return Arrays.stream(res).filter(Objects::nonNull).toArray(Usage[]::new);
    }

    public static void writeNbt(NbtCompound nbt, MetalType mainIngredient, MetalType... sideIngredients) {
        nbt.putString("main_ingredient", mainIngredient.getMetalName());
        for (int k = 0; k <= sideIngredients.length-1; k++) if (sideIngredients[k] != null && sideIngredients[k].getMetalName() != null) nbt.putString("side_ingredient" + k, sideIngredients[k].getMetalName());
    }

    public static String formMonoKey(String name) {
        return C.Prefix.ALLOY_USAGE + "." + name;
    }
    public static String formMonoKey(MetalType type) {
        return formMonoKey(type.getMetalName());
    }

    public static void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        var nbt = stack.getOrCreateNbt();

        if (nbt.getString("main_ingredient") != null && stack.getItem() instanceof Instructed<?> instructed) {
            var main = MetalType.getTypeByLiteral(nbt.getString("main_ingredient"));
            if (main != null) instructed.setInformation(new TranslatableText(C.Prefix.MAIN_INGREDIENT + main.getMetalName()).formatted(Formatting.BLUE));

            boolean condition = false;
            int i = 3;
            do {
                condition |= !Objects.equals(nbt.getString("side_ingredient" + i), "");
            }while (i --> 0);

            if (condition) {
                TranslatableText sideInfo = new TranslatableText("project_industrial.has_side_ingredient");

                int j = 3;
                do {
                    int k = 3 - j;//正序
                    if (nbt.getString("side_ingredient" + k) != null) {
                        var side = MetalType.getTypeByLiteral(nbt.getString("side_ingredient" + k));
                        if (side != null) {
                            if (k > 0) sideInfo.append(new LiteralText(", "));
                            sideInfo.append(new TranslatableText(C.Prefix.SIDE_INGREDIENT + side.getMetalName()));
                        }
                    }
                }while (j --> 0);

                sideInfo.formatted(Formatting.GRAY);
                instructed.appendInformation(sideInfo);
            }

            if (main != null) {//TODO phrase usage
                instructed.appendInformation(new TranslatableText(C.Prefix.ALLOY_USAGE).formatted(Formatting.YELLOW));
                instructed.appendInformation(new TranslatableText(C.Prefix.ALLOY_USAGE + C.Suffix.ON_TOOL).append(new TranslatableText(C.Prefix.ALLOY_USAGE + "." + main.getMetalName() + C.Suffix.ON_TOOL)).formatted(Formatting.ITALIC,Formatting.GRAY));
                instructed.appendInformation(new TranslatableText(C.Prefix.ALLOY_USAGE + C.Suffix.ON_ARMOR).append(new TranslatableText(C.Prefix.ALLOY_USAGE + "." + main.getMetalName() + C.Suffix.ON_ARMOR)).formatted(Formatting.ITALIC,Formatting.GRAY));
            }
            instructed.appendToolInstruct(stack,world,tooltip,context, InstructType.Alt);
        }
    }
}
