package me.spzla.morehudhearts.integration;

import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;
import me.spzla.morehudhearts.MoreHudHeartsClient;

public class ModMenuIntegration implements ModMenuApi {
    @Override
    public ConfigScreenFactory<?> getModConfigScreenFactory() {
        return parent -> MoreHudHeartsClient.getConfig().makeScreen(parent);
    }
}
