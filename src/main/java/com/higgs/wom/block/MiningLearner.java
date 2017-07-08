package com.higgs.wom.block;

import com.higgs.wom.HiggsWom;
import com.higgs.wom.entitydata.WomPlayerData;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

public class MiningLearner extends Block
{
	protected MiningLearner(String unlocalizedName, Material material)
	{
		super(material);
		setBlockName(unlocalizedName);
		setBlockTextureName(HiggsWom.MODID + ":blockLearner");
		setCreativeTab(HiggsWom.tabWom);
		setHardness(2.5f);
		setResistance(6.0f);
		setHarvestLevel("pickaxe", 0);
	}
	
	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int i, float f0, float f1, float f2)
	{
		WomPlayerData.get(player).setMiningSkill(1);
		WomPlayerData.get(player).syncAll();
		
		return blockConstructorCalled;
	}
}
