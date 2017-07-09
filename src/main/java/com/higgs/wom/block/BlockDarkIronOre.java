package com.higgs.wom.block;

import com.higgs.wom.HiggsWom;
import com.higgs.wom.item.WomItems;
import net.minecraft.block.material.Material;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import java.util.ArrayList;

public class BlockDarkIronOre extends WomOre
{
	public BlockDarkIronOre(String unlocalizedName, Material material)
	{
		super(material);
		setBlockName(unlocalizedName);
		setBlockTextureName(HiggsWom.MODID + ":blockDarkIronOre");
		setCreativeTab(HiggsWom.tabWom);
		this.miningLevel = 230;
	}
	
	@Override
    public ArrayList<ItemStack> getDrops(World world, int x, int y, int z, int metadata, int fortune)
    {
        ArrayList<ItemStack> drops = new ArrayList<ItemStack>();
        drops.add(new ItemStack(WomItems.itemDarkIronOre, world.rand.nextInt(7) + 2));
        
        float gem0 = world.rand.nextFloat();
        float gem1 = world.rand.nextFloat();
        float gem2 = world.rand.nextFloat();
        
        if(gem0 <= 0.01f)
        {
            drops.add(new ItemStack(WomItems.itemBlackVitriolGem));
        }
        
        if(gem1 <= 0.009f)
        {
        	drops.add(new ItemStack(WomItems.itemBloodOfTheMountainGem));
        }
        
        if(gem2 <= 0.004f)
        {
        	drops.add(new ItemStack(WomItems.itemBlackDiamondGem));
        }
        
        return drops;
    }
}