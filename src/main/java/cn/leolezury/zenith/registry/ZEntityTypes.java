package cn.leolezury.zenith.registry;

import cn.leolezury.zenith.ZenithMod;
import cn.leolezury.zenith.entity.ZenithSlash;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ZEntityTypes {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES = DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, ZenithMod.ID);

    public static final RegistryObject<EntityType<ZenithSlash>> ZENITH_SLASH = ENTITY_TYPES.register("zenith_slash", () -> EntityType.Builder
		.<ZenithSlash>of(ZenithSlash::new, MobCategory.MISC)
		.sized(0, 0)
		.clientTrackingRange(10)
		.updateInterval(1)
		.build(ZenithMod.strId("zenith_slash")));
}
