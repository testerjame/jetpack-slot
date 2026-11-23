package dev.jetpackslot.mixin;

import dev.jetpackslot.JetpackSlot;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(targets = "dev.aeroforge.JetpackFlight")
public class FlightServerMixin {
    @Redirect(method = { "updateServer" }, at = @At(value = "INVOKE",
        target = "Lnet/minecraft/server/level/ServerPlayer;getItemBySlot(Lnet/minecraft/world/entity/EquipmentSlot;)Lnet/minecraft/world/item/ItemStack;"))
    private static ItemStack jetpackslot$chest(ServerPlayer self, EquipmentSlot slot) {
        return JetpackSlot.lookup(self, slot);
    }
}
