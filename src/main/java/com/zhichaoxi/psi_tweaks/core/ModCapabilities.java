package com.zhichaoxi.psi_tweaks.core;

import com.zhichaoxi.psi_tweaks.PsiTweaks;
import com.zhichaoxi.psi_tweaks.capability.IAdditionalPsiHandler;
import net.neoforged.neoforge.capabilities.EntityCapability;
import net.neoforged.neoforge.capabilities.ItemCapability;

public class ModCapabilities {
    public static final ItemCapability<IAdditionalPsiHandler, Void> ADDITIONAL_PSI_HANDLER_ITEM =
            ItemCapability.create(
                    PsiTweaks.location("psi_handler"),
                    IAdditionalPsiHandler.class,
                    Void.class);

    public static final EntityCapability<IAdditionalPsiHandler, Void> ADDITIONAL_PSI_HANDLER_ENTITY =
            EntityCapability.create(
                    PsiTweaks.location("psi_handler"),
                    IAdditionalPsiHandler.class,
                    Void.class);
}
