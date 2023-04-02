package com.antonvinicius.eventlist.screens.home

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.antonvinicius.eventlist.databinding.ActivityHomeBinding
import com.antonvinicius.eventlist.errors.AppError
import com.antonvinicius.eventlist.models.Participant
import com.antonvinicius.eventlist.screens.home.adapters.ParticipantAdapter
import com.antonvinicius.eventlist.screens.home.viewModels.HomeViewModel
import com.antonvinicius.eventlist.util.Alert

class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding
    private lateinit var viewModel: HomeViewModel
    private lateinit var adapter: ParticipantAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this)[HomeViewModel::class.java]

        adapter = ParticipantAdapter {
            handleRemoveParticipant(it)
        }

        binding.apply {
            lifecycleOwner = this@HomeActivity
            viewModel = this@HomeActivity.viewModel
            adapter = this@HomeActivity.adapter
            activity = this@HomeActivity
        }

        supportActionBar?.hide()
    }

    override fun onStart() {
        super.onStart()

        viewModel.participants.observe(this) { participants ->
            adapter.setParticipants(participants)
        }
    }

    private fun handleRemoveParticipant(participant: Participant) {
        try {
            viewModel.removeParticipant(participant)
        } catch (error: Exception) {
            Alert.show(this, "Remover participante", "Não foi possível remover o participante")
        }
    }

    fun handleAddNewParticipant() {
        try {
            viewModel.addNewParticipant()
        } catch (error: AppError) {
            Alert.show(this, "Novo participante", error.message)
        } catch (error: Exception) {
            Alert.show(this, "Novo participante", "Não foi possível adicionar um novo participante")
        }
    }
}