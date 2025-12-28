package net.kyori.adventure;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;

/**
 * Adventure API implementation for Bukkit 1.21.11
 * Provides modern text and messaging functionality
 */
public class Adventure {
    
    private static final LegacyComponentSerializer legacySerializer = LegacyComponentSerializer.legacySection();
    
    /**
     * Creates a text component from a string
     * @param text Text content
     * @return Text component
     */
    public static Component text(String text) {
        return Component.text(text);
    }
    
    /**
     * Creates a colored text component
     * @param text Text content
     * @param color Text color
     * @return Colored text component
     */
    public static Component text(String text, TextColor color) {
        return Component.text(text, color);
    }
    
    /**
     * Converts a legacy format string to a component
     * @param legacyText Legacy formatted text
     * @return Text component
     */
    public static Component fromLegacy(String legacyText) {
        return legacySerializer.deserialize(legacyText);
    }
    
    /**
     * Converts a component to legacy format
     * @param component Text component
     * @return Legacy formatted string
     */
    public static String toLegacy(Component component) {
        return legacySerializer.serialize(component);
    }
    
    /**
     * Text color enum
     */
    public enum TextColor {
        BLACK, DARK_BLUE, DARK_GREEN, DARK_AQUA, DARK_RED,
        DARK_PURPLE, GOLD, GRAY, DARK_GRAY, BLUE,
        GREEN, AQUA, RED, LIGHT_PURPLE, YELLOW, WHITE
    }
    
    /**
     * Gets the legacy component serializer
     * @return Legacy component serializer
     */
    public static LegacyComponentSerializer getLegacySerializer() {
        return legacySerializer;
    }
}