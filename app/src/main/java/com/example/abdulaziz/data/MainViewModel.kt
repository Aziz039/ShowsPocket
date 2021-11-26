package com.example.abdulaziz.data

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(application: Application): AndroidViewModel(application) {
    private val repo: ShowRepo
    private val shows: LiveData<List<ShowEntity>>

    init {
        val showDAO = ShowDatabase.getDatabase(application).ShowDAO()
        repo = ShowRepo(showDAO)
        shows = repo.getAllShows
    }

    fun getAllShows(): LiveData<List<ShowEntity>> {
        return shows
    }

    fun insertShow(show: ShowEntity) {
        CoroutineScope(Dispatchers.IO).launch {
            repo.insertShow(show)
        }
    }

    // not required to update shows
//    fun updateShow(show: ShowEntity) {
//        CoroutineScope(Dispatchers.IO).launch {
//            repo.updateShow(show)
//        }
//    }

    fun deleteShow(id: Int) {
        CoroutineScope(Dispatchers.IO).launch {
            repo.deleteShow(ShowEntity(id, 0,"", "", "", ""))
        }
    }

}