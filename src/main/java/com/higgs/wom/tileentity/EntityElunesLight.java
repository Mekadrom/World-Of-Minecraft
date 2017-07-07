package com.higgs.wom.tileentity;

import net.minecraft.entity.Entity;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;

public class EntityElunesLight extends Entity
{
	private World world;
	
	public EntityElunesLight(World world, double x, double y, double z)
	{
		super(world);
		this.posX = x;
		this.posY = y;
		this.posZ = z;
		this.world = world;
	}
	
	public void updateEntity()
    {
        if(this.worldObj.getTotalWorldTime() % 80L == 0L)
        {
            this.drawLight();
        }
        
        if(this.worldObj.getTotalWorldTime() % 180L == 0L)
        {
        	world.removeEntity(this);
        }
    }
	
	private void drawLight()
	{
		double d0 = (double)(10);
		
		AxisAlignedBB axisalignedbb = AxisAlignedBB.getBoundingBox((double)this.posX, (double)this.posY, (double)this.posZ, (double)(this.posX + 1), (double)(this.posY + 1), (double)(this.posZ + 1)).expand(d0, d0, d0);
        axisalignedbb.maxY = (double)this.worldObj.getHeight();
	}

	@Override
	protected void entityInit()
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void readEntityFromNBT(NBTTagCompound p_70037_1_)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void writeEntityToNBT(NBTTagCompound p_70014_1_)
	{
		// TODO Auto-generated method stub
		
	}
}
