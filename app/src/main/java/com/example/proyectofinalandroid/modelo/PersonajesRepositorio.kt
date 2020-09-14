package com.example.proyectofinalandroid.modelo

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import com.example.proyectofinalandroid.bd.PersonajesRoomDatabase
import com.example.proyectofinalandroid.pojo.Personajes
import com.example.proyectofinalandroid.remoto.RetrofitClient
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PersonajesRepositorio (context: Context) {

    private val tag = "PersonajesRepositorio"

    //esto viene  de la Base de datos
    private val db: PersonajesRoomDatabase = PersonajesRoomDatabase.getDatabase(context)
    private val personajesList = db.personajesDao().getAllListPersonajes()
    private val favoritosList =  db.personajesFavoritosDAO().getAllFavoritosList()



    //**************PERSONAJES**************//

    //pasa la lista de personajes del LiveData al ViewModel
    fun passLiveDataToViewModel() : LiveData<List<Personajes>> {
        return personajesList
    }




    //**************FAVORITOS**************//

    //ingresar un favoritos en la bd
    suspend fun insertFavoritos(favoritos: PersonajesFavoritos){
         db.personajesFavoritosDAO().insertFavoritos(favoritos)
    }

    //obtener lista de personajes favoritos
    //preguntar por que no debe ser suspend
     fun pasarListaFavoritosToViewModel():LiveData<List<PersonajesFavoritos>> {
        return favoritosList
    }

    //eliminar todos los favoritos de la lista
    suspend fun eliminaFavoritos(){
        return db.personajesFavoritosDAO().deleteAllFavoritos()
    }

    suspend fun eliminaUnFavoritos(favoritos: PersonajesFavoritos){
         db.personajesFavoritosDAO().borrarUnFavorito(favoritos)
    }



    //**************RETROFIT**************//

    // esto hace la llamada a retrofit
    fun fetchDataFromServer() {
        val service = RetrofitClient.retrofitInstance()
        val call = service.getAllPersonajes()

        call.enqueue(object : Callback<List<Personajes>> {
            override fun onResponse(call: Call<List<Personajes>>, response: Response<List<Personajes>>) {
                Log.d(tag, response.body().toString())
                CoroutineScope(Dispatchers.IO).launch {
                    response.body()?.let { db.personajesDao().insertAllPersonajes(it) }
                }
            }
            override fun onFailure(call: Call<List<Personajes>>, t: Throwable) {
                Log.d(tag, t.message.toString())
            }
        })

    }

}