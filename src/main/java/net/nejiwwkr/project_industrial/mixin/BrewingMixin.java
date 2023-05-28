package net.nejiwwkr.project_industrial.mixin;

import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.potion.Potion;
import net.minecraft.potion.Potions;
import net.minecraft.recipe.BrewingRecipeRegistry;
import net.nejiwwkr.project_industrial.ProjectIndustrialMod;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static net.nejiwwkr.project_industrial.ProjectIndustrialMod.*;

@Mixin(BrewingRecipeRegistry.class)
public class BrewingMixin {
	@Inject(at = @At("HEAD"), method = "registerDefaults")
	private static void registerDefaults(CallbackInfo info) {
		ProjectIndustrialMod.LOGGER.info("This line is printed by an example mod mixin!");
		registerPotionRecipe(Potions.EMPTY, Items.APPLE, APPLE_VINEGAR);
		registerPotionRecipe(APPLE_VINEGAR, LEAD_OXIDE, LEAD_SOLUTION);
	}


	@Invoker("registerPotionRecipe")
	private static void registerPotionRecipe(Potion input, Item ignoredItem, Potion ignoredOutput){
	}
}
