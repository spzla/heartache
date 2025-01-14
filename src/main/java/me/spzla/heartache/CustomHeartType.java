package me.spzla.heartache;

import me.spzla.heartache.config.ModConfig;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Identifier;

public enum CustomHeartType {
    BURNING(new SizedTexture(Identifier.of(HeartacheClient.MOD_ID, "hud/heart/burning_full"), 9, 11),
            new SizedTexture(Identifier.of(HeartacheClient.MOD_ID, "hud/heart/burning_full_blinking"), 9, 9),
            new SizedTexture(Identifier.of(HeartacheClient.MOD_ID, "hud/heart/burning_half"), 9, 11),
            new SizedTexture(Identifier.of(HeartacheClient.MOD_ID, "hud/heart/burning_half_blinking"), 9, 9),
            new SizedTexture(Identifier.of(HeartacheClient.MOD_ID, "hud/heart/burning_full"), 9, 9),
            new SizedTexture(Identifier.of(HeartacheClient.MOD_ID, "hud/heart/burning_full_blinking"), 9, 9),
            new SizedTexture(Identifier.of(HeartacheClient.MOD_ID, "hud/heart/burning_half"), 9, 9),
            new SizedTexture(Identifier.of(HeartacheClient.MOD_ID, "hud/heart/burning_half_blinking"), 9, 9)),
    GLOWING(new SizedTexture(Identifier.of(HeartacheClient.MOD_ID, "hud/heart/glowing_full"), 9, 9),
            new SizedTexture(Identifier.ofVanilla("hud/heart/container"), 9, 9),
            new SizedTexture(Identifier.of(HeartacheClient.MOD_ID, "hud/heart/glowing_half"), 9, 9),
            new SizedTexture(Identifier.ofVanilla("hud/heart/container"), 9, 9),
            new SizedTexture(Identifier.of(HeartacheClient.MOD_ID, "hud/heart/glowing_hardcore_full"), 9, 9),
            new SizedTexture(Identifier.ofVanilla("hud/heart/container"), 9, 9),
            new SizedTexture(Identifier.of(HeartacheClient.MOD_ID, "hud/heart/glowing_hardcore_half"), 9, 9),
            new SizedTexture(Identifier.ofVanilla("hud/heart/container"), 9, 9));

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
        ModConfig config = HeartacheClient.getConfig();
        return (player.isOnFire() && config.burningHeartEnabled) ? BURNING
                : (player.isGlowing() && config.glowingHeartEnabled) ? GLOWING : null;
    }
}