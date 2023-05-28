package net.nejiwwkr.project_industrial.crafting.alloy;

import com.google.gson.*;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.util.Identifier;
import net.minecraft.util.JsonHelper;
import net.minecraft.util.registry.Registry;

import java.util.ArrayList;
import java.util.List;

public class AlloyRecipeSerializer implements RecipeSerializer<AlloyRecipe> {
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().disableHtmlEscaping().create();

    @Override
    public AlloyRecipe read(Identifier recipeId, JsonObject json) {
        // 从 JSON 数据中读取配方信息，然后创建一个新的 AlloyRecipe 对象, 返回解析后的数据
        Item outputItem = Registry.ITEM.getOrEmpty(new Identifier(JsonHelper.getString(json, "result")))
                .orElseThrow(() -> new IllegalStateException("Invalid item: " + JsonHelper.getString(json, "result")));
        ItemStack output = new ItemStack(outputItem, JsonHelper.getInt(json, "count", 1));

        int cookTime = JsonHelper.getInt(json, "cookTime");

        JsonArray additivesJson = JsonHelper.getArray(json, "additives");
        List<Ingredient> additives = new ArrayList<>();
        for (JsonElement element : additivesJson) {
            additives.add(Ingredient.fromJson(element));
        }

        Ingredient base = Ingredient.fromJson(json.get("base"));

        return new AlloyRecipe(recipeId, base, additives, output, cookTime);
    }

    @Override
    public AlloyRecipe read(Identifier recipeId, PacketByteBuf buffer) {
        // 从指定的ByteBuf读取配方信息
        int cookTime = buffer.readVarInt();
        Ingredient base = Ingredient.fromPacket(buffer);
        int additivesSize = buffer.readVarInt();
        List<Ingredient> additives = new ArrayList<>(additivesSize);
        for (int i = 0; i < additivesSize; i++) {
            additives.add(Ingredient.fromPacket(buffer));
        }
        ItemStack output = buffer.readItemStack();
        return new AlloyRecipe(recipeId, base, additives, output, cookTime);
    }

    @Override
    public void write(PacketByteBuf buffer, AlloyRecipe recipe) {
        // 将配方信息写入给定的ByteBuf中
        buffer.writeVarInt(recipe.getCookTime());
        recipe.getBase().write(buffer);
        buffer.writeVarInt(recipe.getAdditives().size());
        for (Ingredient ingredient : recipe.getAdditives()) {
            ingredient.write(buffer);
        }
        buffer.writeItemStack(recipe.getOutput());
    }

    public void write(JsonObject json, AlloyRecipe recipe) {
        // 将配方信息写入给定的JSON对象中
        json.addProperty("result", recipe.getOutput().getItem().getName().toString());
        if (recipe.getOutput().getCount() > 1) {
            json.addProperty("count", recipe.getOutput().getCount());
        }
        json.addProperty("cookTime", recipe.getCookTime());

        JsonArray additivesJson = new JsonArray();
        for (Ingredient ingredient : recipe.getAdditives()) {
            additivesJson.add(ingredient.toJson());
        }
        json.add("additives", additivesJson);

        json.add("base", recipe.getBase().toJson());
    }
}