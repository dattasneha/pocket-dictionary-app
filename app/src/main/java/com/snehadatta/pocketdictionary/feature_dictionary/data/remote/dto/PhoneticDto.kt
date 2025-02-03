package com.snehadatta.pocketdictionary.feature_dictionary.data.remote.dto


import com.google.gson.annotations.SerializedName
import com.snehadatta.pocketdictionary.feature_dictionary.domain.model.Meaning
import com.snehadatta.pocketdictionary.feature_dictionary.domain.model.Phonetics

data class PhoneticDto(
    @SerializedName("audio")
    val audio: String
) {
    fun toPhonetics(): Phonetics {
        return Phonetics(
            audio = audio
        )
    }
}