package cn.leolezury.zenith.entity;

import cn.leolezury.zenith.item.ZenithPart;
import cn.leolezury.zenith.registry.ZEntityDataSerializers;
import cn.leolezury.zenith.util.ZMathUtil;
import com.mojang.logging.LogUtils;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.NbtOps;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.Mth;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.TraceableEntity;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.material.PushReaction;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.Nullable;
import org.slf4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ZenithSlash extends Entity implements TraceableEntity {
	private static final Logger LOGGER = LogUtils.getLogger();

	private static final String TAG_ZENITH_PART = "zenith_part";
	private static final String TAG_OWNER = "owner";
	private static final String TAG_AGE = "age";
	private static final String TAG_PITCH = "pitch";
	private static final String TAG_YAW = "yaw";
	private static final String TAG_ROLL = "roll";

	public static final int LIFESPAN = 20;
	public static final int LENGTH = 15;
	public static final float RADIUS_RATIO = 0.3f;

	protected static final EntityDataAccessor<ZenithPart> ZENITH_PART = SynchedEntityData.defineId(ZenithSlash.class, ZEntityDataSerializers.ZENITH_PART.get());

	public void setZenithPart(ZenithPart part) {
		this.getEntityData().set(ZENITH_PART, part);
	}

	public ZenithPart getZenithPart() {
		return this.getEntityData().get(ZENITH_PART);
	}

	protected static final EntityDataAccessor<Integer> OWNER_ID = SynchedEntityData.defineId(ZenithSlash.class, EntityDataSerializers.INT);

	public void setOwnerId(int ownerId) {
		this.getEntityData().set(OWNER_ID, ownerId);
	}

	public int getOwnerId() {
		return this.getEntityData().get(OWNER_ID);
	}

	protected static final EntityDataAccessor<Integer> AGE = SynchedEntityData.defineId(ZenithSlash.class, EntityDataSerializers.INT);

	public void setAge(int age) {
		this.getEntityData().set(AGE, age);
	}

	public int getAge() {
		return this.getEntityData().get(AGE);
	}

	protected static final EntityDataAccessor<Float> PITCH = SynchedEntityData.defineId(ZenithSlash.class, EntityDataSerializers.FLOAT);

	public float getPitch() {
		return this.getEntityData().get(PITCH);
	}

	public void setPitch(float pitch) {
		this.getEntityData().set(PITCH, pitch);
	}

	protected static final EntityDataAccessor<Float> YAW = SynchedEntityData.defineId(ZenithSlash.class, EntityDataSerializers.FLOAT);

	public float getYaw() {
		return this.getEntityData().get(YAW);
	}

	public void setYaw(float yaw) {
		this.getEntityData().set(YAW, yaw);
	}

	protected static final EntityDataAccessor<Float> ROLL = SynchedEntityData.defineId(ZenithSlash.class, EntityDataSerializers.FLOAT);

	public float getRoll() {
		return this.getEntityData().get(ROLL);
	}

	public void setRoll(float roll) {
		this.getEntityData().set(ROLL, roll);
	}

	private int clientAge = -1;

	public int getClientAge() {
		return clientAge;
	}

	public float renderPitch, renderYaw, renderRoll, prevPitch, prevYaw, prevRoll;

	private Entity owner;
	private UUID ownerId;

	private int damageCooldown;

	@Override
	@Nullable
	public Entity getOwner() {
		return owner;
	}

	public void setOwner(Entity owner) {
		this.ownerId = owner.getUUID();
		this.owner = owner;
		if (!level().isClientSide) {
			setOwnerId(owner.getId());
		}
	}

	public ZenithSlash(EntityType<? extends ZenithSlash> type, Level level) {
		super(type, level);
		noCulling = true;
	}

	public ZenithSlash(EntityType<? extends ZenithSlash> type, Level level, LivingEntity owner) {
		this(type, level);
		this.setOwner(owner);
		this.setPitch(-owner.getXRot());
		this.setYaw(owner.yHeadRot + 90);
		this.setRoll(getRandom().nextFloat() * 360);
		this.setPos(getIdealPos(owner, owner.position()));
	}

	@Override
	protected void defineSynchedData(SynchedEntityData.Builder builder) {
		builder.define(ZENITH_PART, new ZenithPart(Items.WOODEN_SWORD.builtInRegistryHolder(), 0x594319, 0.125, Mth.HALF_PI * 0.5f, 1.75, 1))
			.define(OWNER_ID, -1)
			.define(AGE, 0)
			.define(PITCH, 0f)
			.define(YAW, 0f)
			.define(ROLL, 0f);
	}

	@Override
	public PushReaction getPistonPushReaction() {
		return PushReaction.IGNORE;
	}

	@Override
	public void tick() {
		super.tick();
		if (!level().isClientSide) {
			if (owner == null && ownerId != null && level() instanceof ServerLevel serverLevel) {
				Entity entity = serverLevel.getEntity(ownerId);
				if (entity != null) {
					owner = entity;
				}
				if (owner == null) {
					ownerId = null;
				}
			}
			Entity owner = getOwner();
			if (owner != null) {
				setPos(getIdealPos(owner, owner.position()));
			}
			if (damageCooldown > 0) {
				damageCooldown--;
			}
			if (owner instanceof LivingEntity livingOwner) {
				Vec3 endPos = ZMathUtil.rotationToPosition(position(), LENGTH, getPitch(), getYaw());
				List<LivingEntity> entities = level().getEntitiesOfClass(LivingEntity.class, new AABB(position(), endPos).inflate(2));
				List<LivingEntity> result = new ArrayList<>();
				for (LivingEntity entity : entities) {
					AABB aabb = entity.getBoundingBox().inflate(entity.getPickRadius() + 1.5);
					if (aabb.contains(position())) {
						result.add(entity);
						continue;
					}
					if (aabb.clip(position(), endPos).isPresent()) {
						result.add(entity);
					}
				}
				for (LivingEntity entity : result) {
					if (entity != owner) {
						ItemStack weaponItem = livingOwner.getWeaponItem();
						DamageSource damageSource = livingOwner instanceof Player player ? damageSources().playerAttack(player) : damageSources().mobAttack(livingOwner);
						float damage = livingOwner.getAttribute(Attributes.ATTACK_DAMAGE) != null ? (float) (livingOwner.getAttributeValue(Attributes.ATTACK_DAMAGE)) : 1;
						float knockback = livingOwner.getKnockback(entity, damageSource);

						if (livingOwner.level() instanceof ServerLevel serverLevel) {
							damage = EnchantmentHelper.modifyDamage(serverLevel, weaponItem, entity, damageSource, damage);
							knockback = EnchantmentHelper.modifyKnockback(serverLevel, weaponItem, entity, damageSource, knockback);
						}

						if (damageCooldown <= 0) {
							entity.invulnerableTime = 0;
							if (entity.hurt(damageSource, damage) && livingOwner.level() instanceof ServerLevel serverLevel) {
								EnchantmentHelper.doPostAttackEffectsWithItemSource(serverLevel, entity, damageSource, weaponItem);
								damageCooldown = 10;
							}
							entity.invulnerableTime = 0;
						}

						if (knockback > 0.0F) {
							entity.knockback(knockback * 0.5F, Mth.sin(livingOwner.getYRot() * Mth.DEG_TO_RAD), -Mth.cos(livingOwner.getYRot() * Mth.DEG_TO_RAD));
						}
					}
				}
			}
			if (getAge() > LIFESPAN + 3) {
				discard();
			}
			setAge(getAge() + 1);
		} else {
			prevPitch = clientAge == -1 ? getPitch() : renderPitch;
			prevYaw = clientAge == -1 ? getYaw() : renderYaw;
			prevRoll = clientAge == -1 ? getRoll() : renderRoll;
			renderPitch = getPitch();
			renderYaw = getYaw();
			renderRoll = getRoll();
			if (clientAge == -1) {
				clientAge = getAge();
			}
			clientAge++;
			xo = getX();
			yo = getY();
			zo = getZ();
		}
	}

	public Vec3 getIdealPos(Entity owner, Vec3 ownerPos) {
		return ownerPos.add(0, owner.getBbHeight() * 0.6f, 0);
	}

	@Override
	public void push(Entity entity) {
	}

	@Override
	public boolean isPickable() {
		return false;
	}

	@Override
	public boolean isPushable() {
		return false;
	}

	@Override
	public boolean shouldRenderAtSqrDistance(double distance) {
		return distance < 1024;
	}

	@Override
	protected void readAdditionalSaveData(CompoundTag compoundTag) {
		if (compoundTag.contains(TAG_ZENITH_PART)) {
			ZenithPart.CODEC.parse(NbtOps.INSTANCE, compoundTag.get(TAG_ZENITH_PART)).resultOrPartial(s -> LOGGER.warn("Failed to parse Zenith Slash: {}", s)).ifPresent(this::setZenithPart);
		}
		if (compoundTag.hasUUID(TAG_OWNER)) {
			ownerId = compoundTag.getUUID(TAG_OWNER);
		}
		setAge(compoundTag.getInt(TAG_AGE));
		setPitch(compoundTag.getFloat(TAG_PITCH));
		setYaw(compoundTag.getFloat(TAG_YAW));
		setRoll(compoundTag.getFloat(TAG_ROLL));
	}

	@Override
	protected void addAdditionalSaveData(CompoundTag compoundTag) {
		compoundTag.put(TAG_ZENITH_PART, ZenithPart.CODEC.encodeStart(NbtOps.INSTANCE, getZenithPart()).getOrThrow());
		if (owner != null) {
			compoundTag.putUUID(TAG_OWNER, owner.getUUID());
		}
		compoundTag.putInt(TAG_AGE, getAge());
		compoundTag.putFloat(TAG_PITCH, getPitch());
		compoundTag.putFloat(TAG_YAW, getYaw());
		compoundTag.putFloat(TAG_ROLL, getRoll());
	}
}