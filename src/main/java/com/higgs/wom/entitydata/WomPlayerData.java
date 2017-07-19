package com.higgs.wom.entitydata;

import com.higgs.wom.network.PacketDispatcher;
import com.higgs.wom.network.client.WomPacketSyncPlayerData;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraftforge.common.IExtendedEntityProperties;

public class WomPlayerData implements IExtendedEntityProperties
{
	public static final String EXT_PROP_NAME  = "womPlayerData";

	private final EntityPlayer player;

	private int miningSkill;
	private int herbalismSkill;
	private int jewelcraftingSkill;

	private final int MINING_MAX_VALUE = 600;
	private final int HERBALISM_MAX_VALUE = 600;
	private final int JEWELCRAFTING_MAX_VALUE = 600;

	public static final int MINING_WATCHER = 21;
	public static final int HERBALISM_WATCHER = 22;
	public static final int JEWELCRAFTING_WATCHER = 23;

	/**
	 * The default constructor takes no arguments, but I put in the Entity
	 * so I can initialize the above variable 'player'
 	 *
	 * Also, it's best to initialize any other variables you may have added,
	 * just like in any constructor.
	 */
	public WomPlayerData(EntityPlayer player)
	{
		this.player = player;
		//start with 0 of every skill. every player starts with the same amount
		this.miningSkill = 0;
		this.herbalismSkill = 0;
		this.jewelcraftingSkill = 0;
		this.player.getDataWatcher().addObject(MINING_WATCHER, this.miningSkill);
		this.player.getDataWatcher().addObject(HERBALISM_WATCHER, this.herbalismSkill);
		this.player.getDataWatcher().addObject(JEWELCRAFTING_WATCHER, this.jewelcraftingSkill);
	}

	/**
	 * Used to register these extended properties for the player during EntityConstructing event
	 * This method is for convenience only; it will make your code look nicer
	 */
	public static final void register(EntityPlayer player)
	{
		player.registerExtendedProperties(WomPlayerData.EXT_PROP_NAME, new WomPlayerData(player));
	}

	/**
	 * Returns ExtendedPlayer properties for player
	 * This method is for convenience only; it will make your code look nicer
	 */
	public static final WomPlayerData get(EntityPlayer player)
	{
		return (WomPlayerData)player.getExtendedProperties(EXT_PROP_NAME);
	}

	/**
	 * Copies additional player data from the given ExtendedPlayer instance
	 * Avoids NBT disk I/O overhead when cloning a player after respawn
	 */
	public void copy(WomPlayerData props)
	{
		this.miningSkill = props.getMiningSkill();
		this.herbalismSkill = props.getHerbalismSkill();
		this.jewelcraftingSkill = props.getJewelcraftingSkill();

		this.player.getDataWatcher().updateObject(MINING_WATCHER, props.getMiningSkill());
		this.player.getDataWatcher().updateObject(HERBALISM_WATCHER, props.getHerbalismSkill());
		this.player.getDataWatcher().updateObject(JEWELCRAFTING_WATCHER, props.getJewelcraftingSkill());
	}

	// Save any custom data that needs saving here
	@Override
	public void saveNBTData(NBTTagCompound compound)
	{
		// We need to create a new tag compound that will save everything for our Extended Properties
		NBTTagCompound properties = new NBTTagCompound();

//		HiggsWom.logger.info("Saving mining skill...");
		// We only have 2 variables currently; save them both to the new tag
		properties.setInteger("miningSkill", player.getDataWatcher().getWatchableObjectInt(MINING_WATCHER));
		properties.setInteger("herbalismSkill", player.getDataWatcher().getWatchableObjectInt(HERBALISM_WATCHER));
		properties.setInteger("jewelcraftingSkill", player.getDataWatcher().getWatchableObjectInt(JEWELCRAFTING_WATCHER));

		// Now add our custom tag to the player's tag with a unique name (our property's name)
		// This will allow you to save multiple types of properties and distinguish between them
		// If you only have one type, it isn't as important, but it will still avoid conflicts between
		// your tag names and vanilla tag names. For instance, if you add some "Items" tag,
		// that will conflict with vanilla. Not good. So just use a unique tag name.
		compound.setTag(EXT_PROP_NAME, properties);

	}

	// Load whatever data you saved
	@Override
	public void loadNBTData(NBTTagCompound compound)
	{
		// Here we fetch the unique tag compound we set for this class of Extended Properties
		NBTTagCompound properties = (NBTTagCompound)compound.getTag(EXT_PROP_NAME);
		// Get our data from the custom tag compound
		this.miningSkill = properties.getInteger("miningSkill");
		this.herbalismSkill = properties.getInteger("herbalismSkill");
		this.jewelcraftingSkill = properties.getInteger("jewelcraftingSkill");

		player.getDataWatcher().updateObject(MINING_WATCHER, this.miningSkill);
		player.getDataWatcher().updateObject(HERBALISM_WATCHER, this.herbalismSkill);
		player.getDataWatcher().updateObject(JEWELCRAFTING_WATCHER, this.jewelcraftingSkill);
	}

	public boolean isServerSide()
	{
		return this.player instanceof EntityPlayerMP;
	}

	public void syncAll()
	{
		if(!this.isServerSide())
		{
			PacketDispatcher.sendToServer(new WomPacketSyncPlayerData(this.player));
		}
		else
		{
			PacketDispatcher.sendTo(new WomPacketSyncPlayerData(this.player), (EntityPlayerMP)this.player);
		}
	}

	/*
	I personally have yet to find a use for this method. If you know of any,
	please let me know and I'll add it in!
	*/
	@Override
	public void init(Entity entity, World world) {}

	public boolean getHasMining()
	{
		return this.miningSkill >= 1;
	}

	public boolean getHasHerbalism()
	{
		return this.getHerbalismSkill() >= 1;
	}

	public boolean getHasJewelcrafting()
	{
		return this.getJewelcraftingSkill() >= 1;
	}

	public final int getMiningSkill()
	{
		return player.getDataWatcher().getWatchableObjectInt(MINING_WATCHER);
	}
	
	public void setMiningSkill(int amount)
	{
		player.getDataWatcher().updateObject(MINING_WATCHER, amount > 0 ? (amount < MINING_MAX_VALUE ? amount : MINING_MAX_VALUE) : 0);
	}

	public final int getHerbalismSkill()
	{
		return player.getDataWatcher().getWatchableObjectInt(HERBALISM_WATCHER);
	}

	public final int getJewelcraftingSkill()
	{
		return player.getDataWatcher().getWatchableObjectInt(JEWELCRAFTING_WATCHER);
	}
	
	public void incMiningSkill(int amount)
	{
		if(player.getDataWatcher().getWatchableObjectInt(MINING_WATCHER) + amount <= this.MINING_MAX_VALUE)
		{
			player.getDataWatcher().updateObject(MINING_WATCHER, player.getDataWatcher().getWatchableObjectInt(MINING_WATCHER) + amount);
		}
		else
		{
			player.getDataWatcher().updateObject(MINING_WATCHER, MINING_MAX_VALUE);
		}
	}

	public void incHerbalismSkill(int amount)
	{
		if(player.getDataWatcher().getWatchableObjectInt(HERBALISM_WATCHER) + amount <= this.MINING_MAX_VALUE)
		{
			player.getDataWatcher().updateObject(HERBALISM_WATCHER, player.getDataWatcher().getWatchableObjectInt(HERBALISM_WATCHER) + amount);
		}
		else
		{
			player.getDataWatcher().updateObject(HERBALISM_WATCHER, HERBALISM_MAX_VALUE);
		}
	}

	public void incJewelcraftingSkill(int amount)
	{
		if(player.getDataWatcher().getWatchableObjectInt(JEWELCRAFTING_WATCHER) + amount <= this.JEWELCRAFTING_MAX_VALUE)
		{
			player.getDataWatcher().updateObject(JEWELCRAFTING_WATCHER, player.getDataWatcher().getWatchableObjectInt(JEWELCRAFTING_WATCHER) + amount);
		}
		else
		{
			player.getDataWatcher().updateObject(JEWELCRAFTING_WATCHER, JEWELCRAFTING_MAX_VALUE);
		}
	}
}
