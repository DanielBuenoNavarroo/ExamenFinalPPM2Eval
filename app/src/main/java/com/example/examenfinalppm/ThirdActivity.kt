package com.example.examenfinalppm

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import com.example.examenfinalppm.data.JuegosComprados
import com.example.examenfinalppm.data.Videojuego

class ThirdActivity : AppCompatActivity() {
    private lateinit var datos : TextView
    private lateinit var btnNuevo : Button
    private lateinit var btnAtras : Button
    private lateinit var btnGuardar : Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_third)

        datos = findViewById(R.id.TextViewDatos)
        btnNuevo = findViewById(R.id.buttonNuevo)
        btnAtras = findViewById(R.id.buttonAtras2)
        btnGuardar = findViewById(R.id.buttonGuardar)

        val db = JuegosComprados(this)
        val lista = intent.getSerializableExtra("lista") as ArrayList<Videojuego>

        var d = ""
        lista.forEach {
            d = d + "Videojuego ${lista.indexOf(it) + 1}: " + it.toString() + "\n"
        }
        datos.text = d

        btnNuevo.setOnClickListener {
            intent = Intent(this@ThirdActivity, MainActivity::class.java)
            intent.putExtra("lista", lista)
            intent.putExtra("nuevo", true)
            startActivity(intent)
        }

        btnAtras.setOnClickListener {
            intent = Intent(this@ThirdActivity, SecondActivity::class.java)
            intent.putExtra("lista", lista)
            intent.putExtra("edit", true)
            startActivity(intent)
        }

        btnGuardar.setOnClickListener {
            lista.forEach {
                db.escribir(it)
            }
            intent = Intent(this@ThirdActivity, SaveActivity::class.java)
            startActivity(intent)
        }
    }
}