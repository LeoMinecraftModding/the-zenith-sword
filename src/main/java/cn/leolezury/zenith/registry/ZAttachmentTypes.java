package cn.leolezury.zenith.registry;

import cn.leolezury.zenith.ZenithMod;
import net.neoforged.neoforge.attachment.AttachmentType;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

public class ZAttachmentTypes {
	public static final DeferredRegister<AttachmentType<?>> ATTACHMENT_TYPES = DeferredRegister.create(NeoForgeRegistries.ATTACHMENT_TYPES, ZenithMod.ID);

	public static final DeferredHolder<AttachmentType<?>, AttachmentType<Float>> ENSURED_ZENITH_DAMAGE = ATTACHMENT_TYPES.register("ensured_zenith_damage", () -> AttachmentType.builder(() -> -1f).build());
}
