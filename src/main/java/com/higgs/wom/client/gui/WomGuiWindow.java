package com.higgs.wom.client.gui;

import com.higgs.wom.entitydata.WomPlayerData;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

@SideOnly(Side.CLIENT)
public abstract class WomGuiWindow extends GuiScreen
{
    protected static final ResourceLocation field_147001_a = new ResourceLocation("textures/gui/container/inventory.png");
    /** The X size of the window in pixels. */
    protected int xSize;
    /** The Y size of the window in pixels. */
    protected int ySize;
    /** Starting X position for the Gui. Inconsistent use for Gui backgrounds. */
    protected int guiLeft;
    /** Starting Y position for the Gui. Inconsistent use for Gui backgrounds. */
    protected int guiTop;

    protected int displayX;
    protected int displayY;

    protected int dragX;
    protected int dragY;
    protected int lastDragX;
    protected int lastDragY;
    protected boolean dragging;
    protected boolean closeButtonDepressed = false;
    protected int scrollY;
    protected int scrollIncrement = 13;

    protected EntityPlayer player;
    protected WomPlayerData playerData;

    protected String title;

    private static final String __OBFID = "CL_00000737";

    /**
     * Adds the buttons (and other controls) to the screen in question.
     */
    public void initGui()
    {
        super.initGui();
        this.guiLeft = (this.width - this.xSize) / 2;
        this.guiTop = (this.height - this.ySize) / 2;

//        this.buttonList.add(new WomGuiCloseButton(this, 1, displayX + this.width - 14, displayY + 5, ""));
    }

    public EntityPlayer getPlayer()
    {
        return this.player;
    }

    public WomPlayerData getPlayerData()
    {
        return this.playerData;
    }

    public void windowDragged(int x, int y)
    {
        dragX = x - lastDragX;
        dragY = y - lastDragY;
    }

    /**
     * Draws the screen and all the components in it.
     */
    public void drawScreen(int x, int y, float depth)
    {
        this.drawDefaultBackground();
        int k = this.guiLeft;
        int l = this.guiTop;
        this.drawGuiContainerBackgroundLayer(depth, x, y);
        GL11.glDisable(GL12.GL_RESCALE_NORMAL);
        RenderHelper.disableStandardItemLighting();
        GL11.glDisable(GL11.GL_LIGHTING);
        GL11.glDisable(GL11.GL_DEPTH_TEST);
        super.drawScreen(x, y, depth);
        RenderHelper.enableGUIStandardItemLighting();
        GL11.glPushMatrix();
        GL11.glTranslatef((float)k, (float)l, 0.0F);
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        GL11.glEnable(GL12.GL_RESCALE_NORMAL);
        short short1 = 240;
        short short2 = 240;
        OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, (float)short1 / 1.0F, (float)short2 / 1.0F);
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        GL11.glDisable(GL11.GL_LIGHTING);
        this.drawGuiContainerForegroundLayer(x, y);
        GL11.glEnable(GL11.GL_LIGHTING);
        GL11.glPopMatrix();
        GL11.glEnable(GL11.GL_LIGHTING);
        GL11.glEnable(GL11.GL_DEPTH_TEST);
        RenderHelper.enableStandardItemLighting();
    }

    /**
     * Draw the foreground layer for the GuiContainer (everything in front of the items)
     */
    protected void drawGuiContainerForegroundLayer(int x, int y) {}

    protected abstract void drawGuiContainerBackgroundLayer(float depth, int x, int y);

    /**
     * Called when the mouse is clicked.
     */
    protected void mouseClicked(int x, int y, int z)
    {
        super.mouseClicked(x, y, z);
    }

    /**
     * Called when a mouse button is pressed and the mouse is moved around. Parameters are : mouseX, mouseY,
     * lastButtonClicked & timeSinceMouseClick.
     */
    protected void mouseClickMove(int mouseX, int mouseY, int lastButtonClicked, long timeSinceMouseClick)
    {

    }

    /**
     * Called when the mouse is moved or a mouse button is released.  Signature: (mouseX, mouseY, which) which==-1 is
     * mouseMove, which==0 or which==1 is mouseUp
     */
    protected void mouseMovedOrUp(int mouseX, int mouseY, int which)
    {
        super.mouseMovedOrUp(mouseX, mouseY, which); //Forge, Call parent to release buttons

        if(which == 0)
        {
            dragging = false;
        }
    }

    protected void handleMouseClick(WomGuiListItem womGuiListItem, int slotNumber, int x, int y)
    {

    }

    /**
     * Fired when a key is typed. This is the equivalent of KeyListener.keyTyped(KeyEvent e).
     */
    protected void keyTyped(char charId, int keyId)
    {
        if(keyId == 1 || keyId == this.mc.gameSettings.keyBindInventory.getKeyCode())
        {
            this.mc.thePlayer.closeScreen();
        }
    }

    /**
     * Called when the screen is unloaded. Used to disable keyboard repeat events
     */
    public void onGuiClosed()
    {

    }

    /**
     * Returns true if this GUI should pause the game when it is displayed in single-player
     */
    public boolean doesGuiPauseGame()
    {
        return false;
    }

    /**
     * Called from the main game loop to update the screen.
     */
    public void updateScreen()
    {
        super.updateScreen();

        if(!this.mc.thePlayer.isEntityAlive() || this.mc.thePlayer.isDead)
        {
            this.mc.thePlayer.closeScreen();
        }
    }

    public int getScrollY()
    {
        return scrollY;
    }

    /**
     * Draws a rectangle with a vertical gradient between the specified colors.
     */
    protected void drawHorizontalGradientRect(int left, int top, int right, int bottom, int leftColor, int rightColor)
    {
        float f = (float)(leftColor >> 24 & 255) / 255.0F;
        float f1 = (float)(leftColor >> 16 & 255) / 255.0F;
        float f2 = (float)(leftColor >> 8 & 255) / 255.0F;
        float f3 = (float)(leftColor & 255) / 255.0F;
        float f4 = (float)(rightColor >> 24 & 255) / 255.0F;
        float f5 = (float)(rightColor >> 16 & 255) / 255.0F;
        float f6 = (float)(rightColor >> 8 & 255) / 255.0F;
        float f7 = (float)(rightColor & 255) / 255.0F;
        GL11.glDisable(GL11.GL_TEXTURE_2D);
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glDisable(GL11.GL_ALPHA_TEST);
        OpenGlHelper.glBlendFunc(770, 771, 1, 0);
        GL11.glShadeModel(GL11.GL_SMOOTH);
        Tessellator tessellator = Tessellator.instance;
        tessellator.startDrawingQuads();
        tessellator.setColorRGBA_F(f1, f2, f3, f);
        tessellator.addVertex((double)left, (double)bottom, (double)this.zLevel);
        tessellator.addVertex((double)left, (double)top, (double)this.zLevel);
        tessellator.setColorRGBA_F(f5, f6, f7, f4);
        tessellator.addVertex((double)right, (double)top, (double)this.zLevel);
        tessellator.addVertex((double)right, (double)bottom, (double)this.zLevel);
        tessellator.draw();
        GL11.glShadeModel(GL11.GL_FLAT);
        GL11.glDisable(GL11.GL_BLEND);
        GL11.glEnable(GL11.GL_ALPHA_TEST);
        GL11.glEnable(GL11.GL_TEXTURE_2D);
    }
}
