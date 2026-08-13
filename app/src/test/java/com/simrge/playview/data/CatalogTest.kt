package com.simrge.playview.data

import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class CatalogTest {
    @Test
    fun appsHaveUniqueIds() {
        val ids = Catalog.apps.map { it.id }
        assertEquals(ids.size, ids.toSet().size)
    }

    @Test
    fun catalogIsLargeEnoughToScroll() {
        assertTrue(Catalog.apps.size >= 50)
    }

    @Test
    fun searchIsCaseInsensitive() {
        val notes = Catalog.search("northstar")
        val upper = Catalog.search("NORTHSTAR")
        assertEquals(notes.map { it.id }, upper.map { it.id })
        assertTrue(notes.any { it.id == "northstar" })
    }

    @Test
    fun searchNeedsTwoCharacters() {
        assertTrue(Catalog.search("").isEmpty())
        assertTrue(Catalog.search("c").isEmpty())
        assertTrue(Catalog.search("ca").isNotEmpty())
    }

    @Test
    fun searchMatchesCategoryName() {
        val photography = Catalog.search("photography")
        assertTrue(photography.isNotEmpty())
        assertTrue(photography.all { it.categoryId == "photography" })
    }

    @Test
    fun heroesAndShelvesResolve() {
        Catalog.heroes.forEach { hero ->
            assertTrue(Catalog.app(hero.appId) != null)
        }
        Catalog.shelves.forEach { shelf ->
            assertEquals(shelf.appIds.size, Catalog.shelfApps(shelf).size)
        }
        assertEquals(Catalog.heroes.size, Catalog.homeHeroes.size)
        assertEquals(Catalog.shelves.size, Catalog.homeShelves.size)
    }

    @Test
    fun chartsAreSortedByRating() {
        val charts = Catalog.charts()
        assertEquals(Catalog.apps.size, charts.size)
        assertEquals(charts, charts.sortedByDescending { it.rating })
    }

    @Test
    fun everyAppHasACategory() {
        val categoryIds = Catalog.categories.map { it.id }.toSet()
        Catalog.apps.forEach { app ->
            assertTrue(app.categoryId in categoryIds)
        }
    }

    @Test
    fun appsInReturnsOnlyThatCategory() {
        val photography = Catalog.appsIn("photography")
        assertTrue(photography.isNotEmpty())
        assertTrue(photography.all { it.categoryId == "photography" })
        assertTrue(Catalog.appsIn("missing").isEmpty())
    }

    @Test
    fun similarToExcludesSelfAndStaysInCategory() {
        val torch = Catalog.app("torch")!!
        val similar = Catalog.similarTo(torch)
        assertFalse(similar.any { it.id == "torch" })
        assertTrue(similar.all { it.categoryId == torch.categoryId })
    }
}
