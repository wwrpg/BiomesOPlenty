package biomesoplenty.common.fluids.blocks;

import javax.swing.Icon;

import biomesoplenty.BiomesOPlenty;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import net.minecraftforge.fluids.BlockFluidClassic;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockPoisonFluid extends BlockFluidClassic
{
	public static IIcon liquidPoisonStillIcon;
	public static IIcon liquidPoisonFlowingIcon;

	public BlockPoisonFluid()
	{
		//TODO:											  water
		super(FluidRegistry.getFluid("poison"), Material.field_151586_h);

		this.quantaPerBlock = 4;
		//TODO: setLightOpacity()
		this.func_149713_g(3);
	}

	@Override
	//TODO:		onEntityCollidedWithBlock()
	public void func_149670_a(World world, int x, int y, int z, Entity entity)
	{
		int meta = world.getBlockMetadata(x, y, z);

		if (entity instanceof EntityLivingBase)
		{
			((EntityLivingBase)entity).addPotionEffect(new PotionEffect(Potion.poison.id, 100));
			((EntityLivingBase)entity).addPotionEffect(new PotionEffect(Potion.hunger.id, 100));
		}
	}

	@Override
	//TODO:		registerIcons()
	public void func_149651_a(IIconRegister iconRegister)
	{
		liquidPoisonStillIcon = iconRegister.registerIcon("biomesoplenty:liquid_poison_still");
		liquidPoisonFlowingIcon = iconRegister.registerIcon("biomesoplenty:liquid_poison_flowing");
	}

	@Override
	//TODO:		 getIcon()
	public IIcon func_149691_a(int side, int meta)
	{
		return side != 0 && side != 1 ? liquidPoisonFlowingIcon : liquidPoisonStillIcon;
	}
}
