package net.arthur.client.render;

import net.arthur.entity.CustomPlayerEntity;
import net.minecraft.client.render.entity.EntityRenderDispatcher;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.render.entity.model.PlayerEntityModel;
import net.minecraft.util.Identifier;

public class CustomPlayerRenderer extends MobEntityRenderer<CustomPlayerEntity, PlayerEntityModel<CustomPlayerEntity>> {
    public CustomPlayerRenderer(EntityRenderDispatcher entityRenderDispatcher) {
        super(entityRenderDispatcher, new PlayerEntityModel<>(0.0f, false), 0.5f);
    }

    @Override
    public Identifier getTexture(CustomPlayerEntity entity) {
        return entity.getPlayerType().getTexture();
    }
}
