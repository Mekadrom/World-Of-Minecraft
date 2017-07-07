package com.higgs.wom.world;

import java.util.Random;

import com.higgs.wom.block.WomBlocks;

import cpw.mods.fml.common.IWorldGenerator;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenFlowers;
import net.minecraft.world.gen.feature.WorldGenerator;

public class WomFlowerGen implements IWorldGenerator
{
	private WorldGenerator genFirebloomFlower;
	private WorldGenerator genPurpleLotusFlower;
	
	public WomFlowerGen()
	{
	    this.genFirebloomFlower = new WorldGenFlowers(WomBlocks.blockFirebloomFlower);
	    this.genPurpleLotusFlower = new WorldGenFlowers(WomBlocks.blockPurpleLotusFlower);
	}
	
	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider)
	{
		switch (world.provider.dimensionId)
		{
		    case 0:    //Overworld
		    {
		    	this.runGenerator(this.genFirebloomFlower, world, random, chunkX, chunkZ, 4, 0.75f, 56, 256);
		    	this.runGenerator(this.genPurpleLotusFlower, world, random, chunkX, chunkZ, 4, 0.85f, 56, 256);
		    	
		        break;
		    }
		    case -1:   //Nether
		    {
		        break;
		    }
		    case 1:    //End
		    {
		        break;
		    }
	    }
	}
	
	private void runGenerator(WorldGenerator generator, World world, Random rand, int chunk_X, int chunk_Z, int chunkChancesToSpawn, float sparseness, int minHeight, int maxHeight)
	{
	    if(minHeight < 0 || maxHeight > 256 || minHeight > maxHeight)
	    {
	        throw new IllegalArgumentException("Illegal Height Arguments for WorldGenerator");
	    }

	    for(int i = 0; i < chunkChancesToSpawn; i++)
	    {
	        int x = chunk_X * 16 + rand.nextInt(16);
	        int y = rand.nextInt(maxHeight - minHeight) + minHeight;
	        int z = chunk_Z * 16 + rand.nextInt(16);
	        
	        Integer id = Block.getIdFromBlock(world.getBlock(x, y, z));
	        int idBelow = Block.getIdFromBlock(world.getBlock(x, y - 1, z));
	        
	        float f = rand.nextFloat();
	        
	        if(idBelow == Block.getIdFromBlock(Blocks.dirt) || idBelow == Block.getIdFromBlock(Blocks.grass) && (id == Block.getIdFromBlock(Blocks.air) || id == null) && f >= sparseness)
	        {
	        	generator.generate(world, rand, x, y, z);
	        }
	    }
	}
}
