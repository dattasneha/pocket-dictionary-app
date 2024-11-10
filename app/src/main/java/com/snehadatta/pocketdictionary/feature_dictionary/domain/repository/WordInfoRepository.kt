package com.snehadatta.pocketdictionary.feature_dictionary.domain.repository

import com.snehadatta.pocketdictionary.core.util.Resource
import com.snehadatta.pocketdictionary.feature_dictionary.domain.model.WordInfo
import kotlinx.coroutines.flow.Flow

interface WordInfoRepository {

    fun getWordInfo(word:String): Flow<Resource<List<WordInfo>>>
}