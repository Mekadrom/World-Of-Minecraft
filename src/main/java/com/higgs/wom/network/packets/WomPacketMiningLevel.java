package com.higgs.wom.network.packets;

import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;

public class WomPacketMiningLevel implements IMessage
{
	private EntityPlayer player;
	private int mining;

    public WomPacketMiningLevel() { }

    public WomPacketMiningLevel(EntityPlayer player, int mining)
    {
    	this.player = player;
        this.mining = mining;
    }
	
	@Override
	public void fromBytes(ByteBuf buf)
	{
		mining = ByteBufUtils.readVarShort(buf);
	}

	@Override
	public void toBytes(ByteBuf buf)
	{
		ByteBufUtils.writeVarShort(buf, mining);
	}
	
	public static class Handler implements IMessageHandler<WomPacketMiningLevel, IMessage>
	{
		@Override
		public IMessage onMessage(WomPacketMiningLevel message, MessageContext ctx)
		{
            return null; // no response in this case
		}
	}
}
