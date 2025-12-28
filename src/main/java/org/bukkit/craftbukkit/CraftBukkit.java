package org.bukkit.craftbukkit;

import org.bukkit.Bukkit;
import org.bukkit.Server;
import org.bukkit.plugin.PluginManager;

/**
 * CraftBukkit implementation bridge for Bukkit 1.21.11
 * Provides NMS (Net Minecraft Server) bridge functionality
 */
public class CraftBukkit {
    
    private static Server server;
    
    /**
     * Gets the CraftBukkit server instance
     * @return Server instance
     */
    public static Server getServer() {
        return server;
    }
    
    /**
     * Sets the server instance
     * @param server Server instance
     */
    public static void setServer(Server server) {
        CraftBukkit.server = server;
    }
    
    /**
     * Gets the CraftBukkit plugin manager
     * @return Plugin manager instance
     */
    public static PluginManager getPluginManager() {
        return server != null ? server.getPluginManager() : null;
    }
    
    /**
     * Checks if CraftBukkit is properly initialized
     * @return True if initialized
     */
    public static boolean isInitialized() {
        return server != null;
    }
    
    /**
     * Initializes CraftBukkit components
     */
    public static void initialize() {
        if (server == null) {
            server = Bukkit.getServer();
        }
    }
}