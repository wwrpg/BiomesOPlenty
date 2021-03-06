package biomesoplenty.common.world;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.WorldProviderSurface;

public class WorldProviderSurfaceBOP extends WorldProviderSurface
{
    @Override
	public boolean canCoordinateBeSpawn(int x, int z)
    {
    	int y = getTopBlockCoord(this.worldObj, x, z);
    	//TODO:							getBlock()
    	Block topBlock = this.worldObj.func_147439_a(x, y, z);
    	boolean flag = topBlock == Blocks.sand || topBlock == Blocks.stone || topBlock == Blocks.snow_layer && this.worldChunkMgr.getBiomesToSpawnIn().contains(this.worldObj.getBiomeGenForCoordsBody(x, z));
    	boolean isClear = true;
    	
    	for (int ix = -1; ix <= 1; ix++)
    	{
    		for (int iy = -2; iy <= 2; iy++)
    		{
    			for (int iz = -1; iz <= 1; iz++)
    			{
    				if (this.worldObj.func_147439_a(x, y, z).func_149688_o() == Material.field_151586_h)
    				{
    					isClear = false;
    					break;
    				}
    			}
    		}
    	}
    	
    	return flag && isClear;
    }

    public int getTopBlockCoord(World world, int x, int z)
    {
    	int y;

    	for (y = 63; !world.func_147437_c(x, y + 1, z); ++y)
    	{
    		;
    	}

    	return y;
    }
}
