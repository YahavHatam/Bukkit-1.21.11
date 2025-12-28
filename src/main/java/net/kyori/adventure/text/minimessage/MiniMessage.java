package net.kyori.adventure.text.minimessage;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.Adventure;

/**
 * MiniMessage implementation for Adventure API
 * Provides lightweight markup parsing for text components
 */
public class MiniMessage {
    
    private static final MiniMessage INSTANCE = new MiniMessage();
    
    /**
     * Gets the MiniMessage instance
     * @return MiniMessage instance
     */
    public static MiniMessage miniMessage() {
        return INSTANCE;
    }
    
    /**
     * Deserializes a MiniMessage string to a component
     * @param input MiniMessage formatted string
     * @return Text component
     */
    public Component deserialize(String input) {
        if (input == null) {
            return Component.text("");
        }
        
        // Simple implementation - strip basic MiniMessage tags
        String cleanText = input
            .replaceAll("<black>", "")
            .replaceAll("<dark_blue>", "")
            .replaceAll("<dark_green>", "")
            .replaceAll("<dark_aqua>", "")
            .replaceAll("<dark_red>", "")
            .replaceAll("<dark_purple>", "")
            .replaceAll("<gold>", "")
            .replaceAll("<gray>", "")
            .replaceAll("<dark_gray>", "")
            .replaceAll("<blue>", "")
            .replaceAll("<green>", "")
            .replaceAll("<aqua>", "")
            .replaceAll("<red>", "")
            .replaceAll("<light_purple>", "")
            .replaceAll("<yellow>", "")
            .replaceAll("<white>", "")
            .replaceAll("<reset>", "")
            .replaceAll("<b>", "")
            .replaceAll("<i>", "")
            .replaceAll("<u>", "")
            .replaceAll("<s>", "")
            .replaceAll("</[a-z_]+>", "");
        
        return Component.text(cleanText);
    }
    
    /**
     * Serializes a component to MiniMessage format
     * @param component Text component
     * @return MiniMessage formatted string
     */
    public String serialize(Component component) {
        if (component == null) {
            return "";
        }
        
        // Simple implementation - return plain text for now
        return component.content();
    }
    
    /**
     * Creates a MiniMessage parser with custom tags
     * @return MiniMessage parser
     */
    public static Parser parser() {
        return new Parser();
    }
    
    /**
     * MiniMessage parser class
     */
    public static class Parser {
        
        /**
         * Parses a MiniMessage string
         * @param input MiniMessage formatted string
         * @return Text component
         */
        public Component parse(String input) {
            return MiniMessage.miniMessage().deserialize(input);
        }
        
        /**
         * Creates a parser with custom settings
         * @return Parser instance
         */
        public Parser withSettings() {
            return this;
        }
    }
}