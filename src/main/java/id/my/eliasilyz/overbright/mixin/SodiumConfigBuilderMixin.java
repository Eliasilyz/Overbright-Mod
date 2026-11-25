package id.my.eliasilyz.overbright.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

@Pseudo
@Mixin(targets = "net.caffeinemc.mods.sodium.client.gui.SodiumConfigBuilder", remap = false)
public abstract class SodiumConfigBuilderMixin {

    @ModifyArg(
        method = "buildGeneralPage",
        at = @At(
            value = "INVOKE",
            target = "Lnet/caffeinemc/mods/sodium/api/config/structure/IntegerOptionBuilder;setRange(III)Lnet/caffeinemc/mods/sodium/api/config/structure/IntegerOptionBuilder;"
        ),
        index = 1,
        remap = false
    )
    private int overbright$modifySodiumMaxBrightness(int max) {
        if (max == 100) {
            return 1000;
        }
        return max;
    }
}
