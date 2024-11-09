package com.snehadatta.pocketdictionary.feature_dictionary.data.remote.dto


import com.google.gson.annotations.SerializedName

data class MeaningDto(
    @SerializedName("antonyms")
    val antonyms: List<Any>,
    @SerializedName("definitions")
    val definitionDtos: List<DefinitionDto>,
    @SerializedName("partOfSpeech")
    val partOfSpeech: String,
    @SerializedName("synonyms")
    val synonyms: List<String>
)