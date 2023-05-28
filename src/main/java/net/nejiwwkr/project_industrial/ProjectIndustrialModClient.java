package net.nejiwwkr.project_industrial;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.render.fluid.v1.FluidRenderHandlerRegistry;
import net.fabricmc.fabric.api.client.render.fluid.v1.SimpleFluidRenderHandler;
import net.fabricmc.fabric.api.client.screenhandler.v1.ScreenRegistry;
import net.fabricmc.fabric.api.command.v1.CommandRegistrationCallback;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.util.Identifier;
import net.nejiwwkr.project_industrial.command.NbtCommand;
import net.nejiwwkr.project_industrial.fluid.ModFluids;
import net.nejiwwkr.project_industrial.screen.AlloyFurnaceBlockScreen;

import static net.nejiwwkr.project_industrial.ProjectIndustrialMod.LEAD_GLASS;

public class ProjectIndustrialModClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        FluidRenderHandlerRegistry.INSTANCE.register(
                ModFluids.StillBoronFluid,
                ModFluids.FlowingBoronFluid,
                new SimpleFluidRenderHandler(
                        new Identifier("minecraft:block/water_still"),
                        new Identifier("minecraft:block/water_flow"),
                        0xA17795EB
                ));
        BlockRenderLayerMap.INSTANCE.putFluids(RenderLayer.getTranslucent(), ModFluids.StillBoronFluid,ModFluids.FlowingBoronFluid);
        BlockRenderLayerMap.INSTANCE.putBlock(LEAD_GLASS,RenderLayer.getTranslucent());

        ScreenRegistry.register(ProjectIndustrialMod.AF_SCREEN_HANDLER, AlloyFurnaceBlockScreen::new);

        CommandRegistrationCallback.EVENT.register(((dispatcher, dedicated) -> NbtCommand.register(dispatcher)));
    }
}
