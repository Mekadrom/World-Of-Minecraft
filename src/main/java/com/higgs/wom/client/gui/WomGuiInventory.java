package com.higgs.wom.client.gui;

import com.higgs.wom.HiggsWom;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class WomGuiInventory extends WomGuiWindow
{
    private EntityPlayer player;
    private int displayX;
    private int displayY;

    public WomGuiInventory(IInventory playerInv, EntityPlayer player, Container container)
    {
        super(container);
        this.player = player;
        this.mc = Minecraft.getMinecraft();
        this.fontRendererObj = Minecraft.getMinecraft().fontRenderer;
        this.enumWindowType = WomGuiWindow.EnumWindowType.INVENTORY;
        displayX = (mc.displayWidth / 2) - 100;
        displayY = (mc.displayHeight / 2) - 177;

        this.xSize = 184;
        this.ySize = 354;
    }

    public void initGui()
    {
        super.initGui();
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float depth, int mouseX, int mouseY)
    {
//        super.drawGuiContainerBackgroundLayer(depth, mouseX, mouseY);

        GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);

        mc.getTextureManager().bindTexture(new ResourceLocation(HiggsWom.MODID + ":textures/gui/guiWindowInventory.png"));

        drawTexturedModalRect(getGuiX(), getGuiY(), 0, 0, 92, 177);

        AbstractClientPlayer a = (AbstractClientPlayer)player;
        mc.getTextureManager().bindTexture(a.getLocationSkin());
        GL11.glScaled(0.25, 0.125, 0.25);
        drawTexturedModalRect(getGuiX()*4 + 26, getGuiY()*8 + 48, 32, 64, 32, 64);
        drawTexturedModalRect(getGuiX()*4 + 26, getGuiY()*8 + 48, 160, 64, 32, 64);
        GL11.glScaled(4.0, 8.0, 4.0);

        GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
        fontRendererObj.drawString("Inventory", getGuiX() + 23, getGuiY() + 3, 0xffffff, true);
        GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
        this.mc.getTextureManager().bindTexture(new ResourceLocation(HiggsWom.MODID + ":textures/gui/guiWindowElements.png"));

        if(this.closeButtonDepressed)
        {
            drawTexturedModalRect(getGuiX() + (82), getGuiY() + (4), 49, 64, 8, 8);
        }
        else
        {
            drawTexturedModalRect(getGuiX() + (82), getGuiY() + (4), 49, 64, 8, 8);
        }
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY)
    {

    }

    private void guiDrawItemIcon(ItemStack item, int x, int y)
    {
        this.mc.getTextureManager().bindTexture(TextureMap.locationItemsTexture);
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        IIcon icon = item.getItem().getIcon(item, 0);
        GL11.glEnable(GL11.GL_BLEND);
        this.drawTexturedModelRectFromIcon(x, y, icon, 16, 16);
        GL11.glDisable(GL11.GL_BLEND);
        fontRendererObj.drawString("" + item.stackSize, x + 16 - fontRendererObj.getStringWidth("" + item.stackSize), y + 16 - fontRendererObj.FONT_HEIGHT, 0xffffff, false);
    }

    @Override
    protected int getGuiX()
    {
        return displayX;
    }

    @Override
    protected int getGuiY()
    {
        return displayY;
    }

    @Override
    protected void mouseClicked(int mouseX, int mouseY, int button)
    {
        super.mouseClicked(mouseX, mouseY, button);

        if(isInXButtonArea(mouseX, mouseY))
        {
            this.closeButtonDepressed = true;
        }
        else
        {
            this.closeButtonDepressed = false;
        }
    }

    /**
     * Called when the mouse is moved or a mouse button is released.  Signature: (mouseX, mouseY, which) which==-1 is
     * mouseMove, which==0 or which==1 is mouseUp
     */
    protected void mouseMovedOrUp(int mouseX, int mouseY, int which)
    {
        super.mouseMovedOrUp(mouseX, mouseY, which); //Forge, Call parent to release buttons
    }

    private boolean isInXButtonArea(int mouseX, int mouseY)
    {
        return isInRect(82, 4, 8, 8, mouseX, mouseY);
    }
}
