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

public class BlockXalorium extends Block {
    private boolean glowing;
    
	public BlockXalorium(int par1, Material par2Material) {
		super(par1, par2Material);	

		setHardness(2.00F);
		setResistance(30.00F);
		setLightValue(0.87F);
		setStepSound(Block.soundGlassFootstep);
		setUnlocalizedName("xaloriumBlock");
		setCreativeTab(CreativeTabs.tabBlock);
	}

    /**
     * Is this block (a) opaque and (b) a full 1m cube?  This determines whether or not to render the shared face of two
     * adjacent blocks and also whether the player can attach torches, redstone wire, etc to this block.
     */
    public boolean isOpaqueCube()
    {
        return false;
    }

    /**
     * If this block doesn't render as an ordinary block it will return False (examples: signs, buttons, stairs, etc)
     */
    public boolean renderAsNormalBlock()
    {
        return false;
    }
    
    @SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister par1IconRegister)
    {
        this.blockIcon = par1IconRegister.registerIcon(
        		Core.modid + ":" + (this.getUnlocalizedName().substring(5)));
    }
}
