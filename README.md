# CrucibleGradle

A local fork of anatawa12/ForgeGradle-1.2 at `3f5f0d6`, integrating Crucible's
former `buildSrc` at `cd1de3dc`. Original ForgeGradle sources and licenses are
retained. No upstream repository is modified or pushed to.

Build with JDK 21 or newer and the Gradle 9.9.1 wrapper. The `crucible` plugin
prepares Minecraft 1.7.10 sources and compiles server code with `--release 21`.
This `java21only` branch requires Java 21 for the server as well as the plugin. It does not require Java 8, Java 17, Jabel,
or Lombok. Pack200 is provided by `org.glavo:pack200`, not an older JDK.

Crucible includes this build through `pluginManagement.includeBuild`. The
`separated` project is substituted locally, so both build artifacts come from
the same source checkout. Standalone checks:

```sh
./gradlew test validatePlugins :separated:jar
```

The legacy Forge user plugins are retained for source compatibility; the
Crucible server build is the supported integration tested by this change.

## Original ForgeGradle documentation

ForgeGradle
===========

Join Chat [![Discord](https://img.shields.io/discord/834256470580396043)](https://discord.gg/yzEdnuJMXv)

Minecraft mod development framework used by Forge and FML for the gradle build system

this repository is only for ForgeGradle 1.2, ForgeGradle for minecraft 1.7.2, 1.7.10, and 1.8.

[Here](https://github.com/anatawa12/ForgeGradle-2.3) is repository for ForgeGradle 2.3. If you're modding for 1.12.x, use it.

This project is a fork of [ForgeGradle branch 'FG_1.2'](https://github.com/MinecraftForge/ForgeGradle/tree/FG_1.2).

[Example project found here](https://github.com/anatawa12/ForgeGradle-example)

[Documentation found here](http://forgegradle.readthedocs.org/)

## How to replace ForgeGradle 1.2. with anatawa12's fork

See [How to replace ForgeGradle 1.2. with anatawa12's fork in example project](https://github.com/anatawa12/ForgeGradle-example#how-to-replace-forgegradle-12-with-anatawa12s-fork)
