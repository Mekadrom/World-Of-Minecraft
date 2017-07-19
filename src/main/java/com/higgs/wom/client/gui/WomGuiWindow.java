package com.higgs.wom.client.gui;

import com.higgs.wom.entitydata.WomPlayerData;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

public /*abstract*/ class WomGuiWindow extends GuiContainer
{
    /** The X size of the window in pixels. */
    public int xSize;
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

    protected EntityPlayer player;
    protected WomPlayerData playerData;

    protected String title;

    public EnumWindowType enumWindowType;

    private static final String __OBFID = "CL_11110000"; //00000737

    public WomGuiWindow(Container container)
    {
        super(container);
    }

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

    protected int getGuiX()
    {
        return displayX + dragX;
    }

    protected int getGuiY()
    {
        return displayY + dragY;
    }

    public enum EnumWindowType
    {
        PROFESSION_MINING, PROFESSION_HERBALISM, PROFESSION_JEWELCRAFTING, INVENTORY
    }

    public EntityPlayer getPlayer()
    {
        return this.player;
    }

    public WomPlayerData getPlayerData()
    {
        return this.playerData;
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

    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY)
    {

    }

    protected void drawGuiContainerBackgroundLayer(float depth, int mouseX, int mouseY)
    {
        if(dragging)
        {
            windowDragged(mouseX, mouseY);
        }
    }

    public void windowDragged(int x, int y)
    {
        dragX = x - lastDragX;
        dragY = y - lastDragY;
    }

    /**
     * Called when the mouse is clicked.
     */
    @Override
    protected void mouseClicked(int x, int y, int button)
    {
        super.mouseClicked(x, y, button);
    }

    /**
     * Called when a mouse button is pressed and the mouse is moved around. Parameters are : mouseX, mouseY,
     * lastButtonClicked & timeSinceMouseClick.
     */
    @Override
    protected void mouseClickMove(int mouseX, int mouseY, int lastButtonClicked, long timeSinceMouseClick)
    {
        super.mouseClickMove(mouseX, mouseY, lastButtonClicked, timeSinceMouseClick);
    }

    /**
     * Called when the mouse is moved or a mouse button is released.  Signature: (mouseX, mouseY, which) which==-1 is
     * mouseMove, which==0 or which==1 is mouseUp
     */
    protected void mouseMovedOrUp(int mouseX, int mouseY, int which)
    {
        super.mouseMovedOrUp(mouseX, mouseY, which); //Forge, Call parent to release buttons
    }

    /**
     * Fired when a key is typed. This is the equivalent of KeyListener.keyTyped(KeyEvent e).
     */
    @Override
    protected void keyTyped(char charId, int keyId)
    {
        super.keyTyped(charId, keyId);
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

    protected static boolean isInRect(int x, int y, int xSize, int ySize, int mouseX, int mouseY)
    {
        return ((mouseX >= x && mouseX <= x + xSize) && (mouseY >= y && mouseY <= y + ySize));
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
