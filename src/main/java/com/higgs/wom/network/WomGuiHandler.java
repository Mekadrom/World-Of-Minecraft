package com.higgs.wom.network;

import com.higgs.wom.block.WomOre;
import com.higgs.wom.client.gui.WomGuiLoot;
import com.higgs.wom.client.gui.WomGuiMiningSkill;
import com.higgs.wom.guicontainer.WomContainerLoot;
import com.higgs.wom.guicontainer.WomContainerProfessions;
import com.higgs.wom.inventory.WomOreInventory;
import cpw.mods.fml.common.network.IGuiHandler;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

public class WomGuiHandler implements IGuiHandler
{
    public static final int WOM_LOOT_GUI = 0;
    public static final int WOM_MINING_SKILL_GUI = 1;

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
            return new WomContainerProfessions();
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
            return new WomGuiMiningSkill(player);
        }

        return null;
    }
}
