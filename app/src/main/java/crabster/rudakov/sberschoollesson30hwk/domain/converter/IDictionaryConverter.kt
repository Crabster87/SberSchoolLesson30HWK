package crabster.rudakov.sberschoollesson30hwk.domain.converter

import crabster.rudakov.sberschoollesson30hwk.domain.models.DictionaryItem
import crabster.rudakov.sberschoollesson30hwk.domain.repositories.models.DictionaryItemModel


/**
 * Converter interface for model classes [DictionaryItem] and [DictionaryItemModel]
 */
interface IDictionaryConverter {

    fun convert(dictionaryItem: DictionaryItem): DictionaryItemModel

    fun reverse(dictionaryItemModel: DictionaryItemModel): DictionaryItem

    fun reverseList(dictionaryItemModels: List<DictionaryItemModel>): List<DictionaryItem>

}