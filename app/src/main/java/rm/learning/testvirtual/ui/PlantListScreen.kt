package rm.learning.testvirtual.ui


import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import rm.learning.testvirtual.model.Plant
import androidx.compose.ui.graphics.Color
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search


@Composable
fun PlantListScreen(
    plants: List<Plant>,
    onSelect: (String) -> Unit,
    onEdit: (String) -> Unit,
    showSearchBar: Boolean = true
) {
    var search by remember { mutableStateOf("") }

    val filtered = remember(search, plants) {
        if (search.isEmpty()) {
            plants
        } else {
            plants.filter { it.commonName.contains(search, true) || it.botanicalName.contains(search, true) }
        }
    }

    Column(
        Modifier
            .fillMaxSize()
            .padding(12.dp)
    ) {
        if (showSearchBar) {
            OutlinedTextField(
                value = search,
                onValueChange = { search = it },
                placeholder = { Text("Search plants") },
                modifier = Modifier.fillMaxWidth(),
                leadingIcon = {
                    Icon(Icons.Default.Search, contentDescription = null)
                },
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color.White.copy(alpha = 0.95f),
                    unfocusedContainerColor = Color.White.copy(alpha = 0.95f)
                )
            )
            Spacer(Modifier.height(12.dp))
        }

        LazyColumn {
            items(filtered) { plant ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp)
                        .clickable { onSelect(plant.id) },
                    colors = CardDefaults.cardColors()
                ) {
                    Row(Modifier.padding(12.dp)) {
                        AsyncImage(
                            model = plant.imageResIds.firstOrNull(),
                            contentDescription = null,
                            modifier = Modifier.size(72.dp)
                        )
                        Spacer(Modifier.width(12.dp))
                        Column {
                            Text(plant.commonName)
                            Text(plant.botanicalName)
                        }
                    }
                }
            }
        }
    }
}
