package com.zhichaoxi.psi_tweaks.core;

import com.zhichaoxi.psi_tweaks.PsiTweaks;
import com.zhichaoxi.psi_tweaks.capability.EnergyAdditionalPsiHander;
import com.zhichaoxi.psi_tweaks.capability.IAdditionalPsiHandler;
import mekanism.common.registries.MekanismItems;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.capabilities.ItemCapability;
import net.neoforged.neoforge.capabilities.RegisterCapabilitiesEvent;

@EventBusSubscriber(modid = PsiTweaks.MODID)
public class ModCapabilities {
    public static final ItemCapability<IAdditionalPsiHandler, Void> ADDITIONAL_PSI_HANDLER_ITEM =
            ItemCapability.create(
                    PsiTweaks.location("psi_handler"),
                    IAdditionalPsiHandler.class,
                    Void.class);

    @SubscribeEvent
    public static void registerCapabilities(RegisterCapabilitiesEvent event) {
        event.registerItem(ADDITIONAL_PSI_HANDLER_ITEM,
                (stack, ctx) -> new EnergyAdditionalPsiHander(stack),
                MekanismItems.ENERGY_TABLET
        );
    }
}
