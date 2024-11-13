package com.snehadatta.pocketdictionary.feature_dictionary.data.local

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.google.gson.reflect.TypeToken
import com.snehadatta.pocketdictionary.feature_dictionary.data.util.JsonParser
import com.snehadatta.pocketdictionary.feature_dictionary.domain.model.Meaning
import dagger.Provides

@ProvidedTypeConverter
class Converters(
    private val jsonParser: JsonParser
) {
    @TypeConverter
    fun fromMeaningsJson(json: String):List<Meaning> {
        return jsonParser.fromJson<ArrayList<Meaning>>(
            json,
            object : TypeToken<ArrayList<Meaning>>(){}.type
        )?: emptyList()
    }

    @TypeConverter
    fun toMeaningsJson(meanings:List<Meaning>):String {
        return jsonParser.toJson(
            meanings,
            object : TypeToken<ArrayList<Meaning>>(){}.type
        )?: "[]"
    }

    @TypeConverter
    fun fromSourceUrlJson(json: String):List<String> {
        return jsonParser.fromJson<ArrayList<String>>(
            json,
            object : TypeToken<ArrayList<String>>(){}.type
        )?: emptyList()
    }

    @TypeConverter
    fun toSourceUrlJson(sourceUrl:List<String>):String {
        return jsonParser.toJson(
            sourceUrl,
            object : TypeToken<ArrayList<String>>(){}.type
        )?: "[]"
    }

}