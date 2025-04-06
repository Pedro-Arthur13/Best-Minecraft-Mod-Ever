package net.arthur.mixin;

import net.arthur.ClientJumpscareController;
import net.arthur.ModSounds;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.client.sound.PositionedSoundInstance;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.client.MinecraftClient;
import net.minecraft.util.Identifier;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(InGameHud.class)
public class InGameHudMixin {

    private boolean played = false;

    @Inject(method = "render", at = @At("TAIL"))
    private void renderJumpscare(MatrixStack matrices, float tickDelta, CallbackInfo info) {
        MinecraftClient client = MinecraftClient.getInstance();

        if (client.player == null || !ClientJumpscareController.active) {
            played = false;
            return;
        }

        long elapsed = System.currentTimeMillis() - ClientJumpscareController.startTime;
        if (elapsed > 8000) {
            ClientJumpscareController.active = false;
            played = false;
            return;
        }

        if (!played) {
            client.getSoundManager().play(PositionedSoundInstance.master(
                    ModSounds.JUMPSCARE_SOUND, 1.0F
            ));
            played = true;
        }

        client.getTextureManager().bindTexture(new Identifier("mod", "textures/gui/jumpscare.png"));
        // desenha a imagem na tela inteira
        int width = client.getWindow().getScaledWidth();
        int height = client.getWindow().getScaledHeight();
        InGameHud hud = (InGameHud)(Object)this;
        ((net.minecraft.client.gui.DrawableHelper)(Object)hud).drawTexture(matrices, 0, 0, 0.0F, 0.0F, width, height, width, height);
    }
}
