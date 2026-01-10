package cn.leolezury.zenith.registry;

import cn.leolezury.zenith.ZenithMod;
import cn.leolezury.zenith.item.ZenithPart;
import net.minecraft.network.syncher.EntityDataSerializer;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

public class ZEntityDataSerializers {
	public static final DeferredRegister<EntityDataSerializer<?>> ENTITY_DATA_SERIALIZERS = DeferredRegister.create(NeoForgeRegistries.ENTITY_DATA_SERIALIZERS, ZenithMod.ID);

	public static final DeferredHolder<EntityDataSerializer<?>, EntityDataSerializer<ZenithPart>> ZENITH_PART = ENTITY_DATA_SERIALIZERS.register("zenith_part", () -> EntityDataSerializer.forValueType(ZenithPart.STREAM_CODEC));
}
