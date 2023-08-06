package net.nejiwwkr.project_industrial.block.entity;

import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.block.entity.LootableContainerBlockEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventories;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.screen.PropertyDelegate;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.nejiwwkr.project_industrial.crafting.AlloyRecipe;
import net.nejiwwkr.project_industrial.screen.handler.AlloyFurnaceBlockScreenHandler;
import org.jetbrains.annotations.NotNull;

import static net.nejiwwkr.project_industrial.ProjectIndustrialMod.ALLOY_FURNACE_ENTITY;
import static net.nejiwwkr.project_industrial.crafting.AlloyRecipe.matchesWhich;
import static net.nejiwwkr.project_industrial.util.NbtTagUtil.getFuelTime;

public class AlloyFurnaceBlockEntity extends LootableContainerBlockEntity {
    private DefaultedList<ItemStack> inv = DefaultedList.ofSize(8,ItemStack.EMPTY);


    public AlloyFurnaceBlockEntity(BlockPos pos, BlockState state) {
        super(ALLOY_FURNACE_ENTITY, pos, state);
    }

    @Override
    public DefaultedList<ItemStack> getInvStackList() {
        return this.inv;
    }

    @Override
    public void setInvStackList(DefaultedList<ItemStack> list) {
        this.inv = list;
    }

    @Override
    public Text getContainerName() {
        return new TranslatableText("block.project_industrial.alloy_furnace");
    }

    @Override
    protected ScreenHandler createScreenHandler(int syncId, PlayerInventory playerInventory) {
        return new AlloyFurnaceBlockScreenHandler(syncId,playerInventory,this,this.propertyDelegate);
    }

    @Override
    public int size() {
        return 8;
    }

    //计时器
    private int tick = 0;
    private int fuelLeft = 0;
    private int fuelTotal = 0;
    private int cookTime = 0;
    private final PropertyDelegate propertyDelegate = new PropertyDelegate() {
        @Override
        public int get(int index) {
            return switch (index) {
                case 0 -> tick;
                case 1 -> fuelLeft;
                case 2 -> fuelTotal;
                case 3 -> cookTime;
                default-> 0;
            };
        }

        @Override
        public void set(int index, int value) {
            switch (index) {
                case 0 -> tick = value;
                case 1 -> fuelLeft = value;
                case 2 -> fuelTotal = value;
                case 3 -> cookTime = value;
            }
        }

        @Override
        public int size() {
            return 4;
        }
    };

    public static void tick(World world, BlockPos pos, BlockState state, AlloyFurnaceBlockEntity entity) {
        //refresh(entity);

        //如果燃料还有
        if (entity.fuelLeft > 0){
            entity.fuelLeft--;

            //如果主要材料非空
            if (!entity.inv.get(0).isEmpty()) {
                Item mainIngredient = entity.inv.get(0).getItem();
                Item[] sideIngredients = new Item[]{
                        entity.inv.get(1).getItem(),
                        entity.inv.get(2).getItem(),
                        entity.inv.get(3).getItem(),
                        entity.inv.get(4).getItem(),
                };

                //如果物品配方匹配
                if (AlloyRecipe.matches(mainIngredient, sideIngredients)) {
                    var key = matchesWhich(mainIngredient, sideIngredients);
                    var resItem = entity.inv.get(5);

                    //如果结果栏有位置，硼砂还有，开始计时
                    if (key.getLeft().equals(resItem.getItem()) || resItem.isEmpty() && !entity.inv.get(6).isEmpty()) {
                        entity.tick++;
                        entity.cookTime = key.getRight();

                        //时间到了就设置物品，删除物品
                        if (entity.tick >= entity.cookTime) {
                            if (resItem.isEmpty()) entity.inv.set(5, new ItemStack(key.getLeft()));
                            else resItem.increment(1);

                            entity.inv.get(0).decrement(1);
                            entity.inv.get(6).decrement(1);
                            for (int i = 1; i <= 4; i++)
                                if (!entity.inv.get(i).isEmpty()) entity.inv.get(i).decrement(1);

                            entity.tick = 0;
                            entity.cookTime = 0;
                        }
                    }
                    //TODO 燃料相關動畫

                    //Testing.checkTickerIsWorking(world, String.valueOf(entity.tick));
                    //Testing.checkTickerIsWorking(world, String.valueOf(key.getRight()));
                }
            }
        }

        //燃料太少补充
        if (entity.fuelLeft <= 0) {
            var fuelSlot = entity.inv.get(7);
            int fuelTime = getFuelTime(fuelSlot);
            entity.fuelLeft += fuelTime;
            entity.fuelTotal = fuelTime;

            if (entity.inv.get(7).getItem().equals(Items.LAVA_BUCKET)) {
                fuelSlot.decrement(1);
                entity.inv.set(7,new ItemStack(Items.BUCKET));
            }else {
                fuelSlot.decrement(1);
            }
        }
        //Testing.checkTickerIsWorking(world,entity.tick + "   " + entity.cookTime + "\n" + entity.fuelLeft + "   " + entity.fuelTotal + "\n");

    }

    @Override
    public void writeNbt(NbtCompound nbt) {
        super.writeNbt(nbt);
        Inventories.writeNbt(nbt,inv);
        nbt.putInt("tick",this.tick);
        nbt.putInt("fuel",this.fuelLeft);
        nbt.putInt("fuel_total",this.fuelTotal);
    }

    @Override
    public void readNbt(NbtCompound nbt) {
        super.readNbt(nbt);
        Inventories.readNbt(nbt,inv);
        this.tick = nbt.getInt("tick");
        this.fuelLeft = nbt.getInt("fuel");
        this.fuelTotal = nbt.getInt("fuel_total");
    }

    /**
     * Very Important
     * @return ALLOY_FURNACE_TYPE
     */
    @Override
    public BlockEntityType<?> getType() {
        return ALLOY_FURNACE_ENTITY;
    }

    @Deprecated
    private static void refresh(@NotNull AlloyFurnaceBlockEntity e) {
        e.propertyDelegate.set(0,e.tick);
        e.propertyDelegate.set(1,e.fuelLeft);
        e.propertyDelegate.set(2,e.fuelTotal);
        e.propertyDelegate.set(3,e.cookTime);
    }
}
