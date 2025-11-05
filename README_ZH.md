# Vetra UI

<div align="center">

<img src="images/vetra.svg" alt="Vetra UI Logo" width="160"/>

**现代化的、优雅的 Compose Multiplatform UI 组件库**

*用光影、层次与动效，创造极致的交互体验*

[![Kotlin](https://img.shields.io/badge/Kotlin-2.2.21-blue.svg)](https://kotlinlang.org)
[![Compose](https://img.shields.io/badge/Compose%20Multiplatform-1.9.2-brightgreen.svg)](https://www.jetbrains.com/compose-multiplatform/)
[![Maven Central](https://img.shields.io/maven-central/v/com.flyfishxu.vetraui/core)](https://central.sonatype.com/artifact/com.flyfishxu.vetraui/core)
[![License](https://img.shields.io/badge/MIT-yellow.svg)](https://opensource.org/licenses/MIT )

[English](README.md) | 简体中文

</div>

<div align="center">
  <img src="images/showcase.png" alt="Vetra UI Components Showcase" width="100%"/>
</div>

## 核心特性

### 跨平台支持
- 支持 Android、iOS、JVM 和 Web

### 开发者友好
- API 设计接近 Material Design，学习成本低
- 清晰的命名规范和完善的文档
- 每个组件都有全面的 Preview

### 简约的设计
- **优雅胜于炫技**：不为炫技而设计动画——每个动画都有其意义
- **统一胜于多样**：一致的设计语言减少认知负担
- **自然胜于机械**：动效遵循物理规律，交互顺应直觉
- **清晰胜于抽象**：命名直观，API 符合 Compose 思维

## 快速开始

### 安装

在 `libs.versions.toml` 中添加依赖：

```toml
[versions]
vetraui = "1.0.0-alpha02"

[libraries]
vetraui-core = { module = "com.flyfishxu.vetraui:core", version.ref = "vetraui" }
```

在 `build.gradle.kts` 中添加 common 依赖：

```kotlin
commonMain.dependencies {
    implementation(libs.vetraui.core)
}
```

### 基础使用

```kotlin
import com.flyfishxu.vetraui.core.theme.VetraTheme
import com.flyfishxu.vetraui.core.components.*

@Composable
fun App() {
    VetraTheme(darkTheme = false) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // 按钮
            VetraButton(onClick = { /* ... */ }) {
                Text("这是一个基础的按钮")
            }
            
            // 卡片
            VetraCard {
                Text("这是一个 VetraUI 的卡片")
            }
            
            // 文本框
            VetraTextField(
                value = searchText,
                onValueChange = { searchText = it },
                placeholder = "搜索..."
            )
        }
    }
}
```

## 开源协议

本项目基于 [MIT](https://github.com/flyfishxu/vetra-ui/blob/main/LICENSE) 协议，请自由地享受和参与开源。

---

<div align="center">

[示例应用](composeApp/) · [报告问题](https://github.com/flyfishxu/vetra-ui/issues) · [贡献指南](CONTRIBUTING.md)

</div>

