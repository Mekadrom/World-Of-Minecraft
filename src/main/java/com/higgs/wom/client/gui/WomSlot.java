package com.higgs.wom.client.gui;

import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;

public class WomSlot extends Slot
{
    private int startX;
    private int startY;

    public WomSlot(IInventory inventory, int slotIndex, int displayX, int displayY)
    {
        super(inventory, slotIndex, displayX, displayY);
        this.startX = displayX;
        this.xDisplayPosition = displayX;
        this.startY = displayY;
        this.yDisplayPosition = displayY;
    }

    public int getStartX()
    {
        return startX;
    }

    public int getStartY()
    {
        return startY;
    }

    public int getDisplayX()
    {
        return this.xDisplayPosition;
    }

    public int getDisplayY()
    {
        return this.yDisplayPosition;
    }

    public void setDisplayX(int set)
    {
        this.xDisplayPosition = set;
    }

    public void setDisplayY(int set)
    {
        this.yDisplayPosition = set;
    }

    public void draw(int mouseX, int mouseY)
    {

    }
}
