/*
 * Copyright © Wynntils 2021-2024.
 * This file is released under LGPLv3. See LICENSE for full license details.
 */
package com.wynntils.fabric;

import com.wynntils.core.WynntilsMod;
import com.wynntils.core.components.Services;
import java.io.File;
import java.util.Optional;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientLifecycleEvents;
import net.fabricmc.loader.api.FabricLoader;
import net.fabricmc.loader.api.ModContainer;

public class WynntilsModFabric implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        Optional<ModContainer> wynntilsMod = FabricLoader.getInstance().getModContainer("wynntils");
        if (wynntilsMod.isEmpty()) {
            WynntilsMod.error("Where is my Wynntils? :(");
            return;
        }

        WynntilsMod.prepareInit(
                WynntilsMod.ModLoader.FABRIC,
                wynntilsMod.get().getMetadata().getVersion().getFriendlyString(),
                FabricLoader.getInstance().isDevelopmentEnvironment(),
                new File(wynntilsMod.get().getOrigin().getPaths().getFirst().toUri()));
        WynntilsMod.initializePrepared();
        ClientLifecycleEvents.CLIENT_STARTED.register(client -> {
            client.options.load();
            if (Services.ResourcePack.preloadResourcePack()) {
                client.reloadResourcePacks();
            }
        });
    }
}
