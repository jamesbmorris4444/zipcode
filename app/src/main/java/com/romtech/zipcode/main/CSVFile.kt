package com.romtech.zipcode.main

import android.util.Log
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStream
import java.io.InputStreamReader

class CSVFile(private val inputStream: InputStream) {
    fun read(): HashMap<String,String> {
        val resultMap: HashMap<String,String> = hashMapOf()
        val reader = BufferedReader(InputStreamReader(inputStream))
        try {
            var csvLine: String?
            while (reader.readLine().also { csvLine = it } != null) {
                val row: List<String>? = csvLine?.split(",")
                row?.let {
                    resultMap.put(row[1], row[2])
                }
            }
        } catch (ex: IOException) {
            throw RuntimeException("Error in reading CSV file: $ex")
        } finally {
            try {
                inputStream.close()
            } catch (e: IOException) {
                throw RuntimeException("Error while closing input stream: $e")
            }
        }
        return resultMap
    }
}