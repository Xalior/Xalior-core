package com.xalior.minecraft.core;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;

public class XaloriumOre extends Block {

	public XaloriumOre(int par1, Material par2Material) {
		super(par1, par2Material);	
		setHardness(0.25F); // 33% harder than diamond
		setStepSound(Block.soundGlassFootstep);
		setUnlocalizedName("XaloriumOre");
		setCreativeTab(CreativeTabs.tabBlock);
	}

}
