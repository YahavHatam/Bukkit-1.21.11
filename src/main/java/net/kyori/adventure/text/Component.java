package net.kyori.adventure.text;

import net.kyori.adventure.Adventure;
import java.util.List;

/**
 * Component implementation for Adventure API
 */
public class Component {
    
    private final String content;
    private final Adventure.TextColor color;
    
    /**
     * Creates a new text component
     * @param content Text content
     */
    private Component(String content) {
        this(content, null);
    }
    
    /**
     * Creates a new text component with color
     * @param content Text content
     * @param color Text color
     */
    private Component(String content, Adventure.TextColor color) {
        this.content = content;
        this.color = color;
    }
    
    /**
     * Creates a text component
     * @param text Text content
     * @return Text component
     */
    public static Component text(String text) {
        return new Component(text);
    }
    
    /**
     * Creates a colored text component
     * @param text Text content
     * @param color Text color
     * @return Colored text component
     */
    public static Component text(String text, Adventure.TextColor color) {
        return new Component(text, color);
    }
    
    /**
     * Joins multiple components
     * @param components Components to join
     * @return Joined component
     */
    public static Component join(Component... components) {
        StringBuilder builder = new StringBuilder();
        for (Component component : components) {
            builder.append(component.content);
        }
        return new Component(builder.toString());
    }
    
    /**
     * Gets the component content
     * @return Text content
     */
    public String content() {
        return content;
    }
    
    /**
     * Gets the component color
     * @return Text color
     */
    public Adventure.TextColor color() {
        return color;
    }
    
    /**
     * Creates a new component with appended text
     * @param text Text to append
     * @return New component with appended text
     */
    public Component append(String text) {
        return new Component(this.content + text, this.color);
    }
    
    /**
     * Creates a new component with different color
     * @param color New color
     * @return New component with different color
     */
    public Component color(Adventure.TextColor color) {
        return new Component(this.content, color);
    }
    
    @Override
    public String toString() {
        return content;
    }
}