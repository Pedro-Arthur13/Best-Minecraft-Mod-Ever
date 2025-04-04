package net.arthur;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.EffectCommand;
import net.minecraft.server.command.ServerCommandSource;
import static net.minecraft.server.command.CommandManager.literal;
import net.minecraft.text.LiteralText;

public class CustomCommands {

    public static void registerCommands(CommandDispatcher<ServerCommandSource> dispatcher) {
        registerTestCommand(dispatcher);
        registerAddCommand(dispatcher);
        registerJumpscareCommand(dispatcher); // 🔥 Chamando o comando jumpscare




    }

    private static void registerTestCommand(CommandDispatcher<ServerCommandSource> dispatcher) {
        dispatcher.register(CommandManager.literal("testcommand")
                .executes(context -> {
                    context.getSource().sendFeedback(new LiteralText("§aComando executado!"), false);

                    return Command.SINGLE_SUCCESS;
                })
        );
    }


    private static void registerAddCommand(CommandDispatcher<ServerCommandSource> dispatcher) {
        dispatcher.register(CommandManager.literal("add")
                .then(CommandManager.argument("num1", IntegerArgumentType.integer())
                        .then(CommandManager.argument("num2", IntegerArgumentType.integer())
                                .executes(context -> {
                                    int num1 = IntegerArgumentType.getInteger(context, "num1");
                                    int num2 = IntegerArgumentType.getInteger(context, "num2");
                                    int result = num1 + num2;
                                    context.getSource().sendFeedback(new LiteralText("Resultado: " + result), false);
                                    return Command.SINGLE_SUCCESS;
                                }))));
    }
    private static void registerJumpscareCommand(CommandDispatcher<ServerCommandSource> dispatcher) {
        dispatcher.register(
                literal("jumpscare")
                        .requires(src -> src.hasPermissionLevel(0))
                        .executes(ctx -> {
                            JumpscareController.active = true;
                            JumpscareController.startTime = System.currentTimeMillis();
                            return 1;
                        })
        );
    }


}
