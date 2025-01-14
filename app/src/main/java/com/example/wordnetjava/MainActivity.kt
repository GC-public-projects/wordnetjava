package com.example.wordnetjava

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.ui.Alignment
import com.example.wordnetjava.dataclasses.POSData
import com.example.wordnetjava.dataclasses.SynsetData
import com.example.wordnetjava.dataclasses.WordData
import com.example.wordnetjava.ui.theme.WordnetjavaTheme
import net.sf.extjwnl.dictionary.Dictionary

class MainActivity : ComponentActivity() {
    private val dictionary = Dictionary.getDefaultResourceInstance()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            WordnetjavaTheme {
                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    MainScreen(::getWordData)
                }
            }
        }
    }

    fun getWordData(word: String): WordData {
        val indexWords = dictionary.lookupAllIndexWords(word) // Get all entries for the word
        val posDataList = mutableListOf<POSData>()

        // Iterate over all index words to collect data for each POS
        for (indexWord in indexWords.indexWordCollection) {

            val synsets = mutableListOf<SynsetData>()

            // Iterate over all senses (synsets) of the index word
            for (synset in indexWord.senses) {
                val synonyms = synset.words.mapNotNull { word ->
                    if (word.lemma != indexWord.lemma) word.lemma else null
                } // Extract all synonyms


                // Extract gloss and examples
                val glossText = synset.gloss.orEmpty() // Get the gloss (definition)
                // Regular expression to match quoted text
                val quoteRegex = "\"([^\"]*)\"".toRegex()
                // Extract gloss (text outside quotes) by removing quoted parts
                var gloss = glossText.replace(quoteRegex, "").trim()
                gloss = gloss.replace(";", "")
                // Extract examples (text within quotes)
                val examples = quoteRegex.findAll(glossText).map { it.groupValues[1] }.toList()


                synsets.add(
                    SynsetData(
                        pos = indexWord.pos.name, // Part of Speech
                        gloss = gloss,
                        examples = examples,
                        synonyms = synonyms,
                    )
                )
            }

            posDataList.add(
                POSData(
                    pos = indexWord.pos.name,
                    lemma = indexWord.lemma,
                    synsets = synsets
                )
            )
        }

        // Return the complete structured data
        return WordData(
            word = word,
            posDataList = posDataList
        )
    }
}

