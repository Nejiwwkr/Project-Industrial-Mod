package net.nejiwwkr.project_industrial.util.interfaces;

import net.minecraft.nbt.NbtCompound;

public interface NonAutoNbt {
    void writeNbt(NbtCompound nbt);
    void readNbt(NbtCompound nbt);
}
