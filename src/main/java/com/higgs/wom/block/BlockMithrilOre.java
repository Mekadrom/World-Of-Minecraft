package com.higgs.wom.block;

import java.util.ArrayList;

import com.higgs.wom.HiggsWom;
import com.higgs.wom.item.WomItems;

import net.minecraft.block.material.Material;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class BlockMithrilOre extends WomOre
{
	public BlockMithrilOre(String unlocalizedName, Material material)
	{
		super(material);
		setBlockName(unlocalizedName);
		setBlockTextureName(HiggsWom.MODID + ":blockMithrilOre");
		setCreativeTab(HiggsWom.tabWom);
		setHardness(2.0f);
		setResistance(6.0f);
		setHarvestLevel("pickaxe", 2);
	}
	
	@Override
    public ArrayList<ItemStack> getDrops(World world, int x, int y, int z, int metadata, int fortune)
    {
        ArrayList<ItemStack> drops = new ArrayList<ItemStack>();
        
        float dropsAtAll = world.rand.nextFloat();
        if(dropsAtAll <= 0.99f)
        {
        	drops.add(new ItemStack(WomItems.itemMithrilOre, world.rand.nextInt(8) + 2));
        }
        
        float gem0 = world.rand.nextFloat();
        float gem1 = world.rand.nextFloat();
        float gem2 = world.rand.nextFloat();
        float gem3 = world.rand.nextFloat();
        float nugget0 = world.rand.nextFloat();
        
        float drop0 = world.rand.nextFloat();
        float drop1 = world.rand.nextFloat();
        
        float stone = world.rand.nextFloat();
        
        if(gem0 <= 0.03f)
        {
            drops.add(new ItemStack(WomItems.itemAquamarineGem));
        }
        
        if(gem1 <= 0.03f)
        {
        	drops.add(new ItemStack(WomItems.itemCitrineGem));
        }
        
        if(gem2 <= 0.03f)
        {
        	drops.add(new ItemStack(WomItems.itemStarRubyGem));
        }
        
        if(gem3 <= 0.03f)
        {
        	drops.add(new ItemStack(WomItems.itemBlackVitriolGem));
        }
        
        if(nugget0 <= 0.016)
        {
        	drops.add(new ItemStack(WomItems.itemMithrilNugget, world.rand.nextInt(11) + 9));
        }
        
        if(drop0 <= 0.0001)
        {
        	drops.add(new ItemStack(WomItems.itemCoarseStone, world.rand.nextInt(8) + 1));
        }

        if(drop1 <= 0.0001)
        {
        	drops.add(new ItemStack(WomItems.itemTinOre, world.rand.nextInt(3) + 2));
        }
        
        if(stone <= 0.8)
        {
        	drops.add(new ItemStack(WomItems.itemSolidStone, world.rand.nextInt(13) + 1));
        }
        
        return drops;
    }
}
