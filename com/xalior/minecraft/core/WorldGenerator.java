package com.xalior.minecraft.core;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenMinable;
import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.common.IWorldGenerator;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.Init;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;

public class WorldGenerator implements IWorldGenerator { 
		@Override
        public void generate(Random random, int chunkX, int chunkZ, World world,
                        IChunkProvider chunkGenerator, IChunkProvider chunkProvider) {
                switch (world.provider.dimensionId) {
                case -1:
                        generateNether();
                        break;
                case 0:
                        BiomeGenBase b = world.getBiomeGenForCoords(chunkX, chunkZ);
             
                        if(b.biomeName.contains("Hill") ||
                           b.biomeName.contains("Mountain")) {
                            // Begin hilly generation!
                            generateSurface(world, random, chunkX * 16, chunkZ * 16);
                        }
                        break;
                case 1:
                        generateEnd();
                        break;
                }
        }
 
        public void generateSurface(World world, Random rand, int chunkX, int chunkZ) {
                for (int i = 0; i < Core.settingXaloriumOreFrequency; i++) {
                        int randPosX = chunkX + rand.nextInt(16);
                        int randPosY = 64+rand.nextInt(64); 
                        int randPosZ = chunkZ + rand.nextInt(16);
                        (new WorldGenMinable(Core.xaloriumOre.blockID, 3+rand.nextInt(13))).generate(world, rand, randPosX, randPosY, randPosZ);
                }
        }
 
        public void generateNether() {
        }
 
        public void generateEnd() {
        }
}