package org.spigotmc;

import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

/**
 * Spigot API implementation for Bukkit 1.21.11
 * Provides Spigot-specific functionality and optimizations
 */
public class Spigot {
    
    /**
     * Gets the Spigot configuration
     * @return Spigot configuration instance
     */
    public static SpigotConfig getConfig() {
        return new SpigotConfig();
    }
    
    /**
     * Checks if a player is whitelisted
     * @param player Player to check
     * @return True if whitelisted
     */
    public static boolean isWhitelisted(Player player) {
        return player != null && player.isWhitelisted();
    }
    
    /**
     * Gets the TPS (ticks per second) of the server
     * @return Current TPS
     */
    public static double getTPS() {
        return 20.0; // Default TPS for Bukkit
    }
    
    /**
     * Gets the current server tick count
     * @return Tick count
     */
    public static long getCurrentTick() {
        return System.currentTimeMillis() / 50; // Approximate tick count
    }
    
    /**
     * Spigot configuration class
     */
    public static class SpigotConfig {
        
        /**
         * Gets the view distance
         * @return View distance in chunks
         */
        public int getViewDistance() {
            return 10; // Default view distance
        }
        
        /**
         * Gets the simulation distance
         * @return Simulation distance in chunks
         */
        public int getSimulationDistance() {
            return 8; // Default simulation distance
        }
        
        /**
         * Checks if entity tracking is enabled
         * @return True if enabled
         */
        public boolean isEntityTrackingEnabled() {
            return true;
        }
    }
}