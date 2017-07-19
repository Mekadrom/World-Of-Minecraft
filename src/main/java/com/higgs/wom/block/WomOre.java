package com.higgs.wom.block;

import com.higgs.wom.HiggsWom;
import com.higgs.wom.entitydata.WomPlayerData;
import com.higgs.wom.item.WomItems;
import com.higgs.wom.client.gui.WomGuiHandler;
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
	protected int[] levels;
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

		harvestedBy = player;

		boolean hasPickaxe = false;

		if(player.getCurrentEquippedItem() != null)
		{
			if(HiggsWom.minersPickEquippedRequired)
			{
				if(Item.getIdFromItem(player.getCurrentEquippedItem().getItem()) == Item.getIdFromItem(WomItems.itemMinersPick))
				{
					hasPickaxe = true;
				}
			}
			else
			{
				for(ItemStack itemStack : player.inventory.mainInventory)
				{
					if(Item.getIdFromItem(itemStack.getItem()) == Item.getIdFromItem(WomItems.itemMinersPick))
					{
						hasPickaxe = true;
					}
				}
			}
		}

		if(player instanceof EntityPlayerMP)
		{
			if(player.getDataWatcher().getWatchableObjectInt(WomPlayerData.MINING_WATCHER) < this.levels[0])
			{
				player.addChatMessage(new ChatComponentText("§cRequires Mining Skill (" + levels[0] + ").§r"));
			}
			else
			{
				if(hasPickaxe)
				{
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
							determineSkillUp();
						}
					}
					world.setBlock(x, y, z, Blocks.stone);
				}
				else
				{
					player.addChatMessage(new ChatComponentText("§cRequires a Miner's Pick.§r"));
				}
			}
		}
		return true;
	}

	private void determineSkillUp()
	{
		int skillLevel = harvestedBy.getDataWatcher().getWatchableObjectInt(WomPlayerData.MINING_WATCHER);//WomPlayerData.get(player).getMiningSkill();
		if(!harvestedBy.worldObj.isRemote)
		{
			HiggsWom.logger.error("CALLED ONCE");
			if(skillLevel < levels[0])
			{
				HiggsWom.logger.error("Cannot harvest ore vein; you should not be seeing this.");
			}
			else if(skillLevel >= levels[0] && skillLevel < levels[1])
			{
				WomPlayerData.get(harvestedBy).incMiningSkill(1);

				harvestedBy.addChatMessage(new ChatComponentText("§6Mining skill increased by 1 point.§r"));

				WomPlayerData.get(this.harvestedBy).syncAll();
			}
			else if(skillLevel >= levels[1] && skillLevel < levels[2])
			{
				float rand = harvestedBy.worldObj.rand.nextFloat();

				if(rand < 0.5)
				{
					WomPlayerData.get(harvestedBy).incMiningSkill(1);
					harvestedBy.addChatMessage(new ChatComponentText("§eMining skill increased by 1 point.§r"));
				}
				WomPlayerData.get(this.harvestedBy).syncAll();
			}
			else if(skillLevel >= levels[2] && skillLevel < levels[3])
			{
				float rand = harvestedBy.worldObj.rand.nextFloat();

				if(rand < 0.25)
				{
					WomPlayerData.get(harvestedBy).incMiningSkill(1);
					harvestedBy.addChatMessage(new ChatComponentText("§aMining skill increased by 1 point.§r"));
				}
				WomPlayerData.get(this.harvestedBy).syncAll();
			}
		}
	}
}
