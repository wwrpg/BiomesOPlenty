package biomesoplenty.common.eventhandler.misc;

import net.minecraft.block.Block;
import net.minecraft.block.BlockCrops;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenCactus;
import net.minecraftforge.event.entity.player.BonemealEvent;
import biomesoplenty.api.BOPBlockHelper;
import biomesoplenty.common.blocks.BlockBOPColorizedSapling;
import biomesoplenty.common.blocks.BlockBOPSapling;
import cpw.mods.fml.common.eventhandler.Event.Result;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class BonemealEventHandler
{
	@SubscribeEvent
	public void onUseBonemeal(BonemealEvent event)
	{
		World world = event.world;

		int x = event.x;
		int y = event.y;
		int z = event.z;

		Block block = event.block;
		int meta = event.world.getBlockMetadata(x, y, z);
		
		if (block == BOPBlockHelper.get("saplings"))
		{
			event.setResult(Result.ALLOW);

			if (!world.isRemote)
			{
				double chance = 0D;

				switch (meta)
				{
				case 3: // Magic Sapling
					chance = 0.1D;
					break;

				case 7: // Holy Sapling
					chance = 0.15D;
					break;

				case 9: // Origin Sapling
					chance = 1D;
					break;

				default:
					chance = 0.45D;
					break;
				}

				if (world.rand.nextFloat() < chance)
				{
					//TODO:											  growTree()
					((BlockBOPSapling)BOPBlockHelper.get("saplings")).func_149878_d(event.world, x, y, z, event.world.rand);
				}
			}
		}
		else if (block == BOPBlockHelper.get("colorizedSaplings"))
		{
			event.setResult(Result.ALLOW);

			if (!world.isRemote)
			{
				if (world.rand.nextFloat() < 0.45D)
				{
					//TODO:											  					growTree()
					((BlockBOPColorizedSapling)BOPBlockHelper.get("colorizedSaplings")).func_149878_d(event.world, x, y, z, event.world.rand);
				}
			}
		}
		else if (block == BOPBlockHelper.get("coral") && event.world.getBlockMetadata(x, y, z) == 3)
		{
			event.setResult(Result.ALLOW);

			/*TODO: FEATURE if (!event.world.isRemote)
			{
				if (event.world.rand.nextFloat() < 0.45D)
				{
					WorldGenKelp worldgenkelp = new WorldGenKelp(false);
					worldgenkelp.generate(event.world, event.world.rand, event.X, event.Y, event.Z);
				}
			}*/
		}
		else if (block == BOPBlockHelper.get("plants"))
		{
			event.setResult(Result.ALLOW);
			
			if (!event.world.isRemote)
			{
				switch (meta)
				{
				case 7:
					if (event.world.rand.nextFloat() < 0.45D)
					{
						world.func_147465_d(x, y, z, BOPBlockHelper.get("plants"), 10, 2);
						world.func_147465_d(x, y + 1, z, BOPBlockHelper.get("plants"), 9, 2);
					}
					break;
				
				case 12:
					if (world.rand.nextFloat() < 0.45D)
					{
						world.func_147449_b(x, y, z, Blocks.cactus);
					}
					break;
				}
			}
		}
		/*TODO: FEATURE else if (event.ID == Block.plantRed.blockID)
		{
			event.setResult(Result.ALLOW);

			if (!event.world.isRemote)
			{
				if (event.world.rand.nextFloat() < 0.45D)
				{
					WorldGenGiantFlowerRed worldgengiantflowerred = new WorldGenGiantFlowerRed();
					worldgengiantflowerred.generate(event.world, event.world.rand, event.X, event.Y - 1, event.Z);
				}
			}
		}
		else if (event.ID == Block.plantYellow.blockID)
		{
			event.setResult(Result.ALLOW);

			if (!event.world.isRemote)
			{
				if (event.world.rand.nextFloat() < 0.45D)
				{
					WorldGenGiantFlowerYellow worldgengiantfloweryellow = new WorldGenGiantFlowerYellow();
					worldgengiantfloweryellow.generate(event.world, event.world.rand, event.X, event.Y - 1, event.Z);
				}
			}
		}*/
		else if (block == BOPBlockHelper.get("turnip"))
		{
			if (event.world.getBlockMetadata(x, y, z) != 7)
			{
				if (!event.world.isRemote)
				{
					//TODO:									   fertilize
					((BlockCrops)BOPBlockHelper.get("turnip")).func_149863_m(event.world, x, y, z);
				}
			}
		}
		else if (block == BOPBlockHelper.get("grass") && event.world.getBlockMetadata(x, y, z) == 0)
		{
			int var14 = y + 1;

			for (int i1 = 0; i1 < 128; ++i1)
			{

				for (int i2 = 0; i2 < i1 / 16; ++i2)
				{
					x += event.world.rand.nextInt(3) - 1;
					var14 += (event.world.rand.nextInt(3) - 1) * event.world.rand.nextInt(3) / 2;
					z += event.world.rand.nextInt(3) - 1;
				}

				if (event.world.func_147439_a(x, var14, z).isAir(world, x, var14, z))
				{
					if (BOPBlockHelper.get("plants").func_149705_a(world, x, var14, z, 0, new ItemStack(BOPBlockHelper.get("plants"), 1, 4)))
					{
						event.setResult(Result.ALLOW);

						if (!event.world.isRemote)
						{
							//TODO:     setBlock()
							event.world.func_147465_d(x, var14, z, BOPBlockHelper.get("plants"), 4, 2);
						}
					}
				}
			}
		}
	}
}