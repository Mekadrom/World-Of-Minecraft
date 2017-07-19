package com.higgs.wom.client.gui;

import com.higgs.wom.HiggsWom;
import com.higgs.wom.entitydata.WomPlayerData;
import com.higgs.wom.guicontainer.WomContainerDefault;
import com.higgs.wom.guicontainer.WomContainerInventory;
import com.higgs.wom.proxy.ClientProxy;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.entity.player.EntityPlayer;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

import java.util.ArrayList;

public class WomGui extends GuiScreen
{
    private ArrayList<WomGuiWindow> openWindows = new ArrayList<WomGuiWindow>();
    private EntityPlayer player;
    protected boolean mouseDown = false;

    public void setPlayer(EntityPlayer set)
    {
        this.player = set;
    }

    public void openWindow(WomGuiWindow newWindow)
    {
        boolean flag = true;
        for(int i = 0; i < openWindows.size(); i++)
        {
            if(newWindow.enumWindowType == openWindows.get(i).enumWindowType)
            {
                flag = false;
            }
        }
        if(flag)//if(!this.openWindows.contains(newWindow))
        {
            newWindow.initGui();
            this.openWindows.add(newWindow);
        }
    }

    public void closeWindow(WomGuiWindow closed)
    {
        ArrayList<WomGuiWindow> toRemove = new ArrayList<WomGuiWindow>();
        for(int i = 0; i < openWindows.size(); i++)
        {
            if(openWindows.get(i).enumWindowType == closed.enumWindowType)
            {
                toRemove.add(openWindows.get(i));
            }
        }

        if(!toRemove.isEmpty())
        {
            openWindows.removeAll(toRemove);
        }
    }

    public void closeWindow(WomGuiWindow.EnumWindowType type)
    {
        ArrayList<WomGuiWindow> toRemove = new ArrayList<WomGuiWindow>();
        for(int i = 0; i < openWindows.size(); i++)
        {
            if(openWindows.get(i).enumWindowType == type)
            {
                toRemove.add(openWindows.get(i));
            }
        }

        if(!toRemove.isEmpty())
        {
            openWindows.removeAll(toRemove);
        }
    }

    public void closeAllWindows()
    {
        ArrayList<WomGuiWindow> newArrayList = new ArrayList<WomGuiWindow>();
        this.openWindows = newArrayList;
    }

    public boolean isWindowTypeOpen(WomGuiWindow.EnumWindowType type)
    {
        boolean flag = false;

        for(int i = 0; i < openWindows.size(); i++)
        {
            if(openWindows.get(i).enumWindowType == type)
            {
                flag = true;
                break;
            }
        }

        return flag;
    }

    @Override
    public void drawScreen(int x, int y, float depth)
    {
        GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
        if(openWindows.isEmpty())
        {
            player.closeScreen();
        }
        for(WomGuiWindow window : openWindows)
        {
            int k = window.guiLeft;
            int l = window.guiTop;
            window.drawGuiContainerBackgroundLayer(depth, x, y);
            GL11.glDisable(GL12.GL_RESCALE_NORMAL);
            RenderHelper.disableStandardItemLighting();
            GL11.glDisable(GL11.GL_LIGHTING);
            GL11.glDisable(GL11.GL_DEPTH_TEST);
            super.drawScreen(x, y, depth);
            RenderHelper.enableGUIStandardItemLighting();
            GL11.glPushMatrix();
            GL11.glTranslatef((float) k, (float) l, 0.0f);
            GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
            GL11.glDisable(GL11.GL_LIGHTING);
            window.drawGuiContainerForegroundLayer(x, y);
            GL11.glEnable(GL11.GL_LIGHTING);
            GL11.glPopMatrix();
            GL11.glEnable(GL11.GL_LIGHTING);
            GL11.glEnable(GL11.GL_DEPTH_TEST);
            RenderHelper.enableStandardItemLighting();
        }
        GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
        fontRendererObj.drawString("x: " + x + " y: " + y + " " + (mouseDown ? "§2true§r" : "§cfalse§r"), 2, 2, 0xffffff, false);
    }

    @Override
    protected void mouseClicked(int x, int y, int z)
    {
        super.mouseClicked(x, y, z);
        for(int i = 0; i < openWindows.size(); i++)
        {
            openWindows.get(i).mouseClicked(x, y, z);
        }

        mouseDown = true;
    }

    /**
     * Called when a mouse button is pressed and the mouse is moved around. Parameters are : mouseX, mouseY,
     * lastButtonClicked & timeSinceMouseClick.
     */
    @Override
    protected void mouseClickMove(int mouseX, int mouseY, int lastButtonClicked, long timeSinceMouseClick)
    {
        super.mouseClickMove(mouseX, mouseY, lastButtonClicked, timeSinceMouseClick);
        for(int i = 0; i < openWindows.size(); i++)
        {
            openWindows.get(i).mouseClickMove(mouseX, mouseY, lastButtonClicked, timeSinceMouseClick);
        }
    }

    /**
     * Called when the mouse is moved or a mouse button is released.  Signature: (mouseX, mouseY, which) which==-1 is
     * mouseMove, which==0 or which==1 is mouseUp
     */
    @Override
    protected void mouseMovedOrUp(int mouseX, int mouseY, int which)
    {
        super.mouseMovedOrUp(mouseX, mouseY, which); //Forge, Call parent to release buttons

        for(int i = 0; i < openWindows.size(); i++)
        {
            openWindows.get(i).mouseMovedOrUp(mouseX, mouseY, which);
        }

        if(which == 0)
        {
            mouseDown = false;
        }
    }

    /**
     * Returns true if this GUI should pause the game when it is displayed in single-player
     */
    @Override
    public boolean doesGuiPauseGame()
    {
        return false;
    }

    /**
     * Fired when a key is typed. This is the equivalent of KeyListener.keyTyped(KeyEvent e).
     */
    @Override
    protected void keyTyped(char charId, int keyId)
    {
        if(keyId == 1 || keyId == this.mc.gameSettings.keyBindInventory.getKeyCode())
        {
            this.mc.thePlayer.closeScreen();
        }
        else if(keyId == HiggsWom.keyValues[0]) //79
        {
            WomPlayerData.get(player).syncAll();
            if(ClientProxy.playerGui.isWindowTypeOpen(WomGuiWindow.EnumWindowType.PROFESSION_MINING))
            {
                ClientProxy.playerGui.closeWindow(WomGuiWindow.EnumWindowType.PROFESSION_MINING);
            }
            else
            {
                ClientProxy.playerGui.openWindow(new WomGuiMiningSkill(player, new WomContainerDefault()));
//                player.openGui(HiggsWom.class, WomGuiHandler.WOM_MINING_SKILL_GUI, player.worldObj, (int)player.posX, (int)player.posY, (int)player.posZ);
            }
        }
        else if(keyId == HiggsWom.keyValues[1]) //23
        {
            if(ClientProxy.playerGui.isWindowTypeOpen(WomGuiWindow.EnumWindowType.INVENTORY))
            {
                ClientProxy.playerGui.closeWindow(WomGuiWindow.EnumWindowType.INVENTORY);
            }
            else
            {
//                ClientProxy.playerGui.openWindow(new WomGuiInventory(player.inventory, player, new WomContainerInventory(player.inventory, !player.worldObj.isRemote, player)));
                if(ClientProxy.playerGui.isWindowTypeOpen(WomGuiWindow.EnumWindowType.PROFESSION_MINING))
                {
//                    player.closeScreen();
                    ClientProxy.playerGui.closeAllWindows();
//                    player.openGui(HiggsWom.class, WomGuiHandler.WOM_INVENTORY_GUI, player.worldObj, (int) player.posX, (int) player.posY, (int) player.posZ);
                    ClientProxy.playerGui.openWindow(new WomGuiInventory(player.inventory, player, new WomContainerInventory(player.inventory, !player.worldObj.isRemote, player)));
                    ClientProxy.playerGui.openWindow(new WomGuiMiningSkill(player, new WomContainerDefault()));
                }
            }
        }
    }

    /**
     * Called from the main game loop to update the screen.
     */
    @Override
    public void updateScreen()
    {
        super.updateScreen();

        if(!this.mc.thePlayer.isEntityAlive() || this.mc.thePlayer.isDead)
        {
            this.mc.thePlayer.closeScreen();
        }
    }
}
