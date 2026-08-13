package com.simrge.playview.data

data class AppItem(
    val id: String,
    val name: String,
    val summary: String,
    val categoryId: String,
    val rating: Float,
    val accent: Long,
    val downloads: String,
    val blurb: String,
)

data class Category(
    val id: String,
    val name: String,
    val accent: Long,
    val description: String,
)

data class Shelf(
    val id: String,
    val title: String,
    val subtitle: String,
    val appIds: List<String>,
)

data class Hero(
    val id: String,
    val title: String,
    val caption: String,
    val appId: String,
)

object Catalog {
    // Original PlayView 2013 accents, plus a few extras for identity.
    const val Amber = 0xFFF2A400
    const val Crimson = 0xFFE00707
    const val Lime = 0xFF4AC925
    const val Cyan = 0xFF00D5F2
    const val PlayGreen = 0xFF01875F
    const val Indigo = 0xFF3D5AFE
    const val Magenta = 0xFFD500F9
    const val DeepOrange = 0xFFFF6D00
    const val Teal = 0xFF00897B
    const val BlueGray = 0xFF546E7A

    val categories: List<Category> = listOf(
        Category("productivity", "Productivity", PlayGreen, "Focus, notes, and getting things done"),
        Category("photography", "Photography", Amber, "Capture, edit, and share"),
        Category("music", "Music & audio", Magenta, "Listen, create, and mix"),
        Category("social", "Social", Cyan, "Stay close to people"),
        Category("health", "Health & fitness", Lime, "Move, sleep, and recover"),
        Category("finance", "Finance", Teal, "Budgets, bills, and banks"),
        Category("tools", "Tools", BlueGray, "Utilities that stay out of the way"),
        Category("games", "Games", DeepOrange, "Play something great"),
        Category("education", "Education", Indigo, "Learn in small, fast sessions"),
        Category("weather", "Weather", Crimson, "Radar, alerts, and forecasts"),
    )

    val apps: List<AppItem> = listOf(
        app("northstar", "Northstar Notes", "Fast capture that stays out of the way", "productivity", 4.8f, PlayGreen, "5M+", "A notes app that opens before you forget the thought. Offline-first, no account required."),
        app("orbit", "Orbit Tasks", "Lists that don't nag", "productivity", 4.6f, Indigo, "2M+", "Kanban without the ceremony. Swipe to finish, long-press to schedule."),
        app("lumen", "Lumen Focus", "Pomodoro with kinder sounds", "productivity", 4.7f, Amber, "1M+", "Focus sessions with warm analog timers and a weekly heatmap."),
        app("folio", "Folio Docs", "Markdown that looks published", "productivity", 4.5f, Teal, "800K+", "Write in Markdown, export to a page that already looks like a blog."),
        app("harbor", "Harbor Mail", "Inbox zero, less drama", "productivity", 4.4f, BlueGray, "3M+", "Bundles promotions automatically and keeps people at the top."),
        app("prism", "Prism Camera", "Computational, still honest", "photography", 4.9f, Amber, "10M+", "RAW capture, film recipes, and a shutter that respects your battery."),
        app("grain", "Grain Lab", "Film looks without filters soup", "photography", 4.6f, Crimson, "1M+", "Grain, halation, and light leaks as actual parameters — not Instagram presets."),
        app("frame", "Frame Collage", "Layouts that don't scream 2014", "photography", 4.3f, Cyan, "4M+", "Clean collages with optical margins. Export at print DPI."),
        app("dusk", "Dusk Darkroom", "Develop RAWs on device", "photography", 4.8f, BlueGray, "600K+", "Full pipeline on-device. Shadows, curves, and grain that don't leave the phone."),
        app("atlas-cam", "Atlas Cam", "GPS on every frame", "photography", 4.2f, PlayGreen, "900K+", "Stamp location, altitude, and heading into the EXIF you actually keep."),
        app("ripple", "Ripple Radio", "Stations that feel local", "music", 4.5f, Magenta, "6M+", "Independent radio with a waveform you can actually scrub."),
        app("sonder", "Sonder", "Albums, not playlists", "music", 4.7f, Indigo, "2M+", "Listen to records in order. Lyrics, liner notes, and a quiet UI."),
        app("metron", "Metron", "Practice without the guilt", "music", 4.6f, Teal, "400K+", "A metronome, tuner, and loop pedal that fits in a pocket."),
        app("chorus", "Chorus", "Record the room", "music", 4.4f, Amber, "1M+", "Multitrack voice memos with gentle compression and stem export."),
        app("vinyl", "Vinyl Shelf", "Catalog the crates", "music", 4.5f, Crimson, "250K+", "Scan sleeves, rate pressings, and remember which copy is the good one."),
        app("hearth", "Hearth", "Small groups, real talk", "social", 4.6f, Cyan, "8M+", "Rooms of twelve. No public metrics, no streak anxiety."),
        app("postmark", "Postmark", "Letters, not posts", "social", 4.8f, PlayGreen, "500K+", "Send a letter that arrives in the morning. Photos optional."),
        app("constellation", "Constellation", "Friends on a map you control", "social", 4.3f, Indigo, "3M+", "Share location with a timer. Off means off."),
        app("echoes", "Echoes", "Voice notes that don't pile up", "social", 4.5f, Magenta, "1M+", "They expire unless you pin them. Conversation stays light."),
        app("kilometer", "Kilometer", "Run without the leaderboard", "health", 4.7f, Lime, "4M+", "GPS tracks, heart-rate zones, and zero strangers cheering."),
        app("rest", "Rest", "Sleep stories that aren't creepy", "health", 4.6f, Indigo, "2M+", "Wind-down sounds, a dim clock, and a log you'll actually read."),
        app("pulse", "Pulse", "HRV without the cult", "health", 4.4f, Crimson, "900K+", "Morning readings, a simple recovery score, exportable CSV."),
        app("kitchen", "Kitchen", "Recipes that fit your fridge", "health", 4.5f, Amber, "3M+", "Scan what you have. Get dinner, not a 40-ingredient list."),
        app("stretch", "Stretch", "Five minutes, real ROM", "health", 4.8f, Teal, "1M+", "Routines that name the muscle and don't talk down to you."),
        app("ledger", "Ledger Lite", "A budget you can see", "finance", 4.6f, Teal, "5M+", "Envelopes, not categories of shame. Works offline."),
        app("tab", "Tab", "Split the dinner", "finance", 4.7f, PlayGreen, "7M+", "Scan the receipt, assign the dumplings, settle in one tap."),
        app("yield", "Yield", "Markets, quietly", "finance", 4.3f, BlueGray, "2M+", "Watchlists without the casino wallpaper."),
        app("invoice", "Invoice", "Get paid", "finance", 4.5f, Amber, "800K+", "Estimates, invoices, and a PDF that looks like a grown-up sent it."),
        app("cents", "Cents", "Round-ups you'll keep", "finance", 4.2f, Lime, "1M+", "Spare change toward a goal with a graph that isn't neon."),
        app("torch", "Torch", "Flashlight that opens instantly", "tools", 4.9f, Amber, "50M+", "Tap. Light. That's the whole app. Volume keys toggle."),
        app("caliper", "Caliper", "Measure with the camera", "tools", 4.4f, BlueGray, "2M+", "AR rulers that don't drift after two seconds."),
        app("packet", "Packet", "QR, barcodes, NFC", "tools", 4.6f, Indigo, "9M+", "Scan anything, copy the payload, keep a private history."),
        app("draftsman", "Draftsman", "Vector on the go", "tools", 4.5f, PlayGreen, "600K+", "Paths, boolean ops, and SVG export from the train."),
        app("archive", "Archive", "ZIP, 7z, unbloat", "tools", 4.7f, Teal, "4M+", "Compress and extract without uploading your files to a stranger."),
        app("relay", "Relay", "A tiny HTTP client", "tools", 4.6f, Crimson, "300K+", "Hit an API, pretty-print JSON, save the call. No account."),
        app("summit", "Summit", "Climb the tower", "games", 4.8f, DeepOrange, "12M+", "A one-thumb climber with honest physics and no energy walls."),
        app("orchard", "Orchard", "Grow a quiet garden", "games", 4.7f, Lime, "8M+", "Plant, prune, and visit friends' trees. No timers that punish sleep."),
        app("rook", "Rook", "Chess without the chat", "games", 4.6f, BlueGray, "3M+", "Puzzles, a fair bot, and over-the-board two-player."),
        app("drift", "Drift", "Endless coast", "games", 4.5f, Cyan, "6M+", "A driving game that cares about the light more than the leaderboard."),
        app("marble", "Marble", "Puzzles in your pocket", "games", 4.8f, Magenta, "2M+", "Daily marbles. One screen, no ads in the middle of a thought."),
        app("glyph", "Glyph", "Learn a script", "education", 4.7f, Indigo, "1M+", "Stroke order, spaced repetition, and a journal of what stuck."),
        app("orbit-class", "Orbit Classroom", "Flashcards that decay right", "education", 4.6f, PlayGreen, "2M+", "FSRS scheduling, LaTeX, and decks you can export as text."),
        app("atlas", "Atlas", "Maps for curious people", "education", 4.5f, Teal, "4M+", "Tap a country, hear the name, see one story — not a wall of wiki."),
        app("sonnet", "Sonnet", "Poems, one a day", "education", 4.8f, Magenta, "400K+", "A poem every morning. Annotations when you want them."),
        app("abacus", "Abacus", "Mental math, gently", "education", 4.4f, Amber, "900K+", "Timed rounds that get harder only when you're actually ready."),
        app("front", "Front", "Radar that makes sense", "weather", 4.7f, Crimson, "7M+", "Precipitation, alerts, and an actual explanation of the front."),
        app("breeze", "Breeze", "AQI and pollen, not vibes", "weather", 4.5f, Lime, "2M+", "Hourly air, not a generic 'good day for a walk'."),
        app("tide", "Tide", "For people who actually go outside", "weather", 4.6f, Cyan, "1M+", "Tides, swell, and sunrise for the beach you named."),
        app("halo", "Halo", "Sun and moon, accurately", "weather", 4.4f, Amber, "500K+", "Golden hour, moon phase, and ISS passes. Offline almanac."),
        app("isobar", "Isobar", "Forecasts for planners", "weather", 4.3f, BlueGray, "800K+", "10-day with confidence bands, not fake precision."),
        app("quill", "Quill", "A typewriter that syncs later", "productivity", 4.7f, Amber, "200K+", "Full-screen writing. Syncs when you say so."),
        app("mosaic", "Mosaic", "Photo books from the camera roll", "photography", 4.5f, Magenta, "700K+", "Pick a year. Get a book. Print or PDF."),
        app("booth", "Booth", "The group photo, finally", "social", 4.4f, DeepOrange, "1M+", "Timer, frames, and a shared album that dies in 48 hours."),
        app("cadence", "Cadence", "Cycling without segments", "health", 4.6f, PlayGreen, "1M+", "Ride data, no KOMs. Export FIT and go."),
        app("vault", "Vault", "Passwords that stay here", "tools", 4.8f, Indigo, "3M+", "Argon2, a local vault, and a keyboard that never phones home."),
        app("nova", "Nova Chess Puzzles", "Tactics on the commute", "games", 4.7f, Indigo, "900K+", "Rated puzzles, no daily streak hostage situation."),
    )

    val heroes: List<Hero> = listOf(
        Hero("h1", "Editors' choice", "Northstar Notes — capture before it fades", "northstar"),
        Hero("h2", "New this week", "Prism Camera — film recipes, honest RAW", "prism"),
        Hero("h3", "Staff pick", "Summit — climb with one thumb", "summit"),
        Hero("h4", "Worth a listen", "Sonder — albums, not playlists", "sonder"),
        Hero("h5", "Go outside", "Front — radar that explains the sky", "front"),
    )

    val shelves: List<Shelf> = listOf(
        Shelf("top-free", "Top free", "What people actually keep", listOf("torch", "northstar", "hearth", "kilometer", "packet", "summit", "front", "ledger")),
        Shelf("new", "New & updated", "Fresh builds this week", listOf("prism", "postmark", "lumen", "orchard", "vault", "tide", "quill", "relay")),
        Shelf("recommended", "Recommended for you", "Based on this demo catalog", listOf("orbit", "grain", "sonder", "rest", "tab", "rook", "glyph", "halo")),
        Shelf("worth", "Worth paying for", "If this were a real store", listOf("dusk", "folio", "metron", "pulse", "draftsman", "marble", "sonnet", "yield")),
    )

    private val appsById: Map<String, AppItem> = apps.associateBy { it.id }
    private val categoriesById: Map<String, Category> = categories.associateBy { it.id }
    private val appsByCategory: Map<String, List<AppItem>> = apps.groupBy { it.categoryId }

    fun app(id: String): AppItem? = appsById[id]

    fun category(id: String): Category? = categoriesById[id]

    fun appsIn(categoryId: String): List<AppItem> = appsByCategory[categoryId].orEmpty()

    fun shelfApps(shelf: Shelf): List<AppItem> = shelf.appIds.mapNotNull(appsById::get)

    fun similarTo(app: AppItem): List<AppItem> =
        appsIn(app.categoryId).filter { it.id != app.id }.take(8)

    fun search(query: String): List<AppItem> {
        val q = query.trim()
        if (q.length < 2) return emptyList()
        return apps.filter { item ->
            item.name.contains(q, ignoreCase = true) ||
                item.summary.contains(q, ignoreCase = true) ||
                item.blurb.contains(q, ignoreCase = true) ||
                category(item.categoryId)?.name.orEmpty().contains(q, ignoreCase = true)
        }
    }

    private val rankedByRating: List<AppItem> by lazy { apps.sortedByDescending { it.rating } }

    fun charts(): List<AppItem> = rankedByRating

    private fun app(
        id: String,
        name: String,
        summary: String,
        categoryId: String,
        rating: Float,
        accent: Long,
        downloads: String,
        blurb: String,
    ) = AppItem(id, name, summary, categoryId, rating, accent, downloads, blurb)
}
