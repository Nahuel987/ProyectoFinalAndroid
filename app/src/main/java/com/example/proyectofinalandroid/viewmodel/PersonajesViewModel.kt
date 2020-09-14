package com.example.proyectofinalandroid.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.proyectofinalandroid.modelo.PersonajesFavoritos
import com.example.proyectofinalandroid.modelo.PersonajesRepositorio
import com.example.proyectofinalandroid.pojo.Personajes
import kotlinx.coroutines.launch

class PersonajesViewModel (application: Application): AndroidViewModel(application)  {

    private val repository =  PersonajesRepositorio(application)
    private val personajesList = repository.passLiveDataToViewModel()
    private val personajesFavoritosList= repository.pasarListaFavoritosToViewModel()


    //**************PERSONAJES**************//

    //obtener del servidor usando retrofit
    fun fetchFromServer() {
        repository.fetchDataFromServer()
    }

    //obtener lista de personajes de la base de datos
    fun getDataFromDB(): LiveData<List<Personajes>> {
        return personajesList
    }



    //**************FAVORITOS**************//

    //deber ir aqui??? o en personajes favoritos View Model
    //inserta a favoritos
    fun insertFavoritos(favoritos: PersonajesFavoritos)=viewModelScope.launch{
        repository.insertFavoritos(favoritos)

    }

    fun obtenerFavoritosDB():LiveData<List<PersonajesFavoritos>>{
        return personajesFavoritosList
    }

    fun eliminaFavoritosViewModel()=viewModelScope.launch{
        repository.eliminaFavoritos()
    }

    fun borrarUnFavoritos(favoritos: PersonajesFavoritos)=viewModelScope.launch {
        repository.eliminaUnFavoritos(favoritos)
    }

}