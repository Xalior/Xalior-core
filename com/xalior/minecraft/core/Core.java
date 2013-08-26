package com.xalior.minecraft.core;

import java.io.PrintStream;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.Configuration;
import net.minecraftforge.common.ForgeVersion;
import net.minecraftforge.common.MinecraftForge;
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
	
	/* Blocks */
	public static int blockXaloriumOreID;
	
	/* Items */
	public static int itemXaloriumDustID;
	public static int itemXaloriumPigIngotID;
	public static int itemXaloriumIngotID;
	
	/* Config Settings */
	public static int settingXaloriumOreFrequency;
	
	/* Our new Blocks */
	public static Block xaloriumOre;
	
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

		blockXaloriumOreID = config.getBlock("blockXaloriumOreID", 1300).getInt();
		itemXaloriumDustID = config.getItem("itemXaloriumDustID", 1301).getInt();
		itemXaloriumPigIngotID = config.getItem("itemXaloriumPigIngotID", 1302).getInt();
		itemXaloriumIngotID = config.getItem("itemXaloriumIngotID", 1303).getInt();
		
		settingXaloriumOreFrequency = config.get(config.CATEGORY_GENERAL, "xaloriumOreFrequency", 64).getInt();
		
		//yourVariableName = config.get(config.CATEGORY_GENERAL, "doesRandomBlockHurtYou", false).getBoolean(false);
		
		config.save();
	}
	
	@EventHandler // used in 1.6.2
	public void load(FMLInitializationEvent event) {
		
		log(String.format("Initialized (%s) ", this.getVersion()));
		/* Xalorium Ore */
		xaloriumOre = new BlockXaloriumOre(blockXaloriumOreID, true);
		LanguageRegistry.addName(xaloriumOre, "Xalorium Ore");
		MinecraftForge.setBlockHarvestLevel(xaloriumOre, "pickaxe", 1);
		GameRegistry.registerBlock(xaloriumOre, "xaloriumOre");
		
		/* And the worldgen for it... */
        GameRegistry.registerWorldGenerator(worldgenerator);
        
		
		/* Xalorium Dust ::FIXME:: find a grinder API! */
		//GameRegistry.registerItem(itemXaloriumDustID, "itemXaloriumDustID");
		/* Xalorium Pig Ingots */
        xaloriumPigIngot = new ItemXaloriumPigIngot(itemXaloriumPigIngotID);
        LanguageRegistry.addName(xaloriumPigIngot, "Xalorium Pig Ingot");
		GameRegistry.registerItem(xaloriumPigIngot, "itemXaloriumPigIngotID");
		/* Xalorium Ingots */
        xaloriumIngot = new ItemXaloriumIngot(itemXaloriumIngotID);
        LanguageRegistry.addName(xaloriumIngot, "Xalorium Ingot");
		GameRegistry.registerItem(xaloriumIngot, "itemXaloriumIngotID");
		/* 'Micro-optimisations' for stacks */
		ItemStack xaloriumPigIngotStack = new ItemStack(xaloriumPigIngot);
		/* Crafting Stuffs */
		GameRegistry.addShapelessRecipe(new ItemStack(xaloriumIngot),
				xaloriumPigIngotStack, xaloriumPigIngotStack);
		/* Smelting Stuffs */
		GameRegistry.addSmelting(blockXaloriumOreID, xaloriumPigIngotStack, 0.1F);
		/* Xalorium Ingots */
		//GameRegistry.registerItem(itemXaloriumDustID, "itemXaloriumDustID");
		
		
		proxy.registerRenderers();
	}
	
	@EventHandler // used in 1.6.2
	public void postInit(FMLPostInitializationEvent event) {
		// Stub Method
	}

	public static void log(String message) {
  	  System.out.printf("[%s]: %s\n", Core.name, message);
	}
}