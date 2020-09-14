package com.example.proyectofinalandroid

import android.content.Context
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
import com.squareup.picasso.Picasso

class PersonajesAdaptador (var lista : List<Personajes>, val onClick:PasarDatos):RecyclerView.Adapter<PersonajesAdaptador.PersonajesViewHolder>(){


    fun updateData(listPersonajes: List<Personajes>) {
        Log.d("ACTUALIZA", "update ${listPersonajes.size}")
        lista = listPersonajes

        //metodo del recyclerView
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PersonajesViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.contenedor_items,parent,false)
        return PersonajesViewHolder(view)
    }

    override fun getItemCount(): Int {
        return lista.size
    }

    override fun onBindViewHolder(holder: PersonajesViewHolder, position: Int) {

        val personaje = lista[position]

        //se le entrega al holder el personaje a mostrar segun la posicion del array
        holder.id.text=personaje.char_id.toString()
        holder.nombre.text=personaje.name
        holder.estado.text=personaje.status
        holder.alias.text=personaje.nickname
        holder.actor.text=personaje.portrayed

        //mostrar con PICCASO
       // Picasso.get().load(personaje.img).into(holder.imagen)

        //mostrar con GLIDE
        val imgURL:String=personaje.img

        Glide.with(holder.imagen.getContext())
            .load(imgURL)
            .centerCrop()
            .placeholder(R.drawable.abejabarril)
            .into(holder.imagen)


        //***********INSERTAR OBJETO FAVORITO EN LA BASE DE DATOS************************//
        holder.imagen.setOnClickListener {

            onClick.pasarDatos(personaje)

            Toast.makeText(holder.imagen.getContext(), "Se ingresa a "+personaje.name +" en favoritos" ,   Toast.LENGTH_LONG).show()

        }//onclick


    }//onBindViewHolder


    class PersonajesViewHolder(view:View):RecyclerView.ViewHolder(view){

        val id:TextView=itemView.findViewById(R.id.textoId)
        val nombre:TextView=itemView.findViewById(R.id.TextoNombre)
        val estado:TextView=itemView.findViewById(R.id.TextoEstado)
        val alias:TextView=itemView.findViewById(R.id.TextoAlias)
        val actor:TextView=itemView.findViewById(R.id.TextoActor)
        val imagen:ImageView=itemView.findViewById(R.id.imagenCard)

    }//class viewHolder

    interface PasarDatos{

        fun pasarDatos(personajes: Personajes)

    }

}//class adaptador