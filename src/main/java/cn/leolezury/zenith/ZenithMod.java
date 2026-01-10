package cn.leolezury.zenith;

import cn.leolezury.zenith.registry.ZCreativeModeTabs;
import cn.leolezury.zenith.registry.ZEntityDataSerializers;
import cn.leolezury.zenith.registry.ZEntityTypes;
import cn.leolezury.zenith.registry.ZItems;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(ZenithMod.ID)
public class ZenithMod {
	public static final String ID = "zenith";

    public ZenithMod(FMLJavaModLoadingContext context) {
        IEventBus modBus = context.getModEventBus();
		ZItems.ITEMS.register(modBus);
		ZCreativeModeTabs.TABS.register(modBus);
		ZEntityDataSerializers.ENTITY_DATA_SERIALIZERS.register(modBus);
		ZEntityTypes.ENTITY_TYPES.register(modBus);
	}

	public static ResourceLocation id(String string) {
		return ResourceLocation.fromNamespaceAndPath(ID, string);
	}

	public static String strId(String string) {
		return ID + ":" + string;
	}
}
