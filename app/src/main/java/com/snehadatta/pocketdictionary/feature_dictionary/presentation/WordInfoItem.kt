package com.snehadatta.pocketdictionary.feature_dictionary.presentation

import android.hardware.lights.Light
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.snehadatta.pocketdictionary.feature_dictionary.domain.model.Definition
import com.snehadatta.pocketdictionary.feature_dictionary.domain.model.Meaning
import com.snehadatta.pocketdictionary.feature_dictionary.domain.model.WordInfo
import com.snehadatta.pocketdictionary.feature_dictionary.ui.theme.Green
import com.snehadatta.pocketdictionary.feature_dictionary.ui.theme.LightGreen
import com.snehadatta.pocketdictionary.feature_dictionary.ui.theme.Orange
import com.snehadatta.pocketdictionary.feature_dictionary.ui.theme.Peach
import java.util.concurrent.Flow

@Composable
fun WordInfoItem(
    wordInfo: WordInfo,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = wordInfo.word,
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black,
            modifier = Modifier.padding(bottom = 8.dp)
        )
        Text(
            text = wordInfo.phonetic,
            fontWeight = FontWeight.Light,
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = wordInfo.sourceUrls[0],modifier = Modifier.padding(bottom = 8.dp))

        wordInfo.meaning.forEach { meaning ->
            Text(text = meaning.partOfSpeech, fontWeight = FontWeight.Bold, fontSize = 16.sp)
            meaning.definition.forEachIndexed { i, definition ->
                Text(text = "${i + 1}. ${definition.definition}")
                Spacer(modifier = Modifier.height(8.dp))

                definition.example?.let { example ->
                    Text(
                        text = "e.g",
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(bottom = 4.dp)
                    )
                    Text(text = "${example}")
                }
                Spacer(modifier = Modifier.height(8.dp))
            }

            if(!meaning.synonyms.isNullOrEmpty()) {
                SectionTitle("Synonyms")
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(LightGreen, shape = RoundedCornerShape(8.dp))
                ) {
                    WordChips(
                        words = meaning.synonyms,
                        backgroundColor = Green,
                    )
                }
            }

            if(!meaning.antonyms.isNullOrEmpty()) {
                SectionTitle("Antonyms")
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Peach, shape = RoundedCornerShape(8.dp))
                ) {
                    WordChips(
                        words = meaning.antonyms,
                        backgroundColor = Orange
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))
        }

    }
}

@Composable
fun SectionTitle(title: String) {
    Text(
        text = title,
        fontSize = 20.sp,
        fontWeight = FontWeight.SemiBold,
        modifier = Modifier.padding(bottom = 8.dp)
    )
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun WordChips(words: List<String>, backgroundColor: Color) {
    FlowRow(
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        modifier = Modifier
            .fillMaxWidth().padding(8.dp)
    ) {
        words.forEach { word ->
            Box(
                modifier = Modifier
                    .background(backgroundColor, shape = RoundedCornerShape(16.dp))
                    .padding(horizontal = 16.dp, vertical = 8.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
                    text = word,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium,
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}
