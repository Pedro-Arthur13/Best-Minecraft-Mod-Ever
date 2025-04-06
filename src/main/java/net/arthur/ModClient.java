package net.arthur;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.util.Identifier;

public class ModClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        ClientPlayNetworking.registerGlobalReceiver(
                new Identifier("mod", "jumpscare"),
                (client, handler, buf, responseSender) -> {
                    client.execute(() -> {
                        ClientJumpscareController.active = true;
                        ClientJumpscareController.startTime = System.currentTimeMillis();
                    });
                }
        );
    }
}
