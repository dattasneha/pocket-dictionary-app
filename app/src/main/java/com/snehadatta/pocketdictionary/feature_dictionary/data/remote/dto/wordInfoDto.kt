package com.snehadatta.pocketdictionary.feature_dictionary.data.remote.dto


import com.google.gson.annotations.SerializedName

data class wordInfoDto(
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
)