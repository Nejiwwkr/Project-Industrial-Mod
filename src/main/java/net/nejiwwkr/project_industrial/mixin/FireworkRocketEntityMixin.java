package net.nejiwwkr.project_industrial.mixin;

import net.minecraft.entity.projectile.FireworkRocketEntity;
import net.nejiwwkr.project_industrial.util.annotation.Dangerous;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(FireworkRocketEntity.class)
public class FireworkRocketEntityMixin {
    /**
     * @author Nejiwwkr
     * @reason Adding custom firework style
     */
    @Overwrite
    @Dangerous
    private void explode() {
    }
}
