package com.higgs.wom.block;

import com.higgs.wom.HiggsWom;
import com.higgs.wom.item.WomItems;
import net.minecraft.block.material.Material;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import java.util.ArrayList;

public class BlockThoriumOre extends WomOre
{
	public BlockThoriumOre(String unlocalizedName, Material material)
	{
		super(material);
		setBlockName(unlocalizedName);
		setBlockTextureName(HiggsWom.MODID + ":blockThoriumOre");
		setCreativeTab(HiggsWom.tabWom);
        this.levels = new int[]{230, 270, 280, 345};
	}
	
	@Override
    public ArrayList<ItemStack> getDrops(World world, int x, int y, int z, int metadata, int fortune)
    {
        ArrayList<ItemStack> drops = new ArrayList<ItemStack>();
        
        float dropsAtAll = world.rand.nextFloat();
        if(dropsAtAll <= 0.99f)
        {
        	drops.add(new ItemStack(WomItems.itemThoriumOre, world.rand.nextInt(7) + 2));
        }
        
        float gem0 = world.rand.nextFloat();
        float gem1 = world.rand.nextFloat();
        float gem2 = world.rand.nextFloat();
        float gem3 = world.rand.nextFloat();
        float gem4 = world.rand.nextFloat();
        float gem5 = world.rand.nextFloat();
        float gem6 = world.rand.nextFloat();
        float nugget0 = world.rand.nextFloat();
        
        float stone = world.rand.nextFloat();
        
        if(gem0 <= 0.09f)
        {
            drops.add(new ItemStack(WomItems.itemArcaneCrystalGem));
        }
        
        if(gem1 <= 0.03f)
        {
        	drops.add(new ItemStack(WomItems.itemStarRubyGem));
        }
        
        if(gem2 <= 0.03f)
        {
        	drops.add(new ItemStack(WomItems.itemBlackVitriolGem));
        }
        
        if(gem3 <= 0.02f)
        {
        	drops.add(new ItemStack(Items.diamond));
        }
        
        if(gem4 <= 0.02f)
        {
        	drops.add(new ItemStack(WomItems.itemBlueSapphireGem));
        }
        
        if(gem5 <= 0.02f)
        {
        	drops.add(new ItemStack(Items.emerald));
        }
        
        if(gem6 <= 0.02f)
        {
        	drops.add(new ItemStack(WomItems.itemLargeOpalGem));
        }
        
        if(nugget0 <= 0.005)
        {
        	drops.add(new ItemStack(WomItems.itemThoriumNugget, world.rand.nextInt(13) + 7));
        }
        
        if(stone <= 0.99)
        {
        	drops.add(new ItemStack(WomItems.itemDenseStone, world.rand.nextInt(10) + 1));
        }
        
        return drops;
    }
}
