package net.nejiwwkr.project_industrial.item.abstract_mod_item;

import net.minecraft.block.Block;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemStack;
import net.minecraft.text.LiteralText;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Formatting;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

public class ProjectIndustrialInstructedBlockItem extends BlockItem implements Instructed<ProjectIndustrialInstructedBlockItem>{
    private boolean instruct = false;
    private boolean tipAvailable = false;
    private boolean summaryAvailable = false;
    private boolean informationAvailable = false;
    private Text[] Tips; //Shift
    private Text[] Summary; //Ctrl
    private Text[] Information; //Alt

    public ProjectIndustrialInstructedBlockItem(Block block, Settings settings) {
        super(block, settings);
    }

    @Override
    public boolean hasInstruct() {
        return instruct;
    }

    public ProjectIndustrialInstructedBlockItem setTips(Text... tips) {
        this.instruct = true;
        this.tipAvailable = true;
        this.Tips = tips;
        return this;
    }

    @Override
    public ProjectIndustrialInstructedBlockItem setSummary(Text... sum) {
        this.instruct = true;
        this.summaryAvailable = true;
        this.Summary = sum;
        return this;
    }

    @Override
    public ProjectIndustrialInstructedBlockItem setInformation(Text... info) {
        this.instruct = true;
        this.informationAvailable = true;
        this.Information = info;
        return this;
    }

    @Override
    public ProjectIndustrialInstructedBlockItem appendTips(Text... tips) {
        if (!tipAvailable) setTips(tips);
        Tips = Stream.concat(Arrays.stream(Tips),Arrays.stream(tips)).toArray(Text[]::new);
        return this;
    }

    @Override
    public ProjectIndustrialInstructedBlockItem appendSummary(Text... sum) {
        if (!summaryAvailable) setTips(sum);
        Summary = Stream.concat(Arrays.stream(Summary),Arrays.stream(sum)).toArray(Text[]::new);
        return this;
    }

    @Override
    public ProjectIndustrialInstructedBlockItem appendInformation(Text... info) {
        if (!informationAvailable) setTips(info);
        Information = Stream.concat(Arrays.stream(Information),Arrays.stream(info)).toArray(Text[]::new);
        return this;
    }

    @Override
    public void appendToolInstruct(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context,InstructType... type) {
        boolean shiftSelected = Arrays.stream(type).anyMatch(x -> x == InstructType.Shift);
        boolean ctrlSelected = Arrays.stream(type).anyMatch(x -> x == InstructType.Ctrl);
        boolean altSelected = Arrays.stream(type).anyMatch(x -> x == InstructType.Alt);

        if ((world != null && world.isClient()) && this.hasInstruct()) {
            if (Screen.hasShiftDown() && shiftSelected) {
                Collections.addAll(tooltip, Tips);
            } else if (Screen.hasControlDown() && ctrlSelected) {
                Collections.addAll(tooltip, Summary);
            } else if (Screen.hasAltDown() && altSelected) {
                Collections.addAll(tooltip, Information);
            } else {
                if (tipAvailable && shiftSelected) {
                    tooltip.add(new TranslatableText("project_industrial.tip.l").formatted(Formatting.GRAY)
                            .append(new LiteralText("[Shift]").formatted(Formatting.BOLD,Formatting.LIGHT_PURPLE))
                            .append(new TranslatableText("project_industrial.tip.r").formatted(Formatting.GRAY))
                    );
                }
                if (summaryAvailable && ctrlSelected) {
                    tooltip.add(new TranslatableText("project_industrial.sum.l").formatted(Formatting.GRAY)
                            .append(new LiteralText("[Ctrl]").formatted(Formatting.BOLD,Formatting.LIGHT_PURPLE))
                            .append(new TranslatableText("project_industrial.sum.r").formatted(Formatting.GRAY))
                    );
                }
                if (informationAvailable && altSelected) {
                    tooltip.add(new TranslatableText("project_industrial.info.l").formatted(Formatting.GRAY)
                            .append(new LiteralText("[Alt]").formatted(Formatting.BOLD,Formatting.LIGHT_PURPLE))
                            .append(new TranslatableText("project_industrial.info.r").formatted(Formatting.GRAY))
                    );
                }
            }
        }
    }
}
