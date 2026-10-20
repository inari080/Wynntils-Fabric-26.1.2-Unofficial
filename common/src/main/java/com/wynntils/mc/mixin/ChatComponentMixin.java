/*
 * Copyright © Wynntils 2024-2026.
 * This file is released under LGPLv3. See LICENSE for full license details.
 */
package com.wynntils.mc.mixin;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.wynntils.core.events.MixinHelper;
import com.wynntils.mc.event.ChatComponentRenderEvent;
import com.wynntils.mc.event.ChatScreenCreateEvent;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.components.ChatComponent;
import net.minecraft.client.gui.screens.ChatScreen;
import net.minecraft.client.multiplayer.chat.GuiMessage;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ChatComponent.class)
public abstract class ChatComponentMixin {
    @Mutable @Shadow private List<GuiMessage> allMessages;
    @Mutable @Shadow private List<GuiMessage.Line> trimmedMessages;

    @Inject(method = "<init>", at = @At("RETURN"))
    private void onInit(CallbackInfo ci) {
        allMessages = new CopyOnWriteArrayList<>();
        trimmedMessages = new CopyOnWriteArrayList<>();
    }

    @Inject(
            method = "extractRenderState(Lnet/minecraft/client/gui/GuiGraphicsExtractor;Lnet/minecraft/client/gui/Font;IIILnet/minecraft/client/gui/components/ChatComponent$DisplayMode;Z)V",
            at = @At("HEAD"))
    private void setupRender(
            GuiGraphicsExtractor graphics,
            Font font,
            int mouseX,
            int mouseY,
            int focusedMessageIndex,
            ChatComponent.DisplayMode mode,
            boolean showChat,
            CallbackInfo ci) {
        MixinHelper.post(new ChatComponentRenderEvent.Pre((ChatComponent) (Object) this));
    }

    @WrapOperation(
            method = "createScreen(Lnet/minecraft/client/gui/components/ChatComponent$ChatMethod;Lnet/minecraft/client/gui/screens/ChatScreen$ChatConstructor;)Lnet/minecraft/client/gui/screens/ChatScreen;",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/screens/ChatScreen$ChatConstructor;create(Ljava/lang/String;Z)Lnet/minecraft/client/gui/screens/ChatScreen;"))
    private <T extends ChatScreen> T wrapCreateScreen(
            ChatScreen.ChatConstructor<T> constructor, String text, boolean draft, Operation<T> original) {
        T screen = original.call(constructor, text, draft);
        ChatScreenCreateEvent event = new ChatScreenCreateEvent(screen, text, draft);
        MixinHelper.post(event);
        return (T) event.getScreen();
    }
}
