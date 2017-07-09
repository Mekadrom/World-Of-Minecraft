package com.higgs.wom.item;

import com.higgs.wom.HiggsWom;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.item.Item;

public final class WomItems
{
	public static Item itemRoughStone;
	public static Item itemCoarseStone;
	public static Item itemSolidStone;
	public static Item itemDenseStone;
	
	public static Item itemRoughBlastingPowder;
	public static Item itemCoarseBlastingPowder;
	public static Item itemSolidBlastingPowder;
	public static Item itemDenseBlastingPowder;
	
	public static Item itemMalachiteGem;
	public static Item itemTigerseyeGem;
	public static Item itemShadowgemGem;
	public static Item itemMossAgateGem;
	public static Item itemLesserMoonstoneGem;
	public static Item itemJadeGem;
	public static Item itemCitrineGem;
	public static Item itemAquamarineGem;
	public static Item itemStarRubyGem;
	public static Item itemBlackVitriolGem;
	public static Item itemArcaneCrystalGem;
	public static Item itemBlueSapphireGem;
	public static Item itemLargeOpalGem;
	public static Item itemBloodOfTheMountainGem;
	public static Item itemBlackDiamondGem;
	public static Item itemNetherResidue;
	public static Item itemAzureMoonstoneGem;
	public static Item itemBloodGarnetGem;
	public static Item itemDeepPeridotGem;
	public static Item itemFlameSpessariteGem;
	public static Item itemGoldenDraeniteGem;
	public static Item itemShadowDraeniteGem;
	public static Item itemNobleTopazGem;
	public static Item itemTalasiteGem;
	public static Item itemDawnstoneGem;
	public static Item itemLivingRubyGem;
	public static Item itemNightseyeGem;
	public static Item itemStarOfEluneGem;
	
	public static Item itemCopperOre;
	public static Item itemTinOre;
	public static Item itemSilverOre;
	public static Item itemMithrilOre;
	public static Item itemThoriumOre;
	public static Item itemTruesilverOre;
	public static Item itemDarkIronOre;
	public static Item itemFelIronOre;
	public static Item itemEterniumOre;
	
	public static Item itemCopperBar;
	public static Item itemTinBar;
	public static Item itemBronzeBar;
	public static Item itemSilverBar;
	public static Item itemMithrilBar;
	public static Item itemThoriumBar;
	public static Item itemTruesilverBar;
	public static Item itemDarkIronBar;
	public static Item itemFelIronBar;
	public static Item itemEterniumBar;
	
	public static Item itemCopperNugget;
	public static Item itemTinNugget;
	public static Item itemBronzeNugget;
	public static Item itemSilverNugget;
	public static Item itemMithrilNugget;
	public static Item itemThoriumNugget;
	public static Item itemTruesilverNugget;
	public static Item itemDarkIronNugget;
	public static Item itemFelIronNugget;
	public static Item itemEterniumNugget;
	
	public static Item itemMoteOfEarth;
	public static Item itemMoteOfWater;
	public static Item itemMoteOfAir;
	public static Item itemMoteOfFire;
	
	public static Item itemPrimalEarth;
	public static Item itemPrimalWater;
	public static Item itemPrimalAir;
	public static Item itemPrimalFire;
	
	public static Item itemInertStone;
	public static Item itemPhilosophersStone;
	public static Item itemEluneStone;
	public static Item itemMinersPick;
	
	public static final void init()
	{
		GameRegistry.registerItem(itemRoughStone = new WomItem().setUnlocalizedName("itemRoughStone").setCreativeTab(HiggsWom.tabWom).setTextureName(HiggsWom.MODID + ":itemRoughStone"), "itemRoughStone");
		GameRegistry.registerItem(itemCoarseStone = new WomItem().setUnlocalizedName("itemCoarseStone").setCreativeTab(HiggsWom.tabWom).setTextureName(HiggsWom.MODID + ":itemCoarseStone"), "itemCoarseStone");
		GameRegistry.registerItem(itemSolidStone = new WomItem().setUnlocalizedName("itemSolidStone").setCreativeTab(HiggsWom.tabWom).setTextureName(HiggsWom.MODID + ":itemSolidStone"), "itemSolidStone");
		GameRegistry.registerItem(itemDenseStone = new WomItem().setUnlocalizedName("itemDenseStone").setCreativeTab(HiggsWom.tabWom).setTextureName(HiggsWom.MODID + ":itemDenseStone"), "itemDenseStone");
		
		GameRegistry.registerItem(itemRoughBlastingPowder = new ItemBlastingPowder(HiggsWom.roughBlastingPowderStrength).setUnlocalizedName("itemRoughBlastingPowder").setCreativeTab(HiggsWom.tabWom).setTextureName(HiggsWom.MODID + ":itemRoughBlastingPowder"), "itemRoughBlastingPowder");
		GameRegistry.registerItem(itemCoarseBlastingPowder = new ItemBlastingPowder(HiggsWom.coarseBlastingPowderStrength).setUnlocalizedName("itemCoarseBlastingPowder").setCreativeTab(HiggsWom.tabWom).setTextureName(HiggsWom.MODID + ":itemCoarseBlastingPowder"), "itemCoarseBlastingPowder");
		GameRegistry.registerItem(itemSolidBlastingPowder = new ItemBlastingPowder(HiggsWom.solidBlastingPowderStrength).setUnlocalizedName("itemSolidBlastingPowder").setCreativeTab(HiggsWom.tabWom).setTextureName(HiggsWom.MODID + ":itemSolidBlastingPowder"), "itemSolidBlastingPowder");
		GameRegistry.registerItem(itemDenseBlastingPowder = new ItemBlastingPowder(HiggsWom.denseBlastingPowderStrength).setUnlocalizedName("itemDenseBlastingPowder").setCreativeTab(HiggsWom.tabWom).setTextureName(HiggsWom.MODID + ":itemDenseBlastingPowder"), "itemDenseBlastingPowder");
		
		GameRegistry.registerItem(itemMalachiteGem = new WomItem().setUnlocalizedName("itemMalachiteGem").setCreativeTab(HiggsWom.tabWom).setTextureName(HiggsWom.MODID + ":itemMalachiteGem"), "itemMalachiteGem");
		GameRegistry.registerItem(itemTigerseyeGem = new WomItem().setUnlocalizedName("itemTigerseyeGem").setCreativeTab(HiggsWom.tabWom).setTextureName(HiggsWom.MODID + ":itemTigerseyeGem"), "itemShadowgemGem");
		GameRegistry.registerItem(itemShadowgemGem = new WomItem().setUnlocalizedName("itemShadowgemGem").setCreativeTab(HiggsWom.tabWom).setTextureName(HiggsWom.MODID + ":itemShadowgemGem"), "itemTigerseyeGem");
		GameRegistry.registerItem(itemMossAgateGem = new WomItem().setUnlocalizedName("itemMossAgateGem").setCreativeTab(HiggsWom.tabWom).setTextureName(HiggsWom.MODID + ":itemMossAgateGem"), "itemMossAgateGem");
		GameRegistry.registerItem(itemLesserMoonstoneGem = new WomItem().setUnlocalizedName("itemLesserMoonstoneGem").setCreativeTab(HiggsWom.tabWom).setTextureName(HiggsWom.MODID + ":itemLesserMoonstoneGem"), "itemLesserMoonstoneGem");
		GameRegistry.registerItem(itemJadeGem = new WomItem().setUnlocalizedName("itemJadeGem").setCreativeTab(HiggsWom.tabWom).setTextureName(HiggsWom.MODID + ":itemJadeGem"), "itemJadeGem");
		GameRegistry.registerItem(itemCitrineGem = new WomItem().setUnlocalizedName("itemCitrineGem").setCreativeTab(HiggsWom.tabWom).setTextureName(HiggsWom.MODID + ":itemCitrineGem"), "itemCitrineGem");
		GameRegistry.registerItem(itemAquamarineGem = new WomItem().setUnlocalizedName("itemAquamarineGem").setCreativeTab(HiggsWom.tabWom).setTextureName(HiggsWom.MODID + ":itemAquamarineGem"), "itemAquamarineGem");
		GameRegistry.registerItem(itemStarRubyGem = new WomItem().setUnlocalizedName("itemStarRubyGem").setCreativeTab(HiggsWom.tabWom).setTextureName(HiggsWom.MODID + ":itemStarRubyGem"), "itemStarRubyGem");
		GameRegistry.registerItem(itemBlackVitriolGem = new WomItem().setUnlocalizedName("itemBlackVitriolGem").setCreativeTab(HiggsWom.tabWom).setTextureName(HiggsWom.MODID + ":itemBlackVitriolGem"), "itemBlackVitriolGem");
		GameRegistry.registerItem(itemArcaneCrystalGem = new WomItem().setUnlocalizedName("itemArcaneCrystalGem").setCreativeTab(HiggsWom.tabWom).setTextureName(HiggsWom.MODID + ":itemArcaneCrystalGem"), "itemArcaneCrystalGem");
		GameRegistry.registerItem(itemBlueSapphireGem = new WomItem().setUnlocalizedName("itemBlueSapphireGem").setCreativeTab(HiggsWom.tabWom).setTextureName(HiggsWom.MODID + ":itemBlueSapphireGem"), "itemBlueSapphireGem");
		GameRegistry.registerItem(itemLargeOpalGem = new WomItem().setUnlocalizedName("itemLargeOpalGem").setCreativeTab(HiggsWom.tabWom).setTextureName(HiggsWom.MODID + ":itemLargeOpalGem"), "itemLargeOpalGem");
		GameRegistry.registerItem(itemBloodOfTheMountainGem = new WomItem().setUnlocalizedName("itemBloodOfTheMountainGem").setCreativeTab(HiggsWom.tabWom).setTextureName(HiggsWom.MODID + ":itemBloodOfTheMountainGem"), "itemBloodOfTheMountainGem");
		GameRegistry.registerItem(itemBlackDiamondGem = new WomItem().setUnlocalizedName("itemBlackDiamondGem").setCreativeTab(HiggsWom.tabWom).setTextureName(HiggsWom.MODID + ":itemBlackDiamondGem"), "itemBlackDiamondGem");
		GameRegistry.registerItem(itemNetherResidue = new WomItem().setUnlocalizedName("itemNetherResidue").setCreativeTab(HiggsWom.tabWom).setTextureName(HiggsWom.MODID + ":itemNetherResidue"), "itemNetherResidue");
		GameRegistry.registerItem(itemAzureMoonstoneGem = new WomItem().setUnlocalizedName("itemAzureMoonstoneGem").setCreativeTab(HiggsWom.tabWom).setTextureName(HiggsWom.MODID + ":itemAzureMoonstoneGem"), "itemAzureMoonstoneGem");
		GameRegistry.registerItem(itemBloodGarnetGem = new WomItem().setUnlocalizedName("itemBloodGarnetGem").setCreativeTab(HiggsWom.tabWom).setTextureName(HiggsWom.MODID + ":itemBloodGarnetGem"), "itemBloodGarnetGem");
		GameRegistry.registerItem(itemDeepPeridotGem = new WomItem().setUnlocalizedName("itemDeepPeridotGem").setCreativeTab(HiggsWom.tabWom).setTextureName(HiggsWom.MODID + ":itemDeepPeridotGem"), "itemDeepPeridotGem");
		GameRegistry.registerItem(itemFlameSpessariteGem = new WomItem().setUnlocalizedName("itemFlameSpessariteGem").setCreativeTab(HiggsWom.tabWom).setTextureName(HiggsWom.MODID + ":itemFlameSpessariteGem"), "itemFlameSpessariteGem");
		GameRegistry.registerItem(itemGoldenDraeniteGem = new WomItem().setUnlocalizedName("itemGoldenDraeniteGem").setCreativeTab(HiggsWom.tabWom).setTextureName(HiggsWom.MODID + ":itemGoldenDraeniteGem"), "itemGoldenDraeniteGem");
		GameRegistry.registerItem(itemShadowDraeniteGem = new WomItem().setUnlocalizedName("itemShadowDraeniteGem").setCreativeTab(HiggsWom.tabWom).setTextureName(HiggsWom.MODID + ":itemShadowDraeniteGem"), "itemShadowDraeniteGem");
		GameRegistry.registerItem(itemNobleTopazGem = new WomItem().setUnlocalizedName("itemNobleTopazGem").setCreativeTab(HiggsWom.tabWom).setTextureName(HiggsWom.MODID + ":itemNobleTopazGem"), "itemNobleTopazGem");
		GameRegistry.registerItem(itemTalasiteGem = new WomItem().setUnlocalizedName("itemTalasiteGem").setCreativeTab(HiggsWom.tabWom).setTextureName(HiggsWom.MODID + ":itemTalasiteGem"), "itemTalasiteGem");
		GameRegistry.registerItem(itemDawnstoneGem = new WomItem().setUnlocalizedName("itemDawnstoneGem").setCreativeTab(HiggsWom.tabWom).setTextureName(HiggsWom.MODID + ":itemDawnstoneGem"), "itemDawnstoneGem");
		GameRegistry.registerItem(itemLivingRubyGem = new WomItem().setUnlocalizedName("itemLivingRubyGem").setCreativeTab(HiggsWom.tabWom).setTextureName(HiggsWom.MODID + ":itemLivingRubyGem"), "itemLivingRubyGem");
		GameRegistry.registerItem(itemNightseyeGem = new WomItem().setUnlocalizedName("itemNightseyeGem").setCreativeTab(HiggsWom.tabWom).setTextureName(HiggsWom.MODID + ":itemNightseyeGem"), "itemNightseyeGem");
		GameRegistry.registerItem(itemStarOfEluneGem = new WomItem().setUnlocalizedName("itemStarOfEluneGem").setCreativeTab(HiggsWom.tabWom).setTextureName(HiggsWom.MODID + ":itemStarOfEluneGem"), "itemStarOfEluneGem");
		
		GameRegistry.registerItem(itemCopperOre = new WomItem().setUnlocalizedName("itemCopperOre").setCreativeTab(HiggsWom.tabWom).setTextureName(HiggsWom.MODID + ":itemCopperOre"), "itemCopperOre");
		GameRegistry.registerItem(itemTinOre = new WomItem().setUnlocalizedName("itemTinOre").setCreativeTab(HiggsWom.tabWom).setTextureName(HiggsWom.MODID + ":itemTinOre"), "itemTinOre");
		GameRegistry.registerItem(itemSilverOre = new WomItem().setUnlocalizedName("itemSilverOre").setCreativeTab(HiggsWom.tabWom).setTextureName(HiggsWom.MODID + ":itemSilverOre"), "itemSilverOre");
		GameRegistry.registerItem(itemMithrilOre = new WomItem().setUnlocalizedName("itemMithrilOre").setCreativeTab(HiggsWom.tabWom).setTextureName(HiggsWom.MODID + ":itemMithrilOre"), "itemMithrilOre");
		GameRegistry.registerItem(itemThoriumOre = new WomItem().setUnlocalizedName("itemThoriumOre").setCreativeTab(HiggsWom.tabWom).setTextureName(HiggsWom.MODID + ":itemThoriumOre"), "itemThoriumOre");
		GameRegistry.registerItem(itemTruesilverOre = new WomItem().setUnlocalizedName("itemTruesilverOre").setCreativeTab(HiggsWom.tabWom).setTextureName(HiggsWom.MODID + ":itemTruesilverOre"), "itemTruesilverOre");
		GameRegistry.registerItem(itemDarkIronOre = new WomItem().setUnlocalizedName("itemDarkIronOre").setCreativeTab(HiggsWom.tabWom).setTextureName(HiggsWom.MODID + ":itemDarkIronOre"), "itemDarkIronOre");
		GameRegistry.registerItem(itemFelIronOre = new WomItem().setUnlocalizedName("itemFelIronOre").setCreativeTab(HiggsWom.tabWom).setTextureName(HiggsWom.MODID + ":itemFelIronOre"), "itemFelIronOre");
		GameRegistry.registerItem(itemEterniumOre = new WomItem().setUnlocalizedName("itemEterniumOre").setCreativeTab(HiggsWom.tabWom).setTextureName(HiggsWom.MODID + ":itemEterniumOre"), "itemEterniumOre");
		
		GameRegistry.registerItem(itemCopperBar = new WomItem().setUnlocalizedName("itemCopperBar").setCreativeTab(HiggsWom.tabWom).setTextureName(HiggsWom.MODID + ":itemCopperBar"), "itemCopperBar");
		GameRegistry.registerItem(itemTinBar = new WomItem().setUnlocalizedName("itemTinBar").setCreativeTab(HiggsWom.tabWom).setTextureName(HiggsWom.MODID + ":itemTinBar"), "itemTinBar");
		GameRegistry.registerItem(itemBronzeBar = new WomItem().setUnlocalizedName("itemBronzeBar").setCreativeTab(HiggsWom.tabWom).setTextureName(HiggsWom.MODID + ":itemBronzeBar"), "itemBronzeBar");
		GameRegistry.registerItem(itemSilverBar = new WomItem().setUnlocalizedName("itemSilverBar").setCreativeTab(HiggsWom.tabWom).setTextureName(HiggsWom.MODID + ":itemSilverBar"), "itemSilverBar");
		GameRegistry.registerItem(itemMithrilBar = new WomItem().setUnlocalizedName("itemMithrilBar").setCreativeTab(HiggsWom.tabWom).setTextureName(HiggsWom.MODID + ":itemMithrilBar"), "itemMithrilBar");
		GameRegistry.registerItem(itemThoriumBar = new WomItem().setUnlocalizedName("itemThoriumBar").setCreativeTab(HiggsWom.tabWom).setTextureName(HiggsWom.MODID + ":itemThoriumBar"), "itemThoriumBar");
		GameRegistry.registerItem(itemTruesilverBar = new WomItem().setUnlocalizedName("itemTruesilverBar").setCreativeTab(HiggsWom.tabWom).setTextureName(HiggsWom.MODID + ":itemTruesilverBar"), "itemTruesilverBar");
		GameRegistry.registerItem(itemDarkIronBar = new WomItem().setUnlocalizedName("itemDarkIronBar").setCreativeTab(HiggsWom.tabWom).setTextureName(HiggsWom.MODID + ":itemDarkIronBar"), "itemDarkIronBar");
		GameRegistry.registerItem(itemFelIronBar = new WomItem().setUnlocalizedName("itemFelIronBar").setCreativeTab(HiggsWom.tabWom).setTextureName(HiggsWom.MODID + ":itemFelIronBar"), "itemFelIronBar");
		GameRegistry.registerItem(itemEterniumBar = new WomItem().setUnlocalizedName("itemEterniumBar").setCreativeTab(HiggsWom.tabWom).setTextureName(HiggsWom.MODID + ":itemEterniumBar"), "itemEterniumBar");
		
		GameRegistry.registerItem(itemCopperNugget = new WomItem().setUnlocalizedName("itemCopperNugget").setCreativeTab(HiggsWom.tabWom).setTextureName(HiggsWom.MODID + ":itemCopperNugget"), "itemCopperNugget");
		GameRegistry.registerItem(itemTinNugget = new WomItem().setUnlocalizedName("itemTinNugget").setCreativeTab(HiggsWom.tabWom).setTextureName(HiggsWom.MODID + ":itemTinNugget"), "itemTinNugget");
		GameRegistry.registerItem(itemBronzeNugget = new WomItem().setUnlocalizedName("itemBronzeNugget").setCreativeTab(HiggsWom.tabWom).setTextureName(HiggsWom.MODID + ":itemBronzeNugget"), "itemBronzeNugget");
		GameRegistry.registerItem(itemSilverNugget = new WomItem().setUnlocalizedName("itemSilverNugget").setCreativeTab(HiggsWom.tabWom).setTextureName(HiggsWom.MODID + ":itemSilverNugget"), "itemSilverNugget");
		GameRegistry.registerItem(itemMithrilNugget = new WomItem().setUnlocalizedName("itemMithrilNugget").setCreativeTab(HiggsWom.tabWom).setTextureName(HiggsWom.MODID + ":itemMithrilNugget"), "itemMithrilNugget");
		GameRegistry.registerItem(itemThoriumNugget = new WomItem().setUnlocalizedName("itemThoriumNugget").setCreativeTab(HiggsWom.tabWom).setTextureName(HiggsWom.MODID + ":itemThoriumNugget"), "itemThoriumNugget");
		GameRegistry.registerItem(itemTruesilverNugget = new WomItem().setUnlocalizedName("itemTruesilverNugget").setCreativeTab(HiggsWom.tabWom).setTextureName(HiggsWom.MODID + ":itemTruesilverNugget"), "itemTruesilverNugget");
		GameRegistry.registerItem(itemDarkIronNugget = new WomItem().setUnlocalizedName("itemDarkIronNugget").setCreativeTab(HiggsWom.tabWom).setTextureName(HiggsWom.MODID + ":itemDarkIronNugget"), "itemDarkIronNugget");
		GameRegistry.registerItem(itemFelIronNugget = new WomItem().setUnlocalizedName("itemFelIronNugget").setCreativeTab(HiggsWom.tabWom).setTextureName(HiggsWom.MODID + ":itemFelIronNugget"), "itemFelIronNugget");
		GameRegistry.registerItem(itemEterniumNugget = new WomItem().setUnlocalizedName("itemEterniumNugget").setCreativeTab(HiggsWom.tabWom).setTextureName(HiggsWom.MODID + ":itemEterniumNugget"), "itemEterniumNugget");
		
		GameRegistry.registerItem(itemMoteOfEarth = new WomItem().setUnlocalizedName("itemMoteOfEarth").setCreativeTab(HiggsWom.tabWom).setTextureName(HiggsWom.MODID + ":itemMoteOfEarth"), "itemMoteOfEarth");
		GameRegistry.registerItem(itemMoteOfWater = new WomItem().setUnlocalizedName("itemMoteOfWater").setCreativeTab(HiggsWom.tabWom).setTextureName(HiggsWom.MODID + ":itemMoteOfWater"), "itemMoteOfWater");
		GameRegistry.registerItem(itemMoteOfAir = new WomItem().setUnlocalizedName("itemMoteOfAir").setCreativeTab(HiggsWom.tabWom).setTextureName(HiggsWom.MODID + ":itemMoteOfAir"), "itemMoteOfAir");
		GameRegistry.registerItem(itemMoteOfFire = new WomItem().setUnlocalizedName("itemMoteOfFire").setCreativeTab(HiggsWom.tabWom).setTextureName(HiggsWom.MODID + ":itemMoteOfFire"), "itemMoteOfFire");
		
		GameRegistry.registerItem(itemPrimalEarth = new WomItem().setUnlocalizedName("itemPrimalEarth").setCreativeTab(HiggsWom.tabWom).setTextureName(HiggsWom.MODID + ":itemPrimalEarth"), "itemPrimalEarth");
		GameRegistry.registerItem(itemPrimalWater = new WomItem().setUnlocalizedName("itemPrimalWater").setCreativeTab(HiggsWom.tabWom).setTextureName(HiggsWom.MODID + ":itemPrimalWater"), "itemPrimalWater");
		GameRegistry.registerItem(itemPrimalAir = new WomItem().setUnlocalizedName("itemPrimalAir").setCreativeTab(HiggsWom.tabWom).setTextureName(HiggsWom.MODID + ":itemPrimalAir"), "itemPrimalAir");
		GameRegistry.registerItem(itemPrimalFire = new WomItem().setUnlocalizedName("itemPrimalFire").setCreativeTab(HiggsWom.tabWom).setTextureName(HiggsWom.MODID + ":itemPrimalFire"), "itemPrimalFire");
		
		GameRegistry.registerItem(itemInertStone = new WomItem().setUnlocalizedName("itemInertStone").setCreativeTab(HiggsWom.tabWom).setTextureName(HiggsWom.MODID + ":itemInertStone"), "itemInertStone");
		GameRegistry.registerItem(itemPhilosophersStone = new WomItem().setUnlocalizedName("itemPhilosophersStone").setCreativeTab(HiggsWom.tabWom).setTextureName(HiggsWom.MODID + ":itemPhilosophersStone").setMaxStackSize(1), "itemPhilosophersStone");
		GameRegistry.registerItem(itemEluneStone = new WomItem().setUnlocalizedName("itemEluneStone").setCreativeTab(HiggsWom.tabWom).setTextureName(HiggsWom.MODID + ":itemEluneStone").setMaxStackSize(64), "itemEluneStone");
		GameRegistry.registerItem(itemMinersPick = new WomItem().setUnlocalizedName("itemMinersPick").setCreativeTab(HiggsWom.tabWom).setTextureName(HiggsWom.MODID + ":itemMinersPick").setMaxStackSize(1), "itemMinersPick");
	}
}
