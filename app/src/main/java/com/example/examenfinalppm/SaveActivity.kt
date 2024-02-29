package com.example.examenfinalppm

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.example.examenfinalppm.data.JuegosComprados

class SaveActivity : AppCompatActivity() {
    private lateinit var btnAtras : Button
    private lateinit var datos : TextView
    private lateinit var btnBorrar : Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_save)

        btnAtras = findViewById(R.id.buttonAtras3)
        datos = findViewById(R.id.textViewDatosSave)
        btnBorrar = findViewById(R.id.buttonBorrar)

        val db = JuegosComprados(this)
        val lista = db.lectura()

        var d = ""
        lista.forEach {
            d = d + "Videojuego ${lista.indexOf(it) + 1}: " + it.toString() + "\n"
        }
        datos.text = d

        btnAtras.setOnClickListener {
            intent = Intent(this@SaveActivity, MainActivity::class.java)
            startActivity(intent)
        }

        btnBorrar.setOnClickListener {
            db.borrar()
            datos.text = "no hay datos"
        }
    }
}