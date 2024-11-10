package com.snehadatta.pocketdictionary.feature_dictionary.data.remote.dto


import com.google.gson.annotations.SerializedName
import com.snehadatta.pocketdictionary.feature_dictionary.domain.model.WordInfo

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
    val sourceUrls: List<String>,
    @SerializedName("word")
    val word: String
) {
    fun toWordInfo(): WordInfo {
        return WordInfo (
            meaning = meaningDtos.map { it.toMeaning() },
            phonetic = phonetic,
            sourceUrls = sourceUrls,
            word = word
        )
    }

}