package cn.leolezury.zenith.network;

import cn.leolezury.zenith.ZenithMod;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent;
import net.neoforged.neoforge.network.registration.PayloadRegistrar;

@EventBusSubscriber(modid = ZenithMod.ID)
public class ZNetworkHandler {
	@SubscribeEvent
	public static void onRegisterPayloadHandlersEvent(RegisterPayloadHandlersEvent event) {
		final PayloadRegistrar registrar = event.registrar(ZenithMod.ID)
			.optional();
		registrar.playToServer(ZenithAttackPacket.TYPE, ZenithAttackPacket.STREAM_CODEC, (packet, context) -> context.enqueueWork(() -> ZenithAttackPacket.handle(packet, context.player())));
	}
}
