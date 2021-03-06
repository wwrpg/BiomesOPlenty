package biomesoplenty.common.biomes;

import java.util.HashMap;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.entity.monster.EntitySpider;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraft.world.gen.feature.WorldGenerator;
import biomesoplenty.api.BOPBlockHelper;
import biomesoplenty.common.configuration.BOPConfigurationMisc;
import biomesoplenty.common.world.features.WorldGenBOPTallGrass;
import biomesoplenty.common.world.features.trees.WorldGenBOPSwampTree;
import biomesoplenty.common.world.features.trees.WorldGenDeadTree1;

public class BiomeGenSilkglades extends BOPBiome
{
    private static final Height biomeHeight = new Height(0.1F, 0.2F);

    public BiomeGenSilkglades(int id)
    {
        super(id);
        
        //TODO: setHeight()
        this.func_150570_a(biomeHeight);
        //TODO: setColor()
        this.setColor(13420973);
        this.setTemperatureRainfall(0.5F, 0.9F);

        this.spawnableWaterCreatureList.clear();
        this.spawnableCreatureList.clear();

        this.spawnableCreatureList.add(new SpawnListEntry(EntitySpider.class, 7, 1, 2));

        this.waterColorMultiplier = 16777079;

        this.theBiomeDecorator.treesPerChunk = 6;
        this.theBiomeDecorator.grassPerChunk = 2;
        this.theBiomeDecorator.mushroomsPerChunk = 4;
        this.theBiomeDecorator.flowersPerChunk = -999;
        this.theBiomeDecorator.reedsPerChunk = -999;
        this.theBiomeDecorator.sandPerChunk = -999;
        this.theBiomeDecorator.sandPerChunk2 = -999;

        this.bopWorldFeatures.sproutsPerChunk = 2;
        this.bopWorldFeatures.poisonIvyPerChunk = 2;
        this.bopWorldFeatures.cobwebsPerChunk = 9;
        this.bopWorldFeatures.waterReedsPerChunk = 4;
        this.bopWorldFeatures.koruPerChunk = 1;
        this.bopWorldFeatures.generatePumpkins = true;
        this.bopWorldFeatures.cobwebNestsPerChunk = 2;
    }

    @Override
    //TODO:                     getRandomWorldGenForTrees()
    public WorldGenAbstractTree func_150567_a(Random random)
    {
        return random.nextInt(5) == 0 ? new WorldGenBOPSwampTree(Blocks.log, BOPBlockHelper.get("leaves2"), 0, 0, 6, 9, BOPBlockHelper.get("leaves2"), 0) : 
            (random.nextInt(7) == 0 ? new WorldGenDeadTree1(false, Blocks.dirt, Blocks.grass, BOPBlockHelper.get("driedDirt"), BOPBlockHelper.get("mud")) : 
                new WorldGenBOPSwampTree(BOPBlockHelper.get("logs3"), BOPBlockHelper.get("colorizedLeaves2"), 1, 0, 6, 9, BOPBlockHelper.get("colorizedLeaves2"), 0));
    }

    @Override
    public HashMap<WorldGenerator, Double> getWeightedWorldGenForGrass()
    {
        HashMap<WorldGenerator, Double> grassMap = new HashMap();

        grassMap.put(new WorldGenBOPTallGrass(BOPBlockHelper.get("foliage"), 10), 0.5D);
        grassMap.put(new WorldGenBOPTallGrass(BOPBlockHelper.get("foliage"), 11), 0.5D);
        grassMap.put(new WorldGenBOPTallGrass(Blocks.tallgrass, 0), 1D);

        return grassMap;
    }

    @Override
    public void decorate(World world, Random random, int chunkX, int chunkZ)
    {
        super.decorate(world, random, chunkX, chunkZ);
        int var5 = 12 + random.nextInt(6);

        for (int var6 = 0; var6 < var5; ++var6)
        {
            int x = chunkX + random.nextInt(16);
            int y = random.nextInt(28) + 4;
            int z = chunkZ + random.nextInt(16);

            //TODO:             getBlock()
            Block block = world.func_147439_a(x, y, z);

            if (block != null && block.isReplaceableOreGen(world, x, y, z, Blocks.stone))
            {
                //TODO: setBlock()
                world.func_147465_d(x, y, z, BOPBlockHelper.get("gemOre"), 10, 2);
            }
        }
    }

    @Override
    //TODO:     getBiomeGrassColor()
    public int func_150558_b(int x, int y, int z)
    {
        return 13420973;
    }

    @Override
    //TODO:     getBiomeFoliageColor()
    public int func_150571_c(int x, int y, int z)
    {
        return 14146486;
    }


    @Override
    public int getSkyColorByTemp(float par1)
    {
        if (BOPConfigurationMisc.skyColors) return 13553096;
        else return super.getSkyColorByTemp(par1);
    }

    /*
	@Override
	public int getFogColour()
	{
		return 10062450;
	}

    @Override
    public float getFogCloseness()
    {
        // TODO Auto-generated method stub
        return 0.8F;
    }
     */
}
