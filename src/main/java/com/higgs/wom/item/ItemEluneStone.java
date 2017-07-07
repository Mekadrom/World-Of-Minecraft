package com.higgs.wom.item;

import com.higgs.wom.entity.EntityElunesLight;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemEluneStone extends WomItem
{
	public ItemEluneStone()
	{

	}
	
	@Override
	public ItemStack onItemRightClick(ItemStack itemStack, World world, EntityPlayer player)
	{
		world.spawnEntityInWorld(new EntityElunesLight(world, player.posX, player.posY, player.posZ));
		return new ItemStack(itemStack.getItem(), itemStack.stackSize - 1);
	}
}
