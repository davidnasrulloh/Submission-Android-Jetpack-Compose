package com.davidnasrulloh.ptnindo.ui.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.davidnasrulloh.ptnindo.data.UnivRepository
import com.davidnasrulloh.ptnindo.ui.screen.beranda.BerandaViewModel
import com.davidnasrulloh.ptnindo.ui.screen.detailuniv.DetailUnivViewModel
import com.davidnasrulloh.ptnindo.ui.screen.myfavorite.MyFavoriteViewModel

class ViewModelFactory(private val repository: UnivRepository) :
    ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(BerandaViewModel::class.java)) {
            return BerandaViewModel(repository) as T
        }
        if (modelClass.isAssignableFrom(MyFavoriteViewModel::class.java)) {
            return MyFavoriteViewModel(repository) as T
        }
        if (modelClass.isAssignableFrom(DetailUnivViewModel::class.java)) {
            return DetailUnivViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
    }
}