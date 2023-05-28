package net.nejiwwkr.project_industrial.crafting.alloy;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

public enum MetalType {
    GOLD("gold", 4),IRON("iron", 4),COPPER("copper", -2),
    LEAD("lead", 2),SILVER("silver", 4),NICKEL("nickel", 6),URANIUM("uranium", 2),
    MOLYBDENUM("molybdenum",8),CHROME("chrome",3),MANGANESE("manganese",5),
    COAL("coal",0);

    public final String metalName;
    public final int stiffness;
    public Usage usage;

    //return TypedActionResult.success(user.getStackInHand(hand))

    MetalType(String metalName,int stiffness, Usage usage){
        this.metalName = metalName.toLowerCase();
        this.stiffness = stiffness;
        this.usage = usage;
    }

    MetalType(String metalName, int stiffness){
        this.metalName = metalName.toLowerCase();
        this.stiffness = stiffness;
    }
}

@FunctionalInterface
interface Usage {
    TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand);
}