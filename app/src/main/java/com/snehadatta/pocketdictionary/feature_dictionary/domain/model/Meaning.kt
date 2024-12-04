package com.snehadatta.pocketdictionary.feature_dictionary.domain.model

data class Meaning(
    val definition: List<Definition>,
    val partOfSpeech: String,
    val synonyms: List<String>,
    val antonyms: List<String>
)
