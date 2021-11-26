package com.example.abdulaziz

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.abdulaziz.data.MainViewModel
import com.example.abdulaziz.data.ShowEntity
import com.google.android.material.textfield.TextInputEditText
import com.google.gson.Gson
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import org.json.JSONArray
import org.json.JSONObject
import java.net.URL
import android.content.Context.INPUT_METHOD_SERVICE

import androidx.core.content.ContextCompat.getSystemService

import android.view.inputmethod.InputMethodManager
import androidx.core.content.ContextCompat


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [fragment_api.newInstance] factory method to
 * create an instance of this fragment.
 */
class fragment_api : Fragment() {

    private lateinit var viewApi: View
    private lateinit var showNameToSearch: String
    private lateinit var listOfShows: ArrayList<JSONObject>

    private lateinit var rvAdapter: RecyclerAdapter

    // db
    lateinit var mainViewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        viewApi = inflater.inflate(R.layout.fragment_api, container, false)

        // init db
        mainViewModel = ViewModelProvider(this).get(MainViewModel::class.java)


        viewApi.findViewById<Button>(R.id.bt_search).setOnClickListener {
            handle_search()
        }

        rvAdapter = RecyclerAdapter(this)

        val rv_note = viewApi.findViewById<RecyclerView>(R.id.rv_shows)
        rv_note.adapter = rvAdapter
        rv_note.layoutManager = LinearLayoutManager(this.context)



        return viewApi
    }
    private fun addToUI() {
        rvAdapter.update(listOfShows)
        viewApi.findViewById<TextInputEditText>(R.id.ti_show).setText("")
        viewApi.findViewById<TextInputEditText>(R.id.ti_show).clearFocus()
    }

    private fun handle_search() {
        val showName = viewApi.findViewById<TextInputEditText>(R.id.ti_show).text.toString()

        if (showName.isNullOrBlank()) {
            Toast.makeText(this.context, "Enter a show name..", Toast.LENGTH_SHORT).show()
        } else {
            showNameToSearch = showName
            listOfShows = ArrayList<JSONObject>()
            CoroutineScope(IO).launch {
                fetchData()
                showNameToSearch = ""
                Handler(Looper.getMainLooper()).post(Runnable {
                    addToUI()
                })
            }
        }

    }

    private fun fetchData() {
        var response = ""
        try {
            response = URL("https://api.tvmaze.com/search/shows?q=$showNameToSearch").readText()
            if (response != null) {
                //val resFiltered = response.substring(1, response.lastIndexOf("]"))
                val resObject = JSONArray(response)
                for (i in 0 until resObject.length()) {
                    listOfShows.add(resObject[i] as JSONObject)
                }
            }
            Log.d("Logger_api", "Data fetched successfully")

        } catch (e: Exception) {
            Log.d("Logger_api", "Exception: $e")
        }
    }

    fun addToDB(showId: Int, showName: String, showSummary: String, showLanguage: String, showImageURL: String) {
        mainViewModel.insertShow(ShowEntity(0, showId, showName, showSummary, showLanguage, showImageURL))
    }
}