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
    	nbt.setInteger("playerId", this.getPlayerRef().getEntityId());
    	nbt.setInteger("worldId", this.getPlayerRef().dimension);

		NBTTagCompound properties = new NBTTagCompound();

		properties.setInteger("mana", this.getMana());
		properties.setInteger("miningSkill", this.getMiningSkill());
		properties.setInteger("playerId", this.getPlayerRef().getEntityId());
		properties.setInteger("worldId", this.getPlayerRef().dimension);

		nbt.setTag(identifier, properties);
    }

    @Override
    public void loadNBTData(NBTTagCompound nbt)
    {
		NBTTagCompound properties = (NBTTagCompound)nbt.getTag(identifier);

		if(properties != null)
		{
			this.setMana(properties.getInteger("mana")); //= properties.getInteger("CurrentMana");
			this.setMiningSkill(properties.getInteger("miningSkill"));// = properties.getInteger("MaxMana");
		}
    }

    @Override
    public void init(Entity entity, World world)
    {
    	
    }
}
