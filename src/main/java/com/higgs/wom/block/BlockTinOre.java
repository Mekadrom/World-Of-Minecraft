package com.higgs.wom.block;

import com.higgs.wom.HiggsWom;
import com.higgs.wom.item.WomItems;
import net.minecraft.block.material.Material;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import java.util.ArrayList;

public class BlockTinOre extends WomOre
{
	public BlockTinOre(String unlocalizedName, Material material)
	{
		super(material);
		setBlockName(unlocalizedName);
		setBlockTextureName(HiggsWom.MODID + ":blockTinOre");
		setCreativeTab(HiggsWom.tabWom);
		this.miningLevel = 50;
	}
	
	@Override
    public ArrayList<ItemStack> getDrops(World world, int x, int y, int z, int metadata, int fortune)
    {
        ArrayList<ItemStack> drops = new ArrayList<ItemStack>();
        drops.add(new ItemStack(WomItems.itemTinOre, world.rand.nextInt(8) + 2));
        
        float gem0 = world.rand.nextFloat();
        float gem1 = world.rand.nextFloat();
        float gem2 = world.rand.nextFloat();
        float gem3 = world.rand.nextFloat();
        
        float stone = world.rand.nextFloat();
        
        if(gem0 <= 0.04f)
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
        
        if(gem3 <= 0.01f)
        {
        	drops.add(new ItemStack(WomItems.itemJadeGem));
        }
        
        if(stone <= 0.8)
        {
        	drops.add(new ItemStack(WomItems.itemCoarseStone, world.rand.nextInt(13) + 1));
        }
        
        return drops;
    }
}
