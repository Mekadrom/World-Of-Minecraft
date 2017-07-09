package com.higgs.wom.event;

import com.higgs.wom.HiggsWom;
import com.higgs.wom.entitydata.WomPlayerData;
import com.higgs.wom.network.packets.WomPacketSyncPlayerData;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.event.entity.EntityEvent.EntityConstructing;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.terraingen.OreGenEvent;
import org.apache.logging.log4j.Level;

public class WomEventHandlerCommon
{
	@SubscribeEvent
	public void onEntityConstructing(EntityConstructing e)
	{
		if(e.entity instanceof EntityPlayer && WomPlayerData.get((EntityPlayer) e.entity) == null)
		{
			WomPlayerData.register((EntityPlayer)e.entity);
		}
	}

	@SubscribeEvent
	public void onEntityJoinWorld(EntityJoinWorldEvent e)
	{
		if(e.entity instanceof EntityPlayerMP)
		{
			final EntityPlayerMP entity = (EntityPlayerMP)e.entity;
			WomPlayerData.get(entity).loadNBTData(new NBTTagCompound());
			HiggsWom.network.sendTo(new WomPacketSyncPlayerData(entity), entity);
		}
	}
	
	@SubscribeEvent
	public void onClonePlayer(PlayerEvent.Clone event)
	{
		WomPlayerData.get(event.entityPlayer).copy(WomPlayerData.get(event.original));
	}

	@SubscribeEvent
	public void onOreGen(OreGenEvent e)
	{
		HiggsWom.log(Level.INFO, "GOT HERE LUL");
	}
}
