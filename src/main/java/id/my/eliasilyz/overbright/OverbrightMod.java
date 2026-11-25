package id.my.eliasilyz.overbright;

import com.mojang.serialization.Codec;
import net.minecraft.client.OptionInstance;
import net.minecraft.client.Options;
import net.minecraft.network.chat.Component;
import net.minecraft.util.Mth;

import java.util.Optional;

public final class OverbrightMod {
    public static final double MIN_VALUE = 0.0;
    public static final double NORMAL_MAX_VALUE = 1.0;
    public static final double MAX_VALUE = 10.0;
    public static final double DEFAULT_VALUE = 0.5;

    private OverbrightMod() {}

    /**
     * Converts an internal brightness value (0.0 - 10.0) to slider position (0.0 - 1.0).
     * The first half of the slider (0.0 - 0.5) covers 0% - 100% (0.0 - 1.0).
     * The second half of the slider (0.5 - 1.0) covers 100% - 1000% (1.0 - 10.0).
     */
    public static double toSliderValue(Double value) {
        if (value == null) {
            return 0.0;
        }
        double v = Mth.clamp(value, MIN_VALUE, MAX_VALUE);
        if (v <= NORMAL_MAX_VALUE) {
            return v * 0.5;
        } else {
            return 0.5 + (v - 1.0) / 18.0;
        }
    }

    /**
     * Converts a slider position (0.0 - 1.0) back to the internal brightness value (0.0 - 10.0).
     */
    public static Double fromSliderValue(double slider) {
        double s = Mth.clamp(slider, 0.0, 1.0);
        if (s <= 0.5) {
            return s * 2.0;
        } else {
            return 1.0 + (s - 0.5) * 18.0;
        }
    }

    /**
     * Formats the slider label.
     * 0% displays "Brightness: Moody" (vanilla minimum label).
     * Values > 0% display "Brightness: <percent>%" (e.g., "Brightness: 450%").
     */
    public static Component formatLabel(Component caption, Double value) {
        if (value == null) {
            return Options.genericValueLabel(caption, Component.translatable("options.gamma.min"));
        }
        int percent = (int) Math.round(value * 100.0);
        if (percent == 0) {
            return Options.genericValueLabel(caption, Component.translatable("options.gamma.min"));
        }
        return Component.translatable("options.percent_value", caption, percent);
    }

    public static OptionInstance.SliderableValueSet<Double> createValueSet() {
        return new OptionInstance.SliderableValueSet<>() {
            @Override
            public Optional<Double> validateValue(Double value) {
                return (value != null && value >= MIN_VALUE && value <= MAX_VALUE)
                        ? Optional.of(value)
                        : Optional.empty();
            }

            @Override
            public double toSliderValue(Double value) {
                return OverbrightMod.toSliderValue(value);
            }

            @Override
            public Double fromSliderValue(double slider) {
                return OverbrightMod.fromSliderValue(slider);
            }

            @Override
            public Codec<Double> codec() {
                return Codec.withAlternative(
                        Codec.doubleRange(MIN_VALUE, MAX_VALUE),
                        Codec.BOOL,
                        b -> b ? 1.0 : 0.0
                );
            }
        };
    }

    public static OptionInstance<Double> createGammaOption() {
        return new OptionInstance<>(
                "options.gamma",
                OptionInstance.noTooltip(),
                OverbrightMod::formatLabel,
                createValueSet(),
                DEFAULT_VALUE,
                OptionInstance.NO_ACTION
        );
    }
}
