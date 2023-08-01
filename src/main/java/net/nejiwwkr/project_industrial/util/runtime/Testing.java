package net.nejiwwkr.project_industrial.util.runtime;

import net.minecraft.text.LiteralText;
import net.minecraft.text.TranslatableText;
import net.minecraft.world.World;
import net.nejiwwkr.project_industrial.util.interfaces.RuntimeTest;

public class Testing implements RuntimeTest {
    public void checkTickerIsWorking(World world) {
        world.getPlayers().forEach(x -> x.sendMessage(new TranslatableText("ok"),false));
    }
    public void checkTickerIsWorking(World world,String text) {
        world.getPlayers().forEach(x -> x.sendMessage(new LiteralText(text),false));
    }
}
