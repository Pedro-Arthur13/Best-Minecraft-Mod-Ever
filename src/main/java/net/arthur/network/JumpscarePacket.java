package net.arthur.network;

import io.netty.buffer.Unpooled;
import net.arthur.ClientJumpscareController;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Identifier;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;

public class JumpscarePacket {
    public static final Identifier ID = new Identifier("mod", "jumpscare");

    // lado do servidor -> cliente
    public static void sendToPlayer(ServerPlayerEntity player) {
        PacketByteBuf buf = new PacketByteBuf(Unpooled.buffer());
        ServerPlayNetworking.send(player, ID, buf);
    }

    // lado do cliente: ao receber, ativa jumpscare
    public static void registerReceiver() {
        ClientPlayNetworking.registerGlobalReceiver(ID, (client, handler, buf, responseSender) -> {
            client.execute(() -> {
                ClientJumpscareController.activate();
            });
        });
    }
}
