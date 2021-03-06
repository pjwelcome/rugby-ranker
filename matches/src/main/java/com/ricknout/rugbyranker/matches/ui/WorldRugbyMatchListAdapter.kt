package com.ricknout.rugbyranker.matches.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.ricknout.rugbyranker.matches.R
import com.ricknout.rugbyranker.matches.vo.WorldRugbyMatch

class WorldRugbyMatchListAdapter(
    private val onPredictClick: (worldRugbyMatch: WorldRugbyMatch) -> Unit
) : ListAdapter<WorldRugbyMatch, WorldRugbyMatchViewHolder>(DIFF_CALLBACK) {

    var worldRugbyRankingsTeamIds: Map<Long, Boolean> = emptyMap()
        set(value) {
            field = value
            notifyItemRangeChanged(0, itemCount)
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
            WorldRugbyMatchViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.list_item_world_rugby_match, parent, false))

    override fun onBindViewHolder(holder: WorldRugbyMatchViewHolder, position: Int) {
        val worldRugbyMatch = getItem(position) ?: return
        val predictable = worldRugbyRankingsTeamIds[worldRugbyMatch.firstTeamId] == true && worldRugbyRankingsTeamIds[worldRugbyMatch.secondTeamId] == true
        holder.bind(worldRugbyMatch, predictable, onPredictClick)
    }

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<WorldRugbyMatch>() {
            override fun areItemsTheSame(oldItem: WorldRugbyMatch, newItem: WorldRugbyMatch) = oldItem.matchId == newItem.matchId
            override fun areContentsTheSame(oldItem: WorldRugbyMatch, newItem: WorldRugbyMatch) = oldItem == newItem
        }
    }
}
