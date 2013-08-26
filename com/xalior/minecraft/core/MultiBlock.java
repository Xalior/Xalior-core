package com.xalior.minecraft.core;

import java.util.List;
import java.util.Random;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import net.minecraft.block.material.Material;

public class MultiBlock extends Block {
	protected static int varientCount;
	public MultiBlock (int id, Material material) {
		super(id, material);
	}
    
    public int damageDropped(int par1)
    {
    	return par1;
    }
    
	@SideOnly(Side.CLIENT)
	private Icon[] icons;

	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister par1IconRegister)
	{
		icons = new Icon[varientCount];

		for(int i = 0; i < varientCount; i++)
		{
			icons[i] = par1IconRegister.registerIcon(Core.modid + ":" + (this.getUnlocalizedName().substring(5)) + i);
		}
	}

	@SideOnly(Side.CLIENT)
	public Icon getIcon(int par1, int par2)
	{
		if(par2<varientCount)
			return icons[par2];
		System.out.println("Invalid metadata for " + this.getUnlocalizedName());
			return icons[0];
	}

	@SideOnly(Side.CLIENT)
	public void getSubBlocks(int par1, CreativeTabs par2CreativeTabs, List par3List)
	{
        for(int i = 0; i < varientCount; i++)
        {
               par3List.add(new ItemStack(par1, 1, i));
        }
	}
}