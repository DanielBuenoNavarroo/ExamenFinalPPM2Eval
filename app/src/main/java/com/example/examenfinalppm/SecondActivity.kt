package com.example.examenfinalppm

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PatternMatcher
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.examenfinalppm.data.Videojuego
import java.util.Date
import java.util.regex.Pattern

class SecondActivity : AppCompatActivity() {
    private lateinit var nEmpresa : EditText
    private lateinit var date: EditText
    private lateinit var btnSig : Button
    private lateinit var btnAtras : Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        val edit = intent.getBooleanExtra("edit", false)
        val lista = intent.getSerializableExtra("lista") as ArrayList<Videojuego>
        val videojuego = lista.last()
        lista.remove(lista.last())
        
        nEmpresa = findViewById(R.id.editTextEmpresa)
        date = findViewById(R.id.editTextLanzamiento)
        btnSig = findViewById(R.id.buttonSiguiente2)
        btnAtras = findViewById(R.id.buttonAtras)
        
        if (edit){
            nEmpresa.setText(videojuego.getNombreEmpresa())
            date.setText(videojuego.getFecha())
        }
        
        btnSig.setOnClickListener {
            if (nEmpresa.text.isNullOrEmpty() || date.text.isNullOrEmpty()){
                Toast.makeText(this, "Faltan datos", Toast.LENGTH_SHORT).show()
            }
            else {
                intent = Intent(this@SecondActivity, ThirdActivity::class.java)
                videojuego.setnombreEmpresa(nEmpresa.text.toString())
                videojuego.setFecha(date.text.toString())
                lista.add(videojuego)
                intent.putExtra("lista", lista)
                startActivity(intent)
            }
        }

        btnAtras.setOnClickListener {
            intent = Intent(this@SecondActivity, MainActivity::class.java)
            lista.add(videojuego)
            intent.putExtra("lista", lista)
            intent.putExtra("edit", true)
            startActivity(intent)
        }
    }
}