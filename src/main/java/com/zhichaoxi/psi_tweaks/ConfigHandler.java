package com.zhichaoxi.psi_tweaks;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.config.ModConfigEvent;
import net.neoforged.neoforge.common.ModConfigSpec;
import org.apache.commons.lang3.tuple.Pair;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class ConfigHandler {
    public static final Common COMMON;
    public static final ModConfigSpec COMMON_SPEC;

    static {
        final Pair<Common, ModConfigSpec> specPair = new ModConfigSpec.Builder().configure(Common::new);
        COMMON_SPEC = specPair.getRight();
        COMMON = specPair.getLeft();
    }

    public static class Common {
        public final ModConfigSpec.BooleanValue bossImmuneSpell;
        public final ModConfigSpec.DoubleValue maxCastRadius;

        public Common(ModConfigSpec.Builder builder) {
            bossImmuneSpell = builder
                    .comment(" Do Boss immune Psi Spell.")
                    .define("common.bossImmuneSpell", false);

            maxCastRadius = builder
                    .comment(" Set max cast radius of spell.")
                    .defineInRange("common.maxCastRadius", -1, -1, Double.MAX_VALUE);
        }
    }
}