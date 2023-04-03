package com.antonvinicius.eventlist.screens.home.viewModels

import androidx.lifecycle.*
import com.antonvinicius.eventlist.data.dao.ParticipantDao
import com.antonvinicius.eventlist.errors.AppError
import com.antonvinicius.eventlist.models.Participant
import kotlinx.coroutines.launch

class HomeViewModel(private val participantDao: ParticipantDao) : ViewModel() {
    val newParticipantName = MutableLiveData<String>()
    val participants: LiveData<List<Participant>> = participantDao.getAllParticipants()

    private fun isNameInvalidOrIncomplete(): Boolean {
        return newParticipantName.value == null || newParticipantName.value?.isBlank() == true
    }

    fun addNewParticipant() {
        try {
            if (isNameInvalidOrIncomplete())
                throw AppError("Nome do participante invalido ou incompleto. Tente novamente")

            newParticipantName.value?.let { participantName ->
                val participant = Participant(participantName)

                viewModelScope.launch {
                    participantDao.insertParticipant(participant)
                }

                newParticipantName.value = ""
            }
        } catch (error: AppError) {
            throw error
        } catch (error: Exception) {
            throw error
        }
    }

    fun removeParticipant(participant: Participant) {
        try {
            viewModelScope.launch {
                participantDao.deleteParticipant(participant)
            }
        } catch (error: Exception) {
            throw error
        }
    }
}

class HomeViewModelFactory(private val participantDao: ParticipantDao) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return HomeViewModel(participantDao) as T
    }
}