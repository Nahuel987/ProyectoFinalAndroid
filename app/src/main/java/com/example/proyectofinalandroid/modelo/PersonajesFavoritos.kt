package com.example.proyectofinalandroid.modelo

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favoritos_table")
class PersonajesFavoritos (@PrimaryKey val char_id:Int, val name:String, val status:String, val nickname:String, val portrayed:String, val img:String) {


}