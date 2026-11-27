# Inforbal---Virtual-Garden

## How Inforbal Works

Inforbal is a native Android app for exploring botanical and medicinal plant information.

### 1. App entry point

`MainActivity` loads the Jetpack Compose interface and passes the shared `samplePlantData` list to `AppRoot`.

The app currently uses an in-memory list for plant records. It does not connect to a database or remote API. Restarting the app restores the original sample data.

### 2. Plant data

Plants are represented by the `Plant` data model. Each plant contains:

- ID, common name, botanical name, family, and vernacular name
- Drawable image resource IDs
- Video links
- Quick facts
- AYUSH information
- Scientific information
- Cultivation information

The app currently includes two sample plants:

- Ashwagandha (*Withania somnifera*)
- Tulsi (*Ocimum sanctum*)

### 3. Navigation

`AppRoot` manages the app’s selected screen and bottom navigation state.

The app has three main tabs:

- **Home:** Displays the Virtual Herbal Garden welcome screen.
- **Plants:** Displays every plant and provides search by common or botanical name.
- **Bookmarks:** Displays plants added to the current session’s bookmark list.

The search is case-insensitive and updates the displayed list as the user types.

### 4. Plant details

Selecting a plant opens `PlantDetailScreen`. It shows:

- Common and botanical names
- Plant images
- Family and vernacular name
- Video links

Video links are opened through Android’s external browser or video application.

### 5. Bookmark behavior

The bookmark button on a plant’s detail page adds or removes its ID from an in-memory Compose state list.

Bookmarks are therefore:

- Available while the app remains open
- Lost when the app is closed or the process is restarted
- Not associated with a user account or persistent local database

### 7. Technology and requirements

The project uses:

- Kotlin
- Jetpack Compose
- Material 3
- AndroidX Navigation Compose
- Coil for image loading
- Gradle Kotlin DSL

The application requires:

- Android 7.0/API 24 or later
- Android Studio
- JDK 17
- Android SDK 34
- Internet permission, primarily for loading remote links and resources

### 8. Development limitations

The project is currently a prototype with:

- A small hard-coded plant dataset
- No persistent storage
- No authentication or cloud synchronization
- No server or external plant database
- No medicine-specific detail tabs in the currently active `PlantDetailScreen`
- Limited search across only common and botanical names

Medicinal information should be independently reviewed before publishing or presenting it as medical guidance.
