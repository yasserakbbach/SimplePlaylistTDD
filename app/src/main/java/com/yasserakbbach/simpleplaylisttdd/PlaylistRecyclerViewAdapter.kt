package com.yasserakbbach.simpleplaylisttdd

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.yasserakbbach.simpleplaylisttdd.databinding.PlaylistItemBinding

class PlaylistRecyclerViewAdapter(
    private val listener: PlaylistViewHolder.OnItemClickListener,
) : ListAdapter<Playlist, PlaylistRecyclerViewAdapter.PlaylistViewHolder>(diffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlaylistViewHolder =
        PlaylistViewHolder(
            PlaylistItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false,
            ),
            listener
        )

    override fun onBindViewHolder(holder: PlaylistViewHolder, position: Int) {
        getItem(position)?.let { holder.bind(it) }
    }

    class PlaylistViewHolder(
        private val binding: PlaylistItemBinding,
        private val listener: OnItemClickListener,
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(playlist: Playlist) {
            binding.apply {
                imagePlaylist.setImageResource(playlist.image)
                textPlaylistName.text = playlist.name
                textPlaylistCategory.text = playlist.category
                root.setOnClickListener {
                    listener.onPlaylistClick(playlist.id)
                }
            }
        }

        interface OnItemClickListener {
            fun onPlaylistClick(id: String)
        }
    }

    private companion object {
        val diffCallback: DiffUtil.ItemCallback<Playlist> =
            object : DiffUtil.ItemCallback<Playlist>() {
                override fun areItemsTheSame(oldItem: Playlist, newItem: Playlist): Boolean =
                    oldItem.id == newItem.id

                override fun areContentsTheSame(oldItem: Playlist, newItem: Playlist): Boolean =
                    oldItem == newItem
            }
    }

}