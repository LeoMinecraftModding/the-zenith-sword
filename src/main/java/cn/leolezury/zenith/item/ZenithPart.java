package cn.leolezury.zenith.item;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.List;

public record ZenithPart(Holder<Item> item, int color, double rotationCenterHeight, double rotation, double scale,
                         double trailWidth) {
    @SuppressWarnings("deprecation")
    public static final Codec<ZenithPart> CODEC = RecordCodecBuilder.create((instance) -> instance.group(
            BuiltInRegistries.ITEM.holderByNameCodec().fieldOf("item").forGetter(ZenithPart::item),
            Codec.INT.fieldOf("color").forGetter(ZenithPart::color),
            Codec.DOUBLE.fieldOf("rotation_center_height").forGetter(ZenithPart::rotationCenterHeight),
            Codec.DOUBLE.fieldOf("rotation").forGetter(ZenithPart::rotation),
            Codec.DOUBLE.fieldOf("scale").forGetter(ZenithPart::scale),
            Codec.DOUBLE.fieldOf("trail_width").forGetter(ZenithPart::trailWidth)
    ).apply(instance, ZenithPart::new));

    public static final Codec<List<ZenithPart>> LIST_CODEC = CODEC.listOf();

    public static ZenithPart fromNetwork(FriendlyByteBuf byteBuf) {
        String item = byteBuf.readUtf();
        int color = byteBuf.readInt();
        double rotationCenterHeight = byteBuf.readDouble();
        double rotation = byteBuf.readDouble();
        double scale = byteBuf.readDouble();
        double trailWidth = byteBuf.readDouble();
        return new ZenithPart(ForgeRegistries.ITEMS.getDelegateOrThrow(ResourceLocation.parse(item)), color, rotationCenterHeight, rotation, scale, trailWidth);
    }

    public void toNetwork(FriendlyByteBuf byteBuf) {
        ResourceLocation itemKey = ForgeRegistries.ITEMS.getKey(item().value());
        if (itemKey != null) {
            byteBuf.writeUtf(itemKey.toString());
            byteBuf.writeInt(color());
            byteBuf.writeDouble(rotationCenterHeight());
            byteBuf.writeDouble(rotation());
            byteBuf.writeDouble(scale());
            byteBuf.writeDouble(trailWidth());
        }
    }
}
