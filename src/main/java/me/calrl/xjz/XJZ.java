package me.calrl.xjz;

import org.bukkit.plugin.java.JavaPlugin;

public final class XJZ extends JavaPlugin {
    private StoredEntityManager storedEntityManager;

    @Override
    public void onEnable() {
        storedEntityManager = new StoredEntityManager(this);
        getServer().getPluginManager().registerEvents(new PlayerListener(this), this);
        getLogger().info("Enabled XJZ");

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public StoredEntityManager getStoredEntityManager() {
        return storedEntityManager;
    }
}
