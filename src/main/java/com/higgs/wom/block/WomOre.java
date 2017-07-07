package com.higgs.wom.block;

import com.higgs.wom.HiggsWom;
import com.higgs.wom.entitydata.WomPlayerData;
import com.higgs.wom.item.WomItems;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentText;
import net.minecraft.world.World;

public class WomOre extends Block
{
	protected int miningLevel = 1;
	
	protected WomOre(Material material)
	{
		super(material);
	}
	
	protected WomOre(String unlocalizedName, Material material)
	{
		super(material);
		setBlockName(unlocalizedName);
		setBlockTextureName(HiggsWom.MODID + ":blockWomOre");
		setCreativeTab(HiggsWom.tabWom);
//		setHardness(2.2f);
		setBlockUnbreakable();
//		setResistance(6.0f);
//		setHarvestLevel("pickaxe", 0);
	}
	
	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int metadata, float sideX, float sideY, float sideZ)
	{
		super.onBlockActivated(world, x, y, z, player, metadata, sideX, sideY, sideZ);
		
		boolean hasPickaxe = false;

//		WomPlayerData.get(player).setMiningSkill(1);
		WomPlayerData.get(player).syncAll();
		
		if(player.getCurrentEquippedItem() != null)
		{
			if(Item.getIdFromItem(player.getCurrentEquippedItem().getItem()) == Item.getIdFromItem(WomItems.itemWarcraftPickaxe))
			{
				hasPickaxe = true;
			}
		}
		
		if(WomPlayerData.get(player).getMiningSkill() < this.miningLevel)
		{
			System.out.println("PLAYER DOES NOT HAVE MINING " + WomPlayerData.get(player).getMiningSkill());
			player.addChatMessage(new ChatComponentText("Requires Mining Skill (" + miningLevel + ")."));
		}
		else
		{
			if(hasPickaxe)
			{
				if(!world.isRemote)
				{
					for(ItemStack itemStack : getDrops(world, x, y, z, 0, 0))
					{
						EntityItem item = new EntityItem(world, x + 0.5d, y + 0.25d, z + 0.5d, itemStack);
						world.spawnEntityInWorld(item);
					}
				}
				world.setBlockToAir(x, y, z);
			}
			else
			{
				player.addChatMessage(new ChatComponentText("Requires a Warcraft Pickaxe."));
			}
		}
		return blockConstructorCalled;
	}
}
