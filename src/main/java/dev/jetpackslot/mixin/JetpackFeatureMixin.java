package dev.jetpackslot.mixin;

import net.minecraft.client.renderer.entity.state.AvatarRenderState;
import net.minecraft.world.item.ItemStack;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

/** The pack layer draws whatever it finds in chestEquipment; feed it the jetpack-slot stack instead,
 *  leaving the real chestplate rendering untouched. */
@Mixin(targets = "dev.aeroforge.JetpackFeature")
public class JetpackFeatureMixin {
    @Redirect(method = "submit(Lcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/SubmitNodeCollector;ILnet/minecraft/client/renderer/entity/state/AvatarRenderState;FF)V",
              at = @At(value = "FIELD", opcode = Opcodes.GETFIELD,
                       target = "Lnet/minecraft/client/renderer/entity/state/AvatarRenderState;chestEquipment:Lnet/minecraft/world/item/ItemStack;"))
    private ItemStack jetpackslot$pack(AvatarRenderState state) {
        ItemStack s = ((RenderPackHolder) state).jetpackslot$getPack();
        return s.isEmpty() ? state.chestEquipment : s;
    }
}
