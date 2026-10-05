# Progress Pics Body Tracker

Full production Android app for tracking body progress with photos, metrics, and comparisons.

## Architecture

- **UI Framework**: Jetpack Compose
- **Language**: Kotlin
- **Database**: Room (SQLite)
- **Camera**: AndroidX Camera Library
- **Image Loading**: Coil
- **Navigation**: Jetpack Navigation Compose
- **Preferences**: DataStore

## Project Structure

```
app/
├── src/main/
│   ├── kotlin/com/sippy/progresspicsbodytracker/
│   │   ├── MainActivity.kt                    # App entry point
│   │   ├── ui/
│   │   │   ├── screens/                       # Composable screens
│   │   │   ├── components/                    # Reusable UI components
│   │   │   └── theme/                         # Theme and styling
│   │   └── data/
│   │       ├── local/                         # Local database (Room)
│   │       │   ├── entity/                    # Data models
│   │       │   ├── dao/                       # Database access objects
│   │       │   └── converters/                # Type converters
│   │       └── repository/                    # Data repositories
│   └── res/                                   # Android resources
├── build.gradle.kts                           # App build configuration
└── AndroidManifest.xml                        # App manifest
```

## Features

- 📸 **Photo Capture**: Take progress photos with date and metadata
- 📊 **Metrics Tracking**: Record weight and body measurements (chest, waist, hip, arm, thigh)
- 📈 **Progress Visualization**: Compare photos and metrics over time
- 💾 **Offline Storage**: Local database with Room for photos and metrics
- 🎨 **Modern UI**: Built with Material Design 3 and Jetpack Compose
- 📱 **Camera Integration**: Native Android camera support

## Getting Started

### Prerequisites

- Android Studio Giraffe (2022.3.1) or later
- Android SDK 34 (min SDK 26)
- Kotlin 1.9.0+

### Build & Run

1. Clone the repository
2. Open in Android Studio
3. Connect an Android device or start an emulator
4. Click "Run" or press `Shift + F10`

### Dependencies

Key dependencies managed in `app/build.gradle.kts`:

- Jetpack Compose: UI framework
- Room: Database persistence
- AndroidX Camera: Camera functionality
- Coil: Image loading and caching
- Jetpack Navigation: Screen navigation
- DataStore: User preferences

## Development

### Adding a New Screen

1. Create a new composable in `ui/screens/`
2. Add navigation route to `Navigation.kt`
3. Update the navigation graph

### Adding a Database Entity

1. Create entity class in `data/local/entity/`
2. Create DAO in `data/local/dao/`
3. Update `ProgressDatabase.kt`
4. Run migrations as needed

## License

Apache License 2.0 - See LICENSE file for details
