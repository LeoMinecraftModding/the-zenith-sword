package cn.leolezury.zenith.network;

import cn.leolezury.zenith.ZenithMod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.simple.SimpleChannel;

@Mod.EventBusSubscriber(modid = ZenithMod.ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ZNetworkHandler {
    private static final String PROTOCOL_VERSION = "1";
    public static final SimpleChannel INSTANCE = NetworkRegistry.newSimpleChannel(
            ZenithMod.id("main"),
            () -> PROTOCOL_VERSION,
            PROTOCOL_VERSION::equals,
            PROTOCOL_VERSION::equals
    );

    @SubscribeEvent
    public static void onNetworkSetup(FMLCommonSetupEvent event) {
        int id = 0;
        INSTANCE.registerMessage(id++, ZenithAttackPacket.class, ZenithAttackPacket::write, ZenithAttackPacket::read, ZenithAttackPacket::handle);
    }
}
