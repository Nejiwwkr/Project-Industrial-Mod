package net.nejiwwkr.project_industrial.mixin;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import net.minecraft.inventory.CraftingInventory;
import net.minecraft.item.DyeItem;
import net.minecraft.item.FireworkRocketItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.recipe.FireworkStarRecipe;
import net.minecraft.recipe.Ingredient;
import net.minecraft.util.Util;
import net.minecraft.world.World;
import net.nejiwwkr.project_industrial.item.ChemicalItemWithColor;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

import java.util.List;
import java.util.Map;

@Mixin(FireworkStarRecipe.class)
public class FireworkMixin {

    private static final Ingredient TYPE_MODIFIER;
    private static final Ingredient TRAIL_MODIFIER;
    private static final Ingredient FLICKER_MODIFIER;
    private static final Map TYPE_MODIFIER_MAP;
    private static final Ingredient GUNPOWDER;

    /**
     * @author Nejiwwkr
     * @reason Adding extra color for fireworks
     */
    @Overwrite
    public ItemStack craft(CraftingInventory craftingInventory) {
        ItemStack itemStack = new ItemStack(Items.FIREWORK_STAR);
        NbtCompound nbtCompound = itemStack.getOrCreateSubNbt("Explosion");
        FireworkRocketItem.Type type = FireworkRocketItem.Type.SMALL_BALL;
        List<Integer> list = Lists.newArrayList();

        for(int i = 0; i < craftingInventory.size(); ++i) {
            ItemStack itemStack2 = craftingInventory.getStack(i);
            if (TYPE_MODIFIER.test(itemStack2)) {
                type = (FireworkRocketItem.Type)TYPE_MODIFIER_MAP.get(itemStack2.getItem());
            }else if (FLICKER_MODIFIER.test(itemStack2)) {
                nbtCompound.putBoolean("Flicker", true);
            }else if (TRAIL_MODIFIER.test(itemStack2)) {
                nbtCompound.putBoolean("Trail", true);
            }else if (itemStack2.getItem() instanceof DyeItem) {
                list.add(((DyeItem)itemStack2.getItem()).getColor().getFireworkColor());
            }else if (itemStack2.getItem() instanceof ChemicalItemWithColor) {
                list.add(((ChemicalItemWithColor) itemStack2.getItem()).getColor());
            }
        }
        nbtCompound.putIntArray("Colors", list);
        nbtCompound.putByte("Type", (byte)type.getId());
        return itemStack;
    }

    static {
        TYPE_MODIFIER = Ingredient.ofItems(Items.FIRE_CHARGE, Items.FEATHER, Items.GOLD_NUGGET, Items.SKELETON_SKULL, Items.WITHER_SKELETON_SKULL, Items.CREEPER_HEAD, Items.PLAYER_HEAD, Items.DRAGON_HEAD, Items.ZOMBIE_HEAD);
        TRAIL_MODIFIER = Ingredient.ofItems(Items.DIAMOND);
        FLICKER_MODIFIER = Ingredient.ofItems(Items.GLOWSTONE_DUST);
        TYPE_MODIFIER_MAP = Util.make(Maps.newHashMap(), (hashMap) -> {
            hashMap.put(Items.FIRE_CHARGE, FireworkRocketItem.Type.LARGE_BALL);
            hashMap.put(Items.FEATHER, FireworkRocketItem.Type.BURST);
            hashMap.put(Items.GOLD_NUGGET, FireworkRocketItem.Type.STAR);
            hashMap.put(Items.SKELETON_SKULL, FireworkRocketItem.Type.CREEPER);
            hashMap.put(Items.WITHER_SKELETON_SKULL, FireworkRocketItem.Type.CREEPER);
            hashMap.put(Items.CREEPER_HEAD, FireworkRocketItem.Type.CREEPER);
            hashMap.put(Items.PLAYER_HEAD, FireworkRocketItem.Type.CREEPER);
            hashMap.put(Items.DRAGON_HEAD, FireworkRocketItem.Type.CREEPER);
            hashMap.put(Items.ZOMBIE_HEAD, FireworkRocketItem.Type.CREEPER);
        });
        GUNPOWDER = Ingredient.ofItems(Items.GUNPOWDER);
    }

    /**
     * @author Nejiwwkr
     * @reason 允许化合物参与合成烟火配方
     */
    @Overwrite
    public boolean matches(CraftingInventory craftingInventory, World world) {
        boolean bl = false;
        boolean bl2 = false;
        boolean bl3 = false;
        boolean bl4 = false;
        boolean bl5 = false;

        for(int i = 0; i < craftingInventory.size(); ++i) {
            ItemStack itemStack = craftingInventory.getStack(i);
            if (!itemStack.isEmpty()) {
                if (TYPE_MODIFIER.test(itemStack)) {
                    if (bl3) {
                        return false;
                    }

                    bl3 = true;
                } else if (FLICKER_MODIFIER.test(itemStack)) {
                    if (bl5) {
                        return false;
                    }

                    bl5 = true;
                } else if (TRAIL_MODIFIER.test(itemStack)) {
                    if (bl4) {
                        return false;
                    }

                    bl4 = true;
                } else if (GUNPOWDER.test(itemStack)) {
                    if (bl) {
                        return false;
                    }

                    bl = true;
                } else {
                    if (!((itemStack.getItem() instanceof DyeItem) || (itemStack.getItem() instanceof ChemicalItemWithColor))) {
                        return false;
                    }

                    bl2 = true;
                }
            }
        }
        return bl && bl2;
    }
}
