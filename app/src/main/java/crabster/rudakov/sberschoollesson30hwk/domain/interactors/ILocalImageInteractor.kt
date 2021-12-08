package crabster.rudakov.sberschoollesson30hwk.domain.interactors

import android.net.Uri
import io.reactivex.rxjava3.core.Single

interface ILocalImageInteractor {

    fun getLocalImages(): Single<List<Uri>>

}