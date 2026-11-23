package dev.jetpackslot;

import net.minecraft.resources.Identifier;
import net.minecraft.world.Container;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;

/** Inventory slot that only accepts the jetpack. */
public class PackSlot extends Slot {
    public static final int X = 77, Y = 26;
    private static final Identifier ICON = Identifier.fromNamespaceAndPath("jetpackslot", "container/slot/jetpack_slot");

    public PackSlot(Container container, int index, int x, int y) { super(container, index, x, y); }

    @Override public boolean mayPlace(ItemStack stack) { return JetpackSlot.isPack(stack); }
    @Override public int getMaxStackSize() { return 1; }
    @Override public Identifier getNoItemIcon() { return ICON; }
}
