package cn.leolezury.zenith.datagen;

import cn.leolezury.zenith.registry.ZItems;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.ShapelessRecipeBuilder;
import net.minecraft.world.item.Items;

import java.util.function.Consumer;

public class ZRecipeProvider extends RecipeProvider {
    public ZRecipeProvider(PackOutput output) {
        super(output);
	}

	@Override
    protected void buildRecipes(Consumer<FinishedRecipe> recipeOutput) {
		ShapelessRecipeBuilder.shapeless(RecipeCategory.COMBAT, ZItems.ZENITH.get())
			.requires(Items.WOODEN_SWORD)
			.requires(Items.STONE_SWORD)
			.requires(Items.GOLDEN_SWORD)
			.requires(Items.IRON_SWORD)
			.requires(Items.DIAMOND_SWORD)
			.requires(Items.NETHERITE_SWORD)
			.requires(Items.HEART_OF_THE_SEA)
			.requires(Items.NETHER_STAR)
			.requires(Items.DRAGON_BREATH)
			.unlockedBy(getHasName(Items.NETHERITE_SWORD), has(Items.NETHERITE_SWORD))
			.unlockedBy(getHasName(Items.NETHER_STAR), has(Items.NETHER_STAR))
			.unlockedBy(getHasName(Items.DRAGON_BREATH), has(Items.DRAGON_BREATH))
			.save(recipeOutput);
	}
}
