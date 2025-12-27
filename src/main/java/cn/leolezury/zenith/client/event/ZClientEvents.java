package cn.leolezury.zenith.client.event;

import cn.leolezury.zenith.ZenithMod;
import cn.leolezury.zenith.client.renderer.entity.ZenithSlashRenderer;
import cn.leolezury.zenith.network.ZenithAttackPacket;
import cn.leolezury.zenith.registry.ZDataComponents;
import cn.leolezury.zenith.registry.ZEntityTypes;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.ClientTickEvent;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.network.PacketDistributor;

@OnlyIn(Dist.CLIENT)
@EventBusSubscriber(modid = ZenithMod.ID, value = Dist.CLIENT)
public class ZClientEvents {
	@SubscribeEvent
	private static void onRegisterEntityRenderers(EntityRenderersEvent.RegisterRenderers event) {
		event.registerEntityRenderer(ZEntityTypes.ZENITH_SLASH.get(), ZenithSlashRenderer::new);
	}

	public static boolean isZenithAttacking() {
		return Minecraft.getInstance().options.keyAttack.isDown() || Minecraft.getInstance().options.keyUse.isDown();
	}

	@SubscribeEvent
	private static void onPostClientTick(ClientTickEvent.Post event) {
		LocalPlayer player = Minecraft.getInstance().player;
		if (isZenithAttacking()
			&& player != null
			&& player.getMainHandItem().has(ZDataComponents.ZENITH_PARTS.get())
			&& !player.isUsingItem()) {
			PacketDistributor.sendToServer(new ZenithAttackPacket(player.getId()));
		}
	}
}
