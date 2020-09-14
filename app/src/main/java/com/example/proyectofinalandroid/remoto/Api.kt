package com.example.proyectofinalandroid.remoto

import com.example.proyectofinalandroid.pojo.Personajes
import retrofit2.Call
import retrofit2.http.*

interface Api {

    //trae un personaje aleatorio
    @GET("characters")
    fun getAllPersonajes(): Call<List<Personajes>>

}