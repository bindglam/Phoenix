<div align="center">

# 🔥 Phoenix

**A lightweight RPG item framework for Paper-based Minecraft servers.**

[![GitHub Release](https://img.shields.io/github/v/release/bindglam/Phoenix?style=flat-square&color=orange)](https://github.com/bindglam/Phoenix/releases)
[![License](https://img.shields.io/github/license/bindglam/Phoenix?style=flat-square&color=blue)](https://github.com/bindglam/Phoenix/blob/main/LICENSE)
[![Discord](https://img.shields.io/discord/1234567890?logo=discord&logoColor=white&style=flat-square&color=7289da)](https://discord.gg/CZEabRCuK8)
[![Wiki](https://img.shields.io/badge/Documentation-Wiki-success?style=flat-square&logo=github)](https://github.com/bindglam/Phoenix/wiki)
[![](https://deepwiki.com/badge.svg)](https://deepwiki.com/bindglam/Phoenix)

---

Phoenix is a high-performance, lightweight Paper plugin designed to empower server owners to create custom **RPG items** with ease. Define unique attributes, custom behaviors, and immersive descriptions without the overhead of heavy frameworks.

[Features](#-key-features) • [Installation](#-getting-started) • [API Documentation](#-for-developers-api) • [Support](#-support--contribution)

</div>

---

## ✨ Key Features

*   **🛡️ Advanced Attribute System**
    *   Fully customize Attack Damage and Attack Speed.
    *   Dynamic Lore (description) management.
    *   Define custom Item Behaviors to trigger unique effects.
*   **⚡ Optimized for Performance**
    *   Built with a "Performance-First" mindset to ensure zero lag even on high-population servers.
    *   Minimalist footprint by focusing on essential RPG mechanics.
*   **🧩 Developer Friendly**
    *   Clean and intuitive API for developers to extend functionality.
    *   Easy integration via JitPack.

---

## 🚀 Getting Started

### Requirements
*   **Server Software**: [Paper](https://papermc.io/) or its forks (e.g., Purpur, Pufferfish).
*   **Java Version**: Java 17 or higher recommended.

### Installation
1.  Download the latest `.jar` file from the [Releases](https://github.com/bindglam/Phoenix/releases) page.
2.  Place the file into your server's `plugins` folder.
3.  Restart your server.
4.  Configure your items in the generated config files!

---

## 🧑‍💻 For Developers (API)

Empower your own plugins by integrating with the Phoenix API.

### build.gradle.kts
```kotlin
repositories {
    maven { url = uri("https://jitpack.io") }
}

dependencies {
    // Replace <VERSION> with the latest release tag (e.g., 1.0.0)
    implementation("com.github.bindglam.Phoenix:api:<VERSION>")
}