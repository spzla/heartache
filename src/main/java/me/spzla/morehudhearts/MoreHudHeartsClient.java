package me.spzla.morehudhearts;

import net.fabricmc.api.ClientModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MoreHudHeartsClient implements ClientModInitializer {
    public static final String MOD_ID = "morehudhearts";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    @Override
    public void onInitializeClient() {
        LOGGER.info("{} initialized!", MOD_ID);
    }
}
