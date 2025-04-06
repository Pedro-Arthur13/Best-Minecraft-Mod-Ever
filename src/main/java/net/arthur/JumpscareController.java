package net.arthur;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class JumpscareController {
    private static final Set<UUID> activePlayers = new HashSet<>();

    public static void activate(UUID playerId) {
        activePlayers.add(playerId);
    }

    public static boolean isActive(UUID playerId) {
        return activePlayers.contains(playerId);
    }

    public static void clear(UUID playerId) {
        activePlayers.remove(playerId);
    }
}
