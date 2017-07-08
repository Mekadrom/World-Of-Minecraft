package com.higgs.wom.network.packets;

import java.io.IOException;

import com.higgs.wom.entitydata.WomPlayerData;
import com.higgs.wom.network.AbstractMessage.AbstractClientMessage;

import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import cpw.mods.fml.relauncher.Side;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.PacketBuffer;

public class WomPacketSyncPlayerData extends AbstractClientMessage<WomPacketSyncPlayerData> implements IMessage
{
    private NBTTagCompound data;
    protected EntityPlayer entityPlayer;

    public WomPacketSyncPlayerData() { }

    public WomPacketSyncPlayerData(WomPlayerData playerData)
    {
        this.entityPlayer = playerData.getPlayerRef();
        this.data = new NBTTagCompound();
        playerData.saveNBTData(this.data);
    }
    
    public WomPacketSyncPlayerData(EntityPlayer player)
    {
		// create a new tag compound
		data = new NBTTagCompound();
		// and save our player's data into it
		WomPlayerData.get(player).saveNBTData(data);
	}

    @Override
    public void fromBytes(ByteBuf buf)
    {
        this.data = ByteBufUtils.readTag(buf);
    }

    @Override
    public void toBytes(ByteBuf buf)
    {
        ByteBufUtils.writeTag(buf, this.data);
    }

    @Override
	public void process(EntityPlayer player, Side side)
    {
		// now we can just load the NBTTagCompound data directly; one and done, folks
//		TutorialMain.logger.info("Synchronizing extended properties data on CLIENT");
		WomPlayerData.get(player).loadNBTData(data);
	}
    
    // ========================================================================

    public static class Handler implements IMessageHandler<WomPacketSyncPlayerData, IMessage>
    {
    	@Override
    	public final IMessage onMessage(WomPacketSyncPlayerData msg, MessageContext ctx)
    	{
    		EntityPlayer serverPlayer = msg.entityPlayer;

    		if(serverPlayer != null)
    		{
                if (WomPlayerData.get(serverPlayer) != null)
                {
                    WomPlayerData.get(serverPlayer).loadNBTData(new NBTTagCompound());
                }
            }

    		return null;
    	}
    	
//        @Override
//        public IMessage handleClientMessage(final EntityPlayer player, final WomPacketSyncPlayerData msg, MessageContext ctx)
//        {
//            ClientUtils.addScheduledTask(new Runnable()
//            {
//                @Override
//                public void run()
//                {
//                    WomPlayerData.get(player).loadNBTData(msg.data);
//                }
//            });
//            return null;
//        }
//
//        @Override
//        public IMessage handleServerMessage(final EntityPlayer player, WomPacketSyncPlayerData msg, MessageContext ctx)
//        {
//            ServerUtils.addScheduledTask(new Runnable()
//            {
//                @Override
//                public void run() {
//                    WomPlayerData.get(player).syncAll();
//                }
//            });
//            return null;
//        }
    }

	@Override
	protected void read(PacketBuffer buffer) throws IOException
	{
		data = buffer.readNBTTagCompoundFromBuffer();
	}

	@Override
	protected void write(PacketBuffer buffer) throws IOException
	{
		buffer.writeNBTTagCompoundToBuffer(data);
	}
}