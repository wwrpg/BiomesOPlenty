package biomesoplenty.common.world.features;

import java.lang.reflect.Field;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.feature.WorldGenerator;
import biomesoplenty.api.BOPBlockHelper;
import biomesoplenty.common.world.decoration.IBOPDecoration;
import biomesoplenty.common.world.generation.WorldGeneratorBOP;

public class WorldGenWasteland3 extends WorldGeneratorBOP
{
    @Override
    public boolean generate(World world, Random random, int x, int y, int z)
    {
        //TODO:      isAirBlock()
        while (world.func_147437_c(x, y, z) && y > 2)
        {
            --y;
        }

        Block var6 = world.func_147439_a(x, y, z);
        Block var95 = world.func_147439_a(x - 1, y, z);
        Block var96 = world.func_147439_a(x + 1, y, z);
        Block var97 = world.func_147439_a(x, y, z - 1);
        Block var98 = world.func_147439_a(x, y, z + 1);

        if (var6 != BOPBlockHelper.get("driedDirt") || var95 != BOPBlockHelper.get("driedDirt") || var96 != BOPBlockHelper.get("driedDirt") || var97 != BOPBlockHelper.get("driedDirt") || var98 != BOPBlockHelper.get("driedDirt") )
            return false;
        else
        {
            for (int var7 = -2; var7 <= 2; ++var7)
            {
                for (int var8 = -2; var8 <= 2; ++var8)
                {
                    //TODO:  isAirBlock()                                       isAirBlock()
                    if (world.func_147437_c(x + var7, y - 1, z + var8) && world.func_147437_c(x + var7, y - 2, z + var8))
                    {
                        return false;
                    }
                }
            }

            int var999 = random.nextInt(2);

            if (var999 == 0)
            {
                world.func_147449_b(x, y, z, BOPBlockHelper.get("driedDirt"));
                world.func_147449_b(x - 1, y, z, BOPBlockHelper.get("driedDirt"));
                world.func_147449_b(x + 1, y, z, BOPBlockHelper.get("driedDirt"));
                world.func_147449_b(x, y, z - 1, BOPBlockHelper.get("driedDirt"));
                world.func_147449_b(x, y, z + 1, BOPBlockHelper.get("driedDirt"));
                this.func_150516_a(world, x, y + 1, z, BOPBlockHelper.get("driedDirt"), 0);
                this.func_150516_a(world, x + 1, y + 1, z, BOPBlockHelper.get("driedDirt"), 0);
                this.func_150516_a(world, x - 1, y + 1, z, BOPBlockHelper.get("driedDirt"), 0);
                this.func_150516_a(world, x, y + 1, z + 1, BOPBlockHelper.get("driedDirt"), 0);
                this.func_150516_a(world, x, y + 1, z - 1, BOPBlockHelper.get("driedDirt"), 0);
                this.func_150516_a(world, x, y + 2, z, BOPBlockHelper.get("driedDirt"), 0);
                return true;
            }
            if (var999 == 1)
            {
                world.func_147449_b(x, y, z, BOPBlockHelper.get("driedDirt"));
                this.func_150516_a(world, x, y + 1, z, BOPBlockHelper.get("driedDirt"), 0);
                return true;
            }

            return true;
        }
    }

    @Override
    public void doGeneration(World world, Random random, Field worldGeneratorField, WorldGenerator worldGenerator, BiomeGenBase biome, IBOPDecoration bopDecoration, int x, int z) throws Exception
    {
        for (int i = 0; i < worldGeneratorField.getInt(bopDecoration.getWorldFeatures()); i++)
        {
            int randX = x + random.nextInt(16) + 8;
            int randZ = z + random.nextInt(16) + 8;
            int randY = random.nextInt(world.getHeightValue(randX, randZ) * 2);

            worldGenerator.generate(world, random, randX, randY, randZ);
        }
    }
}
