package net.nejiwwkr.project_industrial.util

enum class OreRarity(val size: Int, val deepslateSize: Int, val count: Int, val deepslateCount: Int) {
    LEGENDARY(3, 6, 1, 3),
    RARE(4, 6, 5, 7),
    UNCOMMON(5, 7, 7, 10),
    NORMAL(6, 8, 10, 14),
    COMMON(7, 9, 15, 20),
    FLAT_STONE(80, 60, 22, 16),
    SUPERNATANT_STONE(60, 1, 26, 1);
}