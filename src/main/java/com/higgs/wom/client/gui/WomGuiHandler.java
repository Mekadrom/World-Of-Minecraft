package com.higgs.wom.client.gui;

import com.higgs.wom.block.WomOre;
import com.higgs.wom.guicontainer.WomContainerDefault;
import com.higgs.wom.guicontainer.WomContainerInventory;
import com.higgs.wom.guicontainer.WomContainerLoot;
import com.higgs.wom.inventory.WomOreInventory;
import com.higgs.wom.proxy.ClientProxy;
import cpw.mods.fml.common.network.IGuiHandler;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

public class WomGuiHandler implements IGuiHandler
{
    public static final int WOM_LOOT_GUI = 0;
    public static final int WOM_MINING_SKILL_GUI = 1;
    public static final int WOM_INVENTORY_GUI = 2;

    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
    {
        if(ID == WOM_LOOT_GUI)
        {
            Block blockToCheck = world.getBlock(x, y, z);
            if(blockToCheck instanceof WomOre)
            {
                WomOre womOre = (WomOre)blockToCheck;
                return new WomContainerLoot(player.inventory, new WomOreInventory(world, x, y, z, womOre.getDrops(world, x, y, z, 0, 0)));
            }
        }
        else if(ID == WOM_MINING_SKILL_GUI)
        {
            return new WomContainerDefault();
        }
        else if(ID == WOM_INVENTORY_GUI)
        {
            return new WomContainerInventory(player.inventory, !world.isRemote, player);
        }

        return null;
    }

    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
    {
        if(ID == WOM_LOOT_GUI)
        {
            Block blockToCheck = world.getBlock(x, y, z);
            if(blockToCheck instanceof WomOre)
            {
                WomOre womOre = (WomOre)blockToCheck;
                //gives different items than server side; will implement tile entity to sync drops
                return new WomGuiLoot(player.inventory, new WomOreInventory(world, x, y, z, womOre.getDrops(world, x, y, z, 0, 0)));
            }
        }
        else if(ID == WOM_MINING_SKILL_GUI)
        {
            ClientProxy.playerGui.setPlayer(player);
            ClientProxy.playerGui.openWindow(new WomGuiMiningSkill(player, new WomContainerDefault()));
            return ClientProxy.playerGui;
            //return new WomGuiMiningSkill(player);
        }
        else if(ID == WOM_INVENTORY_GUI)
        {
            ClientProxy.playerGui.setPlayer(player);
            ClientProxy.playerGui.openWindow(new WomGuiInventory(player.inventory, player, new WomContainerInventory(player.inventory, !world.isRemote, player)));
            return ClientProxy.playerGui;
            //return new WomGuiInventory(player);
        }

        return null;
    }
}
