package com.higgs.wom.block;

import com.higgs.wom.HiggsWom;
import com.higgs.wom.entitydata.WomPlayerData;
import com.higgs.wom.item.WomItems;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentText;
import net.minecraft.world.World;

public class WomTestGuiBlock extends Block
{
	protected WomTestGuiBlock(String unlocalizedName, Material material)
	{
		super(material);
		setBlockName(unlocalizedName);
		setBlockTextureName(HiggsWom.MODID + ":blockLearner");
		setCreativeTab(HiggsWom.tabWom);
		setHardness(2.5f);
		setResistance(6.0f);
		setHarvestLevel("pickaxe", 0);
	}
	
	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int i, float f0, float f1, float f2)
	{
		if(player instanceof EntityPlayerMP)
		{
			WomPlayerData playerData = WomPlayerData.get(player);
			if(playerData != null)
			{
				if(playerData.getMiningSkill() == 0)
				{
					WomPlayerData.get(player).setMiningSkill(1);
					WomPlayerData.get(player).syncAll();
					player.addChatComponentMessage(new ChatComponentText("§aYou have learned Mining (1)!§r"));
					EntityItem itemMiningPick = new EntityItem(world, player.posX, player.posY + 0.25d, player.posZ, new ItemStack(WomItems.itemMinersPick, 1));
					world.spawnEntityInWorld(itemMiningPick);
				}
				else
				{
					player.addChatComponentMessage(new ChatComponentText("§cYou already know Mining (1)!§r"));
				}
			}
		}

//		if(!world.isRemote)
//		{
//			player.openGui(HiggsWom.instance, WomGuiHandler.WOM_MINING_SKILL_GUI, world, x, y, z);
//		}

		return true;
	}
}
