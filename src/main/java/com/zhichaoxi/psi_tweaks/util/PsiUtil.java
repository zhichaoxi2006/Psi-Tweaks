package com.zhichaoxi.psi_tweaks.util;

import com.zhichaoxi.psi_tweaks.capability.IAdditionalPsiHandler;
import com.zhichaoxi.psi_tweaks.core.ModCapabilities;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import vazkii.psi.api.PsiAPI;
import vazkii.psi.api.cad.ICAD;
import vazkii.psi.common.core.handler.PlayerDataHandler;

public class PsiUtil {

    public static int getRealAvailablePsi(Player player) {
        int availablePsi = 0;
        ItemStack cad = PsiAPI.getPlayerCAD(player);
        ICAD cadItem = (ICAD) cad.getItem();
        PlayerDataHandler.PlayerData data = PlayerDataHandler.get(player);
        //Debug
        availablePsi += getAdditionalAvailablePsi(player);

        //Psi
        availablePsi += data.getAvailablePsi();
        availablePsi += cadItem.getStoredPsi(cad);
        return availablePsi;
    }

    public static int getAdditionalAvailablePsi(Player player) {
        int amount = 0;

        IAdditionalPsiHandler psiHandlerEntity = player.getCapability(ModCapabilities.ADDITIONAL_PSI_HANDLER_ENTITY);
        if (psiHandlerEntity != null) {
            amount += psiHandlerEntity.getPsiStored();
        }

        //TODO 兼容其他的槽位
        for (ItemStack item : player.getInventory().items) {
            IAdditionalPsiHandler psiHandlerItem = item.getCapability(ModCapabilities.ADDITIONAL_PSI_HANDLER_ITEM);
            if (psiHandlerItem != null) {
                amount += psiHandlerItem.getPsiStored();
            }
        }


        return amount;
    }

    public static int costAdditionalAvailablePsi(Player player, int cost) {
        int amount = 0;

        IAdditionalPsiHandler psiHandlerEntity = player.getCapability(ModCapabilities.ADDITIONAL_PSI_HANDLER_ENTITY);
        if (psiHandlerEntity != null) {
            amount += psiHandlerEntity.getPsiStored();
        }

        //TODO 兼容其他的槽位
        for (ItemStack item : player.getInventory().items) {
            IAdditionalPsiHandler psiHandler = item.getCapability(ModCapabilities.ADDITIONAL_PSI_HANDLER_ITEM);
            if (psiHandler != null) {
                amount += psiHandler.extractPsi(cost, false);
            }
            if (amount >= cost) {
                break;
            }
        }

        return amount;
    }
}
