package net.nejiwwkr.project_industrial.item;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.MusicDiscItem;

import static net.nejiwwkr.project_industrial.Sounds.THEME_MUSIC;

public class ThemeSongMusicDisc extends MusicDiscItem {
    public ThemeSongMusicDisc() {
        super(1, THEME_MUSIC,new FabricItemSettings().maxCount(1));
    }
}
