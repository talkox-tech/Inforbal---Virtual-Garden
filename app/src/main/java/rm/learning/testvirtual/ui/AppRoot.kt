package rm.learning.testvirtual.ui

import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.Grass
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import rm.learning.testvirtual.model.Plant

// Define sealed class for bottom navigation items
sealed class BottomNavItem(val title: String, val icon: ImageVector) {
    object Home : BottomNavItem("Home", Icons.Default.Home)
    object Plants : BottomNavItem("Plants", Icons.Default.Grass)
    object Bookmarks : BottomNavItem("Bookmarks", Icons.Default.Bookmark)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppRoot(plants: MutableList<Plant>) {
    var selectedPlantId by remember { mutableStateOf<String?>(null) }
    var currentBottomTab by remember { mutableStateOf<BottomNavItem>(BottomNavItem.Home) } // Use sealed class
    val bookmarkedIds = remember { mutableStateListOf<String>() }
    val context = LocalContext.current

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        when {
                            selectedPlantId != null -> plants.find { it.id == selectedPlantId }?.commonName ?: "Plant Details"
                            else -> currentBottomTab.title // Use title from BottomNavItem
                        }
                    )
                },
                navigationIcon = {
                    if (selectedPlantId != null) {
                        IconButton(onClick = {
                            selectedPlantId = null // Go back from detail view
                        }) {
                            Icon(Icons.AutoMirrored.Filled.ArrowBack, "Back")
                        }
                    }
                }
            )
        },
        bottomBar = {
            if (selectedPlantId == null) { // Only show bottom bar on main tabs
                NavigationBar {
                    NavigationBarItem(
                        selected = currentBottomTab == BottomNavItem.Home,
                        onClick = { currentBottomTab = BottomNavItem.Home },
                        icon = { Icon(BottomNavItem.Home.icon, BottomNavItem.Home.title) },
                        label = { Text(BottomNavItem.Home.title) }
                    )
                    NavigationBarItem(
                        selected = currentBottomTab == BottomNavItem.Plants,
                        onClick = { currentBottomTab = BottomNavItem.Plants },
                        icon = { Icon(BottomNavItem.Plants.icon, BottomNavItem.Plants.title) },
                        label = { Text(BottomNavItem.Plants.title) }
                    )
                    NavigationBarItem(
                        selected = currentBottomTab == BottomNavItem.Bookmarks,
                        onClick = { currentBottomTab = BottomNavItem.Bookmarks },
                        icon = { Icon(BottomNavItem.Bookmarks.icon, BottomNavItem.Bookmarks.title) },
                        label = { Text(BottomNavItem.Bookmarks.title) }
                    )
                }
            }
        }
    ) { padding ->
        Box(Modifier.padding(padding)) {
            when {
                selectedPlantId != null -> {
                    val plant = plants.find { it.id == selectedPlantId }
                    if (plant != null) {
                        PlantDetailScreen(
                            plant = plant,
                            isBookmarked = bookmarkedIds.contains(plant.id),
                            onToggleBookmark = {
                                if (bookmarkedIds.contains(plant.id)) bookmarkedIds.remove(plant.id) else bookmarkedIds.add(plant.id)
                            },
                            onBack = { selectedPlantId = null } // Allow PlantDetailScreen to request back nav
                        )
                    } else {
                        // Handle case where plant is not found (e.g., deleted)
                        selectedPlantId = null
                        Toast.makeText(context, "Plant not found.", Toast.LENGTH_SHORT).show()
                    }
                }

                else -> when (currentBottomTab) { // Show bottom tab screens
                    BottomNavItem.Home -> HomeScreen()
                    BottomNavItem.Plants -> PlantListScreen(
                        plants = plants,
                        onSelect = { selectedPlantId = it },
                        onEdit = { /* Do nothing for now */ }
                    )
                    BottomNavItem.Bookmarks -> PlantListScreen(
                        plants = plants.filter { bookmarkedIds.contains(it.id) }.toMutableList(),
                        onSelect = { selectedPlantId = it },
                        onEdit = { /* Do nothing for now */ },
                        showSearchBar = false
                    )
                }
            }
        }
    }
}
