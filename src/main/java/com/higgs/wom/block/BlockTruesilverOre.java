package com.higgs.wom.block;

import com.higgs.wom.HiggsWom;
import com.higgs.wom.item.WomItems;
import net.minecraft.block.material.Material;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import java.util.ArrayList;

public class BlockTruesilverOre extends WomOre
{
	public BlockTruesilverOre(String unlocalizedName, Material material)
	{
		super(material);
		setBlockName(unlocalizedName);
		setBlockTextureName(HiggsWom.MODID + ":blockTruesilverOre");
		setCreativeTab(HiggsWom.tabWom);
        setBlockUnbreakable();
        this.levels = new int[]{205, 255, 280, 330};
	}
	
	@Override
    public ArrayList<ItemStack> getDrops(World world, int x, int y, int z, int metadata, int fortune)
    {
        ArrayList<ItemStack> drops = new ArrayList<ItemStack>();
        
        float dropsAtAll = world.rand.nextFloat();
        if(dropsAtAll <= 0.99f)
        {
        	drops.add(new ItemStack(WomItems.itemTruesilverOre, world.rand.nextInt(6) + 2));
        }
        
        float gem0 = world.rand.nextFloat();
        float gem1 = world.rand.nextFloat();
        float gem2 = world.rand.nextFloat();
        float gem3 = world.rand.nextFloat();
        float nugget0 = world.rand.nextFloat();
        
        float ore1 = world.rand.nextFloat();
//        float gem5 = world.rand.nextFloat();
//        float gem6 = world.rand.nextFloat();
        
        float stone = world.rand.nextFloat();
        
        if(gem0 <= 0.05f)
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
        
        if(gem3 <= 0.000015f)
        {
        	drops.add(new ItemStack(WomItems.itemLesserMoonstoneGem));
        }
        
        if(nugget0 <= 0.009)
        {
        	drops.add(new ItemStack(WomItems.itemTruesilverNugget, world.rand.nextInt(13) + 7));
        }
        
        if(ore1 <= 0.0001)
        {
        	drops.add(new ItemStack(WomItems.itemTinOre, world.rand.nextInt(2) + 2));
        }
        
        if(stone <= 0.99)
        {
        	drops.add(new ItemStack(WomItems.itemCoarseStone, world.rand.nextInt(4) + 1));
        }
        
        return drops;
    }
}