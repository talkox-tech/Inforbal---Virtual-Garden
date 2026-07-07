package rm.learning.testvirtual.model


import rm.learning.testvirtual.R


enum class PlantTab { AYUSH, SCIENTIFIC, CULTIVATION }


data class QuickFacts(val rasa: String, val virya: String, val usedPart: String)


data class AyushInfo(val actions: List<String>, val indications: List<String>, val formulations: List<String>, val precaution: String)


data class ScientificInfo(val constituents: String, val researchSummary: String)


data class CultivationInfo(val habitat: String, val propagation: String, val soilClimate: String, val harvesting: String)


data class Plant(
    val id: String,
    val commonName: String,
    val botanicalName: String,
    val family: String,
    val vernacularNames: String,
    val imageResIds: List<Int>,
    val videoLinks: List<String>,
    val quickFacts: QuickFacts,
    val ayush: AyushInfo,
    val scientific: ScientificInfo,
    val cultivation: CultivationInfo
)


// Sample mutable data (can be moved elsewhere if preferred)
val samplePlantData = mutableListOf(
    Plant(
        id = "ashwagandha",
        commonName = "Ashwagandha",
        botanicalName = "Withania somnifera",
        family = "Solanaceae",
        vernacularNames = "Indian ginseng",
        imageResIds = listOf(R.drawable.ashwagandha_image),
        videoLinks = listOf("https://www.youtube.com/watch?v=ashwagandha_video1"),
        quickFacts = QuickFacts("Tikta", "Hot", "Root"),
        ayush = AyushInfo(listOf("Strength"), listOf("Stress relief"), listOf("Churna"), "Consult doctor if pregnant"),
        scientific = ScientificInfo("Withanolides", "Helps reduce stress"),
        cultivation = CultivationInfo("Dry regions", "Seeds", "Hot weather", "6 months")
    ),
    Plant(
        id = "tulsi",
        commonName = "Tulsi",
        botanicalName = "Ocimum sanctum",
        family = "Lamiaceae",
        vernacularNames = "Holy Basil",
        imageResIds = listOf(R.drawable.tulsi_image),
        videoLinks = listOf("https://www.youtube.com/watch?v=tulsi_video1"),
        quickFacts = QuickFacts("Kaṭu, Tikta", "Uṣhṇa", "Leaf"),
        ayush = AyushInfo(listOf("Kapha-Vata Pacifier"), listOf("Cold"), listOf("Tulsi Ark"), "Use fresh"),
        scientific = ScientificInfo("Eugenol", "Antioxidant"),
        cultivation = CultivationInfo("Gardens", "Seeds", "Well-drained soil", "Year-round")
    )
)