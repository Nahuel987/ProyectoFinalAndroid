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
import com.example.proyectofinalandroid.R
import com.example.proyectofinalandroid.modelo.PersonajesFavoritos
import com.example.proyectofinalandroid.pojo.Personajes
import com.example.proyectofinalandroid.viewmodel.PersonajesViewModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_fragmento_uno.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [FragmentoUno.newInstance] factory method to
 * create an instance of this fragment.
 */
class FragmentoUno : Fragment(),PersonajesAdaptador.PasarDatos {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    var listaPersonajes= ArrayList<Personajes>()
    private lateinit var viewAdaptador: PersonajesAdaptador
    private lateinit var mViewModel: PersonajesViewModel


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

        val view:View = inflater.inflate(R.layout.fragment_fragmento_uno, container, false)

        // Inflate the layout for this fragment

         val recyclerView: RecyclerView =view.findViewById(R.id.recycler)

        recyclerView.layoutManager= LinearLayoutManager(context)

        //Iniciando el ViewModel
        //mViewModel = ViewModelProvider(this).get(PersonajesViewModel::class.java)

        mViewModel = ViewModelProvider(activity!!).get(PersonajesViewModel::class.java)

        //recibe respuesta de retrofit y se ingresan datos a la database
        mViewModel.fetchFromServer()

        //iniciando el adaptador
        viewAdaptador= PersonajesAdaptador(listaPersonajes,this)

        //pasando adaptador al recycler
        recyclerView.adapter=viewAdaptador

        //preguntar  por el this que da error?
        mViewModel.getDataFromDB().observe(this, Observer {
            viewAdaptador.updateData(it)
        })

        return view
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment FragmentoUno.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            FragmentoUno().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    override fun pasarDatos(personajes: Personajes) {
        val personajesFavoritos = PersonajesFavoritos(char_id = personajes.char_id, name = personajes.name, status = personajes.status, nickname = personajes.nickname, portrayed = personajes.portrayed, img = personajes.img)
        mViewModel.insertFavoritos(personajesFavoritos)
    }
}//fragmento