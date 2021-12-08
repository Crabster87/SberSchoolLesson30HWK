package crabster.rudakov.sberschoollesson30hwk.domain.interactors

import crabster.rudakov.sberschoollesson30hwk.domain.models.DictionaryItem
import crabster.rudakov.sberschoollesson30hwk.domain.repositories.models.DictionaryItemModel
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

interface IDictionaryInteractor {

    fun add(dictionaryItem: DictionaryItem): Completable

    fun getList(): Single<List<DictionaryItemModel>>

    fun delete(id: Long): Completable

}