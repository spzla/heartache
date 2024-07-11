package me.spzla.heartache.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import dev.isxander.yacl3.api.*;
import dev.isxander.yacl3.api.controller.TickBoxControllerBuilder;
import me.spzla.heartache.HeartacheClient;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.Text;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class ModConfig {
    public static final ModConfig INSTANCE = new ModConfig();

    public final Path configFile = FabricLoader.getInstance().getConfigDir().resolve(HeartacheClient.MOD_ID + ".json");
    private final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    public boolean enabled = true;
    public boolean burningHeartEnabled = true;
    public boolean glowingHeartEnabled = true;

    public void save() {
        try {
            Files.deleteIfExists(configFile);

            JsonObject json = new JsonObject();
            json.addProperty("enabled", enabled);
            json.addProperty("burningHeartEnabled", burningHeartEnabled);
            json.addProperty("glowingHeartEnabled", glowingHeartEnabled);


            Files.writeString(configFile, gson.toJson(json));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void load() {
        try {
            if (Files.notExists(configFile)) {
                save();
                return;
            }

            JsonObject json = gson.fromJson(Files.readString(configFile), JsonObject.class);
            if (json.has("enabled"))
                enabled = json.getAsJsonPrimitive("enabled").getAsBoolean();
            if (json.has("burningHeartEnabled"))
                burningHeartEnabled = json.getAsJsonPrimitive("burningHeartEnabled").getAsBoolean();
            if (json.has("glowingHeartEnabled"))
                glowingHeartEnabled = json.getAsJsonPrimitive("glowingHeartEnabled").getAsBoolean();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Screen makeScreen(Screen parent) {
        return YetAnotherConfigLib.createBuilder()
                .title(Text.translatable("heartache.general.title"))
                .category(ConfigCategory.createBuilder()
                        .name(Text.translatable("heartache.general.title"))
                        .option(Option.<Boolean>createBuilder()
                                .name(Text.translatable("heartache.option.enabled"))
                                .description(OptionDescription.of(Text.translatable("heartache.option.enabled.description")))
                                .binding(
                                        true,
                                        () -> enabled,
                                        value -> enabled = value
                                )
                                .controller(TickBoxControllerBuilder::create)
                                .build())
                        .group(OptionGroup.createBuilder()
                                .name(Text.translatable("heartache.hearttypes.title"))
                                .option(Option.<Boolean>createBuilder()
                                        .name(Text.translatable("heartache.option.burningheart.enabled"))
                                        .binding(
                                                true,
                                                () -> burningHeartEnabled,
                                                value -> burningHeartEnabled = value
                                        )
                                        .controller(TickBoxControllerBuilder::create)
                                        .build())
                                .option(Option.<Boolean>createBuilder()
                                        .name(Text.translatable("heartache.option.glowingheart.enabled"))
                                        .binding(
                                                true,
                                                () -> glowingHeartEnabled,
                                                value -> glowingHeartEnabled = value
                                        )
                                        .controller(TickBoxControllerBuilder::create)
                                        .build())
                                .build())
                        .build())
                .save(this::save)
                .build()
                .generateScreen(parent);
    }
}
