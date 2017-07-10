package com.higgs.wom.proxy;

import com.higgs.wom.block.WomBlocks;
import com.higgs.wom.item.WomItems;
import com.higgs.wom.world.WomFlowerGen;
import com.higgs.wom.world.WomOreGen;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

import java.util.ArrayList;

public class CommonProxy
{
	public void preInit(FMLPreInitializationEvent e)
	{
		WomItems.init();
		WomBlocks.init();
    }

    public void init(FMLInitializationEvent e)
    {
    	OreDictionary.registerOre("dustCopper", WomItems.itemCopperOre);
    	OreDictionary.registerOre("dustTin", WomItems.itemTinOre);
    	OreDictionary.registerOre("dustSilver", WomItems.itemSilverOre);
    	OreDictionary.registerOre("dustPlatinum", WomItems.itemTruesilverOre);
    	
    	OreDictionary.registerOre("oreCopper", WomBlocks.blockCopperOre);
    	OreDictionary.registerOre("oreTin", WomBlocks.blockTinOre);
    	OreDictionary.registerOre("oreSilver", WomBlocks.blockSilverOre);
    	
    	OreDictionary.registerOre("ingotCopper", WomItems.itemCopperBar);
    	OreDictionary.registerOre("ingotTin", WomItems.itemTinBar);
    	OreDictionary.registerOre("ingotSilver", WomItems.itemSilverBar);
    	OreDictionary.registerOre("ingotBronze", WomItems.itemBronzeBar);
    	OreDictionary.registerOre("ingotPlatinum", WomItems.itemTruesilverBar);
    	
    	ArrayList<ItemStack> coppers = OreDictionary.getOres("ingotCopper");
    	ArrayList<ItemStack> tins = OreDictionary.getOres("ingotTin");
//    	ArrayList<ItemStack> silvers = OreDictionary.getOres("ingotSilver");
    	ArrayList<ItemStack> bronzes = OreDictionary.getOres("ingotBronze");
    	ArrayList<ItemStack> platinums = OreDictionary.getOres("ingotPlatinum");
    	
    	ItemStack phillyStone = new ItemStack(WomItems.itemPhilosophersStone.setContainerItem(WomItems.itemPhilosophersStone));
    	
    	GameRegistry.addRecipe(new ItemStack(WomItems.itemInertStone, 1), " * ", "*#*", " * ", '*', Items.iron_ingot, '#', WomItems.itemBlackVitriolGem);
    	GameRegistry.addRecipe(new ItemStack(WomItems.itemPhilosophersStone, 1), "&#&", "#*#", "&#&", '#', new ItemStack(WomBlocks.blockFirebloomFlower), '*', WomItems.itemInertStone, '&', new ItemStack(WomBlocks.blockPurpleLotusFlower));
    	
    	GameRegistry.addShapelessRecipe(new ItemStack(WomItems.itemTruesilverBar), phillyStone, new ItemStack(WomItems.itemMithrilBar));
    	GameRegistry.addShapelessRecipe(new ItemStack(WomItems.itemRoughBlastingPowder), WomItems.itemRoughStone);
    	GameRegistry.addShapelessRecipe(new ItemStack(WomItems.itemCoarseBlastingPowder), WomItems.itemCoarseStone);
    	GameRegistry.addShapelessRecipe(new ItemStack(WomItems.itemSolidBlastingPowder), WomItems.itemSolidStone, WomItems.itemSolidStone);
    	GameRegistry.addShapelessRecipe(new ItemStack(WomItems.itemDenseBlastingPowder), WomItems.itemDenseStone, WomItems.itemDenseStone);
    	
    	GameRegistry.addShapelessRecipe(new ItemStack(WomItems.itemCopperNugget, 9), WomItems.itemCopperBar);
    	GameRegistry.addShapelessRecipe(new ItemStack(WomItems.itemTinNugget, 9), WomItems.itemTinBar);
    	GameRegistry.addShapelessRecipe(new ItemStack(WomItems.itemSilverNugget, 9), WomItems.itemSilverBar);
    	GameRegistry.addShapelessRecipe(new ItemStack(WomItems.itemMithrilNugget, 9), WomItems.itemMithrilBar);
    	GameRegistry.addShapelessRecipe(new ItemStack(WomItems.itemThoriumNugget, 9), WomItems.itemThoriumBar);
    	GameRegistry.addShapelessRecipe(new ItemStack(WomItems.itemTruesilverNugget, 9), WomItems.itemTruesilverBar);
    	GameRegistry.addShapelessRecipe(new ItemStack(WomItems.itemDarkIronNugget, 9), WomItems.itemDarkIronBar);
    	
    	GameRegistry.addShapedRecipe(new ItemStack(WomItems.itemCopperBar), "###", "###", "###", '#', WomItems.itemCopperNugget);
    	GameRegistry.addShapedRecipe(new ItemStack(WomItems.itemCopperBar), "###", "###", "###", '#', WomItems.itemTinNugget);
    	GameRegistry.addShapedRecipe(new ItemStack(WomItems.itemCopperBar), "###", "###", "###", '#', WomItems.itemSilverNugget);
    	GameRegistry.addShapedRecipe(new ItemStack(WomItems.itemCopperBar), "###", "###", "###", '#', WomItems.itemMithrilNugget);
    	GameRegistry.addShapedRecipe(new ItemStack(WomItems.itemCopperBar), "###", "###", "###", '#', WomItems.itemThoriumNugget);
    	GameRegistry.addShapedRecipe(new ItemStack(WomItems.itemCopperBar), "###", "###", "###", '#', WomItems.itemTruesilverNugget);
    	GameRegistry.addShapedRecipe(new ItemStack(WomItems.itemCopperBar), "###", "###", "###", '#', WomItems.itemDarkIronNugget);
    	
    	genGunpowderRecipes();    	
    	genBronzeRecipes(bronzes, coppers, tins);
    	
    	for(int i = 0; i < platinums.size(); i++)
    	{
    		GameRegistry.addShapelessRecipe(new ItemStack(WomItems.itemMithrilBar), phillyStone, platinums.get(i));
    	}

        initWorldGens();
    	initSmeltingRecipes();
    }

    public void postInit(FMLPostInitializationEvent e)
    {
    	
    }
    
    private void initSmeltingRecipes()
    {
    	GameRegistry.addSmelting(new ItemStack(WomItems.itemCopperOre, 1), new ItemStack(WomItems.itemCopperBar, 1), 0.45F);
    	GameRegistry.addSmelting(new ItemStack(WomItems.itemTinOre, 1), new ItemStack(WomItems.itemTinBar, 1), 0.50F);
    	GameRegistry.addSmelting(new ItemStack(WomItems.itemSilverOre, 1), new ItemStack(WomItems.itemSilverBar, 1), 0.55F);
    	GameRegistry.addSmelting(new ItemStack(WomItems.itemMithrilOre, 1), new ItemStack(WomItems.itemMithrilBar, 1), 0.60F);
    	GameRegistry.addSmelting(new ItemStack(WomItems.itemThoriumOre, 1), new ItemStack(WomItems.itemThoriumBar, 1), 0.65F);
    	GameRegistry.addSmelting(new ItemStack(WomItems.itemTruesilverOre, 1), new ItemStack(WomItems.itemTruesilverBar, 1), 0.75F);
    	GameRegistry.addSmelting(new ItemStack(WomItems.itemDarkIronOre, 8), new ItemStack(WomItems.itemDarkIronBar, 1), 0.75f);
    	
    	GameRegistry.addSmelting(new ItemStack(WomBlocks.blockCopperOre, 1), new ItemStack(WomItems.itemCopperBar, 2), 0.80f);
    	GameRegistry.addSmelting(new ItemStack(WomBlocks.blockTinOre, 1), new ItemStack(WomItems.itemTinBar, 2), 0.90f);
    	GameRegistry.addSmelting(new ItemStack(WomBlocks.blockSilverOre, 1), new ItemStack(WomItems.itemSilverBar, 2), 0.95f);
    	GameRegistry.addSmelting(new ItemStack(WomBlocks.blockMithrilOre, 1), new ItemStack(WomItems.itemMithrilBar, 2), 1.10f);
    	GameRegistry.addSmelting(new ItemStack(WomBlocks.blockThoriumOre, 1), new ItemStack(WomItems.itemThoriumBar, 2), 1.20f);
    	GameRegistry.addSmelting(new ItemStack(WomBlocks.blockTruesilverOre, 1), new ItemStack(WomItems.itemTruesilverBar, 2), 1.25f);
    	GameRegistry.addSmelting(new ItemStack(WomBlocks.blockDarkIronOre, 1), new ItemStack(WomItems.itemDarkIronBar, 1), 1.25f);
    }
    
    private void initWorldGens()
    {
    	GameRegistry.registerWorldGenerator(new WomOreGen(), 0);
    	GameRegistry.registerWorldGenerator(new WomFlowerGen(), 0);
    }
    
    private void genBronzeRecipes(ArrayList<ItemStack> bronzes, ArrayList<ItemStack> coppers, ArrayList<ItemStack> tins)
    {
    	for(int i = 0; i < bronzes.size(); i++)
    	{
    		for(int j = 0; j < coppers.size(); j++)
    		{
    			for(int k = 0; k < coppers.size(); k++)
    			{
    				for(int l = 0; l < tins.size(); l++)
    				{
    					GameRegistry.addShapelessRecipe(new ItemStack(WomItems.itemBronzeBar, 3), coppers.get(j), coppers.get(k), tins.get(l), new ItemStack(WomItems.itemPhilosophersStone));
    				}
    			}
    		}
    	}
    }

	public EntityPlayer getPlayerEntity(MessageContext ctx)
	{
		return ctx.getServerHandler().playerEntity;
	}

    private void genGunpowderRecipes()
    {
    	GameRegistry.addShapelessRecipe(new ItemStack(Items.gunpowder, 1), new Object[]
    			{
    					WomItems.itemRoughBlastingPowder, WomItems.itemRoughBlastingPowder,
    					WomItems.itemCoarseBlastingPowder, WomItems.itemCoarseBlastingPowder
    			});
    	
    	GameRegistry.addShapelessRecipe(new ItemStack(Items.gunpowder, 2), new Object[]
    			{
    					WomItems.itemSolidBlastingPowder, WomItems.itemSolidBlastingPowder,
    					WomItems.itemDenseBlastingPowder, WomItems.itemDenseBlastingPowder
    			});
    }
}
