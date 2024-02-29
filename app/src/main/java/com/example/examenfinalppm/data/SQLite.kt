package com.example.examenfinalppm.data

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class JuegosComprados (context: Context) : SQLiteOpenHelper(context, DATABASE, null, DATABASE_VERSION) {
    companion object {
        private const val DATABASE_VERSION = 1
        private const val DATABASE = "Videojuegos"
        private const val TABLA_VIDEOJUEGOS = "TABLA_VIDEOJUEGOS"
        private const val KEY_ID = "_id"
        private const val COLUMN_NOMBRE = "nombre"
        private const val COLUMN_VALORACION = "val"
        private const val COLUMN_NOMBRE_EMPRESA = "nombre_empresa"
        private const val COLUMN_FECHA = "fecha"
    }

    override fun onCreate(db: SQLiteDatabase) {
        val createTable = "CREATE TABLE $TABLA_VIDEOJUEGOS ($KEY_ID INTEGER PRIMARY KEY, $COLUMN_NOMBRE TEXT, $COLUMN_VALORACION FLOAT, $COLUMN_NOMBRE_EMPRESA TEXT, $COLUMN_FECHA TEXT)"
        db.execSQL(createTable)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS $TABLA_VIDEOJUEGOS")
        onCreate(db)
    }

    fun borrar() {
        val db = this.writableDatabase
        db.execSQL("DROP TABLE IF EXISTS $TABLA_VIDEOJUEGOS")
        onCreate(db)
    }

    fun escribir(videojuego: Videojuego) {
        val db = this.writableDatabase
        val values = ContentValues().apply {
            put(COLUMN_NOMBRE, videojuego.getNombre())
            put(COLUMN_VALORACION, videojuego.getValoracion())
            put(COLUMN_NOMBRE_EMPRESA, videojuego.getNombreEmpresa())
            put(COLUMN_FECHA, videojuego.getFecha())
        }
        db.insert(TABLA_VIDEOJUEGOS, null, values)
        db.close()
    }

    @SuppressLint("Range")
    fun lectura(): ArrayList<Videojuego> {
        val lectura = ArrayList<Videojuego>()
        val selectQuery = "SELECT * FROM $TABLA_VIDEOJUEGOS"
        val db = this.readableDatabase
        val cursor = db.rawQuery(selectQuery, null)
        if (cursor.moveToFirst()) {
            do {
                val nombre = cursor.getString(cursor.getColumnIndex(COLUMN_NOMBRE))
                val valoracion = cursor.getFloat(cursor.getColumnIndex(COLUMN_VALORACION))
                val nombreEmpresa = cursor.getString(cursor.getColumnIndex(COLUMN_NOMBRE_EMPRESA))
                val fecha = cursor.getString(cursor.getColumnIndex(COLUMN_FECHA))
                lectura.add(Videojuego(nombre, valoracion, nombreEmpresa, fecha))
            } while (cursor.moveToNext())
        }
        cursor.close()
        db.close()
        return lectura
    }
}