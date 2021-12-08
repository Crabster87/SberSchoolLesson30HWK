package crabster.rudakov.sberschoollesson30hwk.domain.repositories

import android.net.Uri
import io.reactivex.rxjava3.core.Single

interface ILocalImageRepository {

    fun getLocalImages(): Single<List<Uri>>

}