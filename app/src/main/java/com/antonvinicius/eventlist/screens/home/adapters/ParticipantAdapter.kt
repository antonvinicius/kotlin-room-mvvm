package com.antonvinicius.eventlist.screens.home.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.antonvinicius.eventlist.databinding.ItemParticipantBinding
import com.antonvinicius.eventlist.models.Participant

class ParticipantAdapter(private val onClickParticipant: (Participant) -> Unit) : RecyclerView.Adapter<ParticipantViewHolder>() {
    private var participantList = emptyList<Participant>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ParticipantViewHolder {
        val binding = ItemParticipantBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ParticipantViewHolder(binding, onClickParticipant)
    }

    override fun onBindViewHolder(holder: ParticipantViewHolder, position: Int) {
        val currentParticipant = participantList[position]
        holder.bind(currentParticipant)
    }

    override fun getItemCount(): Int {
        return participantList.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setParticipants(participants: List<Participant>) {
        participantList = participants
        notifyDataSetChanged()
    }
}

class ParticipantViewHolder(private val binding: ItemParticipantBinding, private val onClickParticipant: (Participant) -> Unit) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(participant: Participant) {
        binding.participant = participant

        binding.deleteParticipantButton.setOnClickListener {
            onClickParticipant(participant)
        }
    }
}