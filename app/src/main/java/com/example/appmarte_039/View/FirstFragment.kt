package com.example.appmarte_039.View

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.appmarte_039.R
import com.example.appmarte_039.ViewModel.AdapterMars
import com.example.appmarte_039.ViewModel.MarsViewModel
import com.example.appmarte_039.databinding.FragmentFirstBinding

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

    // comunicar con el ViewModel
    private val viewModel: MarsViewModel by activityViewModels()
    private lateinit var _binding: FragmentFirstBinding

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



      /*  viewModel.allMars.observe(viewLifecycleOwner, Observer { data ->
            if (data != null) {
                _binding.textView.text = data.toString()
                Log.d("ListaVista", "Datos recibidos: $data")
            } else {
                Log.d("ListaVista", "Sin datos")
            }
        })*/

        // referecio  adapter

        val adapter = AdapterMars()
        // buscar el RV

        _binding.rvTerrains.adapter = adapter
        _binding.rvTerrains.layoutManager = GridLayoutManager(context,2)



        viewModel.allMars.observe(viewLifecycleOwner,Observer{

            adapter.update(it)
            Log.d("LISTADOF",it.toString())
        })


     adapter.selectedTerrain.observe(viewLifecycleOwner,Observer{

         it.let {

             viewModel.selected(it)
             findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
         }
     })

    }
}
