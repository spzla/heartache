package me.spzla.morehudhearts;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Identifier;

public enum CustomHeartType {
    BURNING(new SizedTexture(Identifier.of(MoreHudHeartsClient.MOD_ID, "textures/gui/burning_full.png"), 9, 16),
            new SizedTexture(Identifier.of(MoreHudHeartsClient.MOD_ID, "textures/gui/burning_full_blinking.png"), 9, 16),
            new SizedTexture(Identifier.of(MoreHudHeartsClient.MOD_ID, "textures/gui/burning_half.png"), 9, 16),
            new SizedTexture(Identifier.of(MoreHudHeartsClient.MOD_ID, "textures/gui/burning_half_blinking.png"), 9, 16),
            new SizedTexture(Identifier.of(MoreHudHeartsClient.MOD_ID, "textures/gui/burning_hardcore_full.png"), 9, 9),
            new SizedTexture(Identifier.of(MoreHudHeartsClient.MOD_ID, "textures/gui/burning_hardcore_full_blinking.png"), 9, 9),
            new SizedTexture(Identifier.of(MoreHudHeartsClient.MOD_ID, "textures/gui/burning_hardcore_half.png"), 9, 9),
            new SizedTexture(Identifier.of(MoreHudHeartsClient.MOD_ID, "textures/gui/burning_hardcore_half_blinking.png"), 9, 9));

    private final SizedTexture fullTexture;
    private final SizedTexture fullBlinkingTexture;
    private final SizedTexture halfTexture;
    private final SizedTexture halfBlinkingTexture;
    private final SizedTexture hardcoreFullTexture;
    private final SizedTexture hardcoreFullBlinkingTexture;
    private final SizedTexture hardcoreHalfTexture;
    private final SizedTexture hardcoreHalfBlinkingTexture;

    CustomHeartType(SizedTexture fullTexture, SizedTexture fullBlinkingTexture, SizedTexture halfTexture, SizedTexture halfBlinkingTexture,
                    SizedTexture hardcoreFullTexture, SizedTexture hardcoreFullBlinkingTexture, SizedTexture hardcoreHalfTexture, SizedTexture hardcoreHalfBlinkingTexture) {
        this.fullTexture = fullTexture;
        this.fullBlinkingTexture = fullBlinkingTexture;
        this.halfTexture = halfTexture;
        this.halfBlinkingTexture = halfBlinkingTexture;
        this.hardcoreFullTexture = hardcoreFullTexture;
        this.hardcoreFullBlinkingTexture = hardcoreFullBlinkingTexture;
        this.hardcoreHalfTexture = hardcoreHalfTexture;
        this.hardcoreHalfBlinkingTexture = hardcoreHalfBlinkingTexture;
    }

    public SizedTexture getTexture(boolean hardcore, boolean half, boolean blinking) {
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