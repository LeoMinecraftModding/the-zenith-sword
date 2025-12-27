package cn.leolezury.zenith.registry;

import cn.leolezury.zenith.ZenithMod;
import cn.leolezury.zenith.entity.ZenithSlash;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ZEntityTypes {
	public static final DeferredRegister<EntityType<?>> ENTITY_TYPES = DeferredRegister.create(BuiltInRegistries.ENTITY_TYPE, ZenithMod.ID);

	public static final DeferredHolder<EntityType<?>, EntityType<ZenithSlash>> ZENITH_SLASH = ENTITY_TYPES.register("zenith_slash", () -> EntityType.Builder
		.<ZenithSlash>of(ZenithSlash::new, MobCategory.MISC)
		.sized(0, 0)
		.clientTrackingRange(10)
		.updateInterval(1)
		.build(ZenithMod.strId("zenith_slash")));
}
