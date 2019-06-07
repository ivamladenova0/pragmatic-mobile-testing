# The Automator

## About

Mobile testing solution based on Java, Gradle, Junit and Appium.

## Execute Tests

### IDE

Tests can be executed against multiple device configs.
Configs are located in `<project-root>/src/test/resources`.

When execute tests via IDE you should specify config as VM option like this:
```
-DappConfig=nativeapp.dev.lgg6
```
![IDE Settings](docs/ide.png "IDE Settings")

### Commandline

Example:
```
./gradlew clean test --tests "nativeapp.tests.*" -PappConfig=nativeapp.dev.lgg6
```
This will run all test classes from `nativeapp.tests` package on configuration specified in `nativeapp.dev.lgg6` properties file.

### TIPs & Tricks

#### Create or Update Gradle Wrapper

```
gradle wrapper --gradle-version 5.4.1 --distribution-type all
```