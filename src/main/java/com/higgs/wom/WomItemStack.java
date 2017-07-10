package com.higgs.wom;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class WomItemStack
{
    private ItemStack itemStack;
    private boolean isConsumedOnRecipeUsage;

    public WomItemStack(Block block, int amount, boolean isConsumedOnRecipeUsage)
    {
        this.itemStack = new ItemStack(block, amount);
        this.isConsumedOnRecipeUsage = isConsumedOnRecipeUsage;
    }

    public WomItemStack(ItemStack itemStack)
    {
        this.itemStack = itemStack;
        this.isConsumedOnRecipeUsage = true;
    }

    public WomItemStack(ItemStack itemStack, boolean isConsumedOnRecipeUsage)
    {
        this.itemStack = itemStack;
        this.isConsumedOnRecipeUsage = isConsumedOnRecipeUsage;
    }

    public WomItemStack(Item item, int amount, boolean isConsumedOnRecipeUsage)
    {
        this.itemStack = new ItemStack(item, amount);
        this.isConsumedOnRecipeUsage = isConsumedOnRecipeUsage;
    }

    public ItemStack getItemStack()
    {
        return itemStack;
    }

    public boolean getIsConsumedOnRecipeUsage()
    {
        return this.isConsumedOnRecipeUsage;
    }
}
