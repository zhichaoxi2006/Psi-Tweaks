package com.zhichaoxi.psi_tweaks.capability;

public interface IAdditionalPsiHandler {
    int receivePsi(int amount, boolean simulate);

    int extractPsi(int amount, boolean simulate);

    int getPsiStored();

    int getMaxPsiStored();

    boolean canExtract();

    boolean canReceive();
}
