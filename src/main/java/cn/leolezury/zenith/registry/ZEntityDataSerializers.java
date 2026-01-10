package cn.leolezury.zenith.registry;

import cn.leolezury.zenith.ZenithMod;
import cn.leolezury.zenith.item.ZenithPart;
import net.minecraft.network.syncher.EntityDataSerializer;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ZEntityDataSerializers {
    public static final DeferredRegister<EntityDataSerializer<?>> ENTITY_DATA_SERIALIZERS = DeferredRegister.create(ForgeRegistries.Keys.ENTITY_DATA_SERIALIZERS, ZenithMod.ID);

    public static final RegistryObject<EntityDataSerializer<ZenithPart>> ZENITH_PART = ENTITY_DATA_SERIALIZERS.register("zenith_part", () -> EntityDataSerializer.simple((byteBuf, part) -> part.toNetwork(byteBuf), ZenithPart::fromNetwork));
}
