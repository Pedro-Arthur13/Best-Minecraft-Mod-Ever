package net.arthur.mixin;

import net.arthur.item.ModArmors;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin {

    @Inject(method = "damage", at = @At("HEAD"), cancellable = true)
    private void makeCustomArmorExplosionImmune(DamageSource source, float amount, CallbackInfoReturnable<Boolean> cir) {
        if (source.isExplosive()) {
            LivingEntity entity = (LivingEntity)(Object)this;

            int count = 0;
            for (ItemStack armorItem : entity.getArmorItems()) {
                if (armorItem.getItem() == ModArmors.GLASS_HELMET ||
                        armorItem.getItem() == ModArmors.GLASS_CHESTPLATE ||
                        armorItem.getItem() == ModArmors.GLASS_LEGGINGS ||
                        armorItem.getItem() == ModArmors.GLASS_BOOTS) {
                    count++;
                }
            }

            if (count == 4) {
                // Cancela o dano de explosão
                cir.setReturnValue(false);
            }
        }
    }
}
