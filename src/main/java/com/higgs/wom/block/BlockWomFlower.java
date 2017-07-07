package com.higgs.wom.block;

import java.util.Random;

import com.higgs.wom.HiggsWom;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.EnumPlantType;
import net.minecraftforge.common.IPlantable;

public class BlockWomFlower extends Block implements IPlantable
{
    public BlockWomFlower(String unlocalizedName, Material par2Material, float f1, float f2, float f3, float f4, float f5, float f6)
    {
        super(par2Material);
//    	super(1);
        setBlockName(unlocalizedName);
        setBlockTextureName(HiggsWom.MODID + ":" + unlocalizedName);
        setCreativeTab(HiggsWom.tabWom);
        this.setStepSound(soundTypeGrass);
        this.setTickRandomly(true);
//        float f = 0.2F;
        //this.setBlockBounds(0.5F - f, 0.0F, 0.5F - f, 0.5F + f, f * 3.0F, 0.5F + f);
        this.setBlockBounds(f1, f2, f3, f4, f5, f6);
    }
    
    public boolean canPlaceBlockAt(World par1World, int par2, int par3, int par4)
    {
        return super.canPlaceBlockAt(par1World, par2, par3, par4) && canBlockStay(par1World, par2, par3, par4);
    }

    public boolean canThisPlantGrowOnThisBlockID(int par1)
    {
        return par1 == Block.getIdFromBlock(Blocks.dirt) || par1 == Block.getIdFromBlock(Blocks.grass);
    }

    public void onNeighborBlockChange(World par1World, int par2, int par3, int par4, Block par5Block)
    {
        super.onNeighborBlockChange(par1World, par2, par3, par4, par5Block);
        this.checkFlowerChange(par1World, par2, par3, par4);
    }

    public void updateTick(World par1World, int par2, int par3, int par4, Random par5Random)
    {
        this.checkFlowerChange(par1World, par2, par3, par4);
    }

    public final void checkFlowerChange(World par1World, int par2, int par3, int par4)
    {
        if(!this.canBlockStay(par1World, par2, par3, par4))
        {
            this.dropBlockAsItem(par1World, par2, par3, par4, par1World.getBlockMetadata(par2, par3, par4), 0);
            par1World.setBlockToAir(par2, par3, par4);
        }
    }
    
    @Override
    public boolean canBlockStay(World par1World, int par2, int par3, int par4)
    {
    	int idBelow = Block.getIdFromBlock(par1World.getBlock(par2,  par3 - 1,  par4));
    	
    	if(idBelow == Block.getIdFromBlock(Blocks.dirt) || idBelow == Block.getIdFromBlock(Blocks.grass))
    	{
    		return true;
    	}
    	else 
    	{
    		return false;
    	}
    }

    public AxisAlignedBB getCollisionBoundingBoxFromPool(World par1World, int par2, int par3, int par4)
    {
        return null;
    }

    public boolean isOpaqueCube()
    {
        return false;
    }

    public boolean renderAsNormalBlock()
    {
        return false;
    }

    public int getRenderType()
    {
        return 1;
    }

    public void onBlockPlacedBy(World world, int x, int y, int z, EntityLiving entityliving, ItemStack itemstack, EntityPlayer player) 
    {
    	if(Block.getIdFromBlock(world.getBlock(x, y - 1, z)) == Block.getIdFromBlock(Blocks.dirt))
    	{
    		
    	}
    	else if(Block.getIdFromBlock(world.getBlock(x, y - 1, z)) == Block.getIdFromBlock(Blocks.grass))
    	{
    		
    	}
    	else
    	{
    		world.setBlockToAir(x, y, z);
    		player.inventory.addItemStackToInventory(itemstack);
    	}
    }

	@Override
	public EnumPlantType getPlantType(IBlockAccess world, int x, int y, int z)
	{
		return null;
	}

	@Override
	public Block getPlant(IBlockAccess world, int x, int y, int z)
	{
		return null;
	}

	@Override
	public int getPlantMetadata(IBlockAccess world, int x, int y, int z)
	{
		return 0;
	}
}