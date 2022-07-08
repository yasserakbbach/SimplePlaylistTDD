package com.yasserakbbach.simpleplaylisttdd

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class PlaylistViewModelFactory : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T =
        when {
            modelClass.isAssignableFrom(PlaylistViewModel::class.java) -> PlaylistViewModel() as T
            else -> throw IllegalArgumentException("Unknown ViewModel class")
        }
}