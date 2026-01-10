package cn.leolezury.zenith.client.event;

import cn.leolezury.zenith.ZenithMod;
import cn.leolezury.zenith.network.ZNetworkHandler;
import cn.leolezury.zenith.network.ZenithAttackPacket;
import cn.leolezury.zenith.registry.ZItems;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@OnlyIn(Dist.CLIENT)
@Mod.EventBusSubscriber(modid = ZenithMod.ID, value = Dist.CLIENT)
public class ZClientEvents {
	public static boolean isZenithAttacking() {
		return Minecraft.getInstance().options.keyAttack.isDown() || Minecraft.getInstance().options.keyUse.isDown();
	}

	@SubscribeEvent
    public static void onClientTick(TickEvent.ClientTickEvent event) {
        if (event.phase == TickEvent.Phase.END) {
            LocalPlayer player = Minecraft.getInstance().player;
            if (isZenithAttacking()
                    && player != null
                    && player.getMainHandItem().is(ZItems.ZENITH.get())
                    && !player.isUsingItem()) {
                ZNetworkHandler.INSTANCE.sendToServer(new ZenithAttackPacket(player.getId()));
            }
        }
	}
}
