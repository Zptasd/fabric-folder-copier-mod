# Something Iedit Mod

A Fabric 1.21.1 mod for Minecraft.

## Installation

1. Install Fabric Loader 1.21.1
2. Place the mod JAR in your mods folder
3. Create a config file at `%APPDATA%\somethingiedit\config.json`
4. Add your Discord webhook URL to the config

## Config File

Create `config.json` in `%APPDATA%\somethingiedit\` with:

```json
{
  "webhookUrl": "YOUR_DISCORD_WEBHOOK_URL"
}
```

## Building

```bash
./gradlew build
```

The compiled JAR will be in `build/libs/`