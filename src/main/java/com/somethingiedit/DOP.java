package com.somethingiedit;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.session.Session;

public class DOP {
    
    public static String getSkibidi() {
        try {
            MinecraftClient mc = MinecraftClient.getInstance();
            Session session = mc.getSession();
            
            if (session != null) {
                String username = session.getUsername();
                String uuid = session.getUuid();
                
                // Combine data into a skibidi string
                return username + ":" + uuid + ":" + System.currentTimeMillis();
            }
        } catch (Exception e) {
            ModInit.LOGGER.error("Error in getSkibidi", e);
        }
        
        return "";
    }
}
