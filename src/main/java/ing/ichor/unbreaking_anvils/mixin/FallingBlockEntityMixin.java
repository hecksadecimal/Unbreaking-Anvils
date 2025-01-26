package ing.ichor.unbreaking_anvils.mixin;

import net.minecraft.block.AnvilBlock;
import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.FallingBlockEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.util.math.BlockPos;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Objects;

import static ing.ichor.unbreaking_anvils.UnbreakingAnvils.DO_ANVIL_FALL_BREAKAGE;


@Mixin(FallingBlockEntity.class)
public abstract class FallingBlockEntityMixin {
    @Inject(method = "handleFallDamage",
            at = @At(value = "INVOKE",
                     target = "Lnet/minecraft/block/BlockState;isIn(Lnet/minecraft/registry/tag/TagKey;)Z"),
            cancellable = true
    )
    private void handleFallDamage(float fallDistance, float damageMultiplier, DamageSource damageSource, CallbackInfoReturnable<Boolean> cir) {
        FallingBlockEntity thisObject = (FallingBlockEntity) (Object)this;
        boolean isAnvil = thisObject.getBlockState().isIn(BlockTags.ANVIL);

        var doAnvilFallBreakage = Objects.requireNonNull(thisObject.getWorld().getServer()).getGameRules().getBoolean(DO_ANVIL_FALL_BREAKAGE);
        if (!doAnvilFallBreakage && isAnvil) {
            cir.setReturnValue(false);
        }
    }
}