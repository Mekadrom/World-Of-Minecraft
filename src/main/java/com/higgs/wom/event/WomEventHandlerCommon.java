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

public class WomEventHandlerCommon
{
	@SubscribeEvent
	public void onEntityConstructing(EntityConstructing e)
	{
//	    if(e.entity instanceof EntityPlayer)// || e.entity instanceof EntityPlayerMP)
//	    {
////	    	e.entity.registerExtendedProperties("womPlayerData", new WomPlayerData((EntityPlayer)e.entity));
//	        WomPlayerData.register((EntityPlayer)e.entity);
//	    }

		if(e.entity instanceof EntityPlayer && WomPlayerData.get((EntityPlayer) e.entity) == null)
		{
			// This is how extended properties are registered using our convenient method from earlier
			WomPlayerData.register((EntityPlayer)e.entity);
		}
		// That will call the constructor as well as cause the init() method
		// to be called automatically

		// If you didn't make the two convenient methods from earlier, your code would be
		// much uglier:
	}

	@SubscribeEvent
	public void onEntityJoinWorld(EntityJoinWorldEvent e)
	{
//	    if(e.entity instanceof EntityPlayer)// || e.entity instanceof EntityPlayerMP)
//	    {
//	    	e.entity.registerExtendedProperties("womPlayerData", new WomPlayerData((EntityPlayer)e.entity));
//	    }

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
}
