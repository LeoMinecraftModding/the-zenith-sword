package cn.leolezury.zenith.event;

import cn.leolezury.zenith.ZenithMod;
import cn.leolezury.zenith.entity.ZenithSlash;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = ZenithMod.ID)
public class ZEvents {
	@SubscribeEvent
	public static void onLivingDamage(LivingDamageEvent event) {
		DamageSource source = event.getSource();
		Entity attacker = source.getEntity();
		if (attacker != null) {
			float ensuredDamage = attacker.getPersistentData().getFloat(ZenithSlash.TAG_ENSURED_ZENITH_DAMAGE);
			if (ensuredDamage > 0 && event.getAmount() < ensuredDamage) {
				event.setAmount(ensuredDamage);
			}
		}
	}
}
