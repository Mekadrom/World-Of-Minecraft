package com.higgs.wom.block;

import java.util.ArrayList;

import com.higgs.wom.HiggsWom;
import com.higgs.wom.item.WomItems;

import net.minecraft.block.material.Material;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class BlockCopperOre extends WomOre
{
	public BlockCopperOre(String unlocalizedName, Material material)
	{
		super(material);
		setBlockName(unlocalizedName);
		setBlockTextureName(HiggsWom.MODID + ":blockCopperOre");
		setCreativeTab(HiggsWom.tabWom);
		setHardness(2.2f);
		setResistance(6.0f);
		setHarvestLevel("pickaxe", 0);
		this.miningLevel = 1;
	}
	
	@Override
    public ArrayList<ItemStack> getDrops(World world, int x, int y, int z, int metadata, int fortune)
    {
        ArrayList<ItemStack> drops = new ArrayList<ItemStack>();
        drops.add(new ItemStack(WomItems.itemCopperOre, world.rand.nextInt(9) + 1));
        
        float gem0 = world.rand.nextFloat();
        float gem1 = world.rand.nextFloat();
        float gem2 = world.rand.nextFloat();
        
        float stone = world.rand.nextFloat();
        
        if(gem0 <= 0.03f)
        {
            drops.add(new ItemStack(WomItems.itemMalachiteGem));
        }
        
        if(gem1 <= 0.03f)
        {
        	drops.add(new ItemStack(WomItems.itemTigerseyeGem));
        }
        
        if(gem2 <= 0.03f)
        {
        	drops.add(new ItemStack(WomItems.itemShadowgemGem));
        }
        
        if(stone <= 0.8)
        {
        	drops.add(new ItemStack(WomItems.itemRoughStone, world.rand.nextInt(11) + 1));
        }
        
        return drops;
    }
}