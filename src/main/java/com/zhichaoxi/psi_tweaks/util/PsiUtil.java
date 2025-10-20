package com.zhichaoxi.psi_tweaks.util;

import com.google.common.collect.Iterables;
import com.zhichaoxi.psi_tweaks.capability.IAdditionalPsiHandler;
import com.zhichaoxi.psi_tweaks.core.ModCapabilities;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.items.IItemHandlerModifiable;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.type.capability.ICuriosItemHandler;
import vazkii.psi.api.PsiAPI;
import vazkii.psi.api.cad.ICAD;
import vazkii.psi.common.core.handler.PlayerDataHandler;

import java.util.Optional;

public class PsiUtil {

    public static int getRealAvailablePsi(Player player) {
        int availablePsi = 0;
        ItemStack cad = PsiAPI.getPlayerCAD(player);
        ICAD cadItem = (ICAD) cad.getItem();
        PlayerDataHandler.PlayerData data = PlayerDataHandler.get(player);

        //Psi Tweaks
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

        Inventory inventory = player.getInventory();
        Iterable<ItemStack> allItems = Iterables.concat(inventory.items, inventory.armor, inventory.offhand);
        for (ItemStack item : allItems) {
            IAdditionalPsiHandler psiHandlerItem = item.getCapability(ModCapabilities.ADDITIONAL_PSI_HANDLER_ITEM);
            if (psiHandlerItem != null) {
                amount += psiHandlerItem.getPsiStored();
            }
        }

        Optional<ICuriosItemHandler> curiosInventory = CuriosApi.getCuriosInventory(player);
        ICuriosItemHandler curiosItemHandler = curiosInventory.get();
        IItemHandlerModifiable equippedCurios = curiosItemHandler.getEquippedCurios();
        for (int i = 0;i < equippedCurios.getSlots();i++) {
            ItemStack item = equippedCurios.getStackInSlot(i);
            IAdditionalPsiHandler psiHandler = item.getCapability(ModCapabilities.ADDITIONAL_PSI_HANDLER_ITEM);
            if (psiHandler != null) {
                amount += psiHandler.getPsiStored();
            }
        }

        return amount;
    }

    public static int costAdditionalAvailablePsi(Player player, int cost) {
        int amount = 0;

        IAdditionalPsiHandler psiHandlerEntity = player.getCapability(ModCapabilities.ADDITIONAL_PSI_HANDLER_ENTITY);
        if (psiHandlerEntity != null) {
            amount += psiHandlerEntity.extractPsi(cost, false);
        }

        Inventory inventory = player.getInventory();
        Iterable<ItemStack> allItems = Iterables.concat(inventory.items, inventory.armor, inventory.offhand);
        for (ItemStack item : allItems) {
            IAdditionalPsiHandler psiHandler = item.getCapability(ModCapabilities.ADDITIONAL_PSI_HANDLER_ITEM);
            if (psiHandler != null) {
                amount += psiHandler.extractPsi(cost, false);
            }
            if (amount >= cost) {
                break;
            }
        }

        Optional<ICuriosItemHandler> curiosInventory = CuriosApi.getCuriosInventory(player);
        ICuriosItemHandler curiosItemHandler = curiosInventory.get();
        IItemHandlerModifiable equippedCurios = curiosItemHandler.getEquippedCurios();
        for (int i = 0;i < equippedCurios.getSlots();i++) {
            ItemStack item = equippedCurios.getStackInSlot(i);
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
