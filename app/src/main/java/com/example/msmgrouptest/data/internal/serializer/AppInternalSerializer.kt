package com.example.data_store.serializer

import androidx.datastore.core.Serializer
import com.example.data_store.entities.AppInternalData
import kotlinx.serialization.SerializationException
import kotlinx.serialization.json.Json
import java.io.InputStream
import java.io.OutputStream
import javax.inject.Singleton

@Singleton
@Suppress("BlockingMethodInNonBlockingContext")
internal class AppInternalSerializer: Serializer<AppInternalData> {
    override val defaultValue: AppInternalData
        get() = AppInternalData()

    override suspend fun readFrom(input: InputStream): AppInternalData {
        return try {
            Json.decodeFromString(
                deserializer = AppInternalData.serializer(),
                string = input.readBytes().decodeToString()
            )
        }catch (e: SerializationException){
            e.printStackTrace()
            defaultValue
        }
    }

    override suspend fun writeTo(t: AppInternalData, output: OutputStream) {
        output.write(
            Json.encodeToString(
                serializer = AppInternalData.serializer(),
                value = t
            ).encodeToByteArray()
        )
    }
}