package cn.leolezury.zenith.registry;

import cn.leolezury.zenith.ZenithMod;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ZCreativeModeTabs {
	public static final DeferredRegister<CreativeModeTab> TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, ZenithMod.ID);

	public static final DeferredHolder<CreativeModeTab, CreativeModeTab> ZENITH = TABS.register("zenith", () -> CreativeModeTab.builder()
		.title(Component.translatable("name." + ZenithMod.ID))
		.icon(() -> ZItems.ZENITH.get().getDefaultInstance())
		.displayItems((params, output) -> ZItems.ITEMS.getEntries().forEach(item -> output.accept(item.get()))).build());
}
