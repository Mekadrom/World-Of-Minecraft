package com.higgs.wom.block;

import com.higgs.wom.HiggsWom;
import com.higgs.wom.entitydata.WomPlayerData;
import com.higgs.wom.item.WomItems;
import com.higgs.wom.network.WomGuiHandler;
import net.minecraft.block.material.Material;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentText;
import net.minecraft.world.World;

public class WomOre extends WomBlock
{
	protected int miningLevel = 1;
	private EntityPlayer harvestedBy;
	
	protected WomOre(Material material)
	{
		super(material);
	}

	protected WomOre(String unlocalizedName, Material material)
	{
		super(material);
		setBlockName(unlocalizedName);
		setBlockTextureName(HiggsWom.MODID + ":" + unlocalizedName);
		setCreativeTab(HiggsWom.tabWom);
		setBlockUnbreakable();
	}
	
	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int metadata, float sideX, float sideY, float sideZ)
	{
		super.onBlockActivated(world, x, y, z, player, metadata, sideX, sideY, sideZ);
		
		boolean hasPickaxe = false;

		if(player.getCurrentEquippedItem() != null)
		{
			if(Item.getIdFromItem(player.getCurrentEquippedItem().getItem()) == Item.getIdFromItem(WomItems.itemMinersPick))
			{
				hasPickaxe = true;
			}
		}
		if(player instanceof EntityPlayerMP)
		{
			if(WomPlayerData.get(player).getMiningSkill() < this.miningLevel)
			{
				player.addChatMessage(new ChatComponentText("Requires Mining Skill (" + miningLevel + ")."));
			}
			else
			{
				if(hasPickaxe)
				{
//					if(!world.isRemote)
//					{
//						for(ItemStack itemStack : getDrops(world, x, y, z, 0, 0))
//						{
//							EntityItem item = new EntityItem(world, player.posX, player.posY, player.posZ, itemStack);
//							world.spawnEntityInWorld(item);
//						}
//					}

//					WomOreInventory dropping = new WomOreInventory(world, x, y, z, getDrops(world, x, y, z, 0, 0));

					if(!world.isRemote)
					{
						if(HiggsWom.harvestingOreOpensGui)
						{
							player.openGui(HiggsWom.instance, WomGuiHandler.WOM_LOOT_GUI, world, x, y, z);
						}
						else
						{
							for(ItemStack itemStack : getDrops(world, x, y, z, 0, 0))
							{
								EntityItem item = new EntityItem(world, player.posX, player.posY, player.posZ, itemStack);
								world.spawnEntityInWorld(item);
							}
						}
					}

					world.setBlock(x, y, z, Blocks.stone);
				}
				else
				{
					player.addChatMessage(new ChatComponentText("Requires a Warcraft Pickaxe."));
				}
			}
		}
		return blockConstructorCalled;
	}
}
