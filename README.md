# Overbright

A modern, lightweight client-side Fabric mod for Minecraft 26.3 that extends the brightness slider up to **1000%**.

![Overbright Logo](logo.png)

## Highlights

- **0% to 1000% Range**: Extends the slider beyond the vanilla 100% cap up to 1000% (internal gamma `0.0` to `10.0`).
- **Dynamic Sodium Compatibility**: Injects into Sodium automatically when installed, expanding Sodium's video settings slider up to 1000%.
- **Smooth Non-Linear Control**: The first half of the slider (0%–50%) spans 0%–100% for fine adjustment, while the second half (50%–100%) smoothly covers 100%–1000%.
- **Clean Persistence**: Higher values are saved cleanly into `options.txt` without getting clamped or reset on startup.
- **Clear Feedback**: Displays exact percentage values (e.g. `Brightness: 450%`, `Brightness: 1000%`), while preserving the classic `Brightness: Moody` label at 0%.
- **Zero Heavy Dependencies**: Client-side only. Requires only Fabric Loader and Minecraft (no Fabric API required).

## Installation

1. Make sure you have **Fabric Loader** installed for Minecraft **26.3**.
2. Download `overbright-1.0.0.jar` from the releases.
3. Place the `.jar` file into your `.minecraft/mods` folder.
4. Launch the game and enjoy fullbright visibility in dark caves and nights!

## Compatibility Notes

- **Vanilla Settings**: Extends the native Brightness slider in Video Settings.
- **Sodium**: Compatible out of the box; Sodium's Video Settings menu will display the slider up to 1000%.
- **Iris / Shaders**: When an external shader pack is active, the shader pack's custom lighting pipeline overrides the vanilla lightmap.

## Notice

> [!WARNING]
> High brightness settings allow clear visibility in unlit areas without light sources. Please verify your multiplayer server rules regarding gamma/fullbright mods before using on public servers.
