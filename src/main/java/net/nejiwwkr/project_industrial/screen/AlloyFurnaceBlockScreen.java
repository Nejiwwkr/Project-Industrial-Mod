package net.nejiwwkr.project_industrial.screen;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.nejiwwkr.project_industrial.screen.handler.AlloyFurnaceBlockScreenHandler;
import net.nejiwwkr.project_industrial.util.C;


public class AlloyFurnaceBlockScreen extends HandledScreen<AlloyFurnaceBlockScreenHandler> {
    public static final Identifier TEXTURE = new Identifier(C.MOD_ID,"textures/gui/alloy_furnace.png");

    public AlloyFurnaceBlockScreen(AlloyFurnaceBlockScreenHandler handler, PlayerInventory inventory, Text title) {
        super(handler, inventory, title);
        this.passEvents = false;
        this.backgroundHeight = 167;
        this.playerInventoryTitleY = this.backgroundHeight - 94;
    }

    @Override
    public void drawBackground(MatrixStack matrices, float delta, int mouseX, int mouseY) {
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0f,1.0f,1.0f,1.0f);
        RenderSystem.setShaderTexture(0,TEXTURE);
        int i = this.x;
        int j = this.y;
        this.drawTexture(matrices,i,j,0,0,this.backgroundWidth,this.backgroundHeight);

        int tick,cookTime,fuelLeft,fuelTotal;
        tick = this.handler.getTick();
        cookTime = this.handler.getCookTime();
        fuelLeft = this.handler.getFuelLeft();
        fuelTotal = this.handler.getFuelTotal();

        //制作进度条动画
        if (cookTime != 0) this.drawTexture(matrices,i + 101,j + 38,176,0,(tick * 24)/cookTime,17);
        //燃料剩余进度条
        if (fuelTotal != 0) this.drawTexture(matrices, i + 7, j + 40, 0, 166, (fuelLeft * 90)/fuelTotal, 7);
    }

    @Override
    public void render(MatrixStack matrices, int mouseX, int mouseY, float delta) {
        this.renderBackground(matrices);
        super.render(matrices, mouseX, mouseY, delta);
        this.drawMouseoverTooltip(matrices,mouseX,mouseY);
    }
}
