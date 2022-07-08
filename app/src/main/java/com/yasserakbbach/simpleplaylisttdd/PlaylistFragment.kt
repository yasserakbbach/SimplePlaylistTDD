package com.yasserakbbach.simpleplaylisttdd

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.yasserakbbach.simpleplaylisttdd.databinding.FragmentPlaylistBinding

class PlaylistFragment : Fragment() {

    private var binding: FragmentPlaylistBinding? = null
    private val playlistCallback: PlaylistRecyclerViewAdapter.PlaylistViewHolder.OnItemClickListener = object :
        PlaylistRecyclerViewAdapter.PlaylistViewHolder.OnItemClickListener {
        override fun onPlaylistClick(id: String) {
            TODO("Not yet implemented")
        }
    }
    private val playlistAdapter: PlaylistRecyclerViewAdapter by lazy {
        PlaylistRecyclerViewAdapter(playlistCallback)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = FragmentPlaylistBinding.inflate(layoutInflater, container, false)
        .also { binding = it }
        .root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.initPlaylistRV()
    }

    private fun FragmentPlaylistBinding.initPlaylistRV() {
        recyclerViewPlaylists.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = playlistAdapter
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            PlaylistFragment()
    }
}