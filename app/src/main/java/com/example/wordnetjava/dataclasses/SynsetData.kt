package com.example.wordnetjava.dataclasses

// Represents information about a single Synset
data class SynsetData(
    val pos: String, // Part of Speech (e.g., noun, verb)
    val gloss: String, // Definition or gloss
    val examples: List<String>, // examples between quotes
    val synonyms: List<String>, // Words in the synset

)