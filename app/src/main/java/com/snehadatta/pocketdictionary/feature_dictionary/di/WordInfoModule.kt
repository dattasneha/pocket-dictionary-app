package com.snehadatta.pocketdictionary.feature_dictionary.di

import android.app.Application
import androidx.room.Room
import com.google.gson.Gson
import com.google.gson.annotations.SerializedName
import com.snehadatta.pocketdictionary.feature_dictionary.data.local.WordInfoDao
import com.snehadatta.pocketdictionary.feature_dictionary.data.local.WordInfoDatabase
import com.snehadatta.pocketdictionary.feature_dictionary.data.remote.DictionaryApi
import com.snehadatta.pocketdictionary.feature_dictionary.data.repository.WordInfoRepositoryImpl
import com.snehadatta.pocketdictionary.feature_dictionary.data.util.GsonParser
import com.snehadatta.pocketdictionary.feature_dictionary.domain.repository.WordInfoRepository
import com.snehadatta.pocketdictionary.feature_dictionary.domain.use_case.GetWordInfo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object WordInfoModule {

    @Provides
    @Singleton
    fun provideGetWordInfoUseCase(repository: WordInfoRepository):GetWordInfo {
        return GetWordInfo(repository)
    }

    @Provides
    @Singleton
    fun provideGetWordInfoRepository(
        db: WordInfoDatabase,
        api: DictionaryApi
    ): WordInfoRepository {
        return  WordInfoRepositoryImpl(api,db.dao)
    }

    @Provides
    @Singleton
    fun provideGetWordInfoDatabase(app: Application):WordInfoDatabase {
        return Room.databaseBuilder(
            app, WordInfoDatabase::class.java, "word_db"
        ).addTypeConverter(GsonParser(Gson()))
            .build()
    }

    @Provides
    @Singleton
    fun provideDictionaryApp(): DictionaryApi {
        return Retrofit.Builder()
            .baseUrl(DictionaryApi.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(DictionaryApi::class.java)
    }
}