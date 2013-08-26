package com.xalior.minecraft.core;

import java.util.Random;

import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.world.World;

public class ItemXaloriumIngot extends Item {
    private boolean glowing;
    
	public ItemXaloriumIngot(int par1) {
		super(par1);	

		setUnlocalizedName("xaloriumIngot");
		setCreativeTab(CreativeTabs.tabMaterials);
	}
	
    @SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister par1IconRegister)
    {
        this.itemIcon = par1IconRegister.registerIcon(
        		Core.modid + ":" + (this.getUnlocalizedName().substring(5))
        );
    }
    
    
}
