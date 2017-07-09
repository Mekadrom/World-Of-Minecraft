package com.higgs.wom.block;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public final class WomBlocks
{
	public static Block blockCopperOre;
	public static Block blockTinOre;
	public static Block blockSilverOre;
	public static Block blockMithrilOre;
	public static Block blockThoriumOre;
	public static Block blockTruesilverOre;
	public static Block blockDarkIronOre;
	public static Block blockFelIronOre;
	public static Block TESTBLOCK;
	
	public static Block blockFirebloomFlower; // = (BlockFlower)( new BlockFirebloomFlower(Material.plants).setBlockName("blockFirebloomFlower"));
	public static Block blockPurpleLotusFlower;
	
	public static final void init()
	{
		GameRegistry.registerBlock(blockCopperOre = new BlockCopperOre("blockCopperOre", Material.rock), "blockCopperOre");
		GameRegistry.registerBlock(blockTinOre = new BlockTinOre("blockTinOre", Material.rock), "blockTinOre");
		GameRegistry.registerBlock(blockSilverOre = new BlockSilverOre("blockSilverOre", Material.rock), "blockSilverOre");
		GameRegistry.registerBlock(blockMithrilOre = new BlockMithrilOre("blockMithrilOre", Material.rock), "blockMithrilOre");
		GameRegistry.registerBlock(blockThoriumOre = new BlockThoriumOre("blockThoriumOre", Material.rock), "blockThoriumOre");
		GameRegistry.registerBlock(blockTruesilverOre = new BlockTruesilverOre("blockTruesilverOre", Material.rock), "blockTruesilverOre");
		GameRegistry.registerBlock(blockDarkIronOre = new BlockDarkIronOre("blockDarkIronOre", Material.rock), "blockDarkIronOre");
		GameRegistry.registerBlock(blockFelIronOre = new BlockFelIronOre("blockFelIronOre", Material.rock), "blockFelIronOre");
		GameRegistry.registerBlock(TESTBLOCK = new WomTestGuiBlock("TESTBLOCK", Material.rock), "TESTBLOCK");
		
		GameRegistry.registerBlock(blockFirebloomFlower = new BlockWomFlower("blockFirebloomFlower", Material.plants, 0.3f, 0.0f, 0.3f, 0.8f, 0.8f, 0.8f), "blockFirebloomFlower");
		GameRegistry.registerBlock(blockPurpleLotusFlower = new BlockWomFlower("blockPurpleLotusFlower", Material.plants, 0.1f, 0.0f, 0.1f, 0.9f, 0.9f, 0.9f), "blockPurpleLotusFlower");
	}
}
