package com.zhichaoxi.psi_tweaks.mixin;

import com.zhichaoxi.psi_tweaks.ConfigHandler;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import vazkii.psi.common.item.tool.IPsimetalTool;

@Mixin(IPsimetalTool.class)
public interface IPsimetalToolMixin {
    @Inject(method = "isItemValidForRegen", at = @At(value = "RETURN", ordinal = 1), cancellable = true)
    private static void isItemValidForRegen(ItemStack stack, Entity entityIn, CallbackInfoReturnable<Boolean> cir) {
        if(ConfigHandler.COMMON.alwaysValidForRegen.getAsBoolean() && stack.getDamageValue() > 0) {
            cir.setReturnValue(true);
        }
    }
}
