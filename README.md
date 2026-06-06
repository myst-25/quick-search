# Quick Search Pro

> **Based on [Quick Search](https://github.com/teja2495/quick-search) by [Teja Karlapudi](https://github.com/teja2495)** — all credit for the original app goes to them. This is a personal fork with custom enhancements maintained by [myst25](https://github.com/myst-25).

A fast & powerful Android launcher/search app that lets you search across **apps, app shortcuts, contacts, calendar events, device files, device settings, app settings, and the web**, plus access tools like **calculator, unit converter, and AI search**—all from a single search bar. Built with Kotlin and Jetpack Compose using Material 3 design.

<a href="https://github.com/myst-25/quick-search/releases/latest"><img src="https://github.com/user-attachments/assets/5d36bf7f-3386-4b0e-b7e1-892daba01343" alt="Get it on GitHub" height="80"></a>

[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](https://opensource.org/licenses/MIT)

## ✨ Key Features

### 🔍 Unified Search
- **Apps**: Search and launch installed applications with smart ranking. App search ignores typos and allows abbreviations. Long press apps to access their shortcuts (if available).
- **All Apps View**: Browse all installed apps in a grid, with a quick "Show All Apps" toggle to expand beyond the initial 8 — just like MIUI or OnePlus launchers.
- **App Shortcuts**: Find and launch app shortcuts (including custom deep links and search/browser shortcuts) alongside app results.
- **Contacts**: Find and call/text contacts with multi-number support and integrations such as WhatsApp, Telegram, Signal, Google Meet, and more. Long press contact actions to customize them.
- **Calendar Events**: Search your calendar events from the search bar.
- **Notes**: Write notes and search them from the search bar. Swipe left on the home screen for a quick note.
- **Files**: Search device files and folders (images, videos, documents, etc.)
- **Device Settings**: Search Android system settings
- **App Settings**: Find and open Quick Search Pro app settings directly from the search bar
- **Top Matches**: Turn this on to see the top result for your query across all categories. You can also enable or disable categories and adjust their priority.
- **Web**: Search the web using 25 search engines with customizable shortcuts and web suggestions. Browsers can be added as search engines.
- **Calculator**: Built-in calculator for math expressions (+, -, *, /, brackets); optional alias to open calculator mode
- **Unit Converter**: Convert units from the search bar (length, mass, temperature, area, volume, time, speed, data, energy, power, pressure, angle, frequency, and more)
- **Currency Converter**: Convert between currencies with live exchange rates directly from the search bar
- **World Clock**: Check the current time across different time zones by searching for city names
- **Date & Time**: Natural-language date parsing, differences, offsets, and time arithmetic from the search bar (optional alias)
- **AI Search**: AI search with AI-powered answers using models and optional personal context
- **AI Providers**: Configure API keys for multiple providers - Gemini, OpenAI, Claude, Groq - at the same time, plus custom AI providers using any OpenAI-compatible API
- **Custom AI Tools**: Create your own AI tools (requires an API key)
- **App Suggestions**: Swipe left or right on the suggestions row to switch between New & Updated, Pinned, and All Apps
- **Search History**: View search history and swipe left or right in the expanded history to switch between recent queries and recently opened results
- **Overlay Mode**: Enable to make the search bar appear over other apps, anywhere—changes how you access search from any screen
- **Home Screen Widget**: Search widget and custom buttons widget with extensive customization options
- **Launch Options**: Widget, Quick Settings Tile, Digital Assistant & set as Launcher
- **Triggers**: Long-press any result to set a trigger. The app opens it automatically when you type the trigger word and press space.

### 🎨 Customization & Features
- **App Themes**: Choose from multiple visual themes (Mono, Forest, Aurora, Sunset) for the app's appearance
- **Material You**: Use your device's color palette from Material You for the app's theme and accent colors
- **Bottom Search Bar**: Option for bottom-positioned search bar for improved accessibility
- **Font Size Control**: Customize font size throughout the app for better readability
- **Custom Backgrounds**: Select any picture from your device for the app background, with transparency and blur controls
- **App Management**: View app details or bulk uninstall apps from search results settings
- **Shortcut Management**: Enable, disable, or add custom shortcuts including search queries, URLs, and app activities
- **Import/Export Settings**: Backup and restore your Quick Search Pro configuration and preferences

### Search Shortcuts
Configure custom keyboard shortcuts for search engines and add them at the start of a query to quickly trigger the respective search engine:
- `ggl` → Google
- `ytb` → YouTube
- `mps` → Google Maps
- And more...

### AI Search
Enable AI-powered answers by:
1. Getting a Gemini API key from [Google AI Studio](https://makersuite.google.com/app/apikey)
2. Adding the key in Settings → Search Engines → AI Search
3. Optionally add personal context for personalized answers
4. Choose any models of your choice

### Search Engines
- **Supported** (25): AI Search, Google, ChatGPT, Gemini, Perplexity, Grok, Google Maps, Google Drive, Google Photos, Google Play, YouTube, YouTube Music, Spotify, Reddit, Amazon, X (Twitter), Facebook Marketplace, Bing, DuckDuckGo, Brave, Startpage, You.com, AI Mode, Claude, Wikipedia—each can be reordered or disabled. You also have option to add **custom** search engines.
- **Browsers**: Add installed browsers as search engines
- **AI Search**: AI answers with Gemini API integration; choose among several Gemini and Gemma models; optional personal context
- **AI Providers**: Support for multiple providers at once (OpenAI, Claude, Groq), plus custom AI providers via any OpenAI-compatible API
- **Style**: Choose between inline or compact styles

[View all features](app/src/main/assets/FEATURES.md)

## 🚀 Installation

### Requirements
- Android 7.0 (API 24) or higher
- Target SDK: Android 15 (API 36)

### Download
- **APK Release**: Download the latest APK from the [Releases](https://github.com/myst-25/quick-search/releases) page

### Build from Source
```bash
# Clone the repository
git clone https://github.com/myst-25/quick-search.git
cd quick-search

# Standard release build (recommended)
./gradlew assembleStandardRelease

# F-Droid build (no Google Play libraries bundled)
./gradlew assembleFdroidRelease

# Debug build
./gradlew assembleStandardDebug
```

## 🛡️ Permissions & Privacy

Quick Search Pro prioritizes your privacy. All permissions are optional, only used to unlock additional features. All search processing happens locally on your device.

**Key Points:**
- No ads or analytics
- Encrypted storage for API keys
- Granular permission controls
- Local data processing

## 🏗️ Architecture

Built with modern Android development practices:

- **Language**: Kotlin 2.0.21
- **UI**: Jetpack Compose with Material 3 (BOM 2025.12.01)
- **Architecture**: MVVM with ViewModels and StateFlow (unidirectional data flow)
- **State Management**: Single source of truth with sealed classes for type-safe states
- **Persistence**: SharedPreferences with encryption for sensitive data (API keys)
- **Widgets**: Jetpack Glance App Widget framework
- **Build System**: Gradle Kotlin DSL with version catalogs
- **Min SDK**: 24 (Android 7.0) | **Target SDK**: 36 (Android 15)

### Project Structure
```
app/src/main/java/com/myst25/quicksearch/
├── app/                    # Application entry point and app-level handlers
├── navigation/             # Navigation management with animated transitions
├── shared/                 # Shared components and utilities
│   ├── ui/                 # Shared UI components and Material 3 theming
│   └── util/               # Shared utility functions
├── search/                 # Main search functionality
│   ├── core/               # SearchViewModel, state management, unified search
│   ├── apps/               # App search, icons, and management
│   ├── contacts/           # Contact search with messaging integrations
│   ├── files/              # File search and management
│   ├── deviceSettings/     # Device settings search
│   └── searchScreen/       # Main search UI and layout orchestration
├── searchEngines/          # Search engine integration and management
├── tools/                  # Built-in tools (calculator, AI search, etc.)
├── settings/               # Settings screens
├── onboarding/             # First-launch setup flow
├── widgets/                # Home screen widgets (Glance framework)
└── overlay/                # Overlay mode (search over other apps)
```

## 🤝 Contributing

1. Fork the repository
2. Create your feature branch (`git checkout -b feature/your-feature`)
3. Commit your changes (`git commit -m 'Add some feature'`)
4. Push to the branch (`git push origin feature/your-feature`)
5. Open a Pull Request at [github.com/myst-25/quick-search](https://github.com/myst-25/quick-search)

## 📄 License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

Original work Copyright (c) 2025 [Teja Karlapudi](https://github.com/teja2495).
Modifications Copyright (c) 2025 [myst25](https://github.com/myst-25).

## 📞 Contact

- **Issues**: [GitHub Issues](https://github.com/myst-25/quick-search/issues)
- **Developer**: [myst25 on GitHub](https://github.com/myst-25)

---

**Made with ❤️ for Android**
