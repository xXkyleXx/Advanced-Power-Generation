package com.xXkyleXx.apg.fluids;

import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fluids.Fluid;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockFluidSteam extends Fluidapg {
    
	@SideOnly(Side.CLIENT)
    public IIcon stillIcon;
    @SideOnly(Side.CLIENT)
    public IIcon flowingIcon;
   
    public BlockFluidSteam(Fluid fluid, Material material) {
            super(fluid, material);
            
    }
   
    @Override
    public IIcon getIcon(int side, int meta) {
            return (side == 0 || side == 1)? stillIcon : flowingIcon;
    }
   
    @SideOnly(Side.CLIENT)
    @Override
    public void registerBlockIcons(IIconRegister iIconregister) {
            stillIcon = iIconregister.registerIcon("apg:steam_Still");
            flowingIcon = iIconregister.registerIcon("apg:steam_Flowing");
    }
   
    @Override
    public boolean canDisplace(IBlockAccess world, int x, int y, int z) {
            if (world.getBlock(x,  y,  z).getMaterial().isLiquid()) return false;
            return super.canDisplace(world, x, y, z);
    }
   
    @Override
    public boolean displaceIfPossible(World world, int x, int y, int z) {
            if (world.getBlock(x,  y,  z).getMaterial().isLiquid()) return false;
            return super.displaceIfPossible(world, x, y, z);
    }
    

}
