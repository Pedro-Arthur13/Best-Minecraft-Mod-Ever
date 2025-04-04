package net.arthur.mixin;

import net.arthur.JumpscareController;
import net.arthur.Mod;
import net.arthur.ModSounds;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.client.sound.PositionedSoundInstance;
import net.minecraft.client.util.math.MatrixStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.client.render.Tessellator;
import net.minecraft.client.render.BufferBuilder;
import net.minecraft.client.render.VertexFormat;
import net.minecraft.client.gui.DrawableHelper;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.client.render.VertexFormats;
import net.minecraft.util.Identifier;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import org.lwjgl.opengl.GL11;
@Mixin(InGameHud.class)
public class InGameHudMixin {

    private boolean played = false;

    @Inject(method = "render", at = @At("TAIL"))
    private void renderJumpscare(MatrixStack matrices, float tickDelta, CallbackInfo info) {
        if (!JumpscareController.active) {
            played = false;
            return;
        }

        MinecraftClient client = MinecraftClient.getInstance();

        // Mostra por 8 segundos
        if (System.currentTimeMillis() - JumpscareController.startTime > 8000) {
            JumpscareController.active = false;
            return;
        }

        if (!played) {
            client.getSoundManager().play(PositionedSoundInstance.master(
                    ModSounds.JUMPSCARE_SOUND, 1.0F
            ));
            played = true;
        }

        // Desenha a imagem cobrindo toda a tela
        client.getTextureManager().bindTexture(new Identifier("mod", "textures/gui/jumpscare.png"));
        int screenWidth = client.getWindow().getScaledWidth();
        int screenHeight = client.getWindow().getScaledHeight();
        DrawableHelper.drawTexture(matrices, 0, 0, 0, 0, screenWidth, screenHeight, screenWidth, screenHeight);
    }
}
