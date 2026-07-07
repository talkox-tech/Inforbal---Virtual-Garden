package rm.learning.testvirtual.ui
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import rm.learning.testvirtual.model.Plant
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.material3.Card
import androidx.compose.foundation.layout.padding
import android.widget.Toast
import android.net.Uri
import android.content.Intent
import androidx.compose.material3.TextButton
import androidx.compose.material3.CardDefaults
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.layout.Row
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.BookmarkBorder
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.Alignment


@Composable
fun PlantDetailScreen(
    plant: Plant,
    isBookmarked: Boolean,
    onToggleBookmark: () -> Unit,
    onBack: () -> Unit // Removed onEdit parameter
) {
    val context = LocalContext.current


    LazyColumn(Modifier.fillMaxSize().padding(16.dp)) {
        item {
            Row(
                modifier = Modifier.fillMaxSize(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Plant Names
                Text(plant.commonName, style = MaterialTheme.typography.headlineLarge)
                
                // Bookmark Icon
                IconButton(onClick = {
                    onToggleBookmark()
                    Toast.makeText(context, if (isBookmarked) "Removed from bookmarks" else "Added to bookmarks", Toast.LENGTH_SHORT).show()
                }) {
                    Icon(
                        imageVector = if (isBookmarked) Icons.Filled.Bookmark else Icons.Filled.BookmarkBorder,
                        contentDescription = if (isBookmarked) "Remove from bookmarks" else "Add to bookmarks",
                        tint = if (isBookmarked) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }
            Text(plant.botanicalName, style = MaterialTheme.typography.titleMedium, color = MaterialTheme.colorScheme.onSurfaceVariant)
            Spacer(Modifier.height(8.dp))
        }


        item {
            LazyRow(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                items(plant.imageResIds.ifEmpty { listOf(android.R.drawable.ic_menu_gallery) }) { res ->
                    Card(modifier = Modifier.size(width = 280.dp, height = 180.dp), colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceContainer)) {
                         AsyncImage(model = res, contentDescription = null, modifier = Modifier.fillMaxSize())
                    }
                }
            }
        }

        // Removed the "Edit" button item
        // item {
        //     Spacer(Modifier.height(8.dp))
        //     TextButton(onClick = { onEdit(plant) }) { Text("Edit") }
        // }


        item {
            Spacer(Modifier.height(12.dp))
            Text("Basic Information", style = MaterialTheme.typography.titleMedium)
            Text("Family: ${plant.family}", style = MaterialTheme.typography.bodyMedium)
            Text("Vernacular: ${plant.vernacularNames}", style = MaterialTheme.typography.bodyMedium)
        }


        if (plant.videoLinks.isNotEmpty()) {
            item {
                Spacer(Modifier.height(12.dp))
                Text("Videos", style = MaterialTheme.typography.titleMedium)
                plant.videoLinks.forEach { link ->
                    Text(
                        text = link,
                        modifier = Modifier.clickable {
                            try {
                                val i = Intent(Intent.ACTION_VIEW, Uri.parse(link))
                                context.startActivity(i)
                            } catch (e: Exception) {
                                Toast.makeText(context, "Cannot open", Toast.LENGTH_SHORT).show()
                            }
                        },
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.primary // Assuming you want links to be colored
                    )
                }
            }
        }
    }
}