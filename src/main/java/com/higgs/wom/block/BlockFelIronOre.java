package com.higgs.wom.block;

import com.higgs.wom.HiggsWom;
import com.higgs.wom.item.WomItems;
import net.minecraft.block.material.Material;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import java.util.ArrayList;

public class BlockFelIronOre extends WomOre
{
	public BlockFelIronOre(String unlocalizedName, Material material)
	{
		super(material);
		setBlockName(unlocalizedName);
		setBlockTextureName(HiggsWom.MODID + ":blockFelIronOre");
		setCreativeTab(HiggsWom.tabWom);
		this.miningLevel = 275;
	}
	
	@Override
    public ArrayList<ItemStack> getDrops(World world, int x, int y, int z, int metadata, int fortune)
    {
        ArrayList<ItemStack> drops = new ArrayList<ItemStack>();
        
        float dropsAtAll = world.rand.nextFloat();
        if(dropsAtAll <= 0.99f)
        {
        	drops.add(new ItemStack(WomItems.itemFelIronOre, world.rand.nextInt(7) + 2));
        }
        
        float gem0 = world.rand.nextFloat();
        float gem1 = world.rand.nextFloat();
        float gem2 = world.rand.nextFloat();
        float gem3 = world.rand.nextFloat();
        float gem4 = world.rand.nextFloat();
        float gem5 = world.rand.nextFloat();
        float gem6 = world.rand.nextFloat();
        float gem7 = world.rand.nextFloat();
        float gem8 = world.rand.nextFloat();
        float gem9 = world.rand.nextFloat();
        float gem10 = world.rand.nextFloat();
        float gem11 = world.rand.nextFloat();
        float gem12 = world.rand.nextFloat();
        float gem13 = world.rand.nextFloat();
        float gem14 = world.rand.nextFloat();
        float gem15 = world.rand.nextFloat();
        
        float nugget0 = world.rand.nextFloat();
        
        if(gem0 <= 0.29)
        {
        	drops.add(new ItemStack(WomItems.itemMoteOfEarth, world.rand.nextInt(1) + 1));
        }
        
        if(gem1 <= 0.29)
        {
        	drops.add(new ItemStack(WomItems.itemMoteOfFire, world.rand.nextInt(1) + 1));
        }
        
        if(gem2 <= 0.06)
        {
        	drops.add(new ItemStack(WomItems.itemEterniumOre, world.rand.nextInt(4) + 1));
        }
        
        if(gem3 <= 0.007)
        {
        	drops.add(new ItemStack(WomItems.itemNetherResidue, world.rand.nextInt(1) + 1));
        }
        
        if(gem4 <= 0.004)
        {
        	drops.add(new ItemStack(WomItems.itemAzureMoonstoneGem, 1));
        }
        
        if(gem5 <= 0.004)
        {
        	drops.add(new ItemStack(WomItems.itemBloodGarnetGem, 1));
        }
        
        if(gem6 <= 0.004)
        {
        	drops.add(new ItemStack(WomItems.itemDeepPeridotGem, 1));
        }
        
        if(gem7 <= 0.004)
        {
        	drops.add(new ItemStack(WomItems.itemFlameSpessariteGem, 1));
        }
        
        if(gem8 <= 0.004)
        {
        	drops.add(new ItemStack(WomItems.itemGoldenDraeniteGem, 1));
        }
        
        if(gem9 <= 0.004)
        {
        	drops.add(new ItemStack(WomItems.itemShadowDraeniteGem, 1));
        }
        
        if(gem10 <= 0.0014)
        {
        	drops.add(new ItemStack(WomItems.itemNobleTopazGem, 1));
        }
        
        if(gem11 <= 0.0014)
        {
        	drops.add(new ItemStack(WomItems.itemTalasiteGem, 1));
        }
        
        if(gem12 <= 0.0013)
        {
        	drops.add(new ItemStack(WomItems.itemDawnstoneGem, 1));
        }
        
        if(gem13 <= 0.0013)
        {
        	drops.add(new ItemStack(WomItems.itemLivingRubyGem, 1));
        }
        
        if(gem14 <= 0.0012)
        {
        	drops.add(new ItemStack(WomItems.itemNightseyeGem, 1));
        }
        
        if(gem15 <= 0.0012)
        {
        	drops.add(new ItemStack(WomItems.itemStarOfEluneGem, 1));
        }
        
        if(nugget0 <= 0.014)
        {
        	drops.add(new ItemStack(WomItems.itemFelIronNugget, world.rand.nextInt(15) + 5));
        }
        
        return drops;
    }
}
