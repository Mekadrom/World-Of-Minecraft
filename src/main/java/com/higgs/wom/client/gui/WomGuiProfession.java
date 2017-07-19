package com.higgs.wom.client.gui;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.inventory.Container;

import java.util.ArrayList;

@SideOnly(Side.CLIENT)
public class WomGuiProfession extends WomGuiWindow
{
    protected ArrayList<WomGuiListItem> listItems = new ArrayList<WomGuiListItem>();
    protected WomGuiListItem selected = null;
    protected int craftingButtonState;
    protected int craftingAllButtonState;
    protected int craftingExitButtonState;
    protected int createAllDecButtonState;
    protected int createAllIncButtonState;
    protected int lastMouse1Y;
    protected int lastMouse2Y;
    protected int scroll1Y;
    protected int scroll2Y;
    protected int scroll1Increment = 13;
    protected int scroll2Increment = 13;
    protected int createAllAmount = 64;

    public WomGuiProfession(Container container)
    {
        super(container);
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float depth, int mouseX, int mouseY)
    {
        super.drawGuiContainerBackgroundLayer(depth, mouseX, mouseY);
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int x, int y)
    {

    }

    @Override
    protected void mouseClicked(int mouseX, int mouseY, int button)
    {
        super.mouseClicked(mouseX, mouseY, button);

        if(isInDragArea(mouseX, mouseY))
        {
            dragging = true;
            lastDragX = mouseX - dragX;
            lastDragY = mouseY - dragY;
        }
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

    protected boolean isInListItemArea(int mouseX, int mouseY)
    {
        return isInRect(getGuiX() + 4, getGuiY() + 36, 113, 149, mouseX, mouseY);
    }

    protected boolean isInListItem(int listItemDefaultY, int mouseX, int mouseY)
    {
        return isInRect(getGuiX() + 9, getGuiY() + listItemDefaultY + scroll1Y + 36, 113, 8, mouseX, mouseY);
    }

    protected boolean isInCraftingButtonArea(int mouseX, int mouseY)
    {
        return isInRect(getGuiX() + 128, getGuiY() + 181, 127, 9, mouseX, mouseY);
    }

    protected boolean isOnCreateButton(int mouseX, int mouseY)
    {
        return isInRect(getGuiX() + 192, getGuiY() + 179, 29, 9, mouseX, mouseY);
    }

    protected boolean isOnCreateAllButton(int mouseX, int mouseY)
    {
        return isInRect(getGuiX() + 130, getGuiY() + 179, 30, 9, mouseX, mouseY);
    }

    protected boolean isInListItemScrollArea(int mouseX, int mouseY)
    {
        return isInRect(getGuiX() + 119, getGuiY() + 40, 7, 141, mouseX, mouseY);
    }

    protected boolean isInDetailsScrollArea(int mouseX, int mouseY)
    {
        return isInRect(getGuiX() + 119, getGuiY() + 40, 7, 141, mouseX, mouseY);
    }

    protected int scrollBar1X = 119;
    protected int scrollBar1Y = 41;
    protected int scrollBar2X = 246;
    protected int scrollBar2Y = 41;

    protected boolean isInListItemScrollBarArea(int mouseX, int mouseY)
    {
        return isInRect(getGuiX() + scrollBar1X, getGuiY() + scrollBar1Y + scroll1Y, 8, 7, mouseX, mouseY);
    }

    protected boolean isInDetailsScrollBarArea(int mouseX, int mouseY)
    {
        return isInRect(getGuiX() + scrollBar2X, getGuiY() + scrollBar2Y + scroll2Y, 8, 7, mouseX, mouseY);
    }

    protected boolean isInDragArea(int mouseX, int mouseY)
    {
        return isInRect(getGuiX() + 23, getGuiY() + 1, 224, 9, mouseX, mouseY);
    }

    protected boolean isInXButtonArea(int mouseX, int mouseY)
    {
        return isInRect(getGuiX() + 248, getGuiY() + 3, 7, 7, mouseX, mouseY);
    }

    protected boolean isInButtonsArea(int mouseX, int mouseY)
    {
        return isInRect(getGuiX() + 128, getGuiY() + 178, 127, 12, mouseX, mouseY);
    }

    protected boolean isOnCreateExitButton(int mouseX, int mouseY)
    {
        return isInRect(getGuiX() + 224, getGuiY() + 181, 29, 9, mouseX, mouseY);
    }

    protected boolean isInCreateAllDecButton(int mouseX, int mouseY)
    {
        return isInRect(getGuiX() + 161, getGuiY() + 180, 6, 6, mouseX, mouseY);
    }

    protected boolean isInCreateAllIncButton(int mouseX, int mouseY)
    {
        return isInRect(getGuiX() + 183, getGuiY() + 180, 6, 6, mouseX, mouseY);
    }
}
