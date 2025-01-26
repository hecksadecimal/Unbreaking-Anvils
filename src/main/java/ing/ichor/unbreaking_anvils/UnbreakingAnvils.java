package ing.ichor.unbreaking_anvils;

import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.gamerule.v1.GameRuleFactory;
import net.fabricmc.fabric.api.gamerule.v1.GameRuleRegistry;
import net.minecraft.world.GameRules;
import net.minecraft.world.GameRules.Category;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UnbreakingAnvils implements ModInitializer {
	public static final String MOD_ID = "unbreaking-anvils";

	public static final GameRules.Key<GameRules.BooleanRule> DO_ANVIL_USE_BREAKAGE = GameRuleRegistry.register("doAnvilUseBreakage", Category.MISC, GameRuleFactory.createBooleanRule(true));
	public static final GameRules.Key<GameRules.BooleanRule> DO_ANVIL_FALL_BREAKAGE = GameRuleRegistry.register("doAnvilFallBreakage", Category.MISC, GameRuleFactory.createBooleanRule(true));

	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);


	@Override
	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.

		LOGGER.info("Unbreaking Anvils loading!");
	}
}