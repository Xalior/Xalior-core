package com.xalior.minecraft.core;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;

public class BlockXaloriumLight extends MultiBlock {    
	public BlockXaloriumLight(int par1, Material par2Material) {
		super(par1, par2Material);	
		varientCount = 16;
		setHardness(2.00F);
		setResistance(30.00F);
		setLightValue(1F);
		setStepSound(Block.soundGlassFootstep);
		setUnlocalizedName("xaloriumLight");
		setCreativeTab(CreativeTabs.tabDecorations);
	}
}
