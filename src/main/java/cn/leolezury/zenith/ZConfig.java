package cn.leolezury.zenith;


import net.minecraftforge.common.ForgeConfigSpec;

public class ZConfig {
	private static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();

	public static final ForgeConfigSpec.DoubleValue ZENITH_ATTACK_DAMAGE_ADDITION = BUILDER
		.comment("Value of the Zenith's attack damage attribute modifier")
		.defineInRange("zenithAttackDamageAddition", 7.0, 0, 1024);

	static final ForgeConfigSpec SPEC = BUILDER.build();
}
