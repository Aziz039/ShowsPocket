package com.example.abdulaziz

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.abdulaziz.data.MainViewModel
import com.example.abdulaziz.data.ShowEntity
import org.json.JSONObject

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [fragment_db.newInstance] factory method to
 * create an instance of this fragment.
 */
class fragment_db : Fragment() {
    private lateinit var view_db: View

    private lateinit var listOfShows: List<ShowEntity>
    private lateinit var rvAdapter: RecyclerAdapterLocal

    // db
    lateinit var mainViewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        view_db = inflater.inflate(R.layout.fragment_db, container, false)

        rvAdapter = RecyclerAdapterLocal(this)
        val rv_note = view_db.findViewById<RecyclerView>(R.id.rv_showsDB)
        rv_note.adapter = rvAdapter
        rv_note.layoutManager = LinearLayoutManager(this.context)

        // init db
        mainViewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        mainViewModel.getAllShows().observe(viewLifecycleOwner, {
                shows ->  rvAdapter.update(shows)
        })

        return view_db
    }

    fun deleteAShow(showId: Int) {
        mainViewModel.deleteShow(showId)
    }

}