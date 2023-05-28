package net.nejiwwkr.project_industrial.mixin;

import net.minecraft.block.Blocks;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(Blocks.class)
public class BlocksMixin {

//    /**
//     * @author Nejiwwkr
//     * @reason Allow bricks to saltpetering
//     */
//    @Inject(at = @At("HEAD"),method = "register")
//    private static void register(String id, Block block, CallbackInfoReturnable<Block> cir) {
//        if (id.equals("bricks")) {
//            block = new SaltpeterableBlock(SALTPETERING_BRICKS);
//        }
//        cir.cancel();
//    }
}
