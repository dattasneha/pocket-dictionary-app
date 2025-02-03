package com.snehadatta.pocketdictionary.feature_dictionary.domain.model

import com.google.gson.annotations.SerializedName
import com.snehadatta.pocketdictionary.feature_dictionary.data.remote.dto.License
import com.snehadatta.pocketdictionary.feature_dictionary.data.remote.dto.MeaningDto
import com.snehadatta.pocketdictionary.feature_dictionary.data.remote.dto.PhoneticDto

data class WordInfo (
    val meaning: List<Meaning>,
    val phonetic: String,
    val sourceUrls: List<String>,
    val word: String,
    val phonetics: List<Phonetics>
)