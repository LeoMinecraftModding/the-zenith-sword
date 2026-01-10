package cn.leolezury.zenith.item;

import cn.leolezury.zenith.registry.ZItems;
import com.google.common.base.Suppliers;
import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import net.minecraft.nbt.NbtOps;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

public class ZenithItem extends Item {
    private static final String TAG_ZENITH_PARTS = "zenith_parts";

    private static final Supplier<List<ZenithPart>> defaultParts = Suppliers.memoize(ZenithItem::createZenithParts);
    private final Multimap<Attribute, AttributeModifier> defaultModifiers;

    public ZenithItem(Properties properties) {
        super(properties);
        ImmutableMultimap.Builder<Attribute, AttributeModifier> builder = ImmutableMultimap.builder();
        builder.put(Attributes.ATTACK_DAMAGE, new AttributeModifier(BASE_ATTACK_DAMAGE_UUID, "Weapon modifier", 7, AttributeModifier.Operation.ADDITION));
        this.defaultModifiers = builder.build();
    }

    @Override
    public Multimap<Attribute, AttributeModifier> getAttributeModifiers(EquipmentSlot slot, ItemStack stack) {
        if (slot == EquipmentSlot.MAINHAND) {
            return defaultModifiers;
        }
        return super.getAttributeModifiers(slot, stack);
    }

    @SuppressWarnings("deprecation")
    public static List<ZenithPart> createZenithParts() {
        return List.of(
                new ZenithPart(Items.WOODEN_SWORD.builtInRegistryHolder(), 0x594319, 0.125, Mth.HALF_PI * 0.5f, 1.75, 1),
                new ZenithPart(Items.STONE_SWORD.builtInRegistryHolder(), 0x787777, 0.125, Mth.HALF_PI * 0.5f, 1.75, 1),
                new ZenithPart(Items.GOLDEN_SWORD.builtInRegistryHolder(), 0xffe745, 0.125, Mth.HALF_PI * 0.5f, 1.75, 1),
                new ZenithPart(Items.IRON_SWORD.builtInRegistryHolder(), 0xbebebe, 0.125, Mth.HALF_PI * 0.5f, 1.75, 1),
                new ZenithPart(Items.DIAMOND_SWORD.builtInRegistryHolder(), 0x54eaf5, 0.125, Mth.HALF_PI * 0.5f, 1.75, 1),
                new ZenithPart(Items.NETHERITE_SWORD.builtInRegistryHolder(), 0x7a42bf, 0.125, Mth.HALF_PI * 0.5f, 1.75, 1),
                new ZenithPart(ZItems.ZENITH.get().builtInRegistryHolder(), 0xb2ffb4, 0.125, Mth.HALF_PI * 0.5f, 3.5, 1.5)
        );
    }

    public static List<ZenithPart> getZenithParts(ItemStack stack) {
        List<ZenithPart> parts = new ArrayList<>();
        ZenithPart.LIST_CODEC.parse(NbtOps.INSTANCE, stack.getOrCreateTag().get(TAG_ZENITH_PARTS)).result().ifPresent(parts::addAll);
        if (parts.isEmpty()) {
            parts.addAll(defaultParts.get());
        }
        return parts;
    }

    @Override
    public int getEnchantmentValue() {
        return 25;
    }
}
