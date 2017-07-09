package com.higgs.wom.world;

import com.higgs.wom.HiggsWom;
import com.higgs.wom.block.WomBlocks;
import cpw.mods.fml.common.IWorldGenerator;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraft.world.gen.feature.WorldGenerator;

import java.util.Random;

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

	    this.genDarkIronOre = new WorldGenMinable(WomBlocks.blockDarkIronOre, HiggsWom.oreDarkIronVeinSize, Blocks.stone); //default 5

	    this.genFelIronOre = new WorldGenMinable(WomBlocks.blockFelIronOre, HiggsWom.oreFelIronVeinSize, Blocks.stone); //default 4
	}
	
	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider)
	{
		switch(world.provider.dimensionId)
		{
		    case 0:    //Overworld
		    {
		    	this.runGenerator(this.genCopperOre, world, random, chunkX, chunkZ, HiggsWom.oreCopperVeinRarity, HiggsWom.oreCopperMinY, HiggsWom.oreCopperMaxY, WomBlocks.blockCopperOre); //defaults 16, 24, 80
		    	this.runGenerator(this.genTinOre, world, random, chunkX, chunkZ, HiggsWom.oreTinVeinRarity, HiggsWom.oreTinMinY, HiggsWom.oreTinMaxY, WomBlocks.blockTinOre); //defaults 14, 12, 48
		    	this.runGenerator(this.genSilverOre, world, random, chunkX, chunkZ, HiggsWom.oreSilverVeinRarity, HiggsWom.oreSilverMinY, HiggsWom.oreSilverMaxY, WomBlocks.blockSilverOre); //defaults 10, 8, 32
		    	this.runGenerator(this.genMithrilOre, world, random, chunkX, chunkZ, HiggsWom.oreMithrilVeinRarity, HiggsWom.oreMithrilMinY, HiggsWom.oreSilverMaxY, WomBlocks.blockMithrilOre); //defaults 10, 4, 28
		    	this.runGenerator(this.genThoriumOre, world, random, chunkX, chunkZ, HiggsWom.oreThoriumVeinRarity, HiggsWom.oreThoriumMinY, HiggsWom.oreThoriumMaxY, WomBlocks.blockThoriumOre); //defaults 6, 0, 20
		    	this.runGenerator(this.genTruesilverOre, world, random, chunkX, chunkZ, HiggsWom.oreTruesilverVeinRarity, HiggsWom.oreTruesilverMinY, HiggsWom.oreTruesilverMaxY, WomBlocks.blockTruesilverOre); //defaults 6, 0, 16
				this.runGenerator(this.genDarkIronOre, world, random, chunkX, chunkZ, HiggsWom.oreDarkIronVeinRarity, HiggsWom.oreDarkIronMinY, HiggsWom.oreDarkIronMaxY, WomBlocks.blockDarkIronOre); //defaults 5, 0, 6
				this.runGenerator(this.genFelIronOre, world, random, chunkX, chunkZ, HiggsWom.oreFelIronVeinRarity, HiggsWom.oreFelIronMinY, HiggsWom.oreFelIronMaxY, WomBlocks.blockFelIronOre); //defaults 4, 0, 256

		    	break;
		    }
		    case -1:   //Nether
		    {
//				this.runGenerator(this.genDarkIronOre, world, random, chunkX, chunkZ, HiggsWom.oreDarkIronVeinRarity, HiggsWom.oreDarkIronMinY, HiggsWom.oreDarkIronMaxY, WomBlocks.blockDarkIronOre); //defaults 5, 0, 6

		        break;
		    }
		    case 1:    //End
		    {
//		    	this.runGenerator(this.genFelIronOre, world, random, chunkX, chunkZ, HiggsWom.oreFelIronVeinRarity, HiggsWom.oreFelIronMinY, HiggsWom.oreFelIronMaxY, WomBlocks.blockFelIronOre); //defaults 4, 0, 256
		    	
		        break;
		    }
	    }
	}
	
	private void runGenerator(WorldGenerator generator, World world, Random rand, int chunk_X, int chunk_Z, int chancesToSpawn, int minHeight, int maxHeight, Block toSpawn)
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

	        if(toSpawn != null)
			{
				int idToSpawn = Block.getIdFromBlock(toSpawn);
				double chanceToReplace = world.rand.nextDouble();

				if(idToSpawn == Block.getIdFromBlock(WomBlocks.blockTinOre) && chanceToReplace <= 0.05)
				{
					world.setBlock(x, y, z, WomBlocks.blockSilverOre);
				}
				else if(idToSpawn == Block.getIdFromBlock(WomBlocks.blockSilverOre) && chanceToReplace <= 0.05)
				{
					world.setBlock(x, y, z, WomBlocks.blockTinOre);
				}
				else if(idToSpawn == Block.getIdFromBlock(WomBlocks.blockMithrilOre) && chanceToReplace <= 0.05)
				{
					world.setBlock(x, y, z, WomBlocks.blockTruesilverOre);
				}
				else if(idToSpawn == Block.getIdFromBlock(WomBlocks.blockTruesilverOre) && chanceToReplace <= 0.05)
				{
					world.setBlock(x, y, z, WomBlocks.blockMithrilOre);
				}
				else if(idToSpawn == Block.getIdFromBlock(WomBlocks.blockThoriumOre) && chanceToReplace <= 0.05)
				{

				}
			}
	    }
	}
}
