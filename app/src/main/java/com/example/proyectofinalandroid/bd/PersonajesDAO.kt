package com.example.proyectofinalandroid.bd

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.proyectofinalandroid.pojo.Personajes

@Dao
interface PersonajesDAO {

    //Insertar un listado de personajes
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllPersonajes(listPersonajes: List<Personajes>)

    // Insertar 1 personaje
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPersonajes(personajes: Personajes)

    // traer todos los elementos de la tabla
    @Query("SELECT * FROM personajes_table ORDER BY char_id DESC")
    fun getAllListPersonajes() : LiveData<List<Personajes>>

    //Borrarlos todos
    @Query("DELETE FROM personajes_table")
    suspend fun deleteAllPersonajes()


}