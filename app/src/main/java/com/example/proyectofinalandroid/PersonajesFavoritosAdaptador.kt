package com.example.proyectofinalandroid

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.proyectofinalandroid.modelo.PersonajesFavoritos
import com.example.proyectofinalandroid.pojo.Personajes

class PersonajesFavoritosAdaptador (var listaFavoritos : List<PersonajesFavoritos>, val onClick:EliminarDatos) : RecyclerView.Adapter<PersonajesFavoritosAdaptador.PersonajesFavoritosViewHolder>() {


    fun updateData(listPersonajesFav: List<PersonajesFavoritos>) {
        Log.d("ACTUALIZA LOS FAV", "update ${listPersonajesFav.size}")
        listaFavoritos = listPersonajesFav

        //metodo del recyclerView
        notifyDataSetChanged()
    }
     



    override fun onCreateViewHolder( parent: ViewGroup, viewType: Int ): PersonajesFavoritosViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.contenedor_items_favoritos,parent,false)
        return PersonajesFavoritosViewHolder(view)
    }

    override fun getItemCount(): Int {
        return listaFavoritos.size
    }

    override fun onBindViewHolder(holder: PersonajesFavoritosViewHolder, position: Int) {

        val personajeFavorito = listaFavoritos[position]

        //se le entrega al holder el personaje a mostrar segun la posicion del array
        holder.id.text=personajeFavorito.char_id.toString()
        holder.nombre.text=personajeFavorito.name
        holder.estado.text=personajeFavorito.status
        holder.alias.text=personajeFavorito.nickname
        holder.actor.text=personajeFavorito.portrayed

        //mostrar con PICCASO
        // Picasso.get().load(personajeFavorito.img).into(holder.imagen)

        //mostrar con GLIDE
        val imgURL:String=personajeFavorito.img

        Glide.with(holder.imagen.getContext())
            .load(imgURL)
            .centerCrop()
            .into(holder.imagen)

        //***********ELIMINAR OBJETO FAVORITO DE LA BASE DE DATOS************************//

        holder.imagen.setOnClickListener{

            onClick.eliminarPersonajeFavoritos(personajeFavorito)

            Toast.makeText(holder.imagen.getContext(), "Se ha eliminado a "+personajeFavorito.name +" de tus favoritos" ,   Toast.LENGTH_LONG).show()

        }//onclick

    }//onBindViewHolder

    interface EliminarDatos{

        fun eliminarPersonajeFavoritos(personajesFavoritos: PersonajesFavoritos)
    }



    class PersonajesFavoritosViewHolder(view: View): RecyclerView.ViewHolder(view){

        val id: TextView =itemView.findViewById(R.id.textoIdFavoritos)
        val nombre: TextView =itemView.findViewById(R.id.TextoNombreFavoritos)
        val estado: TextView =itemView.findViewById(R.id.TextoEstadoFavoritos)
        val alias: TextView =itemView.findViewById(R.id.TextoAliasFavoritos)
        val actor: TextView =itemView.findViewById(R.id.TextoActorFavoritos)
        val imagen: ImageView =itemView.findViewById(R.id.imagenCardFavoritos)

    }//class PersonajesFavoritosViewHolder


}//class PersonajesFavoritosAdaptador