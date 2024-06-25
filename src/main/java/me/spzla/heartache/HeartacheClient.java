package me.spzla.heartache;

import me.spzla.heartache.config.ModConfig;
import net.fabricmc.api.ClientModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HeartacheClient implements ClientModInitializer {
    public static final String MOD_ID = "heartache";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    @Override
    public void onInitializeClient() {
        getConfig().load();

        LOGGER.info("{} initialized!", MOD_ID);
    }

    public static ModConfig getConfig() {
        return ModConfig.INSTANCE;
    }
}
