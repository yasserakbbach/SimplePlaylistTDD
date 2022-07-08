package com.yasserakbbach.simpleplaylisttdd

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.yasserakbbach.simpleplaylisttdd.databinding.FragmentPlaylistBinding

class PlaylistFragment : Fragment() {

    private var binding: FragmentPlaylistBinding? = null
    lateinit var viewModel: PlaylistViewModel
    lateinit var viewModelFactory: PlaylistViewModelFactory
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
        .also { initViewModel() }
        .root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.initPlaylistRV()
        observePlaylists()
    }

    private fun FragmentPlaylistBinding.initPlaylistRV() {
        recyclerViewPlaylists.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = playlistAdapter
        }
    }

    private fun initViewModel() {
        viewModelFactory = PlaylistViewModelFactory()
        viewModel = ViewModelProvider(this, viewModelFactory)[PlaylistViewModel::class.java]
    }

    private fun observePlaylists() {
        viewModel.playlists.observe(viewLifecycleOwner) { result ->
            result.onSuccess {
                playlistAdapter.submitList(it)
            }.onFailure {
                Toast.makeText(requireContext(), R.string.error_occurred, Toast.LENGTH_LONG).show()
            }
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