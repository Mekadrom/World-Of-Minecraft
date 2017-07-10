package com.higgs.wom.event;

import com.higgs.wom.HiggsWom;
import com.higgs.wom.entitydata.WomPlayerData;
import com.higgs.wom.network.PacketDispatcher;
import com.higgs.wom.network.client.WomPacketSyncPlayerData;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.event.entity.EntityEvent.EntityConstructing;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;

public class WomEventHandlerCommon
{
	@SubscribeEvent
	public void onEntityConstructing(EntityConstructing event)
	{
		if(event.entity instanceof EntityPlayer)
		{
			if(WomPlayerData.get((EntityPlayer) event.entity) == null)
			{
				WomPlayerData.register((EntityPlayer)event.entity);
			}
		}
	}

	@SubscribeEvent
	public void onEntityJoinWorld(EntityJoinWorldEvent event)
	{
		if(event.entity instanceof EntityPlayer && !event.entity.worldObj.isRemote)
		{
			PacketDispatcher.sendTo(new WomPacketSyncPlayerData((EntityPlayer)event.entity), (EntityPlayerMP)event.entity);
		}
	}

	@SubscribeEvent
	public void onClonePlayer(PlayerEvent.Clone event)
	{
		HiggsWom.logger.info("Cloning player extended properties");
		WomPlayerData.get(event.entityPlayer).copy(WomPlayerData.get(event.original));
	}
}
