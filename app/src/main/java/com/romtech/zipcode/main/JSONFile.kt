package com.romtech.zipcode.main

import android.os.Parcelable
import com.google.gson.Gson
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader

class JSONFile(private val inputStream: InputStream) {
    @Suppress("UNCHECKED_CAST")
    fun readJsonFromFile(): HashMap<String, ZipCode> {
        val resultMap: HashMap<String, ZipCode> = hashMapOf()
        val inputStreamReader = InputStreamReader(inputStream)
        val bufferedReader = BufferedReader(inputStreamReader)
        val gson = Gson()
        //val arrayZipCodeType = object : TypeToken<Array<ZipCode>>() {}.type
        val allText = bufferedReader.use(BufferedReader::readText)
        val result = StringBuilder()
        result.append(allText)
        val zipCodeData: ZipCodeData = gson.fromJson(result.toString(), ZipCodeData::class.java)
        zipCodeData.zipCodeData.forEach { resultMap[it.zipCode] = it }
        inputStream.close()
        return resultMap
    }
}

@Parcelize
data class ZipCode(
    @SerializedName(value = "zip_code") var zipCode: String,
    @SerializedName(value = "city") var city: String,
    @SerializedName(value = "state") var state: String,
    @SerializedName(value = "classification") var classification: String,
    @SerializedName(value = "is_FDC") var isFdc: Boolean
) : Parcelable

@Parcelize
data class ZipCodeData(
    @SerializedName(value = "data") var zipCodeData: List<ZipCode>,
) : Parcelable