package com.zhichaoxi.psi_tweaks.mixin;

import com.zhichaoxi.psi_tweaks.core.ModAttributes;
import com.zhichaoxi.psi_tweaks.util.PsiUtil;
import jdk.jfr.Name;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import vazkii.psi.common.core.handler.PlayerDataHandler;

import java.lang.ref.WeakReference;

@Mixin(PlayerDataHandler.PlayerData.class)
public abstract class PlayerDataMixin {
    @Shadow @Final public WeakReference<Player> playerWR;

    @ModifyVariable(
            method = "deductPsi(IIZZ)V",
            at = @At("HEAD"),
            ordinal = 0,
            argsOnly = true
    )
    public int deductPsi(int original) {
        Player player = playerWR.get();

        int amount = 0;
        if (player != null) {
            amount = PsiUtil.costAdditionalAvailablePsi(player, original);
        }

        return original - amount;
    }

    @Inject(method = "getTotalPsi", at = @At("RETURN"), cancellable = true)
    private void getTotalPsi(CallbackInfoReturnable<Integer> cir) {
        int totalPsi = cir.getReturnValueI();
        Player player = playerWR.get();
        if (player != null) {
            int attrValue = (int) player.getAttributeValue(ModAttributes.MAX_PSI);
            cir.setReturnValue(totalPsi + attrValue);
        }
    }

    @Inject(method = "getRegenPerTick", at = @At("RETURN"), cancellable = true)
    private void getRegenPerTick(CallbackInfoReturnable<Integer> cir) {
        int regen = cir.getReturnValueI();
        Player player = playerWR.get();
        if (player != null) {
            int attrValue = (int) player.getAttributeValue(ModAttributes.REGEN_PSI);
            cir.setReturnValue(regen + attrValue);
        }
    }
}
