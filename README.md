# Bukkit 1.21.11 (Clean Fork)

A clean fork of the Bukkit API for Minecraft 1.21.11 without Bedrock support.

## Overview

This is a fork of the original Bukkit project that provides the core Bukkit API implementation. This version contains only the standard Java Minecraft server functionality without any Bedrock/Minecraft PE support.

## Features

- Complete Bukkit API implementation
- Plugin system support
- Event handling framework
- Configuration management
- Permission system
- World and entity management
- Inventory system
- Command framework

## Version Information

- **Minecraft Version**: 1.21.11
- **Java Version**: 21
- **Build Tool**: Maven

## Requirements

- Java 21 or higher
- Maven 3.6 or higher

## Building

```bash
# Clone the repository
git clone <repository-url>
cd bukkit

# Build the project
mvn clean install

# The JAR will be available in target/bukkit-1.21.11-R0.1-SNAPSHOT.jar
```

## Usage

Include the built JAR in your server project or use it as a dependency in your plugin development.

## Documentation

For API documentation and usage examples, refer to the official Bukkit documentation.

## License

This project follows the same license as the original Bukkit project.

## Contributing

Contributions are welcome! Please ensure all changes follow the Bukkit coding standards and include appropriate tests.

## Changes from Original

This version differs from the original Bukkit project by:

- Updated to Minecraft 1.21.11
- Removed any Bedrock/Minecraft PE related code
- Clean compilation without external dependencies
- Maven build system optimization

## Original Project

Based on the original Bukkit project available at [http://bukkit.org](http://bukkit.org)
