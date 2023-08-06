package net.nejiwwkr.project_industrial.util;

import net.fabricmc.fabric.impl.content.registry.FuelRegistryImpl;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiPredicate;

public class NbtTagUtil {
    public static boolean containsEnchantment (ItemStack stack, String enchantmentName) {
        if (stack.hasEnchantments()) {
            boolean a = true;
            for (int i = 1; i <= stack.getEnchantments().size(); i++) {
                a = a && !stack.getEnchantments().getString(i).contains(enchantmentName);
            }
            return !a;
        }
        return false;
    }

    public static BiPredicate<ItemStack,String> containsEnchantmentPredicate = (stack, enchantmentName) -> {
        if (stack.hasEnchantments()) {
            boolean a = true;
            for (int i = 1; i <= stack.getEnchantments().size(); i++) {
                a = a && !stack.getEnchantments().getString(i).contains(enchantmentName);
            }
            return !a;
        }
        return false;
    };

    public static boolean isFuel(@NotNull ItemStack stack) {
        return getFuelTime(stack) > 0;
    }

    public static int getFuelTime(@NotNull ItemStack stack) {
        Item item = stack.getItem();
        if (item == null) {
            return 0;
        }
        var f = new FuelRegistryImpl();
        Map<Item,Integer> m = f.getFuelTimes();
        int burnTime = 0;
        if (m.containsKey(item)) burnTime = m.get(item);
        return burnTime;
    }

    /**
     * <i>忽略顺序地</i> 比较两个数组
     * @return 两个数组元素在种类，数量上是否完全一致
     */
    public static boolean compareArrays(Object[] arr1, Object[] arr2) {
        if (arr1.length != arr2.length) {
            return false;
        }

        Map<Object, Integer> map1 = new HashMap<>();
        for (Object obj : arr1) {
            map1.put(obj, map1.getOrDefault(obj, 0) + 1);
        }

        Map<Object, Integer> map2 = new HashMap<>();
        for (Object obj : arr2) {
            map2.put(obj, map2.getOrDefault(obj, 0) + 1);
        }

        return map1.equals(map2);
    }
}
