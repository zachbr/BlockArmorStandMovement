package com.destroystokyo.blockarmorstandmovement;

import com.destroystokyo.paper.event.entity.EntityAddToWorldEvent;
import org.bukkit.entity.ArmorStand;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public final class BlockArmorStandMovement extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        // Plugin startup logic
        if (!this.isSupported()) {
            this.getServer().getPluginManager().disablePlugin(this);
            return;
        }
        this.getServer().getPluginManager().registerEvents(this, this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    @EventHandler
    public void onEntityAdded(EntityAddToWorldEvent e) {
        if (e.getEntity() instanceof ArmorStand) {
            ((ArmorStand) e.getEntity()).setCanMove(false);
        }
    }

    /**
     * Checks if the plugin is running on supported version of Paper, or a derivative
     */
    private boolean isSupported() {
        Class clazz;
        try {
            clazz = Class.forName("com.destroystokyo.paper.PaperWorldConfig");
        } catch (ClassNotFoundException ignored) {
            this.getLogger().warning("You are not running PaperMC or a derivative");
            this.getLogger().warning("This plugin will not work with your server");
            return false;
        }

        try {
            clazz.getField("armorStandEntityLookups");
        } catch (NoSuchFieldException | SecurityException ignored) {
            this.getLogger().warning("You are running an outdated version of PaperMC or a derivative");
            this.getLogger().warning("This plugin will not work without updating your server");
            return false;
        }

        return true;
    }
}
