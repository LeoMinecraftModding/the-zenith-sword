package cn.leolezury.zenith.datagen.model;

import cn.leolezury.zenith.ZenithMod;
import cn.leolezury.zenith.registry.ZItems;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.client.model.generators.ModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

public class ZItemModelProvider extends ItemModelProvider {
	public ZItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
		super(output, ZenithMod.ID, existingFileHelper);
	}

	@Override
	protected void registerModels() {
		handheld(ZItems.TRUE_WOODEN_SWORD.get(), itemTexture(Items.WOODEN_SWORD));
	}

	private ItemModelBuilder handheld(Item item, ResourceLocation texture) {
		return getBuilder(item.toString())
			.parent(new ModelFile.UncheckedModelFile("item/handheld"))
			.texture("layer0", texture);
	}

	public ResourceLocation itemTexture(Item item) {
		ResourceLocation name = key(item);
		return texture(name, ModelProvider.ITEM_FOLDER);
	}

	public ResourceLocation texture(ResourceLocation key, String prefix) {
		return ResourceLocation.fromNamespaceAndPath(key.getNamespace(), prefix + "/" + key.getPath());
	}

	private ResourceLocation key(Item item) {
		return BuiltInRegistries.ITEM.getKey(item);
	}
}
