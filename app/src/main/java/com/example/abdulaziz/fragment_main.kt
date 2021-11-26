package com.example.abdulaziz

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.findNavController

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [fragment_main.newInstance] factory method to
 * create an instance of this fragment.
 */
class fragment_main : Fragment() {
    private lateinit var mainView: View
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        mainView =  inflater.inflate(R.layout.fragment_main, container, false)

        mainView.findViewById<Button>(R.id.bt_api).setOnClickListener {
            mainView.findNavController().navigate(R.id.fragment_api)
        }

        mainView.findViewById<Button>(R.id.bt_db).setOnClickListener {
            mainView.findNavController().navigate(R.id.fragment_db)
        }

        return mainView
    }

}