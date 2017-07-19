package com.higgs.wom.event;

import com.higgs.wom.HiggsWom;
import com.higgs.wom.entitydata.WomPlayerData;
import com.higgs.wom.network.PacketDispatcher;
import com.higgs.wom.client.gui.WomGuiHandler;
import com.higgs.wom.network.client.WomPacketSyncPlayerData;
import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.InputEvent;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiChat;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.event.entity.EntityEvent.EntityConstructing;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import org.lwjgl.input.Keyboard;

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

	@SubscribeEvent
	public void onKeyInput(InputEvent.KeyInputEvent event)
	{
		int key = Keyboard.getEventKey();
		boolean isDown = Keyboard.getEventKeyState();
		if(!FMLClientHandler.instance().isGUIOpen(GuiChat.class))
		{
			if(isDown)
			{
				if(key == HiggsWom.keyValues[0])
				{
					Minecraft.getMinecraft().thePlayer.openGui(HiggsWom.instance, WomGuiHandler.WOM_MINING_SKILL_GUI,
							Minecraft.getMinecraft().thePlayer.worldObj,
							(int)Minecraft.getMinecraft().thePlayer.posX,
							(int)Minecraft.getMinecraft().thePlayer.posY,
							(int)Minecraft.getMinecraft().thePlayer.posZ);
				}
				else if(key == HiggsWom.keyValues[1])
				{
					Minecraft.getMinecraft().thePlayer.openGui(HiggsWom.instance, WomGuiHandler.WOM_INVENTORY_GUI,
							Minecraft.getMinecraft().thePlayer.worldObj,
							(int)Minecraft.getMinecraft().thePlayer.posX,
							(int)Minecraft.getMinecraft().thePlayer.posY,
							(int)Minecraft.getMinecraft().thePlayer.posZ);
				}
			}
		}
	}
}
