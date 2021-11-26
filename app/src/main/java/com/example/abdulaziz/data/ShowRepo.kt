package com.example.abdulaziz.data

import androidx.lifecycle.LiveData


class ShowRepo ( private val ShowDAO: ShowDAO) {
    val getAllShows: LiveData<List<ShowEntity>> = ShowDAO.getAllShows()

    suspend fun insertShow(ShowEntity: ShowEntity) {
        ShowDAO.insertShow(ShowEntity)
    }

    suspend fun updateShow(ShowEntity: ShowEntity) {
        ShowDAO.updateShow(ShowEntity)
    }

    suspend fun deleteShow(ShowEntity: ShowEntity) {
        ShowDAO.deleteShow(ShowEntity)
    }
}