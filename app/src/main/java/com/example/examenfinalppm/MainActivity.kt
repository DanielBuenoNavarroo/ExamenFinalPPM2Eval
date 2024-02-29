package com.example.examenfinalppm

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.examenfinalppm.data.Videojuego
import java.util.Date

class MainActivity : AppCompatActivity() {
    private lateinit var nombre : EditText
    private lateinit var valoracion : EditText
    private lateinit var btnSig : Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        nombre = findViewById(R.id.editTextNombreV)
        valoracion = findViewById(R.id.editTextValoracion)
        btnSig = findViewById(R.id.buttonSiguiente1)

        var videojuego = Videojuego("", 0F, "", "")
        val l = arrayListOf<Videojuego>()
        val edit = intent.getBooleanExtra("edit", false)
        val nuevo = intent.getBooleanExtra("nuevo", false)
        if (nuevo){
            val lista = intent.getSerializableExtra("lista") as ArrayList<Videojuego>
            if (lista.isNotEmpty()){
                lista.forEach { l.add(it) }
            }
        }
        if (edit){
            val lista = intent.getSerializableExtra("lista") as ArrayList<Videojuego>
            if (lista.isNotEmpty()){
                lista.forEach { l.add(it) }
            }
            videojuego = l.last()
            l.remove(l.last())
            nombre.setText(videojuego.getNombre())
            valoracion.setText(videojuego.getValoracion().toString())
        }

        btnSig.setOnClickListener {
            if (nombre.text.isNullOrEmpty() || valoracion.text.isNullOrEmpty()){
                Toast.makeText(this, "Faltan datos", Toast.LENGTH_SHORT).show()
            } else if (valoracion.text.toString().toFloat() > 10 || valoracion.text.toString().toFloat() < 0) {
                Toast.makeText(this, "La valoracion tiene que ser entre 0 y 10", Toast.LENGTH_SHORT).show()
            } else {
                intent = Intent(this@MainActivity, SecondActivity::class.java)
                videojuego.setNombre(nombre.text.toString())
                videojuego.setValoracion(valoracion.text.toString().toFloat())
                l.add(videojuego)
                intent.putExtra("lista", l)
                startActivity(intent)
            }
        }
    }
}