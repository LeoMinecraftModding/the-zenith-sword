package cn.leolezury.zenith;

import cn.leolezury.zenith.registry.*;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;

@Mod(ZenithMod.ID)
public class ZenithMod {
	public static final String ID = "zenith";

	public ZenithMod(IEventBus modBus, ModContainer container) {
		container.registerConfig(ModConfig.Type.STARTUP, ZConfig.SPEC);
		ZDataComponents.DATA_COMPONENTS.register(modBus);
		ZItems.ITEMS.register(modBus);
		ZCreativeModeTabs.TABS.register(modBus);
		ZEntityDataSerializers.ENTITY_DATA_SERIALIZERS.register(modBus);
		ZEntityTypes.ENTITY_TYPES.register(modBus);
		ZAttachmentTypes.ATTACHMENT_TYPES.register(modBus);
	}

	public static ResourceLocation id(String string) {
		return ResourceLocation.fromNamespaceAndPath(ID, string);
	}

	public static String strId(String string) {
		return ID + ":" + string;
	}
}
