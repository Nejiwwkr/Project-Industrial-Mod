package net.nejiwwkr.project_industrial.command;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.CommandDispatcher;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.text.LiteralText;
import net.minecraft.text.TranslatableText;

import static net.minecraft.server.command.CommandManager.literal;

public class NbtCommand {
    private static int getNbt(PlayerEntity player) {
        ItemStack stack = player.getMainHandStack();
        if (stack.hasNbt()) {
            if (stack.getNbt() != null) {
                String res = stack.getNbt().toString();
                player.sendMessage(
                        new LiteralText(new TranslatableText("tip.has_nbt").getString() + "\n" + res),
                        false
                );
            }
            return Command.SINGLE_SUCCESS;
        }else {
            player.sendMessage(new TranslatableText("tip.null_nbt"),false);
        }
        return 0;
    }

    public static void register(CommandDispatcher<ServerCommandSource> dispatcher) {
        dispatcher.register(literal("nbt").requires(p -> p.hasPermissionLevel(4)).executes(p -> getNbt(p.getSource().getPlayer())));
    }
}
