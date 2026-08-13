# PlayView

An offline Play Store–style catalog. Browse apps, open a detail page, search. Nothing is installed, and there is no network.

This is a 2026 rebuild of the 2013 Eclipse ListView sample. The old ADT project does not build on a current SDK. The replacement is Jetpack Compose and Material 3: Home, Charts, Categories, search, and app detail.

## Run

JDK 17+, Android SDK 36.

```bash
./gradlew assembleDebug          # APK → app/build/outputs/apk/debug/app-debug.apk
./gradlew testDebugUnitTest
./gradlew assembleRelease        # use this build to judge scroll smoothness
```

Debug Compose is not a fair performance test. Use the release APK for that.

## Screens

- **Home** — “For you” heroes and horizontal shelves
- **Charts** — every app, ranked by rating
- **Categories** — genre grid, then a filtered chart
- **Search** — type two letters, or tap camera / chess / notes
- **Detail** — about, screenshots, similar apps. Install is a snackbar

The catalog is `app/src/main/java/com/simrge/playview/data/Catalog.kt` (~56 fake apps, 10 categories). The 2013 accents (`#f2a400`, `#e00707`, `#4ac925`, `#00d5f2`) are still the per-app colors.

## How it is put together

```
app/src/main/java/com/simrge/playview/
  MainActivity.kt     edge-to-edge host
  PlayViewApp.kt      tab, search, and detail state
  data/Catalog.kt     in-memory store
  ui/                 home, charts, categories, search, detail, theme
```

There is no Navigation graph and no Material `Scaffold`. `PlayViewApp` keeps a few saved fields (`tab`, `appId`, `categoryId`, `query`). Detail draws on top of the current tab, so Back does not rebuild Home.

Home and Charts are `LazyColumn`. Shelves are a `Row` with `horizontalScroll` — not a nested `LazyRow` — so Compose does not measure the whole store on every frame.

## License

Apache 2.0. Original work © 2013 Aradh Pillai. See [LICENSE](LICENSE).
