package me.spzla.morehudhearts.mixin;

import com.mojang.blaze3d.systems.RenderSystem;
import me.spzla.morehudhearts.CustomHeartType;
import me.spzla.morehudhearts.SizedTexture;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.util.Identifier;
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
        assert this.client.player != null;
        CustomHeartType customType = CustomHeartType.fromPlayerState(this.client.player);

        if (type == InGameHud.HeartType.NORMAL && customType != null) {
            SizedTexture texture = customType.getTexture(hardcore, half, blinking);

            int yOffset = 9 - texture.height;

            RenderSystem.enableBlend();
            context.drawTexture(texture.id, x, y + yOffset, 0, 0, 0, texture.width, texture.height, texture.width, texture.height);
            RenderSystem.disableBlend();

            ci.cancel();
        }
    }
}
