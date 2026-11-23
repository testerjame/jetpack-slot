package dev.jetpackslot.mixin;

import dev.jetpackslot.JetpackSlot;
import dev.jetpackslot.PackSlot;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractCraftingMenu;
import net.minecraft.world.inventory.InventoryMenu;
import net.minecraft.world.inventory.MenuType;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(InventoryMenu.class)
public abstract class InventoryMenuMixin extends AbstractCraftingMenu {
    protected InventoryMenuMixin(MenuType<?> type, int id, int w, int h) { super(type, id, w, h); }

    @Inject(method = "<init>(Lnet/minecraft/world/entity/player/Inventory;ZLnet/minecraft/world/entity/player/Player;)V",
            at = @At("TAIL"))
    private void jetpackslot$addSlot(Inventory inventory, boolean active, Player owner, CallbackInfo ci) {
        this.addSlot(new PackSlot(inventory, JetpackSlot.CONTAINER_INDEX, PackSlot.X, PackSlot.Y));
    }
}
