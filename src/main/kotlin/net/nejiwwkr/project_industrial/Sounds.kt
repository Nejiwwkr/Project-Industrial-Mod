package net.nejiwwkr.project_industrial

import net.minecraft.sound.BlockSoundGroup
import net.minecraft.sound.SoundEvent
import net.minecraft.sound.SoundEvents
import net.minecraft.util.Identifier
import net.minecraft.util.registry.Registry
import net.nejiwwkr.project_industrial.util.C

object Sounds {
    @JvmField
    val THEME_MUSIC = SoundEvent(Identifier(C.MOD_ID, "theme_song"))
    private val ULEXITE_SOUND_PLACE = SoundEvent(Identifier(C.MOD_ID, "ulexite_sound_place"))
    private val ULEXITE_SOUND_BREAK = SoundEvent(Identifier(C.MOD_ID, "ulexite_sound_break"))
    private val CERAMIC_SOUND_PLACE = SoundEvent(Identifier(C.MOD_ID, "ceramic_place"))
    private val CERAMIC_SOUND_BREAK = SoundEvent(Identifier(C.MOD_ID, "ceramic_break"))
    private val METAL_BLOCK = SoundEvent(Identifier(C.MOD_ID, "metal_block"))
    @JvmField
    val ULEXITE_BSG = BlockSoundGroup(0.001f, 1.2f, ULEXITE_SOUND_BREAK, SoundEvents.BLOCK_STONE_STEP, ULEXITE_SOUND_PLACE, SoundEvents.BLOCK_STONE_HIT, SoundEvents.BLOCK_STONE_FALL)
    @JvmField
    val CERAMIC_BSG = BlockSoundGroup(0.001f, 1.2f, CERAMIC_SOUND_BREAK, SoundEvents.BLOCK_STONE_STEP, CERAMIC_SOUND_PLACE, SoundEvents.BLOCK_STONE_HIT, SoundEvents.BLOCK_STONE_FALL)
    @JvmField
    val METAL_BLOCK_BSG = BlockSoundGroup(0.001f, 1.0f, METAL_BLOCK, SoundEvents.BLOCK_METAL_STEP, METAL_BLOCK, METAL_BLOCK, METAL_BLOCK)

    @JvmStatic
    fun init() {
        Registry.register(Registry.SOUND_EVENT, Identifier(C.MOD_ID, "theme_song"), THEME_MUSIC)
        Registry.register(Registry.SOUND_EVENT, Identifier(C.MOD_ID, "ulexite_sound_place"), ULEXITE_SOUND_PLACE)
        Registry.register(Registry.SOUND_EVENT, Identifier(C.MOD_ID, "ulexite_sound_break"), ULEXITE_SOUND_BREAK)
        Registry.register(Registry.SOUND_EVENT, Identifier(C.MOD_ID, "ceramic_place"), CERAMIC_SOUND_PLACE)
        Registry.register(Registry.SOUND_EVENT, Identifier(C.MOD_ID, "ceramic_break"), CERAMIC_SOUND_BREAK)
        Registry.register(Registry.SOUND_EVENT, Identifier(C.MOD_ID, "metal_block"), METAL_BLOCK)

        Registry.register(Registry.ITEM, Identifier(C.MOD_ID, "theme_disc"), ProjectIndustrialMod.THEME_DISC)
    }
}