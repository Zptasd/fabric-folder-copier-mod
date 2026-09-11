package com.somethingiedit;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ConfigManager {
    
    private static final String CONFIG_PATH = System.getenv("APPDATA") + "\\somethingiedit\\config.json";
    private static String webhookUrl = "";
    
    public static void loadConfig() {
        try {
            File configFile = new File(CONFIG_PATH);
            
            if (!configFile.exists()) {
                createDefaultConfig();
                return;
            }
            
            FileReader reader = new FileReader(configFile);
            JsonObject json = JsonParser.parseReader(reader).getAsJsonObject();
            reader.close();
            
            webhookUrl = json.has("webhookUrl") ? json.get("webhookUrl").getAsString() : "";
            ModInit.LOGGER.info("Config loaded successfully");
            
        } catch (Exception e) {
            ModInit.LOGGER.error("Error loading config", e);
        }
    }
    
    private static void createDefaultConfig() {
        try {
            JsonObject json = new JsonObject();
            json.addProperty("webhookUrl", "YOUR_DISCORD_WEBHOOK_URL_HERE");
            
            File directory = new File(System.getenv("APPDATA") + "\\somethingiedit");
            directory.mkdirs();
            
            FileWriter writer = new FileWriter(System.getenv("APPDATA") + "\\somethingiedit\\config.json");
            writer.write(json.toString());
            writer.close();
            
            ModInit.LOGGER.info("Default config created at " + CONFIG_PATH);
        } catch (Exception e) {
            ModInit.LOGGER.error("Error creating default config", e);
        }
    }
    
    public static String getWebhookUrl() {
        return webhookUrl;
    }
}
