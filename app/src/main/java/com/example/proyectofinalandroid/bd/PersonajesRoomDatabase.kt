package com.example.proyectofinalandroid.bd

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.proyectofinalandroid.modelo.PersonajesFavoritos
import com.example.proyectofinalandroid.pojo.Personajes

@Database(entities = [Personajes::class, PersonajesFavoritos::class], version = 1,exportSchema = false)
abstract class PersonajesRoomDatabase : RoomDatabase() {

    abstract fun personajesDao() : PersonajesDAO

    abstract fun personajesFavoritosDAO() : PersonajesFavoritosDAO

    companion object {
        @Volatile
        private var INSTANCE: PersonajesRoomDatabase? = null

        fun getDatabase(context: Context): PersonajesRoomDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    PersonajesRoomDatabase::class.java,
                    "personajes_database")
                    .build()
                INSTANCE = instance
                return instance
            }
        }
    }

}