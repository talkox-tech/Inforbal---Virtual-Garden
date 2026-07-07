package rm.learning.testvirtual.ui


import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import rm.learning.testvirtual.model.*


@Composable
fun EditPlantScreen(plant: Plant, onSave: (Plant) -> Unit, onCancel: () -> Unit) {
    val context = LocalContext.current


    var commonName by remember { mutableStateOf(plant.commonName) }
    var botanicalName by remember { mutableStateOf(plant.botanicalName) }
// ... other fields (same pattern)


    Column(Modifier.fillMaxSize().verticalScroll(rememberScrollState()).padding(16.dp)) {
        OutlinedTextField(value = commonName, onValueChange = { commonName = it }, label = { Text("Common Name") }, modifier = Modifier.fillMaxWidth(), colors = TextFieldDefaults.colors())
        Spacer(Modifier.height(8.dp))
        OutlinedTextField(value = botanicalName, onValueChange = { botanicalName = it }, label = { Text("Botanical Name") }, modifier = Modifier.fillMaxWidth())
        Spacer(Modifier.height(12.dp))


        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly) {
            Button(onClick = onCancel) { Text("Cancel") }
            Button(onClick = {
                val updated = plant.copy(commonName = commonName, botanicalName = botanicalName)
                onSave(updated)
                Toast.makeText(context, "Saved", Toast.LENGTH_SHORT).show()
            }, colors = ButtonDefaults.buttonColors(containerColor = HerbalColorPrimary)) { Text("Save") }
        }
    }
}