package com.example.proyectofinalandroid.vista

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.proyectofinalandroid.PersonajesAdaptador
import com.example.proyectofinalandroid.PersonajesFavoritosAdaptador
import com.example.proyectofinalandroid.R
import com.example.proyectofinalandroid.modelo.PersonajesFavoritos
import com.example.proyectofinalandroid.pojo.Personajes
import com.example.proyectofinalandroid.viewmodel.PersonajesViewModel
import kotlinx.android.synthetic.main.fragment_fragmento_dos.view.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [FragmentoDos.newInstance] factory method to
 * create an instance of this fragment.
 */
class FragmentoDos : Fragment(), PersonajesFavoritosAdaptador.EliminarDatos {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    var listaPersonajesFavoritos= ArrayList<PersonajesFavoritos>()
    private lateinit var viewAdaptadorFavoritosAdaptador: PersonajesFavoritosAdaptador
    private lateinit var mViewModelFavoritos: PersonajesViewModel  //preguntar como crear un view model de favoritos


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view:View = inflater.inflate(R.layout.fragment_fragmento_dos, container, false)

        //iniciando en recycler view favoritos
        val recyclerView: RecyclerView =view.findViewById(R.id.recyclerFavoritos)
        recyclerView.layoutManager= LinearLayoutManager(context)

        //Iniciando el ViewModel
        mViewModelFavoritos= ViewModelProvider(activity!!).get(PersonajesViewModel::class.java)

        //iniciando el adaptador
        viewAdaptadorFavoritosAdaptador= PersonajesFavoritosAdaptador(listaPersonajesFavoritos,this)

        //pasando adaptador al recycler
        recyclerView.adapter=viewAdaptadorFavoritosAdaptador

        //traer los favoritos de la base de datos
        //preguntar  por el this que da error?
        mViewModelFavoritos.obtenerFavoritosDB().observe(this, Observer {
            viewAdaptadorFavoritosAdaptador.updateData(it)
        })


        //boton que elimina todos los favoritos
        view.botonEliminar.setOnClickListener{
            mViewModelFavoritos.eliminaFavoritosViewModel()
        }

        return view
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment FragmentoDos.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            FragmentoDos().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    override fun eliminarPersonajeFavoritos(personajesFavoritos: PersonajesFavoritos) {
        val eliminarPersonaje= PersonajesFavoritos(char_id = personajesFavoritos.char_id, name = personajesFavoritos.name, status = personajesFavoritos.status, nickname = personajesFavoritos.nickname, portrayed = personajesFavoritos.portrayed, img = personajesFavoritos.img)
        mViewModelFavoritos.borrarUnFavoritos(eliminarPersonaje)
    }
}