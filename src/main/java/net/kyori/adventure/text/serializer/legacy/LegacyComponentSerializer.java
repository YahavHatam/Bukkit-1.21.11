package net.kyori.adventure.text.serializer.legacy;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.Adventure;

/**
 * Legacy component serializer for Adventure API
 * Converts between legacy format strings and modern components
 */
public class LegacyComponentSerializer {
    
    private static final LegacyComponentSerializer INSTANCE = new LegacyComponentSerializer();
    
    /**
     * Gets the legacy component serializer instance
     * @return Legacy component serializer
     */
    public static LegacyComponentSerializer legacySection() {
        return INSTANCE;
    }
    
    /**
     * Deserializes a legacy format string to a component
     * @param input Legacy formatted string
     * @return Text component
     */
    public Component deserialize(String input) {
        if (input == null) {
            return Component.text("");
        }
        
        // Simple implementation - strip color codes for now
        String cleanText = input.replaceAll("§[0-9a-fA-Fk-oK-ORr]", "");
        return Component.text(cleanText);
    }
    
    /**
     * Serializes a component to legacy format
     * @param component Text component
     * @return Legacy formatted string
     */
    public String serialize(Component component) {
        if (component == null) {
            return "";
        }
        
        // Simple implementation - return plain text for now
        return component.content();
    }
    
    /**
     * Serializes a component with color codes
     * @param component Text component
     * @return Legacy formatted string with color codes
     */
    public String serializeWithColor(Component component) {
        if (component == null || component.color() == null) {
            return serialize(component);
        }
        
        String colorCode = getColorCode(component.color());
        return colorCode + component.content();
    }
    
    /**
     * Gets the color code for a text color
     * @param color Text color
     * @return Color code
     */
    private String getColorCode(Adventure.TextColor color) {
        switch (color) {
            case BLACK: return "§0";
            case DARK_BLUE: return "§1";
            case DARK_GREEN: return "§2";
            case DARK_AQUA: return "§3";
            case DARK_RED: return "§4";
            case DARK_PURPLE: return "§5";
            case GOLD: return "§6";
            case GRAY: return "§7";
            case DARK_GRAY: return "§8";
            case BLUE: return "§9";
            case GREEN: return "§a";
            case AQUA: return "§b";
            case RED: return "§c";
            case LIGHT_PURPLE: return "§d";
            case YELLOW: return "§e";
            case WHITE: return "§f";
            default: return "";
        }
    }
}