package com.higgs.wom.item;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemBlastingPowder extends WomItem
{
	private float strength = 0;

	public ItemBlastingPowder(float strength)
	{
		this.strength = strength;
	}

	@Override
	public ItemStack onItemRightClick(ItemStack itemStack, World world, EntityPlayer player)
	{
		double x = player.posX;//mop.blockX;
		double y = player.posY;//mop.blockY;
		double z = player.posZ;//mop.blockZ;
		
		world.createExplosion(player, x, y, z, strength, true);

		return new ItemStack(itemStack.getItem(), itemStack.stackSize - 1);
	}
}
