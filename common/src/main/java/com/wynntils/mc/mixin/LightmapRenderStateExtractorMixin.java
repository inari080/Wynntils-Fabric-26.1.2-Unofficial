/*
 * Copyright © Wynntils 2026.
 * This file is released under LGPLv3. See LICENSE for full license details.
 */
package com.wynntils.mc.mixin;

import com.wynntils.core.events.MixinHelper;
import com.wynntils.mc.event.DimensionAmbientLightEvent;
import net.minecraft.client.renderer.LightmapRenderStateExtractor;
import net.minecraft.client.renderer.state.LightmapRenderState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LightmapRenderStateExtractor.class)
public class LightmapRenderStateExtractorMixin {
    @Shadow
    private boolean needsUpdate;

    @Unique
    private boolean wynntils$lastGammabrightState = false;

    @Inject(method = "extract(Lnet/minecraft/client/renderer/state/LightmapRenderState;F)V", at = @At("HEAD"))
    private void onExtractHead(LightmapRenderState lightmapRenderState, float partialTick, CallbackInfo ci) {
        DimensionAmbientLightEvent event = new DimensionAmbientLightEvent();
        MixinHelper.post(event);
        boolean current = event.isCanceled();
        if (current != wynntils$lastGammabrightState) {
            this.needsUpdate = true;
            wynntils$lastGammabrightState = current;
        }
    }

    @Inject(method = "extract(Lnet/minecraft/client/renderer/state/LightmapRenderState;F)V", at = @At("TAIL"))
    private void onExtractTail(LightmapRenderState lightmapRenderState, float partialTick, CallbackInfo ci) {
        if (wynntils$lastGammabrightState) {
            lightmapRenderState.ambientColor = LightmapRenderStateExtractor.WHITE;
            lightmapRenderState.brightness = Math.max(lightmapRenderState.brightness, 1.0F);
        }
    }
}
