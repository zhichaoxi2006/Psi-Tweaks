package com.zhichaoxi.psi_tweaks.core;

import com.zhichaoxi.psi_tweaks.PsiTweaks;
import com.zhichaoxi.psi_tweaks.lib.LibAttributeNames;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.RangedAttribute;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.EntityAttributeModificationEvent;
import net.neoforged.neoforge.registries.DeferredRegister;

@EventBusSubscriber(modid = PsiTweaks.MODID)
public class ModAttributes {

    public static final DeferredRegister<Attribute> DR = DeferredRegister.create(
            BuiltInRegistries.ATTRIBUTE, PsiTweaks.MODID);

    public static Holder<Attribute> MAX_PSI;
    public static Holder<Attribute> REGEN_PSI;

    static {
        MAX_PSI = DR.register(LibAttributeNames.MAX_PSI, () -> new RangedAttribute(
                "attributes.psi_oddities.max_psi",
                0,
                -1000000,
                1000000
        ).setSyncable(true));
        REGEN_PSI = DR.register(LibAttributeNames.REGEN_PSI, () -> new RangedAttribute(
                "attributes.psi_oddities.regen_psi",
                0,
                -1000000,
                1000000
        ).setSyncable(true));
    }

    @SubscribeEvent
    public static void modifyEntityAttribute(EntityAttributeModificationEvent event) {
        event.add(EntityType.PLAYER, MAX_PSI);
        event.add(EntityType.PLAYER, REGEN_PSI);
    }
}
