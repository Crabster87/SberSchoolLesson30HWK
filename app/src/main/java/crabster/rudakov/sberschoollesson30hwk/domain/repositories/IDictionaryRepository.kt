package crabster.rudakov.sberschoollesson30hwk.domain.repositories

import crabster.rudakov.sberschoollesson30hwk.domain.repositories.models.DictionaryItemModel
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

interface IDictionaryRepository {

    fun add(dictionaryItemModel: DictionaryItemModel): Completable

    fun getList(): Single<List<DictionaryItemModel>>

    fun delete(id: Long): Completable

}