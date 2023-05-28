package net.nejiwwkr.project_industrial.util;

import net.minecraft.text.LiteralText;
import net.minecraft.text.TranslatableText;
import net.minecraft.world.World;

public class Testing {
    public static void checkTickerIsWorking(World world) {
        world.getPlayers().forEach(x -> x.sendMessage(new TranslatableText("ok"),false));
    }
    public static void checkTickerIsWorking(World world,String text) {
        world.getPlayers().forEach(x -> x.sendMessage(new LiteralText(text),false));
    }
}
