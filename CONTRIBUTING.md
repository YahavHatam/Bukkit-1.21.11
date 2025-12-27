# How to Contribute

This is a clean fork of the Bukkit project for Minecraft 1.21.11. While we welcome contributions, this is primarily a maintenance fork focused on providing a stable Bukkit API without Bedrock support.

## Quick Guide
1. Check existing [Issues](https://github.com/YahavHatam/Bukkit-1.21.11/issues) for your proposed change
2. Fork the repository if you haven't done so already
3. Make your changes in a new branch
4. Test your changes thoroughly
5. Push to your fork and submit a pull request

## Getting Started
- You'll need a free [GitHub account](https://github.com/signup/free)
- Make sure to check existing issues before creating new ones
- Fork the repository on GitHub
- Create a descriptive issue if one doesn't already exist

## Does the Change Fit This Fork's Goals?
This fork aims to provide a clean, stable Bukkit API implementation. Consider:

* Does it maintain API compatibility with Bukkit?
* Does it fix a bug or improve existing functionality?
* Is it a reasonable enhancement that doesn't break existing plugins?
* Does it avoid exposing unnecessary implementation details?

## Making the Changes
* Create a branch on your fork where you'll be making your changes
* Name your branch something relevant to the change you are making
* Check for unnecessary whitespace with `git diff --check` before committing
* Make sure your code meets [our requirements](#code-requirements)
* Test your changes to make sure they actually address the issue
* Make sure your code compiles under Java 21

### Code Requirements
* We generally follow the [Sun/Oracle coding standards](http://www.oracle.com/technetwork/java/javase/documentation/codeconvtoc-136057.html)
* No tabs; use 4 spaces instead
* No trailing whitespaces
* No CRLF line endings, LF only
* No 80 column limit or 'weird' midstatement newlines
* Any major additions should have documentation ready and provided if applicable
* Try to follow test driven development where applicable
* There needs to be a new line at the end of every file
* Imports should be organised by alphabetical order, separated and grouped by package

### Commit Message Format
> Brief summary of changes

> Detailed description of what the change does, why it's needed, and how it addresses the issue. Keep lines under 78 characters.

## Submitting the Changes
* Push your changes to a topic branch in your fork
* Submit a pull request to this repository
* Make sure your pull request includes:
  - Clear description of the change
  - Testing results
  - Any relevant documentation updates

## Tips to Get Your Pull Request Accepted
* Follow all coding conventions
* Test your code thoroughly
* Provide proper documentation
* Ensure backward compatibility
* Don't break existing functionality

## Useful Resources
* [GitHub documentation](http://help.github.com/)
* [GitHub pull request documentation](http://help.github.com/send-pull-requests/)
* [Java coding conventions](https://oracle.com/technetwork/java/javase/documentation/codeconvtoc-136057.html)

## License
Contributions maintain the same license as the original Bukkit project.
