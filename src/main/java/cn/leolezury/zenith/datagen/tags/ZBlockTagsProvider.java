package cn.leolezury.zenith.datagen.tags;

import cn.leolezury.zenith.ZenithMod;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.BlockTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

import java.util.concurrent.CompletableFuture;

public class ZBlockTagsProvider extends BlockTagsProvider {
    public ZBlockTagsProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> future, ExistingFileHelper helper) {
        super(output, future, ZenithMod.ID, helper);
    }

    @Override
    protected void addTags(HolderLookup.Provider lookupProvider) {

    }
}
