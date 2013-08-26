package com.xalior.minecraft.core;

import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

public class ItemMultiBlock extends ItemBlock
{
    public ItemMultiBlock(int par1)
    {
          super(par1);
          setHasSubtypes(true);
    }
   
    public String getUnlocalizedName(ItemStack itemstack)
    {
          String name = "";
          return getUnlocalizedName() + "." + String.format("%d",itemstack.getItemDamage());
    }
   
    public int getMetadata(int par1)
    {
          return par1;
    }
}