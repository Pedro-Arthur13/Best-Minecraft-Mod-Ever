package net.arthur.mixin;

import net.arthur.ModSounds;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.sound.PositionedSoundInstance;
import net.minecraft.entity.mob.WitherSkeletonEntity;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.hit.HitResult;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.HashSet;
import java.util.Set;

@Mixin(ClientPlayerEntity.class)
public class WitherSkeletonRaycastMixin {
    private static final Set<WitherSkeletonEntity> seenSkeletons = new HashSet<>();

    @Inject(method = "tick", at = @At("HEAD"))
    private void onClientTick(CallbackInfo ci) {
        MinecraftClient client = MinecraftClient.getInstance();
        if (client == null || client.player == null || client.world == null) return;

        HitResult hitResult = client.crosshairTarget;
        if (hitResult instanceof EntityHitResult entityHitResult) {
            if (entityHitResult.getEntity() instanceof WitherSkeletonEntity witherSkeleton) {
                if (!seenSkeletons.contains(witherSkeleton)) {
                    seenSkeletons.add(witherSkeleton);
                    playSound();
                }
            }
        }
    }

    private void playSound() {
        MinecraftClient client = MinecraftClient.getInstance();
        if (client.player != null) {
            SoundEvent sound = ModSounds.DAVI; // Davi?
            client.player.playSound(sound, 1.0F, 1.0F);
        }
    }
}
