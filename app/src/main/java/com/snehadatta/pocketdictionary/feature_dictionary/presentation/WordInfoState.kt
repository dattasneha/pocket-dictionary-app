package com.snehadatta.pocketdictionary.feature_dictionary.presentation

import com.snehadatta.pocketdictionary.core.util.Resource
import com.snehadatta.pocketdictionary.feature_dictionary.domain.model.WordInfo

data class WordInfoState(
    val wordInfoItems: List<WordInfo> = emptyList(),
    val isLoading: Boolean = false
)