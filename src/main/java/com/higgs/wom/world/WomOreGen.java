package com.higgs.wom.world;

import java.util.Random;

import com.higgs.wom.HiggsWom;
import com.higgs.wom.block.WomBlocks;

import cpw.mods.fml.common.IWorldGenerator;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraft.world.gen.feature.WorldGenerator;

public class WomOreGen implements IWorldGenerator
{
	private WorldGenerator genCopperOre; //Generates copper Ore (used in Overworld)
	private WorldGenerator genTinOre;
	private WorldGenerator genSilverOre;
	private WorldGenerator genMithrilOre;
	private WorldGenerator genThoriumOre;
	private WorldGenerator genTruesilverOre;
	private WorldGenerator genDarkIronOre;
	private WorldGenerator genFelIronOre;
	
	public WomOreGen()
	{
	    this.genCopperOre = new WorldGenMinable(WomBlocks.blockCopperOre, HiggsWom.oreCopperVeinSize); //default 7
	    this.genTinOre = new WorldGenMinable(WomBlocks.blockTinOre, HiggsWom.oreTinVeinSize); //default 7 
	    this.genSilverOre = new WorldGenMinable(WomBlocks.blockSilverOre, HiggsWom.oreSilverVeinSize); //default 7
	    this.genMithrilOre = new WorldGenMinable(WomBlocks.blockMithrilOre, HiggsWom.oreMithrilVeinSize); //default 6
	    this.genThoriumOre = new WorldGenMinable(WomBlocks.blockThoriumOre, HiggsWom.oreThoriumVeinSize); //default 6
	    this.genTruesilverOre = new WorldGenMinable(WomBlocks.blockTruesilverOre, HiggsWom.oreTruesilverVeinSize); //default 6
	    this.genDarkIronOre = new WorldGenMinable(WomBlocks.blockDarkIronOre, HiggsWom.oreDarkIronVeinSize); //default 5
	    this.genFelIronOre = new WorldGenMinable(WomBlocks.blockFelIronOre, HiggsWom.oreFelIronVeinSize); //default 4
	}
	
	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider)
	{
		switch (world.provider.dimensionId)
		{
		    case 0:    //Overworld
		    {
		    	this.runGenerator(this.genCopperOre, world, random, chunkX, chunkZ, HiggsWom.oreCopperVeinRarity, HiggsWom.oreCopperMinY, HiggsWom.oreCopperMaxY); //defaults 16, 24, 80
		    	this.runGenerator(this.genTinOre, world, random, chunkX, chunkZ, HiggsWom.oreTinVeinRarity, HiggsWom.oreTinMinY, HiggsWom.oreTinMaxY); //defaults 14, 12, 48
		    	this.runGenerator(this.genSilverOre, world, random, chunkX, chunkZ, HiggsWom.oreSilverVeinRarity, HiggsWom.oreSilverMinY, HiggsWom.oreSilverMaxY); //defaults 10, 8, 32
		    	this.runGenerator(this.genMithrilOre, world, random, chunkX, chunkZ, HiggsWom.oreMithrilVeinRarity, HiggsWom.oreMithrilMinY, HiggsWom.oreSilverMaxY); //defaults 10, 4, 28
		    	this.runGenerator(this.genThoriumOre, world, random, chunkX, chunkZ, HiggsWom.oreThoriumVeinRarity, HiggsWom.oreThoriumMinY, HiggsWom.oreThoriumMaxY); //defaults 6, 0, 20
		    	this.runGenerator(this.genTruesilverOre, world, random, chunkX, chunkZ, HiggsWom.oreTruesilverVeinRarity, HiggsWom.oreTruesilverMinY, HiggsWom.oreTruesilverMaxY); //defaults 6, 0, 16
		    	this.runGenerator(this.genDarkIronOre, world, random, chunkX, chunkZ, HiggsWom.oreDarkIronVeinRarity, HiggsWom.oreDarkIronMinY, HiggsWom.oreDarkIronMaxY); //defaults 5, 0, 6
		    	
		    	break;
		    }
		    case -1:   //Nether
		    {
		        break;
		    }
		    case 1:    //End
		    {
		    	this.runGenerator(this.genFelIronOre, world, random, chunkX, chunkZ, HiggsWom.oreFelIronVeinRarity, HiggsWom.oreFelIronMinY, HiggsWom.oreFelIronMaxY); //defaults 4, 0, 256
		    	
		        break;
		    }
	    }
	}
	
	private void runGenerator(WorldGenerator generator, World world, Random rand, int chunk_X, int chunk_Z, int chancesToSpawn, int minHeight, int maxHeight)
	{
	    if(minHeight < 0 || maxHeight > 256 || minHeight > maxHeight)
	    {
	        throw new IllegalArgumentException("Illegal Height Arguments for WorldGenerator");
	    }

	    int heightDiff = maxHeight - minHeight + 1;
	    
	    for(int i = 0; i < chancesToSpawn; i++)
	    {
	        int x = chunk_X * 16 + rand.nextInt(16);
	        int y = minHeight + rand.nextInt(heightDiff);
	        int z = chunk_Z * 16 + rand.nextInt(16);
	        
	        generator.generate(world, rand, x, y, z);
	    }
	}
}
