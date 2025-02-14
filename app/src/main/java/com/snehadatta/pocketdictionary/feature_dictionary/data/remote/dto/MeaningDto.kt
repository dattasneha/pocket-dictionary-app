package com.snehadatta.pocketdictionary.feature_dictionary.data.remote.dto


import com.google.gson.annotations.SerializedName
import com.snehadatta.pocketdictionary.feature_dictionary.domain.model.Meaning

data class MeaningDto(
    @SerializedName("definitions")
    val definitionDtos: List<DefinitionDto>,
    @SerializedName("partOfSpeech")
    val partOfSpeech: String,
    @SerializedName("synonyms")
    val synonyms: List<String>,
    @SerializedName("antonyms")
    val antonyms: List<String>

) {
    fun toMeaning(): Meaning {
        return Meaning(
            definition = definitionDtos.map { it.toDefinition() },
            partOfSpeech = partOfSpeech,
            synonyms = synonyms,
            antonyms = antonyms
        )
    }
}