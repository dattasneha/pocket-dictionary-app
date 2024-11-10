package com.snehadatta.pocketdictionary.feature_dictionary.data.remote.dto


import com.google.gson.annotations.SerializedName
import com.snehadatta.pocketdictionary.feature_dictionary.data.local.entities.WordInfoEntity

data class WordInfoDto(
    @SerializedName("license")
    val license: License,
    @SerializedName("meanings")
    val meaningDtos: List<MeaningDto>,
    @SerializedName("phonetic")
    val phonetic: String,
    @SerializedName("phonetics")
    val phoneticDtos: List<PhoneticDto>,
    @SerializedName("sourceUrls")
    val sourceUrl: List<String>,
    @SerializedName("word")
    val word: String
) {
    fun toWordInfoEntity(): WordInfoEntity {
        return WordInfoEntity (
            meaning = meaningDtos.map { it.toMeaning() },
            phonetic = phonetic,
            sourceUrl = sourceUrl,
            word = word
        )
    }

}