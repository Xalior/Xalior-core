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

		this.glowing=true;

        if (this.glowing)
            this.setTickRandomly(true);
		setHardness(1.00F);
		setResistance(30.00F);
		setStepSound(Block.soundGlassFootstep);
		setUnlocalizedName("xaloriumOre");
		setCreativeTab(CreativeTabs.tabBlock);
	}

    /**
     * How many world ticks before ticking
     */
    public int tickRate(World par1World)
    {
        return 30;
    }

    /**
     * Called when the block is clicked by a player. Args: x, y, z, entityPlayer
     */
   public void onBlockClicked(World par1World, int par2, int par3, int par4, EntityPlayer par5EntityPlayer)
   {
	   this.glow(par1World, par2, par3, par4);
       super.onBlockClicked(par1World, par2, par3, par4, par5EntityPlayer);
   }

   /**
     * Called whenever an entity is walking on top of this block. Args: world, x, y, z, entity
	 */
   public void onEntityWalking(World par1World, int par2, int par3, int par4, Entity par5Entity)
   {
       this.glow(par1World, par2, par3, par4);
       super.onEntityWalking(par1World, par2, par3, par4, par5Entity);
    }

    /**
      * Called upon block activation (right click on the block.)
      */
    public boolean onBlockActivated(World par1World, int par2, int par3, int par4, EntityPlayer par5EntityPlayer, int par6, float par7, float par8, float par9)
    {
        this.glow(par1World, par2, par3, par4);
        return super.onBlockActivated(par1World, par2, par3, par4, par5EntityPlayer, par6, par7, par8, par9);
    }

    /**
      * The xalorium ore glows.
      */
    private void glow(World par1World, int par2, int par3, int par4)
    {
        this.sparkle(par1World, par2, par3, par4);

        if (!this.glowing)
        	this.glowing = true;
    }

    /**
      * Ticks the block if it's been scheduled
      */
	public void updateTick(World par1World, int par2, int par3, int par4, Random par5Random)
	{
	    if(this.glowing)
	    	this.glowing = false;
	}
    /**
      * Returns the usual quantity dropped by the block plus a bonus of 1 to 'i' (inclusive).
      */
    public int quantityDroppedWithBonus(int par1, Random par2Random)
    {
        return this.quantityDropped(par2Random) + par2Random.nextInt(par1 + 1);
    }

    @SideOnly(Side.CLIENT)
    /**
      * A randomly called display update to be able to add particles or other items for display
      */
    public void randomDisplayTick(World par1World, int par2, int par3, int par4, Random par5Random)
    {
        if (this.glowing)
        {
            this.sparkle(par1World, par2, par3, par4);
        }
    }

    /**
      * The xalorium ore sparkles.
      */
    private void sparkle(World par1World, int par2, int par3, int par4)
    {
        Random random = par1World.rand;
        double d0 = 0.0625D;

        for (int l = 0; l < 6; ++l)
        {
            double d1 = (double)((float)par2 + random.nextFloat());
            double d2 = (double)((float)par3 + random.nextFloat());
            double d3 = (double)((float)par4 + random.nextFloat());

            if (l == 0 && !par1World.isBlockOpaqueCube(par2, par3 + 1, par4))
            {
                d2 = (double)(par3 + 1) + d0;
            }

            if (l == 1 && !par1World.isBlockOpaqueCube(par2, par3 - 1, par4))
            {
                d2 = (double)(par3 + 0) - d0;
            }

            if (l == 2 && !par1World.isBlockOpaqueCube(par2, par3, par4 + 1))
            {
                d3 = (double)(par4 + 1) + d0;
            }

            if (l == 3 && !par1World.isBlockOpaqueCube(par2, par3, par4 - 1))
            {
                d3 = (double)(par4 + 0) - d0;
            }

            if (l == 4 && !par1World.isBlockOpaqueCube(par2 + 1, par3, par4))
            {
                d1 = (double)(par2 + 1) + d0;
            }

            if (l == 5 && !par1World.isBlockOpaqueCube(par2 - 1, par3, par4))
            {
                d1 = (double)(par2 + 0) - d0;
            }
	    }
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
