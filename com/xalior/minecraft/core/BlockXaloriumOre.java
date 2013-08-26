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
    
	public BlockXaloriumOre(int par1, boolean par2) {
		super(par1, Material.rock);

        if (par2)
        {
            this.setTickRandomly(true);
        }

        this.glowing = par2;
		setHardness(1.00F);
		setResistance(15.00F);
		setLightValue(0.75F);
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
     * The redstone ore glows.
     */
    private void glow(World par1World, int par2, int par3, int par4)
    {
        this.sparkle(par1World, par2, par3, par4);

        if (this.blockID == Block.oreRedstone.blockID)
        {
            par1World.setBlock(par2, par3, par4, Block.oreRedstoneGlowing.blockID);
        }
    }

    /**
     * Ticks the block if it's been scheduled
     */
    public void updateTick(World par1World, int par2, int par3, int par4, Random par5Random)
    {
        if (this.blockID == Block.oreRedstoneGlowing.blockID)
        {
            par1World.setBlock(par2, par3, par4, Block.oreRedstone.blockID);
        }
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

    public int getRenderBlockPass()
    {
		return 1;
    	
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
    private final String[] particleList = {"largesmoke", "smoke", "cloud","suspended", "depthsuspend", "townaura"};
    
    /**
     * The xalorium ore, with a chance of sparkles.
     */
    private void sparkle(World par1World, int par2, int par3, int par4)
    {
        Random random = par1World.rand;

        if(random.nextBoolean())
        	par1World.spawnParticle("cloud",
            		(double)((float)par2 + random.nextFloat()),
             		(double)((float)par3 + random.nextFloat()),
               		(double)((float)par4 + random.nextFloat()),
               		0.0D, 0.0D, 0.0D);
    }
    
    public void registerIcons(IconRegister par1IconRegister)
    {
        this.blockIcon = par1IconRegister.registerIcon(
        		Core.modid + ":" + (this.getUnlocalizedName().substring(5)));
    }
    
    
}
