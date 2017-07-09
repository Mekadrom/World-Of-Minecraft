package com.higgs.wom.client.gui;

import com.higgs.wom.HiggsWom;
import com.higgs.wom.guicontainer.WomContainerLoot;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.inventory.IInventory;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class WomGuiLoot extends GuiContainer
{

    public WomGuiLoot(IInventory playerInv, IInventory loot)
    {
        super(new WomContainerLoot(playerInv, loot));

        this.xSize = 176;
        this.ySize = 166;
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float depth, int x, int y)
    {
        GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);

        this.mc.getTextureManager().bindTexture(new ResourceLocation(HiggsWom.MODID + ":textures/gui/guiTestGui.png"));

//        drawTexturedModalRect((width / 2) - (xSize / 2), (height / 2) - (ySize / 2), 0, 0, this.xSize, this.ySize);
        drawTexturedModalRect(guiLeft, guiTop, 0, 0, xSize, ySize);
    }
}
