package com.example.proyectofinalandroid.vista

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.proyectofinalandroid.R
import kotlinx.android.synthetic.main.fragment_fragmento_menu.view.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [FragmentoMenu.newInstance] factory method to
 * create an instance of this fragment.
 */
class FragmentoMenu : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

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
        val view:View=inflater.inflate(R.layout.fragment_fragmento_menu, container, false)

        //ir a fragmento 01
        view.irFRAG01.setOnClickListener{

            activity!!.supportFragmentManager.beginTransaction().replace(R.id.contenedorFragmentos,FragmentoUno.newInstance("","")).addToBackStack(null).commit()
        }

        //ir a fragmento 02
        view.irFRAG02.setOnClickListener{

            activity!!.supportFragmentManager.beginTransaction().replace(R.id.contenedorFragmentos,FragmentoDos.newInstance("","")).addToBackStack(null).commit()
        }

        //pensado si se usa este boton???
/*
        //ir a fragmento 03
        view.irFRAG03.setOnClickListener{

            activity!!.supportFragmentManager.beginTransaction().replace(R.id.contenedorFragmentos,FragmentoTres.newInstance("","")).addToBackStack(null).commit()
        }
        */

        return view

    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment FragmentoMenu.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            FragmentoMenu().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}