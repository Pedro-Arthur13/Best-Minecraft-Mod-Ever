package net.arthur.client.render;

import net.arthur.entity.CustomPlayerEntity;
import net.minecraft.client.render.entity.EntityRenderDispatcher;
import net.minecraft.client.render.entity.LivingEntityRenderer;
import net.minecraft.client.render.entity.model.PlayerEntityModel;
import net.minecraft.util.Identifier;

public class CustomPlayerRenderer extends LivingEntityRenderer<CustomPlayerEntity, PlayerEntityModel<CustomPlayerEntity>> {
    private static final Identifier TEXTURE = new Identifier("modid", "textures/entity/custom_player.png");

    public CustomPlayerRenderer(EntityRenderDispatcher dispatcher) {
        super(dispatcher, new PlayerEntityModel<>(0, false), 0.5f);
    }

    @Override
    public Identifier getTexture(CustomPlayerEntity entity) {
        return TEXTURE;
    }
}
