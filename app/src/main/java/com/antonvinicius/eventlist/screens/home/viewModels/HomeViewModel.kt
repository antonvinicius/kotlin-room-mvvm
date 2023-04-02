package com.antonvinicius.eventlist.screens.home.viewModels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.antonvinicius.eventlist.errors.AppError
import com.antonvinicius.eventlist.models.Participant

class HomeViewModel : ViewModel() {
    val newParticipantName = MutableLiveData<String>()
    val participants = MutableLiveData<MutableList<Participant>>()

    private fun isNameInvalidOrIncomplete(): Boolean {
        return newParticipantName.value == null || newParticipantName.value?.isBlank() == true
    }

    fun addNewParticipant() {
        try {
            if (isNameInvalidOrIncomplete())
                throw AppError("Nome do participante invalido ou incompleto. Tente novamente")

            newParticipantName.value?.let { participantName ->
                val participant = Participant(participantName)
                val currentParticipants = participants.value.orEmpty().toMutableList()
                currentParticipants.add(participant)

                participants.value = currentParticipants
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
            val currentParticipants = participants.value.orEmpty().toMutableList()
            currentParticipants.remove(participant)

            participants.value = currentParticipants
        } catch (error: Exception) {
            throw error
        }
    }
}