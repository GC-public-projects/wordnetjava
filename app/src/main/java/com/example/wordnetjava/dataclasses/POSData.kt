package com.example.wordnetjava.dataclasses

// Represents information grouped by a specific Part of Speech
data class POSData(
    val pos: String, // Part of Speech
    val lemma: String, // original form of the word
    val synsets: List<SynsetData> // All synsets under this POS
)