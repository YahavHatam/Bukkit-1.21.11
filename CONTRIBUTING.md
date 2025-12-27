# Contributing to Bukkit 1.21.11

Welcome to the Bukkit 1.21.11 clean fork! This is a community-driven project focused on maintaining a stable Bukkit API without Bedrock support.

## Quick Start

1. **Check existing issues** on GitHub before starting
2. **Fork the repository** 
3. **Create a descriptive branch** for your changes
4. **Make your changes** following our guidelines
5. **Test thoroughly** 
6. **Submit a pull request** with clear description

## What We Accept

- **Bug fixes** that maintain API compatibility
- **Performance improvements** 
- **Documentation enhancements**
- **Reasonable feature additions** that don't break existing plugins
- **Code cleanup** and refactoring

## Coding Standards

### Requirements
- **Java 21** compatibility
- **4 spaces** for indentation (no tabs)
- **No trailing whitespace**
- **LF line endings** (not CRLF)
- **Newline at end of every file**
- **Alphabetical imports** grouped by package

### Example Import Organization
```java
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
```

### Code Style
- Follow standard Java conventions
- Include Javadoc for public methods/classes
- Keep methods focused and concise
- Use descriptive variable names
- Avoid magic numbers - use constants

## Commit Guidelines

### Format
```
Brief summary of changes

Detailed description explaining what the change does, 
why it's needed, and how it addresses the issue.
Keep lines under 78 characters.
```

### Examples
```
Fix player teleportation validation

Added null checks for teleport destination to prevent
NullPointerException when teleporting to invalid locations.
```

```
Add configuration option for spawn protection

New config option 'spawn-protection-radius' allows server
administrators to customize the protected spawn area size.
```

## Making Changes

### Before You Start
- Search existing issues and pull requests
- Ensure your change fits project goals
- Plan your approach to minimize impact

### During Development
- Make small, focused commits
- Test your changes as you go
- Ensure code compiles without errors
- Check for unnecessary whitespace: `git diff --check`

### Testing
- Test with existing plugins if possible
- Verify no regression in functionality
- Test edge cases and error conditions
- Include unit tests for new features

## Pull Request Process

### PR Title
Use a clear, descriptive title:
- `Fix: brief description of bug fix`
- `Add: brief description of new feature` 
- `Update: brief description of documentation change`

### PR Description
Include:
- **Problem**: What issue does this address?
- **Solution**: How does this PR fix it?
- **Testing**: How did you test this change?
- **Impact**: Any breaking changes or compatibility notes?

### PR Checklist
- [ ] Code compiles without errors
- [ ] Follows coding standards
- [ ] Includes tests if applicable
- [ ] Documentation updated if needed
- [ ] No breaking changes without justification

## API Compatibility

This fork maintains **full API compatibility** with Bukkit. When contributing:

- **DO NOT** remove or change public API methods
- **DO NOT** change method signatures
- **DO NOT** remove events
- **DO** add new methods and events
- **DO** deprecate old methods before removal (if ever needed)

## Examples

### Adding a New Event
```java
/**
 * Called when a player achieves something
 */
public class PlayerAchievementEvent extends PlayerEvent {
    private final String achievement;
    
    public PlayerAchievementEvent(Player player, String achievement) {
        super(player);
        this.achievement = achievement;
    }
    
    public String getAchievement() {
        return achievement;
    }
}
```

### Firing an Event
```java
PlayerAchievementEvent event = new PlayerAchievementEvent(player, "first.join");
Bukkit.getServer().getPluginManager().callEvent(event);
```

## Getting Help

- **GitHub Issues**: Report bugs and request features
- **Pull Requests**: For code contributions
- **Discussions**: For questions and general discussion

## License

By contributing, you agree that your contributions will be licensed under the same license as the original Bukkit project.

---

Thank you for contributing to Bukkit 1.21.11! Your help keeps this project alive and improving.