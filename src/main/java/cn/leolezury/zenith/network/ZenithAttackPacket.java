package cn.leolezury.zenith.network;

import cn.leolezury.zenith.ZenithMod;
import cn.leolezury.zenith.entity.ZenithSlash;
import cn.leolezury.zenith.item.ZenithPart;
import cn.leolezury.zenith.registry.ZDataComponents;
import cn.leolezury.zenith.registry.ZEntityTypes;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;

import java.util.List;

public record ZenithAttackPacket(int playerId) implements CustomPacketPayload {
	public static final Type<ZenithAttackPacket> TYPE = new Type<>(ZenithMod.id("zenith_attack"));
	public static final StreamCodec<RegistryFriendlyByteBuf, ZenithAttackPacket> STREAM_CODEC = StreamCodec.composite(ByteBufCodecs.INT, ZenithAttackPacket::playerId, ZenithAttackPacket::new);

	public static void handle(ZenithAttackPacket packet, Player player) {
		if (player.getId() == packet.playerId()
			&& player.getMainHandItem().has(ZDataComponents.ZENITH_PARTS.get())
			&& !player.isUsingItem()) {
			ZenithSlash slash = new ZenithSlash(ZEntityTypes.ZENITH_SLASH.get(), player.level(), player);
			List<ZenithPart> parts = player.getMainHandItem().get(ZDataComponents.ZENITH_PARTS.get());
			if (parts != null && !parts.isEmpty()) {
				slash.setZenithPart(parts.get(player.getRandom().nextInt(parts.size())));
			}
			player.level().addFreshEntity(slash);
			player.swing(InteractionHand.MAIN_HAND, true);
		}
	}

	@Override
	public Type<? extends CustomPacketPayload> type() {
		return TYPE;
	}
}
