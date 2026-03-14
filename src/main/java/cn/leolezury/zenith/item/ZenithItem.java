package cn.leolezury.zenith.item;

import cn.leolezury.zenith.ZConfig;
import cn.leolezury.zenith.registry.ZItems;
import net.minecraft.core.component.DataComponents;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.util.Mth;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.component.ItemAttributeModifiers;

import java.util.List;

public class ZenithItem extends Item {
	public ZenithItem(Properties properties) {
		super(properties
			.component(DataComponents.ATTRIBUTE_MODIFIERS, createAttributes())
			.component(DataComponents.TOOL, SwordItem.createToolProperties()));
	}

	public static ItemAttributeModifiers createAttributes() {
		return ItemAttributeModifiers.builder()
			.add(
				Attributes.ATTACK_DAMAGE,
				new AttributeModifier(BASE_ATTACK_DAMAGE_ID, ZConfig.ZENITH_ATTACK_DAMAGE_ADDITION.get(), AttributeModifier.Operation.ADD_VALUE),
				EquipmentSlotGroup.MAINHAND
			)
			.build();
	}

	public static List<ZenithPart> createZenithParts() {
		return List.of(
			new ZenithPart(Items.WOODEN_SWORD.builtInRegistryHolder(), 0x594319, 0.125, Mth.HALF_PI * 0.5f, 1.75, 1),
			new ZenithPart(Items.STONE_SWORD.builtInRegistryHolder(), 0x787777, 0.125, Mth.HALF_PI * 0.5f, 1.75, 1),
			new ZenithPart(Items.GOLDEN_SWORD.builtInRegistryHolder(), 0xffe745, 0.125, Mth.HALF_PI * 0.5f, 1.75, 1),
			new ZenithPart(Items.IRON_SWORD.builtInRegistryHolder(), 0xbebebe, 0.125, Mth.HALF_PI * 0.5f, 1.75, 1),
			new ZenithPart(Items.DIAMOND_SWORD.builtInRegistryHolder(), 0x54eaf5, 0.125, Mth.HALF_PI * 0.5f, 1.75, 1),
			new ZenithPart(Items.NETHERITE_SWORD.builtInRegistryHolder(), 0x7a42bf, 0.125, Mth.HALF_PI * 0.5f, 1.75, 1),
			new ZenithPart(ZItems.ZENITH, 0xb2ffb4, 0.125, Mth.HALF_PI * 0.5f, 3.5, 1.5)
		);
	}

	public static List<ZenithPart> createTrueWoodenSwordParts() {
		return List.of(
			new ZenithPart(Items.WOODEN_SWORD.builtInRegistryHolder(), 0x594319, 0.125, Mth.HALF_PI * 0.5f, 1.75, 1)
		);
	}

	@Override
	public int getEnchantmentValue() {
		return 25;
	}

	@Override
	public boolean canBeHurtBy(ItemStack stack, DamageSource source) {
		return source.is(DamageTypeTags.BYPASSES_INVULNERABILITY);
	}
}
