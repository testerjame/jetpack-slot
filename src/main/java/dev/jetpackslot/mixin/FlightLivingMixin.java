package dev.jetpackslot.mixin;

import dev.jetpackslot.JetpackSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(targets = "dev.aeroforge.JetpackFlight")
public class FlightLivingMixin {
    @Redirect(method = { "canGlide", "selected" }, at = @At(value = "INVOKE",
        target = "Lnet/minecraft/world/entity/LivingEntity;getItemBySlot(Lnet/minecraft/world/entity/EquipmentSlot;)Lnet/minecraft/world/item/ItemStack;"))
    private static ItemStack jetpackslot$chest(LivingEntity self, EquipmentSlot slot) {
        return JetpackSlot.lookup(self, slot);
    }
}
