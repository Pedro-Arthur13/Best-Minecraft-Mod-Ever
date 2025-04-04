package net.arthur;

import net.arthur.client.render.CustomPlayerRenderer;
import net.arthur.registry.ModEntities;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendereregistry.v1.EntityRendererRegistry;

public class ModClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        EntityRendererRegistry.INSTANCE.register(ModEntities.CUSTOM_PLAYER, (dispatcher, context) ->
                new CustomPlayerRenderer(dispatcher)
        );
    }
}
