# Project Industrial Mod

## Idea

Inspired by the famous mod IC2  
This might be a great high-version technology mod  
This mod is aimed at restoring industrial in the real world especially about chemistry,physics and biology

## Work In Progress

### Relics of modern industrial

Actually,the worldview of this mod is that some villagers stepped into the industrial age before the player.But the overuse of resources led them to demise.

## New Biome

- overcut forest
- illusory forest
- nucleared dirt

## completely customized alloy

Allowing you to make alloy with one main ingredient and five side ingredients.  
Just like what we can do in the mod ___Tinkers' Construct___,each ingredient will give the alloy some special effect that can be applied to the tools or armor.
Also,some special recipes like stainless steel will be added in.

> For other mod developers,you can add project-industrial alloy recipe by using the static method ___net.nejiwwkr.project_industrial.crafting.AlloyRecipe___
> example:
> ```java
> class MyMod {
>   static{
>       project_industrial.crafting.AlloyRecipe.addRecipe(new AlloyRecipe(Items.IRON_INGOT,Items.GOLD_INGOT,200,Items.COLD_NUGGET));
>       //AlloyRecipe(Item mainIngredient, Item resultItem, int smeltingTime, Item... sideIngredients)
>   }
> }
> ```


## Future Update

|version|isAvailable|
|----|----|
|1.17-|x|
|1.18.2|√|
|1.19.2|√|

## License

This template is available under the CC0 license.
