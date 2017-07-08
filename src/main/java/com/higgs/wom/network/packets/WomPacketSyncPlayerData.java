package com.higgs.wom.network.packets;

import com.higgs.wom.entitydata.WomPlayerData;
import com.higgs.wom.network.AbstractMessage.AbstractClientMessage;
import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import cpw.mods.fml.relauncher.Side;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.PacketBuffer;
import net.minecraft.world.World;
import net.minecraftforge.common.DimensionManager;

import java.io.IOException;

public class WomPacketSyncPlayerData extends AbstractClientMessage<WomPacketSyncPlayerData> implements IMessage
{
    private NBTTagCompound data;
    protected EntityPlayer entityPlayer;
    private int miningSkill;
    private int worldId;
    private int playerId;

    public WomPacketSyncPlayerData() { }

    public WomPacketSyncPlayerData(WomPlayerData playerData)
    {
        this.worldId = playerData.getPlayerRef().dimension;
        this.playerId = playerData.getPlayerRef().getEntityId();
        this.entityPlayer = playerData.getPlayerRef();
        this.data = new NBTTagCompound();
        playerData.saveNBTData(this.data);
    }
    
    public WomPacketSyncPlayerData(EntityPlayer player)
    {
        this.worldId = player.dimension;
        this.playerId = player.getEntityId();
        this.entityPlayer = player;
		// create a new tag compound
		data = new NBTTagCompound();
		// and save our player's data into it
		WomPlayerData.get(player).saveNBTData(data);
	}

    @Override
    public void fromBytes(ByteBuf buf)
    {
        this.worldId = buf.readInt();
        this.playerId = buf.readInt();
        this.data = ByteBufUtils.readTag(buf);
    }

    @Override
    public void toBytes(ByteBuf buf)
    {
        buf.writeInt(worldId);
        buf.writeInt(playerId);
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
            World world = DimensionManager.getWorld(msg.worldId);
            if(world == null)
                return null;

            EntityPlayer player = (EntityPlayer)world.getEntityByID(msg.playerId);
            if(player == null)
            {
                return null;
            }

            if (WomPlayerData.get(player) != null)
            {
                WomPlayerData.get(player).loadNBTData(msg.data);
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