package com.xalior.minecraft.core;

import java.io.PrintStream;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
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

@Mod(modid="xalior-core", name="Xalior's core worldgen+basics", version="0.0.0.<>")
@NetworkMod(clientSideRequired=true, serverSideRequired=false)
public class Core {
	public final static String modid = "xalior-core";
	public final static String name = "Xalior's core worldgen+basics";
	public final static String version = "0.0.0.<>";
	
	public final static Block xaloriumOre = new BlockXaloriumOre(1300, Material.rock);

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
		// Stub Method
	}
	
	@EventHandler // used in 1.6.2
	public void load(FMLInitializationEvent event) {
		Core.log(String.format("Initialized (%s) ", this.getVersion()));
		LanguageRegistry.addName(xaloriumOre, "Xalorium Ore");
		MinecraftForge.setBlockHarvestLevel(xaloriumOre, "pickaxe", 1);
		GameRegistry.registerBlock(xaloriumOre, "xaloriumOre");

        GameRegistry.registerWorldGenerator(worldgenerator);
        
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