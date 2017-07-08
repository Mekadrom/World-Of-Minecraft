package com.higgs.wom.entitydata;

import com.higgs.wom.HiggsWom;
import com.higgs.wom.network.packets.WomPacketMiningLevel;
import com.higgs.wom.network.packets.WomPacketSyncPlayerData;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraftforge.common.IExtendedEntityProperties;

public class WomPlayerData implements IExtendedEntityProperties
{
	private final EntityPlayer player;
	public static final String identifier = "womPlayerData";
	private int mana;
	private boolean mining;// = false;
	private int miningSkill;// = 0;
	
	public WomPlayerData(EntityPlayer player)
	{
		this.player = player;
		this.mana = 100;
		this.miningSkill = 0;
	}
	
	public static WomPlayerData get(EntityPlayer player)
	{
	    return (WomPlayerData) player.getExtendedProperties(identifier);
	}

	public EntityPlayer getPlayerRef()
	{
		return this.player;
	}

	public void copy(WomPlayerData data)
	{
		this.mana = data.getMana();
		this.miningSkill = data.getMiningSkill();
	}

	public static void register(EntityPlayer player)
	{
	    player.registerExtendedProperties(identifier, new WomPlayerData(player));
	}

	public boolean isServerSide()
	{
	    return this.player instanceof EntityPlayerMP;
	}
	
	public void setMana(int mana)
	{
	    this.mana = mana;
	}

	public int getMana()
	{
	    return this.mana;
	}

	public boolean getHasMining()
	{
		return this.miningSkill == 1;
	}

	public int getMiningSkill()
	{
		return this.miningSkill;
	}
	
	public void setMiningSkill(int newSkill)
	{
		this.miningSkill = newSkill;
	}
	
	public void incMiningSkill(int amount)
	{
		this.miningSkill += amount;
	}
	
	public void syncMiningSkill()
	{
	    if(!this.isServerSide())
	    {
	        HiggsWom.network.sendToServer(new WomPacketMiningLevel(this.player, this.getMiningSkill()));
	    }
	    else
	    {
	    	HiggsWom.network.sendTo(new WomPacketMiningLevel(this.player, this.getMiningSkill()), (EntityPlayerMP)this.player);
	    }
	}

	public void syncMana()
	{
	    if(!this.isServerSide())
	    {
	        HiggsWom.network.sendToServer(new WomPacketMiningLevel(this.player, this.getMana()));
	    }
	    else
	    {
	    	HiggsWom.network.sendTo(new WomPacketMiningLevel(this.player, this.getMana()), (EntityPlayerMP)player);
	    }
	}
	
	public void syncAll()
	{
	    if(!this.isServerSide())
	    {
	    	HiggsWom.network.sendToServer(new WomPacketSyncPlayerData(this));
	    }
	    else
	    {
	        HiggsWom.network.sendTo(new WomPacketSyncPlayerData(this), (EntityPlayerMP)this.player);
	    }
	}

    @Override
    public void saveNBTData(NBTTagCompound nbt)
    {
    	nbt.setInteger("mana", this.getMana());
    	nbt.setInteger("miningSkill", this.getMiningSkill());
    	
		// We need to create a new tag compound that will save everything for our Extended Properties
		NBTTagCompound properties = new NBTTagCompound();

		// We only have 2 variables currently; save them both to the new tag
		properties.setInteger("mana", this.getMana());
		properties.setInteger("miningSkill", this.getMiningSkill());

		// Now add our custom tag to the player's tag with a unique name (our property's name)
		// This will allow you to save multiple types of properties and distinguish between them
		// If you only have one type, it isn't as important, but it will still avoid conflicts between
		// your tag names and vanilla tag names. For instance, if you add some "Items" tag,
		// that will conflict with vanilla. Not good. So just use a unique tag name.
		nbt.setTag(identifier, properties);
    }

    @Override
    public void loadNBTData(NBTTagCompound nbt)
    {
//    	if(nbt.hasKey("mana", 3))
//    	{
//    		System.out.println("MANA HAS VALUE " + nbt.getInteger("mana"));
//            this.setMana(nbt.getInteger("mana"));
//    	}
//    	if(nbt.hasKey("miningSkill", 3))
//    	{
//    		System.out.println("MINING SKILL HAS VALUE " + nbt.getInteger("miningSkill"));
//            this.setMiningSkill(nbt.getInteger("miningSkill"));
//    	}

		// Here we fetch the unique tag compound we set for this class of Extended Properties
		NBTTagCompound properties = (NBTTagCompound)nbt.getTag(identifier);
		// Get our data from the custom tag compound
		if(properties != null)
		{
			this.setMana(properties.getInteger("mana")); //= properties.getInteger("CurrentMana");
			this.setMiningSkill(properties.getInteger("miningSkill"));// = properties.getInteger("MaxMana");
		}
		// Just so you know it's working, add this line:
//		System.out.println("[WOM PROPS] Mana/Mining Skill from NBT: " + this.getMana() + "/" + this.getMiningSkill());
    }

    @Override
    public void init(Entity entity, World world)
    {
    	
    }
}
