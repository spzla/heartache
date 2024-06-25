package me.spzla.heartache.mixin;

import me.spzla.heartache.HeartacheClient;
import me.spzla.heartache.config.ModConfig;
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
    private static void heartache$renderFireOverlay(MinecraftClient client, MatrixStack matrices, CallbackInfo ci) {
        ModConfig config = HeartacheClient.getConfig();

        if (!config.enabled || config.fireOverlayEnabled) return;

        ci.cancel();
    }

}
