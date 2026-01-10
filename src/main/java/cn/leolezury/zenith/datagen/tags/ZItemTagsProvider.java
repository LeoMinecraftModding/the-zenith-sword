package cn.leolezury.zenith.datagen.tags;

import cn.leolezury.zenith.ZenithMod;
import cn.leolezury.zenith.registry.ZItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.data.ExistingFileHelper;

import java.util.concurrent.CompletableFuture;

public class ZItemTagsProvider extends ItemTagsProvider {
	public ZItemTagsProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> future, CompletableFuture<TagLookup<Block>> provider, ExistingFileHelper helper) {
		super(output, future, provider, ZenithMod.ID, helper);
	}

	@Override
	protected void addTags(HolderLookup.Provider lookupProvider) {
		tag(ItemTags.SWORDS)
			.add(
				ZItems.ZENITH.get()
			);
	}

	@Override
	public IntrinsicTagAppender<Item> tag(TagKey<Item> tag) {
		return super.tag(tag);
	}
}
