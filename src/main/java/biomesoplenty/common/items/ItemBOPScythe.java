package biomesoplenty.common.items;

import net.minecraft.block.Block;
import net.minecraft.block.BlockFlower;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import biomesoplenty.BiomesOPlenty;
import biomesoplenty.api.BOPBlockHelper;
import biomesoplenty.api.BOPItemHelper;
import biomesoplenty.common.core.BOPItems;

public class ItemBOPScythe extends Item
{
	public int textureID = 0;
	
    protected ToolMaterial toolMaterial;

	public ItemBOPScythe(ToolMaterial toolMaterial, int texture)
	{
        this.toolMaterial = toolMaterial;
        this.maxStackSize = 1;
        this.setMaxDamage(toolMaterial.getMaxUses());
		textureID = texture;
        
		this.setCreativeTab(BiomesOPlenty.tabBiomesOPlenty);
	}
	
    @Override
    //TODO:			onBlockDestroyed()?
	public boolean func_150894_a(ItemStack itemstack, World world, Block block, int x, int y, int z, EntityLivingBase entity)
    {
        int radius = 1;
        int height = 3;
        
        if (toolMaterial == ToolMaterial.IRON || toolMaterial == ToolMaterial.GOLD)
        {
        	if (block != null)
        	{
        		if (block.isLeaves(world, x, y, z))
        			height = 2;
        	}
        	
        	radius = 2;
        }
        else if (toolMaterial == ToolMaterial.EMERALD)
        {
        	if (block != null)
        	{
        		if (block.isLeaves(world, x, y, z))
        			height = 3;
        	}
        	
        	radius = 3;
        }
        else if (toolMaterial == BOPItemHelper.toolMaterialAmethyst)
        {
        	if (block != null)
        	{
        		if (block.isLeaves(world, x, y, z))
        			height = 4;
        	}
        	
        	radius = 4;
        }
        else
        {
        	if (block != null)
        	{
        		if (block.isLeaves(world, x, y, z))
        			height = 0;
        	}
        }
    	
    	if (block != null)
    	{
    		if (block.isLeaves(world, x, y, z))
    		{
        		if (height > 0)
        		{
        			trimLeaves(itemstack, entity, world, x, y, z, height, radius);

        			return true;
        		}
    		}
    		else
    		{
    			trimCutCorner(itemstack, entity, world, x, y, z, height, radius);

    			if (world.rand.nextInt(3) == 0)
    			{
    				trim(itemstack, entity, world, x, y, z, height, radius - 1);

    				return true;
    			}
    		}
    	}
    	
        return false;
    }
    
    public void trim(ItemStack stack, EntityLivingBase entity, World world, int x, int y, int z, int height, int radius)
    {
    	for (int aX = -radius; aX <= radius; aX++)
    	{
    		for (int aY = 0; aY <= radius; aY++)
    		{
    			for (int aZ = -radius; aZ <= radius; aZ++)
    			{
    				//TODO:		  	getBlock()
    				Block block = world.func_147439_a(x + aX, y + aY, z + aZ);
    				int meta = world.getBlockMetadata(x + aX, y + aY, z + aZ);

    				if (block != null)
    				{
    					if (toolMaterial == ToolMaterial.IRON || toolMaterial == ToolMaterial.GOLD)
    					{
    						if (world.rand.nextInt(7) == 0)
    						{
    							stack.damageItem(1, entity);
    						}
    					}
    			        else if (toolMaterial == ToolMaterial.EMERALD)
    			        {
    						if (world.rand.nextInt(8) == 0)
    						{
    							stack.damageItem(1, entity);
    						}
    			        }
    			        else if (toolMaterial == BOPItemHelper.toolMaterialAmethyst)
    			        {
    						if (world.rand.nextInt(9) == 0)
    						{
    							stack.damageItem(1, entity);
    						}
    			        }
    			        else if (world.rand.nextInt(5) == 0)
						{
							stack.damageItem(1, entity);
						}
    					
    					if (block == BOPBlockHelper.get("foliage") && (meta == 1 || meta == 2 || meta == 6))
    					{
    						if (meta == 1)
    						{
    							//TODO: dropBlockAsItem()
    							block.func_149697_b(world, x + aX, y + aY, z + aZ, meta, 0);
    							//TODO: setBlockToAir()
    							world.func_147468_f(x + aX, y + aY, z + aZ);
    						}
    						else if (meta == 2)
    						{
    							//TODO: dropBlockAsItem()
    							block.func_149697_b(world, x + aX, y + aY, z + aZ, meta, 0);
        						//TODO: setBlock()
    							world.func_147465_d(x + aX, y + aY, z + aZ, BOPBlockHelper.get("foliage"), 1, 2);
    						}
    						else if (meta == 6)
    						{
    							//TODO: dropBlockAsItem()
    							block.func_149697_b(world, x + aX, y + aY, z + aZ, meta, 0);
    							//TODO: setBlockToAir()
    							world.func_147468_f(x + aX, y + aY, z + aZ);
    						}
    					}
    					else if (block == Blocks.tallgrass)
    					{
							//TODO: dropBlockAsItem()
    						block.func_149697_b(world, x + aX, y + aY, z + aZ, meta, 0);
    						//TODO: setBlock()
    						world.func_147465_d(x + aX, y + aY, z + aZ, BOPBlockHelper.get("foliage"), 2, 2);
    					}
    					else if (block != Blocks.waterlily && block instanceof BlockFlower)
    					{
							//TODO: dropBlockAsItem()
    						block.func_149697_b(world, x + aX, y + aY, z + aZ, meta, 0);
							//TODO: setBlockToAir()
    						world.func_147468_f(x + aX, y + aY, z + aZ);
    					}
    				}
    			}
    		}
    	}
    }
    
    public void trimLeaves(ItemStack stack, EntityLivingBase entity, World world, int x, int y, int z, int height, int radius)
    {
    	for (int aX = -radius; aX <= radius; aX++)
    	{
    		for (int aY = -radius; aY <= radius; aY++)
    		{
    			for (int aZ = -radius; aZ <= radius; aZ++)
    			{
    				//TODO:		  	getBlock()
    				Block block = world.func_147439_a(x + aX, y + aY, z + aZ);
    				int meta = world.getBlockMetadata(x + aX, y + aY, z + aZ);

    				if (block != null)
    				{    					
    					if (block.isLeaves(world, x + aX, y + aY, z + aZ))
    					{
        					if (toolMaterial == ToolMaterial.IRON || toolMaterial == ToolMaterial.GOLD)
        					{
        						if (world.rand.nextInt(6) == 0)
        						{
        							stack.damageItem(1, entity);
        						}
        					}
        			        else if (toolMaterial == ToolMaterial.EMERALD)
        			        {
        						if (world.rand.nextInt(7) == 0)
        						{
        							stack.damageItem(1, entity);
        						}
        			        }
        			        else if (toolMaterial == BOPItemHelper.toolMaterialAmethyst)
        			        {
        						if (world.rand.nextInt(8) == 0)
        						{
        							stack.damageItem(1, entity);
        						}
        			        }
        			        else if (world.rand.nextInt(4) == 0)
    						{
    							stack.damageItem(1, entity);
    						}

							//TODO: dropBlockAsItem()
    						block.func_149697_b(world, x + aX, y + aY, z + aZ, meta, 0);
							//TODO: setBlockToAir()
    						world.func_147468_f(x + aX, y + aY, z + aZ);
    					}
    				}
    			}
    		}
    	}
    }
    
    public void trimCutCorner(ItemStack stack, EntityLivingBase entity, World world, int x, int y, int z, int height, int radius)
    {
    	for (int aX = -radius; aX <= radius; aX++)
    	{
    		for (int aY = 0; aY <= radius; aY++)
    		{
    			for (int aZ = -radius; aZ <= radius; aZ++)
    			{
    				if ((aX + aZ < radius * 2) && (-aX + aZ < radius * 2) && (aX + -aZ < radius * 2) && (-aX + -aZ < radius * 2))
    				{
        				//TODO:		  	getBlock()
        				Block block = world.func_147439_a(x + aX, y + aY, z + aZ);
    					int meta = world.getBlockMetadata(x + aX, y + aY, z + aZ);

    					if (block != null)
    					{
        					if (toolMaterial == ToolMaterial.IRON || toolMaterial == ToolMaterial.GOLD)
        					{
        						if (world.rand.nextInt(8) == 0)
        						{
        							stack.damageItem(1, entity);
        						}
        					}
        			        else if (toolMaterial == ToolMaterial.EMERALD)
        			        {
        						if (world.rand.nextInt(9) == 0)
        						{
        							stack.damageItem(1, entity);
        						}
        			        }
        			        else if (toolMaterial == BOPItemHelper.toolMaterialAmethyst)
        			        {
        						if (world.rand.nextInt(10) == 0)
        						{
        							stack.damageItem(1, entity);
        						}
        			        }
        			        else if (world.rand.nextInt(5) == 0)
    						{
    							stack.damageItem(1, entity);
    						}
    						
    						if (block == BOPBlockHelper.get("foliage") && (meta == 1 || meta == 2 || meta == 6))
    						{
    							if (meta == 1)
    							{
        							//TODO: dropBlockAsItem()
    								block.func_149697_b(world, x + aX, y + aY, z + aZ, meta, 0);
        							//TODO: setBlockToAir()
    								world.func_147468_f(x + aX, y + aY, z + aZ);
    							}
    							else if (meta == 2)
    							{
        							//TODO: dropBlockAsItem()
    								block.func_149697_b(world, x + aX, y + aY, z + aZ, meta, 0);
    	    						//TODO: setBlock()
    								world.func_147465_d(x + aX, y + aY, z + aZ, BOPBlockHelper.get("foliage"), 1, 2);
    							}
    							else if (meta == 6)
    							{
        							//TODO: dropBlockAsItem()
    								block.func_149697_b(world, x + aX, y + aY, z + aZ, meta, 0);
        							//TODO: setBlockToAir()
    								world.func_147468_f(x + aX, y + aY, z + aZ);
    							}
    						}
    						else if (block == Blocks.tallgrass)
    						{
    							//TODO: dropBlockAsItem()
    							block.func_149697_b(world, x + aX, y + aY, z + aZ, meta, 0);
        						//TODO: setBlock()
    							world.func_147465_d(x + aX, y + aY, z + aZ, BOPBlockHelper.get("foliage"), 2, 2);
    						}
    						else if (block != Blocks.waterlily && block instanceof BlockFlower)
    						{
    							//TODO: dropBlockAsItem()
    							block.func_149697_b(world, x + aX, y + aY, z + aZ, meta, 0);
    							//TODO: setBlockToAir()
    							world.func_147468_f(x + aX, y + aY, z + aZ);
    						}
    					}
    				}
    			}
    		}
    	}
    }
    
	@Override
	//TODO:		 getStrVsBlock
	public float func_150893_a(ItemStack item, Block block)
	{
		return Items.shears.func_150893_a(item, block);
	}

	@Override
    public int getItemEnchantability()
    {
        return this.toolMaterial.getEnchantability();
    }

	@Override
	public boolean getIsRepairable(ItemStack itemToRepair, ItemStack itemToRepairWith)
	{
		//TODO:													 getItemFromBlock
		if (textureID == 0 && itemToRepairWith.getItem() == Item.func_150898_a(Blocks.planks))
		{
			return true;
		}
		//TODO:													 getItemFromBlock
		if (textureID == 1 && itemToRepairWith.getItem() == Item.func_150898_a(Blocks.cobblestone))
		{
			return true;
		}
		if (textureID == 2 && itemToRepairWith.getItem() == Items.iron_ingot)
		{
			return true;
		}
		if (textureID == 3 && itemToRepairWith.getItem() == Items.gold_ingot)
		{
			return true;
		}
		if (textureID == 4 && itemToRepairWith.getItem() == Items.diamond)
		{
			return true;
		}
		if (textureID == 6 && itemToRepairWith.getItem() == BOPItemHelper.get("misc") && itemToRepairWith.getItemDamage() == 2)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	@Override
    public boolean isFull3D()
    {
        return true;
    }

	@Override
	public void registerIcons(IIconRegister iconRegister)
	{
		if (textureID == 0)
		{ 
			itemIcon = iconRegister.registerIcon("biomesoplenty:woodscythe"); 
		}
		else if (textureID == 1)
		{ 
			itemIcon = iconRegister.registerIcon("biomesoplenty:stonescythe"); 
		}
		else if (textureID == 2)
		{ 
			itemIcon = iconRegister.registerIcon("biomesoplenty:ironscythe"); 
		}
		else if (textureID == 3)
		{ 
			itemIcon = iconRegister.registerIcon("biomesoplenty:goldscythe"); 
		}
		else if (textureID == 4)
		{ 
			itemIcon = iconRegister.registerIcon("biomesoplenty:diamondscythe"); 
		}
		else if (textureID == 5)
		{ 
			itemIcon = iconRegister.registerIcon("biomesoplenty:mudscythe"); 
		}
		else if (textureID == 6)
		{ 
			itemIcon = iconRegister.registerIcon("biomesoplenty:amethystscythe"); 
		}
		else 
		{ 
			itemIcon = iconRegister.registerIcon("biomesoplenty:mudball"); 
		}
	}
}
