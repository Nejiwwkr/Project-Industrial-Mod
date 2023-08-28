package net.nejiwwkr.project_industrial.item.abstract_mod_item;

import com.google.errorprone.annotations.CanIgnoreReturnValue;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.world.World;
import net.nejiwwkr.project_industrial.util.annotation.Essential;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public interface Instructed<T extends Item> {
    boolean hasInstruct();
    @CanIgnoreReturnValue
    T setTips(Text... tips);
    @CanIgnoreReturnValue
    T setSummary(Text... sum);
    @CanIgnoreReturnValue
    T appendInformation(Text... info);
    @CanIgnoreReturnValue
    T appendTips(Text... tips);
    @CanIgnoreReturnValue
    T appendSummary(Text... sum);
    @CanIgnoreReturnValue
    T setInformation(Text... info);
    @Essential
    void appendToolInstruct(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context, InstructType... type);
}
