package com.snehadatta.pocketdictionary.feature_dictionary.presentation

import android.hardware.lights.Light
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.snehadatta.pocketdictionary.R
import com.snehadatta.pocketdictionary.feature_dictionary.domain.model.Definition
import com.snehadatta.pocketdictionary.feature_dictionary.domain.model.Meaning
import com.snehadatta.pocketdictionary.feature_dictionary.domain.model.WordInfo
import com.snehadatta.pocketdictionary.feature_dictionary.ui.theme.Green
import com.snehadatta.pocketdictionary.feature_dictionary.ui.theme.Lavender
import com.snehadatta.pocketdictionary.feature_dictionary.ui.theme.LightGreen
import com.snehadatta.pocketdictionary.feature_dictionary.ui.theme.Orange
import com.snehadatta.pocketdictionary.feature_dictionary.ui.theme.Peach
import com.snehadatta.pocketdictionary.feature_dictionary.ui.theme.Purple200
import com.snehadatta.pocketdictionary.feature_dictionary.ui.theme.White
import java.util.concurrent.Flow

@Composable
fun WordInfoItem(
    wordInfo: WordInfo,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
    ) {
        Column (
            modifier = modifier
                .fillMaxSize()
                .background(Lavender, shape = RoundedCornerShape(8.dp))
        ) {
            Text(
                text = wordInfo.word,
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                modifier = Modifier.padding(bottom = 8.dp)
            )

            val itemPosition = remember {
                mutableStateOf(0)
            }

            wordInfo.meaning[itemPosition.value].let {
                Text(
                    text = wordInfo.phonetic,
                    fontWeight = FontWeight.Light,
                )
            }

            Spacer(modifier = Modifier.height(16.dp))
            Text(text = wordInfo.sourceUrls[0],modifier = Modifier.padding(bottom = 8.dp))

            DropDownDemo(wordInfo,itemPosition)

            wordInfo.meaning[itemPosition.value].let { meaning ->
                meaning.definition.forEachIndexed { i, definition ->
                    Column (
                        modifier = modifier
                            .fillMaxSize()
                            .background(White, shape = RoundedCornerShape(8.dp))
                    ) {
                        Text(text = "${i + 1}. ${definition.definition}")
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    definition.example?.let { example ->
                        Column (
                            modifier = modifier
                                .fillMaxSize()
                                .background(White, shape = RoundedCornerShape(8.dp))
                        ) {
                            Text(
                                text = "e.g",
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier.padding(bottom = 4.dp)
                            )
                            Text(text = "${example}")
                        }
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                }
        }

        wordInfo.meaning[itemPosition.value].let { meaning ->
            if(!meaning.synonyms.isNullOrEmpty()) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                        .background(LightGreen, shape = RoundedCornerShape(8.dp))
                ) {
                    SectionTitle("Synonyms")
                    WordChips(
                        words = meaning.synonyms,
                        backgroundColor = Green,
                    )
                }
            }

            if(!meaning.antonyms.isNullOrEmpty()) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                        .background(Peach, shape = RoundedCornerShape(8.dp))
                ) {
                    SectionTitle("Antonyms")
                    WordChips(
                        words = meaning.antonyms,
                        backgroundColor = Orange
                    )
                }
            }
        }
            Spacer(modifier = Modifier.height(16.dp))
        }

    }
}

@Composable
fun DropDownDemo(
    wordInfo: WordInfo,
    itemPosition: MutableState<Int>
) {

    val isDropDownExpanded = remember {
        mutableStateOf(false)
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .background(Purple200)
        ) {
            Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.clickable {
                    isDropDownExpanded.value = true
                }
            ) {
                Text(
                    text = wordInfo.meaning[itemPosition.value].partOfSpeech,
                    modifier = Modifier.padding(8.dp))

                Image(
                    painter = painterResource(id = R.drawable.baseline_arrow_drop_down_24),
                    contentDescription = "DropDown Icon",
                )
            }
            DropdownMenu(
                expanded = isDropDownExpanded.value,
                onDismissRequest = {
                    isDropDownExpanded.value = false
                }) {
                wordInfo.meaning.forEachIndexed { index, meaning ->
                    DropdownMenuItem(text = {
                        Text(text = meaning.partOfSpeech)
                    },
                        onClick = {
                            isDropDownExpanded.value = false
                            itemPosition.value = index
                        })
                }
            }
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
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp),
        modifier = Modifier
            .fillMaxWidth().padding(12.dp)
    ) {
        words.forEach { word ->
            Box(
                modifier = Modifier
                    .background(backgroundColor, shape = RoundedCornerShape(16.dp))
                    .padding(horizontal = 16.dp, vertical = 8.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    modifier = Modifier.padding(horizontal = 4.dp, vertical = 2.dp),
                    text = word,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium,
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}
