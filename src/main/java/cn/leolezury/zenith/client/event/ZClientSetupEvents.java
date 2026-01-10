package cn.leolezury.zenith.client.event;

import cn.leolezury.zenith.ZenithMod;
import cn.leolezury.zenith.client.renderer.entity.ZenithSlashRenderer;
import cn.leolezury.zenith.registry.ZEntityTypes;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@OnlyIn(Dist.CLIENT)
@Mod.EventBusSubscriber(modid = ZenithMod.ID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ZClientSetupEvents {
    @SubscribeEvent
    public static void onRegisterEntityRenderers(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(ZEntityTypes.ZENITH_SLASH.get(), ZenithSlashRenderer::new);
    }
}
