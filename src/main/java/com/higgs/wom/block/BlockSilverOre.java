package com.higgs.wom.block;

import java.util.ArrayList;

import com.higgs.wom.HiggsWom;
import com.higgs.wom.item.WomItems;

import net.minecraft.block.material.Material;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class BlockSilverOre extends WomOre
{
	public BlockSilverOre(String unlocalizedName, Material material)
	{
		super(material);
		setBlockName(unlocalizedName);
		setBlockTextureName(HiggsWom.MODID + ":blockSilverOre");
		setCreativeTab(HiggsWom.tabWom);
		setHardness(2.0f);
		setResistance(6.0f);
		setHarvestLevel("pickaxe", 2);
		this.miningLevel = 65;
	}
	
	@Override
    public ArrayList<ItemStack> getDrops(World world, int x, int y, int z, int metadata, int fortune)
    {
        ArrayList<ItemStack> drops = new ArrayList<ItemStack>();
        drops.add(new ItemStack(WomItems.itemSilverOre, world.rand.nextInt(7) + 2));
        
        float gem0 = world.rand.nextFloat();
        float gem1 = world.rand.nextFloat();
        float gem2 = world.rand.nextFloat();
        float nugget0 = world.rand.nextFloat();
        
        if(gem0 <= 0.05f)
        {
            drops.add(new ItemStack(WomItems.itemMossAgateGem));
        }
        
        if(gem1 <= 0.03f)
        {
        	drops.add(new ItemStack(WomItems.itemLesserMoonstoneGem));
        }
        
        if(gem2 <= 0.03f)
        {
        	drops.add(new ItemStack(WomItems.itemShadowgemGem));
        }
        
        if(nugget0 <= 0.006)
        {
        	drops.add(new ItemStack(WomItems.itemSilverNugget, world.rand.nextInt(6) + 14));
        }
        
        return drops;
    }
}
