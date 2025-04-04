package net.arthur.mixin;

import net.minecraft.client.gui.screen.TitleScreen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

@Mixin(TitleScreen.class)
public class TitleScreenMixin {
    @Inject(method = "init", at = @At("HEAD"))
    private void replaceSplash(CallbackInfo info) {
        List<String> mySplashes = Arrays.asList(
                "Guh uhh!",
                "#1 Rizzler!",
                "Olábeleza!",
                "Eu sou o dono da roblox!",
                "A!",
                "Never gonna give you up!",
                "Eu ganhava doido."
        );


        String customSplash = mySplashes.get(new Random().nextInt(mySplashes.size()));

        try {
            // Reflete e altera o campo `splashText` da TitleScreen
            Field field = TitleScreen.class.getDeclaredField("splashText");
            field.setAccessible(true);
            field.set(this, customSplash);
        } catch (Exception e) {
            System.out.println("Erro ao modificar splash text: " + e);
        }
    }
}
