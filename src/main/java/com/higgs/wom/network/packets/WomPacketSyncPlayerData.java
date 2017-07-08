package com.higgs.wom.network.packets;

import com.higgs.wom.entitydata.WomPlayerData;
import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraftforge.common.DimensionManager;

public class WomPacketSyncPlayerData implements IMessage
{
    private NBTTagCompound data;
    private EntityPlayer entityPlayer;
    private int worldId;
    private int playerId;

    public WomPacketSyncPlayerData() {}

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
		data = new NBTTagCompound();
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

    public static class Handler implements IMessageHandler<WomPacketSyncPlayerData, IMessage>
    {
    	@Override
    	public final IMessage onMessage(WomPacketSyncPlayerData msg, MessageContext ctx)
    	{
            World world = DimensionManager.getWorld(msg.worldId);
            if(world != null)
            {
                EntityPlayer player = (EntityPlayer) world.getEntityByID(msg.playerId);
                if(player == null)
                {
                    return null;
                }

                if(WomPlayerData.get(player) != null)
                {
                    WomPlayerData.get(player).loadNBTData(msg.data);
                }
            }

    		return null;
    	}
    }
}
