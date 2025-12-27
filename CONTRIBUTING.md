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

## Advanced Development Guidelines

### Plugin API Design Principles

When extending the Bukkit API, follow these design principles:

#### 1. Backward Compatibility
- Never remove or change existing public methods
- Use `@Deprecated` annotations for methods planned for removal
- Provide migration paths for deprecated APIs
- Maintain binary compatibility between minor versions

#### 2. Event System Design
- Events should be immutable where possible
- Provide both synchronous and asynchronous variants when appropriate
- Include comprehensive event cancellation support
- Document event firing order and dependencies

#### 3. Configuration Management
- Use Bukkit's built-in configuration system
- Provide sensible defaults for all settings
- Validate configuration values on load
- Support configuration reloading without server restart

#### 4. Performance Considerations
- Avoid object allocation in hot paths
- Use efficient data structures for frequent operations
- Consider thread safety for multi-threaded environments
- Profile critical code paths before optimization

### Code Architecture Patterns

#### Service Pattern
```java
/**
 * Example service for managing player data
 */
public interface PlayerDataService {
    /**
     * Gets player data by UUID
     * @param uuid Player UUID
     * @return Player data or null if not found
     */
    PlayerData getPlayerData(UUID uuid);
    
    /**
     * Sets player data
     * @param uuid Player UUID
     * @param data Player data to store
     */
    void setPlayerData(UUID uuid, PlayerData data);
    
    /**
     * Removes player data
     * @param uuid Player UUID
     * @return True if data was removed
     */
    boolean removePlayerData(UUID uuid);
}

public class PlayerDataServiceImpl implements PlayerDataService {
    private final Map<UUID, PlayerData> playerDataCache = new ConcurrentHashMap<>();
    
    @Override
    public PlayerData getPlayerData(UUID uuid) {
        return playerDataCache.get(uuid);
    }
    
    @Override
    public void setPlayerData(UUID uuid, PlayerData data) {
        playerDataCache.put(uuid, data);
    }
    
    @Override
    public boolean removePlayerData(UUID uuid) {
        return playerDataCache.remove(uuid) != null;
    }
}
```

#### Factory Pattern
```java
/**
 * Factory for creating different types of events
 */
public class EventFactory {
    /**
     * Creates a custom player event
     * @param player The player involved
     * @param type Event type
     * @return Custom event instance
     */
    public static CustomPlayerEvent createCustomEvent(Player player, EventType type) {
        switch (type) {
            case JOIN:
                return new CustomPlayerJoinEvent(player);
            case LEAVE:
                return new CustomPlayerLeaveEvent(player);
            case ACHIEVEMENT:
                return new CustomPlayerAchievementEvent(player);
            default:
                throw new IllegalArgumentException("Unknown event type: " + type);
        }
    }
}
```

#### Builder Pattern
```java
/**
 * Builder for complex configuration objects
 */
public class ConfigurationBuilder {
    private boolean debugMode = false;
    private int maxPlayers = 20;
    private String serverName = "Bukkit Server";
    private List<String> allowedCommands = new ArrayList<>();
    
    public ConfigurationBuilder setDebugMode(boolean debugMode) {
        this.debugMode = debugMode;
        return this;
    }
    
    public ConfigurationBuilder setMaxPlayers(int maxPlayers) {
        this.maxPlayers = maxPlayers;
        return this;
    }
    
    public ConfigurationBuilder setServerName(String serverName) {
        this.serverName = serverName;
        return this;
    }
    
    public ConfigurationBuilder addAllowedCommand(String command) {
        this.allowedCommands.add(command);
        return this;
    }
    
    public ServerConfiguration build() {
        return new ServerConfiguration(debugMode, maxPlayers, serverName, allowedCommands);
    }
}
```

### Testing Strategies

#### Unit Testing
```java
/**
 * Example unit test for PlayerManager
 */
public class PlayerManagerTest {
    private PlayerManager playerManager;
    private Server mockServer;
    
    @Before
    public void setUp() {
        mockServer = mock(Server.class);
        playerManager = new PlayerManager(mockServer);
    }
    
    @Test
    public void testAddPlayer() {
        Player mockPlayer = mock(Player.class);
        when(mockPlayer.getUniqueId()).thenReturn(UUID.randomUUID());
        
        assertTrue(playerManager.addPlayer(mockPlayer));
        assertEquals(1, playerManager.getPlayerCount());
    }
    
    @Test
    public void testRemovePlayer() {
        Player mockPlayer = mock(Player.class);
        UUID playerId = UUID.randomUUID();
        when(mockPlayer.getUniqueId()).thenReturn(playerId);
        
        playerManager.addPlayer(mockPlayer);
        assertTrue(playerManager.removePlayer(playerId));
        assertEquals(0, playerManager.getPlayerCount());
    }
    
    @Test
    public void testGetPlayerByUUID() {
        Player mockPlayer = mock(Player.class);
        UUID playerId = UUID.randomUUID();
        when(mockPlayer.getUniqueId()).thenReturn(playerId);
        
        playerManager.addPlayer(mockPlayer);
        assertEquals(mockPlayer, playerManager.getPlayer(playerId));
    }
}
```

#### Integration Testing
```java
/**
 * Example integration test for plugin functionality
 */
public class PluginIntegrationTest {
    private Server mockServer;
    private PluginManager mockPluginManager;
    private TestPlugin testPlugin;
    
    @Before
    public void setUp() {
        mockServer = mock(Server.class);
        mockPluginManager = mock(PluginManager.class);
        when(mockServer.getPluginManager()).thenReturn(mockPluginManager);
        
        testPlugin = new TestPlugin();
        testPlugin.onLoad();
    }
    
    @Test
    public void testPluginEnable() {
        assertTrue(testPlugin.onEnable());
        verify(mockPluginManager).registerEvents(any(Listener.class), eq(testPlugin));
    }
    
    @Test
    public void testEventFiring() {
        Player mockPlayer = mock(Player.class);
        PlayerJoinEvent event = new PlayerJoinEvent(mockPlayer, "Player joined");
        
        // Test that our plugin handles the event correctly
        testPlugin.onPlayerJoin(event);
        // Add assertions based on expected behavior
    }
}
```

### Performance Optimization

#### Memory Management
```java
/**
 * Example of efficient object pooling
 */
public class ParticlePool {
    private final Queue<Particle> particlePool = new ConcurrentLinkedQueue<>();
    private final int maxPoolSize;
    
    public ParticlePool(int maxPoolSize) {
        this.maxPoolSize = maxPoolSize;
    }
    
    public Particle acquire() {
        Particle particle = particlePool.poll();
        if (particle == null) {
            particle = new Particle();
        }
        return particle;
    }
    
    public void release(Particle particle) {
        if (particlePool.size() < maxPoolSize) {
            particle.reset();
            particlePool.offer(particle);
        }
    }
}
```

#### Caching Strategies
```java
/**
 * Example of efficient caching with expiration
 */
public class CacheManager<K, V> {
    private final Map<K, CacheEntry<V>> cache = new ConcurrentHashMap<>();
    private final long expirationTime;
    
    public CacheManager(long expirationTime, TimeUnit unit) {
        this.expirationTime = unit.toMillis(expirationTime);
    }
    
    public V get(K key) {
        CacheEntry<V> entry = cache.get(key);
        if (entry == null || entry.isExpired()) {
            cache.remove(key);
            return null;
        }
        return entry.getValue();
    }
    
    public void put(K key, V value) {
        cache.put(key, new CacheEntry<>(value, System.currentTimeMillis() + expirationTime));
    }
    
    private static class CacheEntry<V> {
        private final V value;
        private final long expirationTime;
        
        CacheEntry(V value, long expirationTime) {
            this.value = value;
            this.expirationTime = expirationTime;
        }
        
        V getValue() {
            return value;
        }
        
        boolean isExpired() {
            return System.currentTimeMillis() > expirationTime;
        }
    }
}
```

### Security Best Practices

#### Input Validation
```java
/**
 * Example of comprehensive input validation
 */
public class InputValidator {
    private static final Pattern PLAYER_NAME_PATTERN = Pattern.compile("^[a-zA-Z0-9_]{1,16}$");
    private static final int MAX_MESSAGE_LENGTH = 256;
    
    /**
     * Validates player name
     * @param playerName Player name to validate
     * @return True if valid
     */
    public static boolean isValidPlayerName(String playerName) {
        return playerName != null && 
               PLAYER_NAME_PATTERN.matcher(playerName).matches();
    }
    
    /**
     * Validates chat message
     * @param message Message to validate
     * @return True if valid
     */
    public static boolean isValidChatMessage(String message) {
        return message != null && 
               message.length() <= MAX_MESSAGE_LENGTH &&
               !message.contains("\u0000"); // Null byte check
    }
    
    /**
     * Sanitizes command arguments
     * @param args Command arguments
     * @return Sanitized arguments
     */
    public static String[] sanitizeCommandArgs(String[] args) {
        return Arrays.stream(args)
                .filter(arg -> arg != null && !arg.trim().isEmpty())
                .map(arg -> arg.replaceAll("[^a-zA-Z0-9_\\-]", ""))
                .toArray(String[]::new);
    }
}
```

#### Permission Checking
```java
/**
 * Example of comprehensive permission checking
 */
public class PermissionChecker {
    private final Server server;
    
    public PermissionChecker(Server server) {
        this.server = server;
    }
    
    /**
     * Checks if player has permission
     * @param player Player to check
     * @param permission Permission node
     * @return True if player has permission
     */
    public boolean hasPermission(Player player, String permission) {
        if (player == null || permission == null) {
            return false;
        }
        
        // Check explicit permission
        if (player.hasPermission(permission)) {
            return true;
        }
        
        // Check wildcard permissions
        String[] parts = permission.split("\\.");
        for (int i = parts.length - 1; i > 0; i--) {
            String wildcard = String.join(".", Arrays.copyOf(parts, i)) + ".*";
            if (player.hasPermission(wildcard)) {
                return true;
            }
        }
        
        return false;
    }
    
    /**
     * Checks if player is operator
     * @param player Player to check
     * @return True if player is operator
     */
    public boolean isOperator(Player player) {
        return player != null && player.isOp();
    }
}
```

### Error Handling and Logging

#### Exception Handling
```java
/**
 * Example of comprehensive exception handling
 */
public class ExceptionHandler {
    private static final Logger logger = Logger.getLogger("Bukkit");
    
    /**
     * Handles exceptions with proper logging
     * @param context Context where error occurred
     * @param exception The exception
     * @param fallback Fallback action
     */
    public static void handleException(String context, Exception exception, Runnable fallback) {
        logger.log(Level.SEVERE, "Exception in " + context, exception);
        
        if (fallback != null) {
            try {
                fallback.run();
            } catch (Exception fallbackException) {
                logger.log(Level.SEVERE, "Fallback action failed", fallbackException);
            }
        }
    }
    
    /**
     * Handles exceptions with user-friendly messages
     * @param player Player to notify (optional)
     * @param userMessage User-friendly message
     * @param exception The exception
     */
    public static void handleException(Player player, String userMessage, Exception exception) {
        logger.log(Level.WARNING, userMessage, exception);
        
        if (player != null && player.isOnline()) {
            player.sendMessage(ChatColor.RED + "Error: " + userMessage);
        }
    }
}
```

#### Structured Logging
```java
/**
 * Example of structured logging
 */
public class StructuredLogger {
    private final Logger logger;
    private final String component;
    
    public StructuredLogger(String component) {
        this.logger = Logger.getLogger("Bukkit." + component);
        this.component = component;
    }
    
    public void logInfo(String message, Object... args) {
        logger.info(formatMessage("INFO", message, args));
    }
    
    public void logWarning(String message, Object... args) {
        logger.warning(formatMessage("WARN", message, args));
    }
    
    public void logError(String message, Throwable throwable, Object... args) {
        logger.severe(formatMessage("ERROR", message, args));
        if (throwable != null) {
            logger.log(Level.SEVERE, "Stack trace:", throwable);
        }
    }
    
    private String formatMessage(String level, String message, Object... args) {
        String timestamp = Instant.now().toString();
        String formattedMessage = String.format(message, args);
        return String.format("[%s] [%s] [%s] %s", timestamp, level, component, formattedMessage);
    }
}
```

### Configuration Management

#### Advanced Configuration
```java
/**
 * Example of advanced configuration management
 */
public class AdvancedConfiguration extends FileConfiguration {
    private final Map<String, Object> defaults;
    private final Set<String> requiredKeys;
    
    public AdvancedConfiguration() {
        this.defaults = new HashMap<>();
        this.requiredKeys = new HashSet<>();
        initializeDefaults();
    }
    
    private void initializeDefaults() {
        defaults.put("server.name", "Bukkit Server");
        defaults.put("server.max-players", 20);
        defaults.put("server.debug", false);
        defaults.put("database.url", "jdbc:sqlite:plugins.db");
        
        requiredKeys.add("server.name");
        requiredKeys.add("server.max-players");
    }
    
    @Override
    public void set(String path, Object value) {
        if (value == null) {
            logger.warning("Attempting to set null value for path: " + path);
            return;
        }
        
        // Validate value type
        if (!isValidType(path, value)) {
            logger.warning("Invalid type for path " + path + ": " + value.getClass().getSimpleName());
            return;
        }
        
        super.set(path, value);
    }
    
    @Override
    public Object get(String path) {
        Object value = super.get(path);
        if (value == null && defaults.containsKey(path)) {
            return defaults.get(path);
        }
        return value;
    }
    
    public boolean validateConfiguration() {
        List<String> missingKeys = requiredKeys.stream()
                .filter(key -> !contains(key))
                .collect(Collectors.toList());
        
        if (!missingKeys.isEmpty()) {
            logger.severe("Missing required configuration keys: " + String.join(", ", missingKeys));
            return false;
        }
        
        return true;
    }
    
    private boolean isValidType(String path, Object value) {
        // Add type validation logic based on path patterns
        if (path.startsWith("server.max-")) {
            return value instanceof Number;
        }
        if (path.endsWith(".enabled")) {
            return value instanceof Boolean;
        }
        return true;
    }
}
```

### Database Integration

#### Database Connection Pool
```java
/**
 * Example of database connection pooling
 */
public class DatabaseManager {
    private final HikariDataSource dataSource;
    private final StructuredLogger logger;
    
    public DatabaseManager(DatabaseConfig config) {
        this.logger = new StructuredLogger("Database");
        this.dataSource = createDataSource(config);
    }
    
    private HikariDataSource createDataSource(DatabaseConfig config) {
        HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setJdbcUrl(config.getUrl());
        hikariConfig.setUsername(config.getUsername());
        hikariConfig.setPassword(config.getPassword());
        hikariConfig.setMaximumPoolSize(config.getMaxPoolSize());
        hikariConfig.setMinimumIdle(config.getMinIdle());
        hikariConfig.setConnectionTimeout(config.getConnectionTimeout());
        hikariConfig.setIdleTimeout(config.getIdleTimeout());
        
        return new HikariDataSource(hikariConfig);
    }
    
    /**
     * Executes a query with result mapping
     * @param sql SQL query
     * @param mapper Result mapper
     * @param parameters Query parameters
     * @return List of mapped results
     */
    public <T> List<T> query(String sql, RowMapper<T> mapper, Object... parameters) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            
            // Set parameters
            for (int i = 0; i < parameters.length; i++) {
                statement.setObject(i + 1, parameters[i]);
            }
            
            // Execute query
            try (ResultSet resultSet = statement.executeQuery()) {
                List<T> results = new ArrayList<>();
                while (resultSet.next()) {
                    results.add(mapper.mapRow(resultSet));
                }
                return results;
            }
        } catch (SQLException e) {
            logger.logError("Database query failed", e);
            throw new DatabaseException("Query execution failed", e);
        }
    }
    
    /**
     * Executes an update statement
     * @param sql SQL update
     * @param parameters Update parameters
     * @return Number of affected rows
     */
    public int update(String sql, Object... parameters) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            
            for (int i = 0; i < parameters.length; i++) {
                statement.setObject(i + 1, parameters[i]);
            }
            
            return statement.executeUpdate();
        } catch (SQLException e) {
            logger.logError("Database update failed", e);
            throw new DatabaseException("Update execution failed", e);
        }
    }
    
    public void close() {
        if (dataSource != null && !dataSource.isClosed()) {
            dataSource.close();
            logger.logInfo("Database connection pool closed");
        }
    }
}
```

### Network Communication

#### Packet Handling
```java
/**
 * Example of custom packet handling
 */
public class PacketHandler {
    private final Map<Integer, PacketProcessor> processors = new ConcurrentHashMap<>();
    private final StructuredLogger logger;
    
    public PacketHandler() {
        this.logger = new StructuredLogger("PacketHandler");
        registerProcessors();
    }
    
    private void registerProcessors() {
        processors.put(0x01, new KeepAliveProcessor());
        processors.put(0x02, new ChatMessageProcessor());
        processors.put(0x03, new PositionUpdateProcessor());
    }
    
    /**
     * Processes incoming packet
     * @param player Player who sent packet
     * @param packetId Packet ID
     * @param data Packet data
     */
    public void processPacket(Player player, int packetId, byte[] data) {
        PacketProcessor processor = processors.get(packetId);
        if (processor == null) {
            logger.logWarning("Unknown packet ID: " + packetId + " from player: " + player.getName());
            return;
        }
        
        try {
            processor.process(player, data);
        } catch (Exception e) {
            logger.logError("Error processing packet " + packetId, e);
            // Optionally disconnect player for malformed packets
            if (processor.isCritical()) {
                player.kickPlayer("Packet processing error");
            }
        }
    }
    
    /**
     * Registers a new packet processor
     * @param packetId Packet ID
     * @param processor Packet processor
     */
    public void registerProcessor(int packetId, PacketProcessor processor) {
        processors.put(packetId, processor);
        logger.logInfo("Registered packet processor for ID: " + packetId);
    }
}
```

### World Management

#### Chunk Management
```java
/**
 * Example of efficient chunk management
 */
public class ChunkManager {
    private final Map<ChunkCoordinate, Chunk> loadedChunks = new ConcurrentHashMap<>();
    private final Queue<ChunkCoordinate> unloadQueue = new ConcurrentLinkedQueue<>();
    private final int maxLoadedChunks;
    private final StructuredLogger logger;
    
    public ChunkManager(int maxLoadedChunks) {
        this.maxLoadedChunks = maxLoadedChunks;
        this.logger = new StructuredLogger("ChunkManager");
    }
    
    /**
     * Loads a chunk
     * @param coordinate Chunk coordinate
     * @return Loaded chunk
     */
    public Chunk loadChunk(ChunkCoordinate coordinate) {
        Chunk chunk = loadedChunks.get(coordinate);
        if (chunk != null) {
            return chunk;
        }
        
        // Check if we need to unload chunks
        if (loadedChunks.size() >= maxLoadedChunks) {
            unloadLeastRecentlyUsedChunk();
        }
        
        // Load new chunk
        chunk = loadChunkFromDisk(coordinate);
        loadedChunks.put(coordinate, chunk);
        
        logger.logInfo("Loaded chunk at " + coordinate);
        return chunk;
    }
    
    /**
     * Unloads a chunk
     * @param coordinate Chunk coordinate
     * @return True if chunk was unloaded
     */
    public boolean unloadChunk(ChunkCoordinate coordinate) {
        Chunk chunk = loadedChunks.remove(coordinate);
        if (chunk != null) {
            saveChunkToDisk(chunk);
            logger.logInfo("Unloaded chunk at " + coordinate);
            return true;
        }
        return false;
    }
    
    /**
     * Gets loaded chunk
     * @param coordinate Chunk coordinate
     * @return Chunk or null if not loaded
     */
    public Chunk getChunk(ChunkCoordinate coordinate) {
        return loadedChunks.get(coordinate);
    }
    
    private void unloadLeastRecentlyUsedChunk() {
        // Find least recently used chunk
        ChunkCoordinate oldestChunk = loadedChunks.keySet().stream()
                .min(Comparator.comparing(coord -> loadedChunks.get(coord).getLastAccess()))
                .orElse(null);
        
        if (oldestChunk != null) {
            unloadChunk(oldestChunk);
        }
    }
    
    private Chunk loadChunkFromDisk(ChunkCoordinate coordinate) {
        // Implementation for loading chunk from disk
        return new Chunk(coordinate);
    }
    
    private void saveChunkToDisk(Chunk chunk) {
        // Implementation for saving chunk to disk
    }
}
```

### Plugin Development

#### Plugin Lifecycle Management
```java
/**
 * Example of comprehensive plugin lifecycle management
 */
public abstract class AdvancedPlugin extends JavaPlugin {
    private final List<ManagedService> services = new ArrayList<>();
    private final StructuredLogger logger;
    private volatile boolean enabled = false;
    
    public AdvancedPlugin() {
        this.logger = new StructuredLogger(getName());
    }
    
    @Override
    public final void onEnable() {
        if (enabled) {
            logger.logWarning("Plugin is already enabled");
            return;
        }
        
        try {
            // Initialize services
            initializeServices();
            
            // Register events
            registerEvents();
            
            // Register commands
            registerCommands();
            
            // Start services
            startServices();
            
            // Custom enable logic
            onPluginEnable();
            
            enabled = true;
            logger.logInfo("Plugin enabled successfully");
            
        } catch (Exception e) {
            logger.logError("Failed to enable plugin", e);
            // Disable plugin on failure
            getServer().getPluginManager().disablePlugin(this);
        }
    }
    
    @Override
    public final void onDisable() {
        if (!enabled) {
            logger.logWarning("Plugin is already disabled");
            return;
        }
        
        try {
            // Custom disable logic
            onPluginDisable();
            
            // Stop services
            stopServices();
            
            // Cleanup resources
            cleanup();
            
            enabled = false;
            logger.logInfo("Plugin disabled successfully");
            
        } catch (Exception e) {
            logger.logError("Error during plugin disable", e);
        }
    }
    
    /**
     * Initializes plugin services
     */
    protected void initializeServices() {
        // Override in subclasses
    }
    
    /**
     * Registers event listeners
     */
    protected void registerEvents() {
        // Override in subclasses
    }
    
    /**
     * Registers commands
     */
    protected void registerCommands() {
        // Override in subclasses
    }
    
    /**
     * Custom enable logic
     */
    protected abstract void onPluginEnable();
    
    /**
     * Custom disable logic
     */
    protected abstract void onPluginDisable();
    
    /**
     * Adds a managed service
     * @param service Service to manage
     */
    protected final void addService(ManagedService service) {
        services.add(service);
    }
    
    private void startServices() {
        for (ManagedService service : services) {
            try {
                service.start();
                logger.logInfo("Started service: " + service.getName());
            } catch (Exception e) {
                logger.logError("Failed to start service: " + service.getName(), e);
            }
        }
    }
    
    private void stopServices() {
        for (ManagedService service : services) {
            try {
                service.stop();
                logger.logInfo("Stopped service: " + service.getName());
            } catch (Exception e) {
                logger.logError("Failed to stop service: " + service.getName(), e);
            }
        }
    }
    
    private void cleanup() {
        services.clear();
    }
}
```

### Command System

#### Advanced Command Handling
```java
/**
 * Example of advanced command system
 */
public class CommandManager {
    private final Map<String, CommandExecutor> commands = new ConcurrentHashMap<>();
    private final Map<String, CommandPermission> permissions = new ConcurrentHashMap<>();
    private final StructuredLogger logger;
    
    public CommandManager() {
        this.logger = new StructuredLogger("CommandManager");
    }
    
    /**
     * Registers a command
     * @param name Command name
     * @param executor Command executor
     * @param permission Required permission
     */
    public void registerCommand(String name, CommandExecutor executor, String permission) {
        commands.put(name.toLowerCase(), executor);
        permissions.put(name.toLowerCase(), new CommandPermission(permission));
        logger.logInfo("Registered command: " + name);
    }
    
    /**
     * Executes a command
     * @param sender Command sender
     * @param command Command to execute
     * @param args Command arguments
     * @return True if command was executed
     */
    public boolean executeCommand(CommandSender sender, String command, String[] args) {
        CommandExecutor executor = commands.get(command.toLowerCase());
        if (executor == null) {
            sender.sendMessage(ChatColor.RED + "Unknown command: " + command);
            return false;
        }
        
        CommandPermission permission = permissions.get(command.toLowerCase());
        if (!permission.hasPermission(sender)) {
            sender.sendMessage(ChatColor.RED + "You don't have permission to use this command");
            return false;
        }
        
        try {
            return executor.onCommand(sender, command, args);
        } catch (Exception e) {
            logger.logError("Error executing command: " + command, e);
            sender.sendMessage(ChatColor.RED + "An error occurred while executing the command");
            return false;
        }
    }
    
    /**
     * Gets command suggestions
     * @param sender Command sender
     * @param command Partial command
     * @param args Current arguments
     * @return List of suggestions
     */
    public List<String> getTabCompletions(CommandSender sender, String command, String[] args) {
        CommandExecutor executor = commands.get(command.toLowerCase());
        if (executor == null) {
            return Collections.emptyList();
        }
        
        CommandPermission permission = permissions.get(command.toLowerCase());
        if (!permission.hasPermission(sender)) {
            return Collections.emptyList();
        }
        
        try {
            return executor.onTabComplete(sender, command, args);
        } catch (Exception e) {
            logger.logError("Error getting tab completions for command: " + command, e);
            return Collections.emptyList();
        }
    }
    
    /**
     * Gets all registered commands
     * @return Set of command names
     */
    public Set<String> getCommands() {
        return new HashSet<>(commands.keySet());
    }
}
```

### Metrics and Analytics

#### Performance Monitoring
```java
/**
 * Example of performance monitoring system
 */
public class PerformanceMonitor {
    private final Map<String, Metric> metrics = new ConcurrentHashMap<>();
    private final ScheduledExecutorService scheduler;
    private final StructuredLogger logger;
    
    public PerformanceMonitor() {
        this.logger = new StructuredLogger("Performance");
        this.scheduler = Executors.newScheduledThreadPool(1);
        startMonitoring();
    }
    
    /**
     * Records a metric value
     * @param name Metric name
     * @param value Metric value
     */
    public void recordMetric(String name, double value) {
        Metric metric = metrics.computeIfAbsent(name, k -> new Metric(k));
        metric.record(value);
    }
    
    /**
     * Records execution time
     * @param name Operation name
     * @param operation Operation to measure
     * @return Operation result
     */
    public <T> T measureTime(String name, Supplier<T> operation) {
        long startTime = System.nanoTime();
        try {
            T result = operation.get();
            long endTime = System.nanoTime();
            recordMetric(name + ".duration", (endTime - startTime) / 1_000_000.0); // Convert to milliseconds
            return result;
        } catch (Exception e) {
            long endTime = System.nanoTime();
            recordMetric(name + ".duration", (endTime - startTime) / 1_000_000.0);
            recordMetric(name + ".errors", 1);
            throw e;
        }
    }
    
    /**
     * Gets metric statistics
     * @param name Metric name
     * @return Metric statistics
     */
    public MetricStatistics getStatistics(String name) {
        Metric metric = metrics.get(name);
        return metric != null ? metric.getStatistics() : null;
    }
    
    private void startMonitoring() {
        scheduler.scheduleAtFixedRate(() -> {
            try {
                reportMetrics();
            } catch (Exception e) {
                logger.logError("Error reporting metrics", e);
            }
        }, 0, 60, TimeUnit.SECONDS);
    }
    
    private void reportMetrics() {
        for (Metric metric : metrics.values()) {
            MetricStatistics stats = metric.getStatistics();
            logger.logInfo("Metric " + metric.getName() + 
                         ": count=" + stats.getCount() +
                         ", avg=" + String.format("%.2f", stats.getAverage()) +
                         ", min=" + String.format("%.2f", stats.getMin()) +
                         ", max=" + String.format("%.2f", stats.getMax()));
        }
    }
    
    public void shutdown() {
        scheduler.shutdown();
        try {
            if (!scheduler.awaitTermination(5, TimeUnit.SECONDS)) {
                scheduler.shutdownNow();
            }
        } catch (InterruptedException e) {
            scheduler.shutdownNow();
            Thread.currentThread().interrupt();
        }
    }
    
    private static class Metric {
        private final String name;
        private final AtomicLong count = new AtomicLong(0);
        private final AtomicDouble sum = new AtomicDouble(0.0);
        private final AtomicDouble min = new AtomicDouble(Double.MAX_VALUE);
        private final AtomicDouble max = new AtomicDouble(Double.MIN_VALUE);
        
        public Metric(String name) {
            this.name = name;
        }
        
        public void record(double value) {
            count.incrementAndGet();
            sum.addAndGet(value);
            min.updateAndGet(current -> Math.min(current, value));
            max.updateAndGet(current -> Math.max(current, value));
        }
        
        public String getName() {
            return name;
        }
        
        public MetricStatistics getStatistics() {
            long countValue = count.get();
            return new MetricStatistics(
                countValue,
                countValue > 0 ? sum.get() / countValue : 0.0,
                min.get(),
                max.get()
            );
        }
    }
    
    public static class MetricStatistics {
        private final long count;
        private final double average;
        private final double min;
        private final double max;
        
        public MetricStatistics(long count, double average, double min, double max) {
            this.count = count;
            this.average = average;
            this.min = min;
            this.max = max;
        }
        
        public long getCount() { return count; }
        public double getAverage() { return average; }
        public double getMin() { return min; }
        public double getMax() { return max; }
    }
}
```

### Internationalization

#### Multi-language Support
```java
/**
 * Example of internationalization system
 */
public class I18nManager {
    private final Map<String, Map<String, String>> translations = new ConcurrentHashMap<>();
    private final Map<UUID, String> playerLocales = new ConcurrentHashMap<>();
    private final String defaultLocale;
    private final StructuredLogger logger;
    
    public I18nManager(String defaultLocale) {
        this.defaultLocale = defaultLocale;
        this.logger = new StructuredLogger("I18n");
        loadTranslations();
    }
    
    /**
     * Gets translated message
     * @param key Message key
     * @param locale Locale code
     * @param args Message arguments
     * @return Translated message
     */
    public String getMessage(String key, String locale, Object... args) {
        Map<String, String> localeTranslations = translations.get(locale);
        if (localeTranslations == null) {
            localeTranslations = translations.get(defaultLocale);
        }
        
        String message = localeTranslations != null ? localeTranslations.get(key) : null;
        if (message == null) {
            logger.logWarning("Missing translation for key: " + key + " in locale: " + locale);
            return key; // Return key as fallback
        }
        
        return args.length > 0 ? String.format(message, args) : message;
    }
    
    /**
     * Gets translated message for player
     * @param player Player
     * @param key Message key
     * @param args Message arguments
     * @return Translated message
     */
    public String getMessage(Player player, String key, Object... args) {
        String locale = playerLocales.getOrDefault(player.getUniqueId(), defaultLocale);
        return getMessage(key, locale, args);
    }
    
    /**
     * Sets player locale
     * @param player Player
     * @param locale Locale code
     */
    public void setPlayerLocale(Player player, String locale) {
        playerLocales.put(player.getUniqueId(), locale);
        logger.logInfo("Set locale for player " + player.getName() + " to " + locale);
    }
    
    /**
     * Sends translated message to player
     * @param player Player
     * @param key Message key
     * @param args Message arguments
     */
    public void sendMessage(Player player, String key, Object... args) {
        String message = getMessage(player, key, args);
        player.sendMessage(ChatColor.translateAlternateColorCodes('&', message));
    }
    
    private void loadTranslations() {
        // Load translations from files or configuration
        Map<String, String> enTranslations = new HashMap<>();
        enTranslations.put("command.success", "Command executed successfully");
        enTranslations.put("command.error", "Error executing command");
        enTranslations.put("player.join", "%s joined the game");
        enTranslations.put("player.leave", "%s left the game");
        translations.put("en", enTranslations);
        
        Map<String, String> esTranslations = new HashMap<>();
        esTranslations.put("command.success", "Comando ejecutado exitosamente");
        esTranslations.put("command.error", "Error ejecutando comando");
        esTranslations.put("player.join", "%s se unió al juego");
        esTranslations.put("player.leave", "%s abandonó el juego");
        translations.put("es", esTranslations);
        
        logger.logInfo("Loaded translations for " + translations.size() + " locales");
    }
}
```

---

Thank you for contributing to Bukkit 1.21.11! Your help keeps this project alive and improving.