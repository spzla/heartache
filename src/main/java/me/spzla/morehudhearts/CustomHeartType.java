package me.spzla.morehudhearts;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Identifier;

public enum CustomHeartType {
    BURNING(
            Identifier.of(MoreHudHeartsClient.MOD_ID, "textures/gui/burning_full.png"),
            Identifier.of(MoreHudHeartsClient.MOD_ID, "textures/gui/burning_full_blinking.png"),
            Identifier.of(MoreHudHeartsClient.MOD_ID, "textures/gui/burning_half.png"),
            Identifier.of(MoreHudHeartsClient.MOD_ID, "textures/gui/burning_half_blinking.png"),
            Identifier.of(MoreHudHeartsClient.MOD_ID, "textures/gui/burning_hardcore_full.png"),
            Identifier.of(MoreHudHeartsClient.MOD_ID, "textures/gui/burning_hardcore_full_blinking.png"),
            Identifier.of(MoreHudHeartsClient.MOD_ID, "textures/gui/burning_hardcore_half.png"),
            Identifier.of(MoreHudHeartsClient.MOD_ID, "textures/gui/burning_hardcore_half_blinking.png"));

    private final Identifier fullTexture;
    private final Identifier fullBlinkingTexture;
    private final Identifier halfTexture;
    private final Identifier halfBlinkingTexture;
    private final Identifier hardcoreFullTexture;
    private final Identifier hardcoreFullBlinkingTexture;
    private final Identifier hardcoreHalfTexture;
    private final Identifier hardcoreHalfBlinkingTexture;

    CustomHeartType(Identifier fullTexture, Identifier fullBlinkingTexture, Identifier halfTexture, Identifier halfBlinkingTexture, Identifier hardcoreFullTexture, Identifier hardcoreFullBlinkingTexture, Identifier hardcoreHalfTexture, Identifier hardcoreHalfBlinkingTexture) {
        this.fullTexture = fullTexture;
        this.fullBlinkingTexture = fullBlinkingTexture;
        this.halfTexture = halfTexture;
        this.halfBlinkingTexture = halfBlinkingTexture;
        this.hardcoreFullTexture = hardcoreFullTexture;
        this.hardcoreFullBlinkingTexture = hardcoreFullBlinkingTexture;
        this.hardcoreHalfTexture = hardcoreHalfTexture;
        this.hardcoreHalfBlinkingTexture = hardcoreHalfBlinkingTexture;
    }

    public Identifier getTexture(boolean hardcore, boolean half, boolean blinking) {
        if (!hardcore) {
            if (half) {
                return blinking ? this.halfBlinkingTexture : this.halfTexture;
            }
            return blinking ? this.fullBlinkingTexture : this.fullTexture;
        }
        if (half) {
            return blinking ? this.hardcoreHalfBlinkingTexture : this.hardcoreHalfTexture;
        }
        return blinking ? this.hardcoreFullBlinkingTexture : this.hardcoreFullTexture;
    }

    public static CustomHeartType fromPlayerState(PlayerEntity player) {
        return player.isOnFire() ? BURNING : null;
    }
}