package com.higgs.wom.inventory;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import java.util.ArrayList;

/**
 * Created by Administrator on 7/8/2017.
 */
public class WomOreInventory implements IInventory
{
    private ArrayList<ItemStack> items;
    private Block oreBlock;
    public int slotsRequired;
    private int actionX;
    private int actionY;
    private int actionZ;
    private String inventoryName;

    public WomOreInventory(World world, int x, int y, int z, ArrayList<ItemStack> itemStacks)
    {
        this.oreBlock = world.getBlock(x, y, z);
        this.actionX = x;
        this.actionY = y;
        this.actionZ = z;
        inventoryName = oreBlock.getLocalizedName();
        this.items = itemStacks;
        this.slotsRequired = items.size();
        buildInventory();
    }

    public void buildInventory()
    {
        for(int i = 0; i < items.size(); i++)
        {
            setInventorySlotContents(i, items.get(i));
        }
    }

    @Override
    public int getSizeInventory()
    {
        return 9; //slotsRequired;
    }

    @Override
    public ItemStack getStackInSlot(int slotIndex)
    {
        if(slotIndex >= items.size())
        {
            return null;
        }
        else
        {
            return items.get(slotIndex);
        }
    }

    @Override
    public ItemStack decrStackSize(int slotIndex, int amount)
    {
        return new ItemStack(getStackInSlot(slotIndex).getItem(), getStackInSlot(slotIndex).stackSize - amount);
    }

    @Override
    public ItemStack getStackInSlotOnClosing(int slotIndex)
    {
        return getStackInSlot(slotIndex);
    }

    @Override
    public void setInventorySlotContents(int slotIndex, ItemStack itemStack)
    {
        if(slotIndex < items.size())
        {
            items.set(slotIndex, itemStack);
        }
    }

    @Override
    public String getInventoryName()
    {
        return inventoryName;
    }

    @Override
    public boolean hasCustomInventoryName()
    {
        return inventoryName == null;
    }

    @Override
    public int getInventoryStackLimit()
    {
        return 64;
    }

    @Override
    public void markDirty()
    {

    }

    @Override
    public boolean isUseableByPlayer(EntityPlayer player)
    {
        if(Math.sqrt(player.getDistanceSq(actionX, actionY, actionZ)) < 8)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    @Override
    public void openInventory()
    {

    }

    @Override
    public void closeInventory()
    {

    }

    @Override
    public boolean isItemValidForSlot(int p_94041_1_, ItemStack p_94041_2_)
    {
        return false;
    }
}
