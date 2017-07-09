package com.higgs.wom.client.gui;

import com.higgs.wom.HiggsWom;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class WomGuiMiningSkill extends WomGuiWindow
{
    private EntityPlayer player;

    public WomGuiMiningSkill(EntityPlayer player)
    {
        this.player = player;
        this.width = 256;
        this.height = 256;
        displayX = 150;
        displayY = 100;//(this.mc.displayWidth / 2) - (256) + 100;
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float depth, int x, int y)
    {
        GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);

        if(dragging)
        {
            windowDragged(x, y);
        }

        this.mc.getTextureManager().bindTexture(new ResourceLocation(HiggsWom.MODID + ":textures/gui/guiWindowSkill.png"));

        drawTexturedModalRect(displayX + dragX, displayY + dragY, 0, 0, 256, 256);

        this.mc.getTextureManager().bindTexture(new ResourceLocation(HiggsWom.MODID + ":textures/items/itemMinersPick.png"));

        drawTexturedModalRect(displayX + dragX + 496, displayY + dragY + 7, 96, 128, 15, 14);
    }

    @Override
    public void drawGuiContainerForegroundLayer(int mouseX, int mouseY)
    {
        GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
    }

    public static boolean isInRect(int x, int y, int xSize, int ySize, int mouseX, int mouseY)
    {
        return ((mouseX >= x && mouseX <= x + xSize) && (mouseY >= y && mouseY <= y + ySize));
    }

    @Override
    protected void mouseClicked(int x, int y, int button)
    {
        super.mouseClicked(x, y, button);

        if(isInRect(displayX + dragX + 46, displayY + dragY + 6, 447, 14, x, y))
        {
            dragging = true;
            lastDragX = x - dragX;
            lastDragY = y - dragY;
        }
    }
}
