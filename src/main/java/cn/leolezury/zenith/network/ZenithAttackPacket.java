package cn.leolezury.zenith.network;

import cn.leolezury.zenith.entity.ZenithSlash;
import cn.leolezury.zenith.item.ZenithItem;
import cn.leolezury.zenith.item.ZenithPart;
import cn.leolezury.zenith.registry.ZEntityTypes;
import cn.leolezury.zenith.registry.ZItems;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.network.NetworkEvent;

import java.util.List;
import java.util.function.Supplier;

public record ZenithAttackPacket(int playerId) {
	public static ZenithAttackPacket read(FriendlyByteBuf byteBuf) {
		return new ZenithAttackPacket(byteBuf.readInt());
	}

	public static void write(ZenithAttackPacket packet, FriendlyByteBuf byteBuf) {
		byteBuf.writeInt(packet.playerId());
	}

	public static void handle(ZenithAttackPacket packet, Supplier<NetworkEvent.Context> context) {
		context.get().enqueueWork(() -> {
			Player player = context.get().getSender();
			if (player != null
				&& player.getId() == packet.playerId()
				&& player.getMainHandItem().is(ZItems.ZENITH.get())
				&& !player.isUsingItem()) {
				ZenithSlash slash = new ZenithSlash(ZEntityTypes.ZENITH_SLASH.get(), player.level(), player);
				List<ZenithPart> parts = ZenithItem.getZenithParts(player.getMainHandItem());
				if (!parts.isEmpty()) {
					slash.setZenithPart(parts.get(player.getRandom().nextInt(parts.size())));
				}
				player.level().addFreshEntity(slash);
				player.swing(InteractionHand.MAIN_HAND, true);
			}
		});
		context.get().setPacketHandled(true);
	}
}
