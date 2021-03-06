package biomesoplenty.client.render.blocks;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.EntityRenderer;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.init.Blocks;
import net.minecraft.world.IBlockAccess;
import biomesoplenty.api.BOPBlockHelper;
import biomesoplenty.client.render.RenderUtils;
import biomesoplenty.common.blocks.BlockBOPPlant;
import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;

public class PlantsRenderer implements ISimpleBlockRenderingHandler
{
	@Override
	public void renderInventoryBlock(Block block, int metadata, int modelID, RenderBlocks renderer)
	{
		// Doesn't render in inventory
	}

	@Override
	public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer)
	{
		if (modelId == RenderUtils.plantsModel)
		{
			int meta = world.getBlockMetadata(x, y, z);
			if (meta < 5)
				return renderCrossedSquares(block, x, y, z, renderer, true);
			if (meta == 5)
				//TODO:			renderCrossedSquares
				return renderer.func_147746_l(block, x, y, z);
			if (meta == 6)
				return renderBlockCrops(block, x, y, z, renderer);
			if (meta == 7)
				//TODO: 		renderBlockCrops
				return renderer.func_147796_n(block, x, y, z);
			if (meta == 8)
				//TODO: 		renderBlockCrops
				return renderer.func_147796_n(block, x, y, z);
			if (meta == 9)
				//TODO: 		renderBlockCrops
				return renderer.func_147796_n(block, x, y, z);
			if (meta == 10)
				//TODO: 		renderBlockCrops
				return renderer.func_147796_n(block, x, y, z);
			if (meta == 11)
				//TODO: 		renderBlockCrops
				return renderer.func_147796_n(block, x, y, z);
			if (meta == 12)
				return renderCrossedSquares(block, x, y, z, renderer, true);
			if (meta == 13)
				//TODO: 		renderBlockCrops
				return renderer.func_147796_n(block, x, y, z);
			if (meta == 14)
			{
				return renderCrossedSquares(block, x, y, z, renderer, false);
			}
			if (meta == 15)
				return renderCrossedSquares(block, x, y, z, renderer, true);
		}
		return true;
	}

	@Override
	public boolean shouldRender3DInInventory(int modelId)
	{
		return false;
	}

	@Override
	public int getRenderId()
	{
		return RenderUtils.plantsModel;
	}

	private boolean renderBlockCrops(Block block, int x, int y, int z, RenderBlocks renderer)
	{
		Tessellator tessellator = Tessellator.instance;
		//TODO:						  blockAccess
		IBlockAccess world = renderer.field_147845_a;
		//TODO:								getMixedBrightnessForBlock()
		tessellator.setBrightness(block.func_149677_c(world, x, y, z));
		tessellator.setColorOpaque_F(1.0F, 1.0F, 1.0F);

		double d0 = x;
		double d1 = y;
		double d2 = z;

		long i1 = x * 3129871 ^ z * 116129781L ^ y;

		i1 = i1 * i1 * 42317861L + i1 * 11L;
		d0 += ((i1 >> 16 & 15L) / 15.0F - 0.5D) * 0.125D;
		d2 += ((i1 >> 24 & 15L) / 15.0F - 0.5D) * 0.125D;

		//TODO:	 renderCropBlocksImpl()
		renderer.func_147795_a(block, world.getBlockMetadata(x, y, z), d0, y - 0.0625F, d2);
		return true;
	}

	private boolean renderCrossedSquares(Block block, int x, int y, int z, RenderBlocks renderer, boolean colourMultiply)
	{
		Tessellator tessellator = Tessellator.instance;
		//TODO:						  blockAccess
		IBlockAccess world = renderer.field_147845_a;
		//TODO:								getMixedBrightnessForBlock()
		tessellator.setBrightness(block.func_149677_c(world, x, y, z));
		float f = 1.0F;
		//TODO:			  colorMultiplier()
		int l = block.func_149720_d(world, x, y, z);
		float f1 = (l >> 16 & 255) / 255.0F;
		float f2 = (l >> 8 & 255) / 255.0F;
		float f3 = (l & 255) / 255.0F;

		if (EntityRenderer.anaglyphEnable)
		{
			float f4 = (f1 * 30.0F + f2 * 59.0F + f3 * 11.0F) / 100.0F;
			float f5 = (f1 * 30.0F + f2 * 70.0F) / 100.0F;
			float f6 = (f1 * 30.0F + f3 * 70.0F) / 100.0F;
			f1 = f4;
			f2 = f5;
			f3 = f6;
		}
		
		if (!colourMultiply)
		{
			f1 = f;
			f2 = f;
			f3 = f;
		}

		tessellator.setColorOpaque_F(f * f1, f * f2, f * f3);
		double d0 = x;
		double d1 = y;
		double d2 = z;

		long i1 = x * 3129871 ^ z * 116129781L ^ y;
		
		int meta = world.getBlockMetadata(x, y, z);

		if (meta == 15)
		{
			i1 = i1 * i1 * 42317861L + i1 * 11L;
			d0 += ((i1 >> 16 & 15L) / 15.0F - 0.5D) * 0.2D;
			d1 -= ((i1 >> 20 & 15L) / 15.0F - 1.0D) * 0.4D;
			d2 += ((i1 >> 24 & 15L) / 15.0F - 0.5D) * 0.2D;
		}
		else
		{
			i1 = i1 * i1 * 42317861L + i1 * 11L;
			d0 += ((i1 >> 16 & 15L) / 15.0F - 0.5D) * 0.5D;
			d1 += ((i1 >> 20 & 15L) / 15.0F - 1.0D) * 0.2D;
			d2 += ((i1 >> 24 & 15L) / 15.0F - 0.5D) * 0.5D;
		}
		
		if (meta == 14)
		{
			//TODO:	 drawCrossedSquares()
			renderer.func_147765_a(block.func_149691_a(0, world.getBlockMetadata(x, y, z)), d0, d1, d2, 1.0F);
			//TODO:	 drawCrossedSquares()
			renderer.func_147765_a(((BlockBOPPlant)BOPBlockHelper.get("plants")).reedbottom, d0, d1 - 1, d2, 1.0F);
		}
		else
		{
			//TODO:	 drawCrossedSquares()
			renderer.func_147765_a(block.func_149691_a(0, world.getBlockMetadata(x, y, z)), d0, d1, d2, 1.0F);
		}
		return true;
	}
}
