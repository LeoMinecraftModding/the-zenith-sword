package cn.leolezury.zenith.event;

import cn.leolezury.zenith.ZenithMod;
import cn.leolezury.zenith.registry.ZAttachmentTypes;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.living.LivingDamageEvent;

@EventBusSubscriber(modid = ZenithMod.ID)
public class ZEvents {
	@SubscribeEvent
	private static void onPreLivingDamage(LivingDamageEvent.Pre event) {
		DamageSource source = event.getSource();
		Entity attacker = source.getEntity();
		if (attacker != null) {
			float ensuredDamage = attacker.getData(ZAttachmentTypes.ENSURED_ZENITH_DAMAGE.get());
			if (ensuredDamage > 0 && event.getNewDamage() < ensuredDamage) {
				event.setNewDamage(ensuredDamage);
			}
		}
	}
}
