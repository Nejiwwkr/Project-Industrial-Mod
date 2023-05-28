package net.nejiwwkr.project_industrial.screen.handler;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.ArrayPropertyDelegate;
import net.minecraft.screen.PropertyDelegate;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.slot.Slot;
import net.nejiwwkr.project_industrial.ProjectIndustrialMod;

import static net.nejiwwkr.project_industrial.ProjectIndustrialMod.BORAX;
import static net.nejiwwkr.project_industrial.util.NBT_TAG_Util.isFuel;

public class AlloyFurnaceBlockScreenHandler extends ScreenHandler {
    public Inventory inv;
    private final PropertyDelegate propertyDelegate;

    public AlloyFurnaceBlockScreenHandler (int syncId, PlayerInventory playerInventory) {
        this(syncId,playerInventory,new SimpleInventory(8),new ArrayPropertyDelegate(4));
    }

    public AlloyFurnaceBlockScreenHandler(int syncId, PlayerInventory playerInventory, Inventory inventory, PropertyDelegate propertyDelegate) {
        super(ProjectIndustrialMod.AF_SCREEN_HANDLER,syncId);
        this.inv = inventory;
        this.propertyDelegate = propertyDelegate;
        this.addProperties(propertyDelegate);
        checkSize(inventory,8);
        checkDataCount(propertyDelegate,4);

        this.addSlot(new Slot(this.inv,0,8,20){
            @Override
            public boolean canInsert(ItemStack stack) {
                return true;
            }
        });
        this.addSlot(new Slot(this.inv,1,26,20){
            @Override
            public boolean canInsert(ItemStack stack) {
                return true;
            }
        });
        this.addSlot(new Slot(this.inv,2,44,20){
            @Override
            public boolean canInsert(ItemStack stack) {
                return true;
            }
        });
        this.addSlot(new Slot(this.inv,3,62,20){
            @Override
            public boolean canInsert(ItemStack stack) {
                return true;
            }
        });
        this.addSlot(new Slot(this.inv,4,80,20){
            @Override
            public boolean canInsert(ItemStack stack) {
                return true;
            }
        });

        this.addSlot(new Slot(this.inv,5,138,38){
            @Override
            public boolean canInsert(ItemStack stack) {
                return false;
            }
        });

        this.addSlot(new Slot(this.inv,6,8,51){
            @Override
            public boolean canInsert(ItemStack stack) {
                return stack.getItem() == BORAX;
            }
        });
        this.addSlot(new Slot(this.inv,7,44,51){
            @Override
            public boolean canInsert(ItemStack stack) {
                return isFuel(stack);
            }
        });

        //绘制玩家物品栏
        int i;
        for (i = 0;i < 3;++i){
            for (int j = 0; j < 9; ++j) {
                this.addSlot(new Slot(playerInventory,j + i*9 +9,8 + j*18,84 + i*18));
            }
        }
        for (i = 0;i <9;++i) {
            this.addSlot(new Slot(playerInventory,i,8 + i*18,142));
        }
    }

    public boolean canUse(PlayerEntity player) {
        return true;
    }

    @Override
    public ItemStack transferSlot(PlayerEntity player, int invSlot) {
        ItemStack newStack = ItemStack.EMPTY;
        Slot slot = this.slots.get(invSlot);
        if (slot != null && slot.hasStack()) {
            ItemStack originalStack = slot.getStack();
            newStack = originalStack.copy();
            if (invSlot < this.inv.size()) {
                if (!this.insertItem(originalStack, this.inv.size(), this.slots.size(), true)) {
                    return ItemStack.EMPTY;
                }
            } else if (!this.insertItem(originalStack, 0, this.inv.size(), false)) {
                return ItemStack.EMPTY;
            }

            if (originalStack.isEmpty()) {
                slot.setStack(ItemStack.EMPTY);
            } else {
                slot.markDirty();
            }
        }
        return newStack;
    }

    public int getTick() {
        return this.propertyDelegate.get(0);
    }

    public int getFuelLeft() {
        return this.propertyDelegate.get(1);
    }

    public int getFuelTotal() {
        return this.propertyDelegate.get(2);
    }

    public int getCookTime() {
        return this.propertyDelegate.get(3);
    }
}
