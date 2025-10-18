package com.zhichaoxi.psi_tweaks;

import net.neoforged.neoforge.common.ModConfigSpec;
import org.apache.commons.lang3.tuple.Pair;

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
        public final ModConfigSpec.BooleanValue alwaysValidForRegen;

        public Common(ModConfigSpec.Builder builder) {
            bossImmuneSpell = builder
                    .comment("Determines whether the boss is immune to Psi spells.")
                    .define("common.bossImmuneSpell", false);

            maxCastRadius = builder
                    .comment("Set the maximum casting radius for the spell.")
                    .defineInRange("common.maxCastRadius", -1, -1, Double.MAX_VALUE);

            alwaysValidForRegen = builder
                    .comment("Always active for Psi Metal Tool regeneration. (true indicates this effect applies to Psi Metal Tool regeneration for your main or off-hand equipped tool)")
                    .define("common.alwaysValidForRegen", true);
        }
    }
}