package crabster.rudakov.sberschoollesson30hwk.presentation.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import crabster.rudakov.sberschoollesson30hwk.domain.interactors.IDictionaryInteractor
import crabster.rudakov.sberschoollesson30hwk.domain.interactors.ILocalImageInteractor


class ViewModelFactory(
    application: Application,
    private val dictionaryInteractor: IDictionaryInteractor,
    private val localImageInteractor: ILocalImageInteractor
) :
    ViewModelProvider.AndroidViewModelFactory(application) {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return SharedViewModel(dictionaryInteractor, localImageInteractor) as T
    }

}