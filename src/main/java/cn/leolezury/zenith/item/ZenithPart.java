package cn.leolezury.zenith.item;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.Item;

public record ZenithPart(Holder<Item> item, int color, double rotationCenterHeight, double rotation, double scale, double trailWidth) {
	public static final Codec<ZenithPart> CODEC = RecordCodecBuilder.create((instance) -> instance.group(
		BuiltInRegistries.ITEM.holderByNameCodec().fieldOf("item").forGetter(ZenithPart::item),
		Codec.INT.fieldOf("color").forGetter(ZenithPart::color),
		Codec.DOUBLE.fieldOf("rotation_center_height").forGetter(ZenithPart::rotationCenterHeight),
		Codec.DOUBLE.fieldOf("rotation").forGetter(ZenithPart::rotation),
		Codec.DOUBLE.fieldOf("scale").forGetter(ZenithPart::scale),
		Codec.DOUBLE.fieldOf("trail_width").forGetter(ZenithPart::trailWidth)
	).apply(instance, ZenithPart::new));

	public static final StreamCodec<RegistryFriendlyByteBuf, ZenithPart> STREAM_CODEC = StreamCodec.composite(
		ByteBufCodecs.fromCodec(BuiltInRegistries.ITEM.holderByNameCodec()), ZenithPart::item,
		ByteBufCodecs.INT, ZenithPart::color,
		ByteBufCodecs.DOUBLE, ZenithPart::rotationCenterHeight,
		ByteBufCodecs.DOUBLE, ZenithPart::rotation,
		ByteBufCodecs.DOUBLE, ZenithPart::scale,
		ByteBufCodecs.DOUBLE, ZenithPart::trailWidth,
		ZenithPart::new
	);
}
