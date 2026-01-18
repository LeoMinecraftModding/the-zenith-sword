package cn.leolezury.zenith.mixin;

import cn.leolezury.zenith.entity.ZenithOwner;
import net.minecraft.world.entity.Entity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;

@Mixin(Entity.class)
public class EntityMixin implements ZenithOwner {
	@Unique
	private float ensuredZenithDamage;

	@Override
	public void setEnsuredZenithDamage(float damage) {
		ensuredZenithDamage = damage;
	}

	@Override
	public float getEnsuredZenithDamage() {
		return ensuredZenithDamage;
	}
}
