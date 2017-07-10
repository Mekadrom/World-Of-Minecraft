package com.higgs.wom;

import com.higgs.wom.event.WomEventHandlerCommon;
import com.higgs.wom.item.WomItems;
import com.higgs.wom.network.PacketDispatcher;
import com.higgs.wom.network.WomGuiHandler;
import com.higgs.wom.proxy.CommonProxy;
import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;

@Mod(modid = HiggsWom.MODID, name = HiggsWom.MODNAME, version = HiggsWom.VERSION)
public class HiggsWom
{
    public static final String MODID = "wom";
    public static final String MODNAME = "Higgs' World of Minecraft";
    public static final String VERSION = "2.0.0";
    
    public static Configuration config;
    
    public static boolean verboseOutput;
    public static final Logger logger = LogManager.getLogger(MODID);

    public static boolean oreCopperVeinSpawns;
    public static int oreCopperVeinSize;
    public static int oreCopperVeinRarity;
    public static int oreCopperMinY;
    public static int oreCopperMaxY;
    public static int oreCopperDropRateOre;
    
    public static boolean oreTinVeinSpawns;
    public static int oreTinVeinSize;
    public static int oreTinVeinRarity;
    public static int oreTinMinY;
    public static int oreTinMaxY;
    public static int oreTinDropRateOre;
    
    public static boolean oreSilverVeinSpawns;
    public static int oreSilverVeinSize;
    public static int oreSilverVeinRarity;
    public static int oreSilverMinY;
    public static int oreSilverMaxY;
    public static int oreSilverDropRateOre;
    
    public static boolean oreMithrilVeinSpawns;
    public static int oreMithrilVeinSize;
    public static int oreMithrilVeinRarity;
    public static int oreMithrilMinY;
    public static int oreMithrilMaxY;
    public static int oreMithrilDropRateOre;
    
    public static boolean oreThoriumVeinSpawns;
    public static int oreThoriumVeinSize;
    public static int oreThoriumVeinRarity;
    public static int oreThoriumMinY;
    public static int oreThoriumMaxY;
    public static int oreThoriumDropRateOre;
    
    public static boolean oreTruesilverVeinSpawns;
    public static int oreTruesilverVeinSize;
    public static int oreTruesilverVeinRarity;
    public static int oreTruesilverMinY;
    public static int oreTruesilverMaxY;
    public static int oreTruesilverDropRateOre;
    
    public static boolean oreDarkIronVeinSpawns;
    public static int oreDarkIronVeinSize;
    public static int oreDarkIronVeinRarity;
    public static int oreDarkIronMinY;
    public static int oreDarkIronMaxY;
    public static int oreDarkIronDropRateOre;
    
    public static boolean oreFelIronVeinSpawns;
    public static int oreFelIronVeinSize;
    public static int oreFelIronVeinRarity;
    public static int oreFelIronMinY;
    public static int oreFelIronMaxY;
    public static int oreFelIronDropRateOre;
    
    public static int flowerFirebloomRarity;
    public static int flowerPurpleLotusRarity;
    
    public static float roughBlastingPowderStrength;
    public static float coarseBlastingPowderStrength;
    public static float solidBlastingPowderStrength;
    public static float denseBlastingPowderStrength;

    public static boolean harvestingOreOpensGui;

    @SidedProxy(clientSide="com.higgs.wom.proxy.ClientProxy", serverSide="com.higgs.wom.proxy.CommonProxy")
	public static CommonProxy proxy;
    
    @Instance
    public static HiggsWom instance = new HiggsWom();
    
    @EventHandler
    public void preInit(FMLPreInitializationEvent e)
    {
    	FMLLog.getLogger().info("Higg's World of Minecraft is initializing!");
    	
//    	network = NetworkRegistry.INSTANCE.newSimpleChannel("womChannel");
//
//    	network.registerMessage(WomPacketSyncPlayerData.Handler.class, WomPacketSyncPlayerData.class, 0, Side.CLIENT);
    	
    	config = new Configuration(e.getSuggestedConfigurationFile());
    	
    	syncConfig();

        PacketDispatcher.registerPackets();

    	proxy.preInit(e);
    }
        
    @EventHandler
    public void init(FMLInitializationEvent e)
    {
        ArrayList<WomRecipe> recipes = new ArrayList<WomRecipe>();

        WomRecipe recipe1 = new WomRecipe(new ItemStack(WomItems.itemCopperBar, 1), new int[]{1, 20, 40, 60}, WomRecipe.EnumRecipe.MINING, "Smelt Copper");
        WomRecipe recipe2 = new WomRecipe(new ItemStack(WomItems.itemTinBar, 1), new int[]{45, 50, 57, 75}, WomRecipe.EnumRecipe.MINING, "Smelt Tin");

        recipe1.addIngredients(new WomItemStack(new ItemStack(WomItems.itemCopperOre, 1)));
        recipe2.addIngredients(new WomItemStack(new ItemStack(WomItems.itemTinOre, 1)));

        recipes.add(recipe1);
        recipes.add(recipe2);

        for(WomRecipe r : recipes)
        {
            WomRecipe.addRecipe(r);
        }

        MinecraftForge.EVENT_BUS.register(new WomEventHandlerCommon());
        NetworkRegistry.INSTANCE.registerGuiHandler(HiggsWom.instance, new WomGuiHandler());

        proxy.init(e);
    }
        
    @EventHandler
    public void postInit(FMLPostInitializationEvent e)
    {
    	proxy.postInit(e);
    }

    public static void syncConfig() //gets called from preinit()
    {
        try
        {
            // Load configuration
            config.load();

            // Read properties from config
            Property verboseOutputProp = config.get(Configuration.CATEGORY_GENERAL, "verboseOutput", "true", "toggles verbosity of debug output.");

            Property oreCopperVeinSpawnsProp = config.get("copper", "oreCopperVeinSpawns", "true", "determines whether or not copper ore spawns in the world");
            Property oreCopperVeinSizeProp = config.get("copper", "oreCopperVeinSize", "7", "determines size of copper ore veins.");
            Property oreCopperVeinRarityProp = config.get("copper", "oreCopperVeinRarity", "16", "determines the rarity of copper ore veins (chance to spawn a vein in a particular chunk).");
            Property oreCopperVeinMinYProp = config.get("copper", "oreCopperVeinMinY", "24", "determines the minimum y value that copper ore veins can spawn at.");
            Property oreCopperVeinMaxYProp = config.get("copper", "oreCopperVeinMaxY", "80", "determines the maximum y value that copper ore veins can spawn at.");
            
            Property oreTinVeinSpawnsProp = config.get("tin", "oreTinVeinSpawns", "true", "determines whether or not tin ore spawns in the world");
            Property oreTinVeinSizeProp = config.get("tin", "oreTinVeinSize", "7", "determines size of tin ore veins.");
            Property oreTinVeinRarityProp = config.get("tin", "oreTinVeinRarity", "14", "determines the rarity of tin ore veins (chance to spawn a vein in a particular chunk).");
            Property oreTinVeinMinYProp = config.get("tin", "oreTinVeinMinY", "12", "determines the minimum y value that tin ore veins can spawn at.");
            Property oreTinVeinMaxYProp = config.get("tin", "oreTinVeinMaxY", "48", "determines the maximum y value that tin ore veins can spawn at.");
            
            Property oreSilverVeinSpawnsProp = config.get("silver", "oreSilverVeinSpawns", "true", "determines whether or not silver ore spawns in the world");
            Property oreSilverVeinSizeProp = config.get("silver", "oreSilverVeinSize", "7", "determines size of silver ore veins.");
            Property oreSilverVeinRarityProp = config.get("silver", "oreSilverVeinRarity", "10", "determines the rarity of silver ore veins (chance to spawn a vein in a particular chunk).");
            Property oreSilverVeinMinYProp = config.get("silver", "oreSilverVeinMinY", "8", "determines the minimum y value that silver ore veins can spawn at.");
            Property oreSilverVeinMaxYProp = config.get("silver", "oreSilverVeinMaxY", "32", "determines the maximum y value that silver ore veins can spawn at.");
            
            Property oreMithrilVeinSpawnsProp = config.get("mithril", "oreMithrilVeinSpawns", "true", "determines whether or not mithril ore spawns in the world");
            Property oreMithrilVeinSizeProp = config.get("mithril", "oreMithrilVeinSize", "6", "determines size of mithril ore veins.");
            Property oreMithrilVeinRarityProp = config.get("mithril", "oreMithrilVeinRarity", "10", "determines the rarity of mithril ore veins (chance to spawn a vein in a particular chunk).");
            Property oreMithrilVeinMinYProp = config.get("mithril", "oreMithrilVeinMinY", "4", "determines the minimum y value that mithril ore veins can spawn at.");
            Property oreMithrilVeinMaxYProp = config.get("mithril", "oreMithrilVeinMaxY", "28", "determines the maximum y value that mithril ore veins can spawn at.");
            
            Property oreThoriumVeinSpawnsProp = config.get("thorium", "oreThoriumVeinSpawns", "true", "determines whether or not thorium ore spawns in the world");
            Property oreThoriumVeinSizeProp = config.get("thorium", "oreThoriumVeinSize", "6", "determines size of thorium ore veins.");
            Property oreThoriumVeinRarityProp = config.get("thorium", "oreThoriumVeinRarity", "6", "determines the rarity of thorium ore veins (chance to spawn a vein in a particular chunk).");
            Property oreThoriumVeinMinYProp = config.get("thorium", "oreThoriumVeinMinY", "0", "determines the minimum y value that thorium ore veins can spawn at.");
            Property oreThoriumVeinMaxYProp = config.get("thorium", "oreThoriumVeinMaxY", "24", "determines the maximum y value that thorium ore veins can spawn at.");
            
            Property oreTruesilverVeinSpawnsProp = config.get("truesilver", "oreTruesilverVeinSpawns", "true", "determines whether or not truesilver ore spawns in the world");
            Property oreTruesilverVeinSizeProp = config.get("truesilver", "oreTruesilverVeinSize", "6", "determines size of truesilver ore veins.");
            Property oreTruesilverVeinRarityProp = config.get("truesilver", "oreTruesilverVeinRarity", "6", "determines the rarity of truesilver ore veins (chance to spawn a vein in a particular chunk).");
            Property oreTruesilverVeinMinYProp = config.get("truesilver", "oreTruesilverVeinMinY", "0", "determines the minimum y value that truesilver ore veins can spawn at.");
            Property oreTruesilverVeinMaxYProp = config.get("truesilver", "oreTruesilverVeinMaxY", "16", "determines the maximum y value that truesilver ore veins can spawn at.");
            
            Property oreDarkIronVeinSpawnsProp = config.get("darkiron", "oreDarkIronVeinSpawns", "true", "determines whether or not dark iron ore spawns in the world");
            Property oreDarkIronVeinSizeProp = config.get("darkiron", "oreDarkIronVeinSize", "5", "determines size of dark iron ore veins.");
            Property oreDarkIronVeinRarityProp = config.get("darkiron", "oreDarkIronVeinRarity", "5", "determines the rarity of dark iron ore veins (chance to spawn a vein in a particular chunk).");
            Property oreDarkIronVeinMinYProp = config.get("darkiron", "oreDarkIronVeinMinY", "0", "determines the minimum y value that dark iron ore veins can spawn at.");
            Property oreDarkIronVeinMaxYProp = config.get("darkiron", "oreDarkIronVeinMaxY", "6", "determines the maximum y value that dark iron ore veins can spawn at.");
            
            Property oreFelIronVeinSpawnsProp = config.get("feliron", "oreFelIronVeinSpawns", "true", "determines whether or not fel iron ore spawns in the world");
            Property oreFelIronVeinSizeProp = config.get("feliron", "oreFelIronVeinSize", "4", "determines size of fel iron ore veins.");
            Property oreFelIronVeinRarityProp = config.get("feliron", "oreFelIronVeinRarity", "5", "determines the rarity of fel iron ore veins (chance to spawn a vein in a particular chunk).");
            Property oreFelIronVeinMinYProp = config.get("feliron", "oreFelIronVeinMinY", "0", "determines the minimum y value that fel iron ore veins can spawn at.");
            Property oreFelIronVeinMaxYProp = config.get("feliron", "oreFelIronVeinMaxY", "6", "determines the maximum y value that fel iron ore veins can spawn at.");

            Property flowerFirebloomRarityProp = config.get("flowers", "flowerFirebloomRarity", "4", "determines the rarity of fireblooms.");
            Property flowerPurpleLotusRarityProp = config.get("flowers", "flowerPurpleLotusRarity", "4", "determines the rarity of purple lotuses.");

            Property roughBlastingPowderStrengthProp = config.get("blastingpowder", "roughBlastingPowderStrength", "0.25", "determines the strength of rough blasting powder.");
            Property coarseBlastingPowderStrengthProp = config.get("blastingpowder", "oreDarkIronVeinMaxY", "0.33", "determines the strength of coarse blasting powder.");
            Property solidBlastingPowderStrengthProp = config.get("blastingpowder", "oreDarkIronVeinMaxY", "0.50", "determines the strength of solid blasting powder.");
            Property denseBlastingPowderStrengthProp = config.get("blastingpowder", "oreDarkIronVeinMaxY", "0.75", "determines the strength of dense blasting powder.");
            
            Property harvestingOreOpensGuiProp = config.get("miscellaneous", "harvestingOreOpensGui", "false", "toggles whether harvesting ores opens an inventory with the contents instead of dropping them.");
            
            verboseOutput = verboseOutputProp.getBoolean();

            oreCopperVeinSpawns = oreCopperVeinSpawnsProp.getBoolean();
            oreCopperVeinSize = oreCopperVeinSizeProp.getInt();
            oreCopperVeinRarity = oreCopperVeinRarityProp.getInt();
            oreCopperMinY = oreCopperVeinMinYProp.getInt();
            oreCopperMaxY = oreCopperVeinMaxYProp.getInt();

            oreTinVeinSpawns = oreTinVeinSpawnsProp.getBoolean();
            oreTinVeinSize = oreTinVeinSizeProp.getInt();
            oreTinVeinRarity = oreTinVeinRarityProp.getInt();
            oreTinMinY = oreTinVeinMinYProp.getInt();
            oreTinMaxY = oreTinVeinMaxYProp.getInt();

            oreSilverVeinSpawns = oreSilverVeinSpawnsProp.getBoolean();
            oreSilverVeinSize = oreSilverVeinSizeProp.getInt();
            oreSilverVeinRarity = oreSilverVeinRarityProp.getInt();
            oreSilverMinY = oreSilverVeinMinYProp.getInt();
            oreSilverMaxY = oreSilverVeinMaxYProp.getInt();

            oreMithrilVeinSpawns = oreMithrilVeinSpawnsProp.getBoolean();
            oreMithrilVeinSize = oreMithrilVeinSizeProp.getInt();
            oreMithrilVeinRarity = oreMithrilVeinRarityProp.getInt();
            oreMithrilMinY = oreMithrilVeinMinYProp.getInt();
            oreMithrilMaxY = oreMithrilVeinMaxYProp.getInt();

            oreThoriumVeinSpawns = oreThoriumVeinSpawnsProp.getBoolean();
            oreThoriumVeinSize = oreThoriumVeinSizeProp.getInt();
            oreThoriumVeinRarity = oreThoriumVeinRarityProp.getInt();
            oreThoriumMinY = oreThoriumVeinMinYProp.getInt();
            oreThoriumMaxY = oreThoriumVeinMaxYProp.getInt();

            oreTruesilverVeinSpawns = oreTruesilverVeinSpawnsProp.getBoolean();
            oreTruesilverVeinSize = oreTruesilverVeinSizeProp.getInt();
            oreTruesilverVeinRarity = oreTruesilverVeinRarityProp.getInt();
            oreTruesilverMinY = oreTruesilverVeinMinYProp.getInt();
            oreTruesilverMaxY = oreTruesilverVeinMaxYProp.getInt();

            oreDarkIronVeinSpawns = oreDarkIronVeinSpawnsProp.getBoolean();
            oreDarkIronVeinSize = oreDarkIronVeinSizeProp.getInt();
            oreDarkIronVeinRarity = oreDarkIronVeinRarityProp.getInt();
            oreDarkIronMinY = oreDarkIronVeinMinYProp.getInt();
            oreDarkIronMaxY = oreDarkIronVeinMaxYProp.getInt();

            oreFelIronVeinSpawns = oreFelIronVeinSpawnsProp.getBoolean();
            oreFelIronVeinSize = oreFelIronVeinSizeProp.getInt();
            oreFelIronVeinRarity = oreFelIronVeinRarityProp.getInt();
            oreFelIronMinY = oreFelIronVeinMinYProp.getInt();
            oreFelIronMaxY = oreFelIronVeinMaxYProp.getInt();

            flowerFirebloomRarity = flowerFirebloomRarityProp.getInt();
            flowerPurpleLotusRarity = flowerPurpleLotusRarityProp.getInt();

            roughBlastingPowderStrength = Float.parseFloat(roughBlastingPowderStrengthProp.getString());
            coarseBlastingPowderStrength = Float.parseFloat(coarseBlastingPowderStrengthProp.getString());
            solidBlastingPowderStrength = Float.parseFloat(solidBlastingPowderStrengthProp.getString());
            denseBlastingPowderStrength = Float.parseFloat(denseBlastingPowderStrengthProp.getString());

            harvestingOreOpensGui = harvestingOreOpensGuiProp.getBoolean();

            if(verboseOutput)
            {
	            HiggsWom.log(Level.INFO, "Size of copper ore veins configured to be " + oreCopperVeinSize + (oreCopperVeinSpawns ? " and they DO spawn." : " but they DO NOT spawn."));
	            HiggsWom.log(Level.INFO, "Size of tin ore veins configured to be " + oreTinVeinSize + (oreTinVeinSpawns ? " and they DO spawn." : " but they DO NOT spawn."));
	            HiggsWom.log(Level.INFO, "Size of silver ore veins configured to be " + oreSilverVeinSize + (oreSilverVeinSpawns ? " and they DO spawn." : " but they DO NOT spawn."));
	            HiggsWom.log(Level.INFO, "Size of mithril ore veins configured to be " + oreMithrilVeinSize + (oreMithrilVeinSpawns ? " and they DO spawn." : " but they DO NOT spawn."));
	            HiggsWom.log(Level.INFO, "Size of thorium ore veins configured to be " + oreThoriumVeinSize + (oreThoriumVeinSpawns ? " and they DO spawn." : " but they DO NOT spawn."));
	            HiggsWom.log(Level.INFO, "Size of truesilver ore veins configured to be " + oreTruesilverVeinSize + (oreTruesilverVeinSpawns ? " and they DO spawn." : " but they DO NOT spawn."));
	            HiggsWom.log(Level.INFO, "Size of dark iron ore veins configured to be " + oreDarkIronVeinSize + (oreDarkIronVeinSpawns ? " and they DO spawn." : " but they DO NOT spawn."));
	            HiggsWom.log(Level.INFO, "Size of fel iron ore veins configured to be " + oreFelIronVeinSize + (oreFelIronVeinSpawns ? " and they DO spawn." : " but they DO NOT spawn."));
            }
        }
        catch(Exception e)
        {
        	FMLLog.getLogger().error("Could not read config.");
        	e.printStackTrace();
        }
        finally
        {
            // Save props to config IF config changed
            if(config.hasChanged())
            {
            	config.save();
            }
        }
    }

    public static CreativeTabs tabWom = new CreativeTabs("tabWom")
	{
		@Override
		public Item getTabIconItem()
		{
			return WomItems.itemPhilosophersStone;
		}
	};

    public EntityPlayer getPlayerEntity(MessageContext ctx)
    {
        return ctx.getServerHandler().playerEntity;
    }
    
    public static void log(Level level, String message)
    {
    	FMLLog.getLogger().log(level, message);
    }
}
