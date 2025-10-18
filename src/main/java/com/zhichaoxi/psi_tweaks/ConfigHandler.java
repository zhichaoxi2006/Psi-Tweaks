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
                    .comment(" Do Boss immune Psi Spell.")
                    .define("common.bossImmuneSpell", false);

            maxCastRadius = builder
                    .comment(" Set max cast radius of spell.")
                    .defineInRange("common.maxCastRadius", -1, -1, Double.MAX_VALUE);

            alwaysValidForRegen = builder
                    .comment(" Do Always valid for regen of Psimetal Tool." +
                            "(true is mean it is valid for regen of Psimetal Tool on your mainhand or offhand)")
                    .define("common.alwaysValidForRegen", true);
        }
    }
}