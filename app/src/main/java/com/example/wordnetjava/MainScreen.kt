package com.example.wordnetjava

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.wordnetjava.dataclasses.WordData

@Composable
fun MainScreen(
    getWordData: (String) -> WordData
) {
    var myWord by remember { mutableStateOf("") }
    val modifyMyWord = { word: String -> myWord = word }

    var result by remember { mutableStateOf("") }
    val modifyResult = { res: String -> result = res }

    var myWordData by remember { mutableStateOf<WordData?>(null) }

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        OutlinedTextField(
            value = myWord,
            onValueChange = modifyMyWord,
            label = { Text("word") },
            placeholder = { Text("Type a word") },
            singleLine = true,
        )
        Button(onClick = { myWordData = getWordData(myWord) }) {
            Text("show word data")
        }
//        Text(result)
        if (
            myWordData != null &&
            myWordData!!.posDataList.isNotEmpty()
        ) {
            Column {
                var synsetNumber = 1
                Text(
                    text = "Word: ${myWordData!!.word}",
                    style = TextStyle(
                        fontWeight = FontWeight.Bold,
                        fontSize = 35.sp,
                        color = Color.Gray
                    ),
                )
                Spacer(Modifier.height(10.dp))
                myWordData!!.posDataList.forEach { posData ->
                    Text(
                        text = "Part of Speech: ${posData.pos}",
                        style = TextStyle(
                            fontWeight = FontWeight.Bold,
                            fontSize = 25.sp,
                            color = Color.Red
                        ),
                        modifier = Modifier.padding(2.dp)
                    )
                    Text(
                        text = "Lemma: ${posData.lemma}",
                        style = TextStyle(
                            fontWeight = FontWeight.Bold,
                            fontSize = 20.sp,
                            color = Color.Blue
                        ),
                        modifier = Modifier.padding(5.dp)
                    )
                    posData.synsets.forEach { synset ->
                        SynsetCard(
                            synsetNumber,
                            synset.gloss,
                            synset.examples,
                            synset.synonyms
                        )
                        synsetNumber += 1
                    }
                    synsetNumber = 1
                    Spacer(modifier = Modifier.height(20.dp))
                }
            }
        } else {
            Text(text = "word not found or not setup yet !!")
        }
    }
}
