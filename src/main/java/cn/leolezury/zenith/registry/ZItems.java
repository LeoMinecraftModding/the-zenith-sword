package cn.leolezury.zenith.registry;

import cn.leolezury.zenith.ZenithMod;
import cn.leolezury.zenith.item.ZenithItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ZItems {
	public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, ZenithMod.ID);

	public static final RegistryObject<ZenithItem> ZENITH = ITEMS.register("zenith", () ->
		new ZenithItem(new Item.Properties()
			.rarity(Rarity.EPIC)
			.stacksTo(1)));

	public static final RegistryObject<ZenithItem> TRUE_WOODEN_SWORD = ITEMS.register("true_wooden_sword", () ->
		new ZenithItem(new Item.Properties()
			.rarity(Rarity.EPIC)
			.stacksTo(1)));
}
