package net.nejiwwkr.project_industrial;

import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import static net.nejiwwkr.project_industrial.ProjectIndustrialMod.MOD_ID;
import static net.nejiwwkr.project_industrial.ProjectIndustrialMod.THEME_DISC;

public class Sounds {
    public static final SoundEvent THEME_MUSIC = new SoundEvent(new Identifier(MOD_ID,"theme_song"));
    public static final SoundEvent ULEXITE_SOUND_PLACE = new SoundEvent(new Identifier(MOD_ID,"ulexite_sound_place"));
    public static final SoundEvent ULEXITE_SOUND_BREAK = new SoundEvent(new Identifier(MOD_ID,"ulexite_sound_break"));
    public static final SoundEvent CERAMIC_SOUND_PLACE = new SoundEvent(new Identifier(MOD_ID,"ceramic_place"));
    public static final SoundEvent CERAMIC_SOUND_BREAK = new SoundEvent(new Identifier(MOD_ID,"ceramic_break"));
    public static final SoundEvent METAL_BLOCK = new SoundEvent(new Identifier(MOD_ID,"metal_block"));

    public static final BlockSoundGroup ULEXITE_BSG = new BlockSoundGroup(0.001f,1.2f,ULEXITE_SOUND_BREAK,SoundEvents.BLOCK_STONE_STEP,ULEXITE_SOUND_PLACE,SoundEvents.BLOCK_STONE_HIT,SoundEvents.BLOCK_STONE_FALL);
    public static final BlockSoundGroup CERAMIC_BSG = new BlockSoundGroup(0.001f,1.2f,CERAMIC_SOUND_BREAK,SoundEvents.BLOCK_STONE_STEP,CERAMIC_SOUND_PLACE,SoundEvents.BLOCK_STONE_HIT,SoundEvents.BLOCK_STONE_FALL);
    public static final BlockSoundGroup METAL_BLOCK_BSG = new BlockSoundGroup(0.001f,1.0f,METAL_BLOCK, SoundEvents.BLOCK_METAL_STEP,METAL_BLOCK,METAL_BLOCK,METAL_BLOCK);

    public static void init() {
        Registry.register(Registry.SOUND_EVENT,new Identifier(MOD_ID,"theme_song"), THEME_MUSIC);
        Registry.register(Registry.SOUND_EVENT,new Identifier(MOD_ID,"ulexite_sound_place"), ULEXITE_SOUND_PLACE);
        Registry.register(Registry.SOUND_EVENT,new Identifier(MOD_ID,"ulexite_sound_break"), ULEXITE_SOUND_BREAK);
        Registry.register(Registry.SOUND_EVENT,new Identifier(MOD_ID,"ceramic_place"), CERAMIC_SOUND_PLACE);
        Registry.register(Registry.SOUND_EVENT,new Identifier(MOD_ID,"ceramic_break"), CERAMIC_SOUND_BREAK);
        Registry.register(Registry.SOUND_EVENT,new Identifier(MOD_ID,"metal_block"), METAL_BLOCK);

        Registry.register(Registry.ITEM,new Identifier(MOD_ID,"theme_disc"),THEME_DISC);
    }
}
