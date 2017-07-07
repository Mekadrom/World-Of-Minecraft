package com.higgs.wom.network.packets;

import com.higgs.wom.entitydata.WomPlayerData;

import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;

public class WomPacketMiningCheck implements IMessage
{
	private EntityPlayer player;
	private boolean hasMining;

    public WomPacketMiningCheck() { }

    public WomPacketMiningCheck(EntityPlayer player, boolean hasMining)
    {
    	this.player = player;
        this.hasMining = hasMining;
    }
	
	@Override
	public void fromBytes(ByteBuf buf)
	{
		hasMining = Boolean.getBoolean(ByteBufUtils.readUTF8String(buf));
	}

	@Override
	public void toBytes(ByteBuf buf)
	{
		ByteBufUtils.writeUTF8String(buf, String.valueOf(hasMining));
	}
	
	public static class Handler implements IMessageHandler<WomPacketMiningCheck, IMessage>
	{
		@Override
		public IMessage onMessage(WomPacketMiningCheck message, MessageContext ctx)
		{
			WomPlayerData.get(message.player).setHasMining(message.hasMining);
            return null; // no response in this case
		}
	}
}
