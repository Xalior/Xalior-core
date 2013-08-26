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

public class BlockXaloriumOre extends Block {
    private boolean glowing;
    
	public BlockXaloriumOre(int par1, Material par2Material) {
		super(par1, par2Material);	

		setHardness(1.00F);
		setResistance(30.00F);
		setLightValue(1F);
		setStepSound(Block.soundGlassFootstep);
		setUnlocalizedName("xaloriumOre");
		setCreativeTab(CreativeTabs.tabBlock);
	}

    /**
      * Returns the usual quantity dropped by the block plus a bonus of 1 to 'i' (inclusive).
      */
    public int quantityDroppedWithBonus(int par1, Random par2Random)
    {
        return this.quantityDropped(par2Random) + par2Random.nextInt(par1 + 1);
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
