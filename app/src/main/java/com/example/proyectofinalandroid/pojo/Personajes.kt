package com.example.proyectofinalandroid.pojo

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "personajes_table")
data class Personajes (@PrimaryKey val char_id:Int, val name:String, val status:String, val nickname:String, val portrayed:String, val img:String) {

}