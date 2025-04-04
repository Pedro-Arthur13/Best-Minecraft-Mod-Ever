package net.arthur;

import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ModSounds {
    public static final SoundEvent JUMPSCARE_SOUND = registerSound("jumpscare");
    public static final SoundEvent DAVI = registerSound("davi");
    public static final SoundEvent MYMUSIC = registerSound("mymusic");

    private static SoundEvent registerSound(String name) {
        Identifier id = new Identifier("mod", name);
        return Registry.register(Registry.SOUND_EVENT, id, new SoundEvent(id));
    }

    public static void registerModSounds() {
        // Apenas para garantir que a classe seja carregada
    }
}
