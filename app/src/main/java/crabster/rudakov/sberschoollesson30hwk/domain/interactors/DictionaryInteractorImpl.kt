package crabster.rudakov.sberschoollesson30hwk.domain.interactors

import crabster.rudakov.sberschoollesson30hwk.domain.converter.IDictionaryConverter
import crabster.rudakov.sberschoollesson30hwk.domain.models.DictionaryItem
import crabster.rudakov.sberschoollesson30hwk.domain.repositories.IDictionaryRepository
import crabster.rudakov.sberschoollesson30hwk.domain.repositories.models.DictionaryItemModel
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

class DictionaryInteractorImpl(
    private val repository: IDictionaryRepository,
    private val converter: IDictionaryConverter
) : IDictionaryInteractor {

    override fun add(dictionaryItem: DictionaryItem): Completable {
        return repository.add(converter.convert(dictionaryItem))
    }

    override fun getList(): Single<List<DictionaryItemModel>> {
        return repository.getList()
    }

    override fun delete(id: Long): Completable {
        return repository.delete(id)
    }

}