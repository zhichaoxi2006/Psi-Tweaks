package com.zhichaoxi.psi_tweaks.capability;

import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.capabilities.Capabilities;
import net.neoforged.neoforge.energy.IEnergyStorage;

public class EnergyAdditionalPsiHander implements IAdditionalPsiHandler{

    private ItemStack itemStack;

    public EnergyAdditionalPsiHander(ItemStack stack) {
        itemStack = stack;
    }

    @Override
    public int receivePsi(int amount, boolean simulate) {
        IEnergyStorage energyStorage = itemStack.getCapability(Capabilities.EnergyStorage.ITEM);
        if (energyStorage != null) {
            return energyStorage.receiveEnergy(amount, simulate);
        }
        return 0;
    }

    @Override
    public int extractPsi(int amount, boolean simulate) {
        IEnergyStorage energyStorage = itemStack.getCapability(Capabilities.EnergyStorage.ITEM);
        if (energyStorage != null) {
            return energyStorage.extractEnergy(amount, simulate);
        }
        return 0;
    }

    @Override
    public int getPsiStored() {
        IEnergyStorage energyStorage = itemStack.getCapability(Capabilities.EnergyStorage.ITEM);
        if (energyStorage != null) {
            return energyStorage.getEnergyStored();
        }
        return 0;
    }

    @Override
    public int getMaxPsiStored() {
        IEnergyStorage energyStorage = itemStack.getCapability(Capabilities.EnergyStorage.ITEM);
        if (energyStorage != null) {
            return energyStorage.getMaxEnergyStored();
        }
        return 0;
    }

    @Override
    public boolean canExtract() {
        IEnergyStorage energyStorage = itemStack.getCapability(Capabilities.EnergyStorage.ITEM);
        if (energyStorage != null) {
            return energyStorage.canExtract();
        }
        return false;
    }

    @Override
    public boolean canReceive() {
        IEnergyStorage energyStorage = itemStack.getCapability(Capabilities.EnergyStorage.ITEM);
        if (energyStorage != null) {
            return energyStorage.canReceive();
        }
        return false;
    }
}
