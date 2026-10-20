# Wynntils 4.2.13 — unofficial Fabric port for Minecraft 26.1.2

This repository contains an unofficial community port of [Wynntils](https://github.com/Wynntils/Wynntils) 4.2.13 for Minecraft 26.1.2 on Fabric. It is not an official Wynntils release and is not affiliated with or endorsed by Team Wynntils.

The port has been tested in a Wynncraft play session. That does not guarantee every feature works; please report reproducible issues with the relevant `latest.log` excerpt and steps to reproduce.

## Build

Requirements: Java 25.

```powershell
.\gradlew.bat :fabric:shadowJar --no-daemon
```

The Fabric JAR is written to `fabric/build/libs/`.

## Changes

This port updates Minecraft/Fabric compatibility for Minecraft 26.1.2, including updated interaction and rendering mixins, access-widener metadata, and safer handling of invalid cached resource packs.

## License and attribution

Wynntils is licensed under the GNU Lesser General Public License v3.0 only. This fork retains the upstream `LICENSE` and applicable copyright notices. Port modifications are distributed under the same license. The upstream project and its assets remain attributed to Team Wynntils; see the upstream repository for asset-specific notices.

This is an unofficial port. “Wynntils” is used only to identify the upstream project on which it is based.
