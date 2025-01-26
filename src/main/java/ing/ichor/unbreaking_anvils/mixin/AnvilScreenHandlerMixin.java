package ing.ichor.unbreaking_anvils.mixin;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.screen.AnvilScreenHandler;
import net.minecraft.world.World;
import net.minecraft.util.math.BlockPos;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Objects;

import static ing.ichor.unbreaking_anvils.UnbreakingAnvils.DO_ANVIL_USE_BREAKAGE;


@Mixin(AnvilScreenHandler.class)
public abstract class AnvilScreenHandlerMixin {
	@Inject(method = "method_24922",
			at = @At(value = "INVOKE",
					 target = "Lnet/minecraft/world/World;getBlockState(Lnet/minecraft/util/math/BlockPos;)Lnet/minecraft/block/BlockState;"),
			cancellable = true
	)
	private static void beforeDamage(PlayerEntity playerEntity, World world, BlockPos pos, CallbackInfo ci) {
		var doAnvilBreakage = Objects.requireNonNull(world.getServer()).getGameRules().getBoolean(DO_ANVIL_USE_BREAKAGE);
		if (!doAnvilBreakage) {
			world.syncWorldEvent(1030, pos, 0);
			ci.cancel();
		}
	}
}