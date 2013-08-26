package com.xalior.minecraft.core;

import java.io.PrintStream;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.Configuration;
import net.minecraftforge.common.ForgeVersion;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.oredict.OreDictionary;
import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;

@Mod(modid="xalior-core", name="Xalior's core worldgen+basics", version="0.0.2.<>")
@NetworkMod(clientSideRequired=true, serverSideRequired=false)
public class Core {
	public final static String modid = "xalior-core";
	public final static String name = "Xalior's core worldgen+basics";
	public final static String version = "0.0.2.<>";
	
	private static final String[] colours = { 
		"Clear", "Orange", "Magenta", "Light Blue",
		"Yellow", "Light Green", "Pink", "Dark Grey",
		"Light Grey", "Cyan", "Purple", "Blue",
		"Brown", "Green", "Red", "Black"
	};
	
	/* Blocks */
	public static int blockXaloriumOreID;
	public static int blockXaloriumID;
	public static int blockXaloriumLightID;
	
	/* Items */
	public static int itemXaloriumDustID;
	public static int itemXaloriumPigIngotID;
	public static int itemXaloriumIngotID;
	
	/* Config Settings */	
	public static boolean enableWorldGen;
	
	public static int settingXaloriumOreFrequency;
	
	/* Our new Blocks */
	public static Block xaloriumOre;
	public static Block xaloriumBlock;
	public static Block xaloriumLight;
	
	/* Our new Items */
	public static Item xaloriumDust;
	public static Item xaloriumPigIngot;
	public static Item xaloriumIngot;

	public String getVersion() {
		return(version);
	}
	
    WorldGenerator worldgenerator = new WorldGenerator();
	// The instance of your mod that Forge uses.
	@Instance("xalior-core")
	public static Core instance;
	
	// Says where the client and server 'proxy' code is loaded.
	@SidedProxy(clientSide="com.xalior.minecraft.core.client.ClientProxy",
				serverSide="com.xalior.minecraft.core.CommonProxy")
	public static CommonProxy proxy;
	
	@EventHandler // used in 1.6.2
	public void preInit(FMLPreInitializationEvent event) {
		Configuration config = new Configuration(event.getSuggestedConfigurationFile());

		config.load();
		
		enableWorldGen = config.get(config.CATEGORY_GENERAL, "enableWorldGen", true).getBoolean(true);	
		
		blockXaloriumOreID = config.getBlock("blockXaloriumOreID", 1300).getInt();
		itemXaloriumDustID = config.getItem("itemXaloriumDustID", 1301).getInt();
		itemXaloriumPigIngotID = config.getItem("itemXaloriumPigIngotID", 1302).getInt();
		itemXaloriumIngotID = config.getItem("itemXaloriumIngotID", 1303).getInt();
		blockXaloriumID = config.getBlock("blockXaloriumID", 1304).getInt();
		blockXaloriumLightID = config.getBlock("blockXaloriumLightID", 1302).getInt();
		
		settingXaloriumOreFrequency = config.get(config.CATEGORY_GENERAL, "xaloriumOreFrequency", 64).getInt();
		
		//yourVariableName = config.get(config.CATEGORY_GENERAL, "doesRandomBlockHurtYou", false).getBoolean(false);
		
		config.save();
	}
	
	@EventHandler // used in 1.6.2
	public void load(FMLInitializationEvent event) {
		log(String.format("Initialized (%s) ", this.getVersion()));
		addObjects();
        addNames();
		addRecipes();
		
		/* And the WorldGeneration for it... */
		if(enableWorldGen)
			GameRegistry.registerWorldGenerator(worldgenerator);
        
		proxy.registerRenderers();
	}
	
	public static void addNames()
	{
	
		LanguageRegistry.addName(xaloriumOre, "Xalorium Ore");
	    LanguageRegistry.addName(xaloriumPigIngot, "Xalorium Pig Ingot");
	    LanguageRegistry.addName(xaloriumIngot, "Xalorium Ingot");
		LanguageRegistry.addName(xaloriumBlock, "Xalorium Block");
		for(int stackCounter=0;stackCounter<16;stackCounter++)
		{	
			ItemStack newItem = new ItemStack(xaloriumLight, 1, stackCounter);
			LanguageRegistry.addName(newItem, 
					String.format("%s %s", colours[newItem.getItemDamage()], "Xalorium Light"));
		}
	}

	public static void addObjects()
	{
		/* Xalorium Ore */
		xaloriumOre = new BlockXaloriumOre(blockXaloriumOreID, true);
		MinecraftForge.setBlockHarvestLevel(xaloriumOre, "pickaxe", 1);
		GameRegistry.registerBlock(xaloriumOre, "xaloriumOre");
		
		/* Xalorium Dust ::FIXME:: find a grinder API! */
		//GameRegistry.registerItem(itemXaloriumDustID, "itemXaloriumDustID");
		
		/* Xalorium Pig Ingots */
	    xaloriumPigIngot = new ItemXaloriumPigIngot(itemXaloriumPigIngotID);
		GameRegistry.registerItem(xaloriumPigIngot, "itemXaloriumPigIngotID");
		
		/* Xalorium Ingots */
	    xaloriumIngot = new ItemXaloriumIngot(itemXaloriumIngotID);
		GameRegistry.registerItem(xaloriumIngot, "itemXaloriumIngotID");
		
		/* Xalorium Blocks */
		xaloriumBlock = new BlockXalorium(blockXaloriumID, Material.rock);
		MinecraftForge.setBlockHarvestLevel(xaloriumBlock, "pickaxe", 1);
		GameRegistry.registerBlock(xaloriumBlock, "xaloriumBlock");
		
		/* Xalorium Lights */
		xaloriumLight = new BlockXaloriumLight(blockXaloriumLightID, Material.rock);
		MinecraftForge.setBlockHarvestLevel(xaloriumBlock, "pickaxe", 1);
        GameRegistry.registerBlock(xaloriumLight, ItemMultiBlock.class, modid + (xaloriumLight.getUnlocalizedName().substring(5)));
	}

	public static void addRecipes()
	{
		
		/*
		 *  'Micro-optimisations' for stacks
		 */
		ItemStack xaloriumPigIngotStack = new ItemStack(xaloriumPigIngot);
		ItemStack xaloriumIngotStack = new ItemStack(xaloriumIngot);
		
	    OreDictionary.registerOre("ingotXalorium", new ItemStack(xaloriumIngot));
	    
	    /*
	     * Crafting
	     */
		GameRegistry.addShapelessRecipe(new ItemStack(xaloriumIngot),
				xaloriumPigIngotStack, xaloriumPigIngotStack);
	    GameRegistry.addRecipe(new ItemStack(xaloriumBlock), "xxx", "xxx","xxx",
	            'x', xaloriumIngotStack);
		/* 
		 * Smelting
		 */
		GameRegistry.addSmelting(blockXaloriumOreID, xaloriumPigIngotStack, 0.1F);
	}
	
	@EventHandler // used in 1.6.2
	public void postInit(FMLPostInitializationEvent event) {
		// Stub Method
	}

	public static void log(String message) {
  	  System.out.printf("[%s]: %s\n", Core.name, message);
	}
}