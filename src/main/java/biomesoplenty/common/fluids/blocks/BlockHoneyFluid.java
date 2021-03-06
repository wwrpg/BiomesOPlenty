package biomesoplenty.common.fluids.blocks;

import biomesoplenty.BiomesOPlenty;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import net.minecraftforge.fluids.BlockFluidFinite;
import net.minecraftforge.fluids.FluidRegistry;

public class BlockHoneyFluid extends BlockFluidFinite
{
	public static IIcon honeyStillIcon;
	public static IIcon honeyFlowingIcon;

	public BlockHoneyFluid()
	{
		//TODO:											  water
		super(FluidRegistry.getFluid("honey"), Material.field_151586_h);

		//TODO: setLightOpacity()
		this.func_149713_g(1);
	}
	
	@Override
	//TODO:		onEntityCollidedWithBlock()
	public void func_149670_a(World world, int x, int y, int z, Entity entity)
	{
		int meta = world.getBlockMetadata(x, y, z);

		if (entity instanceof EntityLivingBase)
		{
			((EntityLivingBase)entity).addPotionEffect(new PotionEffect(Potion.moveSlowdown.id, 200, 2));
		}
	}

	@Override
	//TODO:		registerIcons()
	public void func_149651_a(IIconRegister iconRegister)
	{
		honeyStillIcon = iconRegister.registerIcon("biomesoplenty:honey_still");
		honeyFlowingIcon = iconRegister.registerIcon("biomesoplenty:honey_flowing");
	}

	@Override
	//TODO:		 getIcon()
	public IIcon func_149691_a(int side, int meta)
	{
		return side != 0 && side != 1 ? honeyFlowingIcon : honeyStillIcon;
	}
}
