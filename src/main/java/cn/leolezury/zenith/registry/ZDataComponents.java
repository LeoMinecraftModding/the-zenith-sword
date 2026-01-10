package cn.leolezury.zenith.registry;

import cn.leolezury.zenith.ZenithMod;
import cn.leolezury.zenith.item.ZenithPart;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.codec.ByteBufCodecs;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.List;

public class ZDataComponents {
	public static final DeferredRegister.DataComponents DATA_COMPONENTS = DeferredRegister.createDataComponents(Registries.DATA_COMPONENT_TYPE, ZenithMod.ID);

	public static final DeferredHolder<DataComponentType<?>, DataComponentType<List<ZenithPart>>> ZENITH_PARTS = DATA_COMPONENTS.register("zenith_parts", () -> DataComponentType.<List<ZenithPart>>builder()
		.persistent(ZenithPart.LIST_CODEC)
		.networkSynchronized(ZenithPart.STREAM_CODEC.apply(ByteBufCodecs.list()))
		.cacheEncoding()
		.build());
}
