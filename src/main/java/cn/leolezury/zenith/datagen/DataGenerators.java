package cn.leolezury.zenith.datagen;

import cn.leolezury.zenith.ZenithMod;
import cn.leolezury.zenith.datagen.tags.ZBlockTagsProvider;
import cn.leolezury.zenith.datagen.tags.ZItemTagsProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.concurrent.CompletableFuture;

@Mod.EventBusSubscriber(modid = ZenithMod.ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class DataGenerators {
    @SubscribeEvent
    public static void onGatherData(GatherDataEvent event) {
        DataGenerator generator = event.getGenerator();
        PackOutput output = generator.getPackOutput();
        CompletableFuture<HolderLookup.Provider> lookupProvider = event.getLookupProvider();
        ExistingFileHelper helper = event.getExistingFileHelper();

        ZBlockTagsProvider blockTags = new ZBlockTagsProvider(output, lookupProvider, helper);
        generator.addProvider(event.includeServer(), blockTags);
        generator.addProvider(event.includeServer(), new ZItemTagsProvider(output, lookupProvider, blockTags.contentsGetter(), helper));

        generator.addProvider(event.includeServer(), new ZRecipeProvider(output));
    }
}
