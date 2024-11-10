package com.snehadatta.pocketdictionary.feature_dictionary.data.repository

import android.net.http.HttpException
import android.os.Build
import androidx.annotation.RequiresExtension
import com.snehadatta.pocketdictionary.core.util.Resource
import com.snehadatta.pocketdictionary.feature_dictionary.data.local.WordInfoDao
import com.snehadatta.pocketdictionary.feature_dictionary.data.remote.DictionaryApi
import com.snehadatta.pocketdictionary.feature_dictionary.domain.model.WordInfo
import com.snehadatta.pocketdictionary.feature_dictionary.domain.repository.WordInfoRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException

class WordInfoRepositoryImpl(
    private val api: DictionaryApi,
    private val dao: WordInfoDao
): WordInfoRepository {
    @RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
    override fun getWordInfo(word: String): Flow<Resource<List<WordInfo>>> = flow {
        emit(Resource.Loading())

        val wordInfos = dao.getWordInfos(word).map { it.toWordInfo() }
        emit(Resource.Loading(data = wordInfos))

        try {
            val remoteWordInfo = api.getWordInfo(word)
            dao.deleteWordInfos(remoteWordInfo.map { it.word })
            dao.insertWordInfos(remoteWordInfo.map { it.toWordInfoEntity() })

        }catch (e: HttpException) {
            emit(Resource.Error(
                message = "Oops, Something went wrong!",
                data = wordInfos
            ))

        }catch (e: IOException) {
            emit(Resource.Error(
                message = "Couldn't reach server, Check your internet connection.",
                data = wordInfos
            ))

        }

        val newWordInfo = dao.getWordInfos(word).map { it.toWordInfo() }
        emit(Resource.Success(newWordInfo))
    }
}
