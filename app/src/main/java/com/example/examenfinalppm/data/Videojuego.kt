package com.example.examenfinalppm.data

import java.io.Serializable

class Videojuego(
    private var nombre : String,
    private var valoracion : Float,
    private var nombreEmpresa : String,
    private var fecha : String
) : Serializable {
    fun setNombre(nombre: String){
        this.nombre = nombre
    }
    fun setValoracion(valoracion: Float){
        this.valoracion = valoracion
    }
    fun setnombreEmpresa(nombreEmpresa: String){
        this.nombreEmpresa = nombreEmpresa
    }
    fun setFecha(fecha: String){
        this.fecha = fecha
    }
    fun getNombre(): String{
        return this.nombre
    }
    fun getValoracion() : Float{
        return this.valoracion
    }
    fun getNombreEmpresa() : String{
        return this.nombreEmpresa
    }
    fun getFecha() : String{
        return this.fecha
    }

    override fun toString(): String {
        return "nombre=$nombre, valoracion=$valoracion, nombreEmpresa=$nombreEmpresa, fecha=$fecha"
    }

}