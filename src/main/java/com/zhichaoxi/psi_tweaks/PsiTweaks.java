package com.zhichaoxi.psi_tweaks;

import com.mojang.logging.LogUtils;
import com.zhichaoxi.psi_tweaks.core.ModAttributes;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import org.slf4j.Logger;

@Mod(PsiTweaks.MODID)
public class PsiTweaks {
    public static final String MODID = "psi_tweaks";
    private static final Logger LOGGER = LogUtils.getLogger();

    public static ResourceLocation location(String string) {
        return ResourceLocation.fromNamespaceAndPath(PsiTweaks.MODID, string);
    }

    public PsiTweaks(IEventBus modEventBus, ModContainer modContainer) {
        modEventBus.addListener(this::commonSetup);

        ModAttributes.DR.register(modEventBus);

        modContainer.registerConfig(ModConfig.Type.COMMON, ConfigHandler.COMMON_SPEC);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {}

}
