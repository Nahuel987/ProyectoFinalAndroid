package com.example.proyectofinalandroid

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.proyectofinalandroid.modelo.PersonajesFavoritos
import com.example.proyectofinalandroid.pojo.Personajes
import com.example.proyectofinalandroid.viewmodel.PersonajesViewModel
import com.example.proyectofinalandroid.vista.FragmentoMenu


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager.beginTransaction().add(R.id.contenedorFragmentos,FragmentoMenu.newInstance("FG","DESDE FRGA MENU")).commit()

    }//on create

}//class