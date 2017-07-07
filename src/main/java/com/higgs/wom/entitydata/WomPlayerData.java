package com.higgs.wom.entitydata;

import com.higgs.wom.HiggsWom;
import com.higgs.wom.network.packets.WomPacketMiningCheck;
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
	private static final String identifier = "womPlayerData";
	private int mana;
	private boolean mining;// = false;
	private int miningSkill;// = 0;
	
	public WomPlayerData(EntityPlayer player)
	{
		this.player = player;
		this.mana = 100;
		this.mining = false;
		this.miningSkill = 0;
	}
	
	public static WomPlayerData get(EntityPlayer player)
	{
	    return (WomPlayerData) player.getExtendedProperties(identifier);
	}
	
	public void copy(WomPlayerData data)
	{
		this.mana = data.getMana();
		this.mining = data.getHasMining();
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
		return this.mining;
	}
	
	public void setHasMining(boolean has)
	{
		this.mining = has;
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
	
	public void syncHasMining()
	{
	    if(!this.isServerSide())
	    {
	        HiggsWom.network.sendToServer(new WomPacketMiningCheck(this.player, this.getHasMining()));
	    }
	    else
	    {
	    	HiggsWom.network.sendTo(new WomPacketMiningCheck(this.player, this.getHasMining()), (EntityPlayerMP)player);
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

	public void requestSyncAll()
	{
	    if(!this.isServerSide())
	    {
	    	HiggsWom.network.sendToServer(new WomPacketSyncPlayerData());
	    }
	}
	
    @Override
    public void saveNBTData(NBTTagCompound nbt)
    {
    	nbt.setInteger("mana", this.getMana());
    	nbt.setInteger("miningSkill", this.getMiningSkill());
    	
    	nbt.setBoolean("mining", this.getHasMining());
    }

    @Override
    public void loadNBTData(NBTTagCompound nbt)
    {
    	if(nbt.hasKey("mana", 3))
    	{
    		System.out.println("MANA HAS VALUE " + nbt.getInteger("mana"));
            this.setMana(nbt.getInteger("mana"));
    	}
    	if(nbt.hasKey("miningSkill", 3))
    	{
    		System.out.println("MINING SKILL HAS VALUE " + nbt.getInteger("miningSkill"));
            this.setMiningSkill(nbt.getInteger("miningSkill"));
    	}
    	
    	if(nbt.hasKey("mining", 1))
    	{
    		System.out.println("MINING CHECK HAS VALUE " + nbt.getBoolean("mining"));
            this.setHasMining(nbt.getBoolean("mining"));
    	}
    }

    @Override
    public void init(Entity entity, World world)
    {
    	
    }
}
