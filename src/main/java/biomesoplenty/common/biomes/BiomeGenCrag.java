package biomesoplenty.common.biomes;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import biomesoplenty.api.BOPBlockHelper;
import biomesoplenty.common.configuration.BOPConfigurationMisc;

public class BiomeGenCrag extends BOPBiome
{
	private static final Height biomeHeight = new Height(2.0F, 3.0F);
	
	public BiomeGenCrag(int id)
	{
		super(id);
		
        //TODO: setHeight()
        this.func_150570_a(biomeHeight);
        //TODO:	setColor()
        this.setColor(5209457);
        this.setTemperatureRainfall(2.0F, 0.0F);
		
		this.spawnableCreatureList.clear();
		this.spawnableWaterCreatureList.clear();
		
		this.topBlock = BOPBlockHelper.get("cragRock");
		this.fillerBlock = BOPBlockHelper.get("cragRock");
		this.theBiomeDecorator.treesPerChunk = -999;


		this.waterColorMultiplier = 944693;
	}
	
	@Override
	public void decorate(World world, Random random, int chunkX, int chunkZ)
	{
		super.decorate(world, random, chunkX, chunkZ);
		int var5 = 12 + random.nextInt(6);

		for (int i = 0; i < var5; ++i)
		{
			int x = chunkX + random.nextInt(16);
			int y = random.nextInt(28) + 4;
			int z = chunkZ + random.nextInt(16);

			//TODO:				getBlock()
			Block block = world.func_147439_a(x, y, z);

			if (block != null && block.isReplaceableOreGen(world, x, y, z, Blocks.stone))
			{
				//TODO:	setBlock()
				world.func_147465_d(x, y, z, BOPBlockHelper.get("gemOre"), 12, 2);
			}
		}
	}
	
	 @Override
	 public int getSkyColorByTemp(float par1)
	 {
		 if (BOPConfigurationMisc.skyColors) return 4944498;
		 else return super.getSkyColorByTemp(par1);
	 }
	
	/**
	 * Fog Color
	 */
	/*
	@Override
	public int getFogColour()
	{
		return 10514245;
	}
	*/
	 
	/*
    @Override
    public float getFogCloseness()
    {
        // TODO Auto-generated method stub
        return 1.0F;
    }
    */
}
