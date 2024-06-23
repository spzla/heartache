package me.spzla.morehudhearts.mixin;

import com.mojang.blaze3d.systems.RenderSystem;
import me.spzla.morehudhearts.CustomHeartType;
import me.spzla.morehudhearts.MoreHudHeartsClient;
import me.spzla.morehudhearts.SizedTexture;
import me.spzla.morehudhearts.config.ModConfig;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.hud.InGameHud;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Environment(EnvType.CLIENT)
@Mixin(InGameHud.class)
public class InGameHudMixin {
    @Final
    @Shadow
    private MinecraftClient client;

    @Inject(method = "drawHeart", at = @At("HEAD"), cancellable = true)
    private void morehudhearts$drawCustomHeart(DrawContext context, InGameHud.HeartType type, int x, int y, boolean hardcore,
                                            boolean blinking, boolean half, CallbackInfo ci) {
        ModConfig config = MoreHudHeartsClient.getConfig();
        if (!config.enabled) return;

        assert this.client.player != null;
        CustomHeartType customType = CustomHeartType.fromPlayerState(this.client.player);

        if (type == InGameHud.HeartType.NORMAL && customType != null) {
            SizedTexture texture = customType.getTexture(hardcore, half, blinking);

            int yOffset = 9 - texture.height;

            RenderSystem.enableBlend();
            context.drawGuiTexture(texture.id, x, y + yOffset, texture.width, texture.height);
            RenderSystem.disableBlend();

            ci.cancel();
        }
    }
}
