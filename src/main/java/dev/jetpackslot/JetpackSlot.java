package dev.jetpackslot;

import net.fabricmc.api.ModInitializer;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

/**
 * The jetpack lives in the player's vanilla BODY equipment slot (unused on players), which Minecraft already
 * saves, syncs and drops. The chest slot is left alone.
 */
public final class JetpackSlot implements ModInitializer {
    /** Index of the BODY slot inside the player Inventory container. */
    public static final int CONTAINER_INDEX = 41;
    public static final EquipmentSlot STORAGE = EquipmentSlot.BODY;

    private static Item pack;

    @Override
    public void onInitialize() {}

    public static boolean isPack(ItemStack s) {
        if (s.isEmpty()) return false;
        if (pack == null) {
            pack = BuiltInRegistries.ITEM.getValue(Identifier.fromNamespaceAndPath("aeroforge", "jetpack"));
        }
        return s.is(pack);
    }

    /** What the jetpack mod sees where it used to read the chest slot: our slot first, chest as fallback. */
    public static ItemStack lookup(LivingEntity e, EquipmentSlot slot) {
        if (slot == EquipmentSlot.CHEST) {
            ItemStack own = e.getItemBySlot(STORAGE);
            if (isPack(own)) return own;
        }
        return e.getItemBySlot(slot);
    }

    /** The jetpack in our slot (same stack instance, so energy changes persist), or EMPTY. */
    public static ItemStack slotStack(LivingEntity e) {
        ItemStack own = e.getItemBySlot(STORAGE);
        return isPack(own) ? own : ItemStack.EMPTY;
    }
}
