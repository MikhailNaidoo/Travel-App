# Travel App

A native Android flight-search travel app built with **Kotlin + Jetpack Compose +
Material 3**. Clean Google-Flights-leaning UI, foldable-aware (re-uses the
`resizeableActivity` setup from the starter template).

## Screens

| Screen | What it shows |
| --- | --- |
| **Explore** | Hero, trip-type tabs (Round trip / One way / Multi-city), flight search box, top deals carousel, best departing flights |
| **Flight Results** | Date strip with prices, sortable list of flight cards with Best/Cheapest badges, sticky select-flight footer |
| **Trips** | Upcoming/Past tabs, calendar-block trip cards, "Add trip" CTA — backed by Room |
| **Watch** | Price-tracked routes with up/down deltas, "Track prices" CTA |
| **Profile** | User header, stats tiles, account section, sign out |
| **Drawer** | Modal nav drawer with profile, primary nav, secondary nav, Premium upsell, sign out |
| Stubs | Deals, Price Alerts, Saved Flights, Airports, Airlines, Settings, Help — placeholder screens reachable from the drawer |

## Project layout

```
.
├── app/src/main/java/com/example/travelapp/
│   ├── MainActivity.kt                # ComponentActivity → AppScaffold
│   ├── TravelApp.kt                   # Application — owns DB + repos
│   ├── data/
│   │   ├── MockData.kt                # airports, airlines, flights, deals, watched routes
│   │   ├── TripRepository.kt          # Room repo + seed
│   │   ├── db/                        # Room: TripEntity, TripDao, AppDatabase
│   │   └── model/Models.kt            # plain data models
│   └── ui/
│       ├── AppScaffold.kt             # NavHost + bottom nav + modal drawer
│       ├── SearchState.kt             # shared search-box state via CompositionLocal
│       ├── components/                # OutlinedField, FlightCard, etc.
│       ├── nav/                       # destinations + drawer content
│       ├── screen/                    # one file per screen
│       └── theme/                     # colour, typography, theme
├── APK/                               # build outputs land here, named per version
├── gradle/libs.versions.toml          # version catalog
└── version.properties                 # VERSION_NAME / VERSION_CODE
```

## Building

Requires JDK 17 and the Android SDK (Android Studio installs both).

```bash
./gradlew assembleDebug      # debug APK
./gradlew assembleRelease    # release APK (R8 + shrinker)
./gradlew assemble           # both
```

After a successful assemble, APKs are copied into `APK/`:

```
APK/TravelApp-v<versionName>-debug.apk
APK/TravelApp-v<versionName>-release.apk
```

## Bumping the version

Edit `version.properties`:

```properties
VERSION_NAME=1.1.0
VERSION_CODE=2
```

## CI

`.github/workflows/build-apk.yml` runs `assembleDebug assembleRelease` on every
push and uploads `APK/` as a workflow artifact named `TravelApp-v<versionName>`.

## Persistence

Trips are persisted with Room (`travel-app.db`). On first launch the database is
seeded with three upcoming trips so the Trips screen has data out of the box.

Other lists (top deals, best departing flights, watched routes, airlines,
airports) are static mock data in `data/MockData.kt` and easy to swap for real
APIs later.
