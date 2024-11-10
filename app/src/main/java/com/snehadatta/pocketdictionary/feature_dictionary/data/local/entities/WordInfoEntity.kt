package com.snehadatta.pocketdictionary.feature_dictionary.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.snehadatta.pocketdictionary.feature_dictionary.domain.model.Meaning
import com.snehadatta.pocketdictionary.feature_dictionary.domain.model.WordInfo

@Entity
data class WordInfoEntity (
    val word : String,
    val phonetic: String,
    val sourceUrl: List<String>,
    val meaning: List<Meaning>,
    @PrimaryKey val id: Int? = null
) {
    fun toWordInfo(): WordInfo {
        return WordInfo(
            meaning = meaning,
            phonetic = phonetic,
            sourceUrls = sourceUrl,
            word = word
        )
    }
}