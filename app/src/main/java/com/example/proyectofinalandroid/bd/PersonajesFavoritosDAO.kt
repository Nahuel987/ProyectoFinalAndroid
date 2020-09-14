package com.example.proyectofinalandroid.bd

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.proyectofinalandroid.modelo.PersonajesFavoritos

@Dao
interface PersonajesFavoritosDAO {

    //Insertar un listado de post
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllFavoritos(listFavoritos: List<PersonajesFavoritos>)

    // Insertar 1 favoritos
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavoritos(favoritos: PersonajesFavoritos)

    // traer todos los elementos de la tabla
    @Query("SELECT * FROM favoritos_table ORDER BY char_id DESC")
    fun getAllFavoritosList() : LiveData<List<PersonajesFavoritos>>

    //Borrarlos todos
    @Query("DELETE FROM favoritos_table")
    suspend fun deleteAllFavoritos()

    //Borrar un favorito
    @Delete
    suspend fun borrarUnFavorito(favoritos: PersonajesFavoritos)

}