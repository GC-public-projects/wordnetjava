package com.example.wordnetjava.dataclasses

// Represents all information for a specific word
data class WordData(
    val word: String, // The searched word
    val posDataList: List<POSData> // Organized data by Part of Speech
)