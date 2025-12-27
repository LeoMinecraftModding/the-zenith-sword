package cn.leolezury.zenith.datagen.tags;

import cn.leolezury.zenith.ZenithMod;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.BlockTagsProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

import java.util.concurrent.CompletableFuture;

public class ZBlockTagsProvider extends BlockTagsProvider {
	public ZBlockTagsProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> future, ExistingFileHelper helper) {
		super(output, future, ZenithMod.ID, helper);
	}

	@Override
	protected void addTags(HolderLookup.Provider lookupProvider) {

	}
}
