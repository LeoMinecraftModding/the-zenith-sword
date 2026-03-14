package cn.leolezury.zenith;

import net.neoforged.neoforge.common.ModConfigSpec;

public class ZConfig {
	private static final ModConfigSpec.Builder BUILDER = new ModConfigSpec.Builder();

	public static final ModConfigSpec.DoubleValue ZENITH_ATTACK_DAMAGE_ADDITION = BUILDER
		.comment("Value of the Zenith's attack damage attribute modifier")
		.defineInRange("zenithAttackDamageAddition", 7.0, 0, 1024);

	static final ModConfigSpec SPEC = BUILDER.build();
}
