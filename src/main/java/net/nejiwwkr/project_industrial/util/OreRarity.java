package net.nejiwwkr.project_industrial.util;

public enum OreRarity {
    LEGENDARY(3, 6, 1, 3),
    RARE(4, 6, 5, 7),
    UNCOMMON(5, 7, 7, 10),
    NORMAL(6, 8, 10, 14),
    COMMON(7, 9, 15, 20),
    FLAT_STONE(80, 60, 22, 16),
    SUPERNATANT_STONE(60,1,26,1);

    final int size;
    final int deepslateSize;
    final int count;
    final int deepslateCount;

    OreRarity(int size, int deepslateSize, int count, int deepslateCount) {
        this.size = size;
        this.deepslateSize = deepslateSize;
        this.count = deepslateCount;
        this.deepslateCount = deepslateCount;
    }
}
