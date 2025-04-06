package net.arthur;

public class ClientJumpscareController {
    public static boolean active = false;
    public static long startTime = 0;

    public static void activate() {
        active = true;
        startTime = System.currentTimeMillis();
    }
}
