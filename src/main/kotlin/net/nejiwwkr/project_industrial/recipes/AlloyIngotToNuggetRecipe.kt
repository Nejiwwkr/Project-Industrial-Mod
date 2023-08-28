package net.nejiwwkr.project_industrial.recipes

import net.minecraft.inventory.CraftingInventory
import net.minecraft.item.ItemStack
import net.minecraft.recipe.RecipeSerializer
import net.minecraft.recipe.SpecialCraftingRecipe
import net.minecraft.util.Identifier
import net.minecraft.world.World
import net.nejiwwkr.project_industrial.ProjectIndustrialMod
import net.nejiwwkr.project_industrial.item.alloy.AlloyIngotItem

class AlloyIngotToNuggetRecipe(id: Identifier?) : SpecialCraftingRecipe(id) {
    override fun matches(inventory: CraftingInventory?, world: World?): Boolean {
        val list = getActualList(inventory)
        return list.size == 1 && list[0].item is AlloyIngotItem
    }

    override fun craft(inventory: CraftingInventory?): ItemStack {
        val k = ItemStack(ProjectIndustrialMod.ALLOY_NUGGET,9)
        k.orCreateNbt.copyFrom(getActualList(inventory)[0].orCreateNbt)
        return if (matches(inventory,null)) k else ItemStack.EMPTY
    }

    override fun fits(width: Int, height: Int): Boolean = width * height >= 1

    override fun getSerializer(): RecipeSerializer<*> = ProjectIndustrialMod.ALLOY_INGOT_TO_NUGGET_RECIPE_SERIALIZER

    private fun getActualList(inventory: CraftingInventory?): ArrayList<ItemStack> {
        val list = ArrayList<ItemStack>()
        for (i in 0..(inventory?.size()?:0)) if (inventory?.getStack(i)?.isEmpty == false) list.add(inventory.getStack(i))
        return list
    }
}