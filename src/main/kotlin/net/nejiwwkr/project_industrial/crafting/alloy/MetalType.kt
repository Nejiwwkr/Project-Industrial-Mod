package net.nejiwwkr.project_industrial.crafting.alloy

import net.minecraft.item.Item
import net.minecraft.item.Items
import net.nejiwwkr.project_industrial.ProjectIndustrialMod
import net.nejiwwkr.project_industrial.item.IngotItem
import net.nejiwwkr.project_industrial.item.NuggetItem


enum class MetalType(val metalName: String, val stiffness: Double, val meltingPoint: Int){
    GOLD("gold", 4.0, 1064),
    IRON("iron", 4.0, 1539),
    COPPER("copper", -2.0, 1358),
    LEAD("lead", 2.0, 327),
    SILVER("silver", 4.0, 961),
    NICKEL("nickel", 6.0, 1453),
    URANIUM("uranium", 2.0, 1132),
    MOLYBDENUM("molybdenum", 8.0, 2620),
    CHROME("chrome", 3.0, 1907),
    MANGANESE("manganese", 5.0, 1244),
    COAL("coal", 0.0, -500);

    companion object {
        @JvmStatic
        fun getTypeByItem(item: Item): MetalType? {
            return when (item) {
                is IngotItem -> item.type
                is NuggetItem-> item.type
                Items.GOLD_INGOT,Items.GOLD_NUGGET -> GOLD
                Items.IRON_INGOT,Items.IRON_NUGGET -> IRON
                Items.COPPER_INGOT -> COPPER
                Items.COAL,Items.CHARCOAL,ProjectIndustrialMod.COAL_NUGGET -> COAL
                else -> null
            }
        }
        @JvmStatic
        fun getTypeByItem(item: Array<Item>): Array<MetalType?> {
            return item.map { getTypeByItem(it) }.toTypedArray()
        }

        @JvmStatic
        fun getTypeByLiteral(name: String): MetalType? {
            for (type in entries) if (type.metalName == name) return type
            return null
        }

        @JvmStatic
        fun getTypeByLiteral(name: Array<String>): Array<MetalType?> {
            return name.map { getTypeByLiteral(it) }.toTypedArray()
        }
    }
}