package cn.leolezury.zenith.registry;

import cn.leolezury.zenith.ZenithMod;
import cn.leolezury.zenith.item.ZenithItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ZItems {
	public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(ZenithMod.ID);

	public static final DeferredHolder<Item, ZenithItem> ZENITH = ITEMS.register("zenith", () ->
		new ZenithItem(new Item.Properties()
			.component(ZDataComponents.ZENITH_PARTS.get(), ZenithItem.createZenithParts())
			.rarity(Rarity.EPIC)
			.stacksTo(1)));
}
