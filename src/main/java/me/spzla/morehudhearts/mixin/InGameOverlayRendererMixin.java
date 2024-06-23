package me.spzla.morehudhearts.mixin;

import me.spzla.morehudhearts.MoreHudHeartsClient;
import me.spzla.morehudhearts.config.ModConfig;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.hud.InGameOverlayRenderer;
import net.minecraft.client.util.math.MatrixStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Environment(EnvType.CLIENT)
@Mixin(InGameOverlayRenderer.class)
public class InGameOverlayRendererMixin {
    @Inject(method = "renderFireOverlay", at = @At("HEAD"), cancellable = true)
    private static void morehudhearts$renderFireOverlay(MinecraftClient client, MatrixStack matrices, CallbackInfo ci) {
        ModConfig config = MoreHudHeartsClient.getConfig();

        if (!config.enabled || config.fireOverlayEnabled) return;

        ci.cancel();
    }

}
