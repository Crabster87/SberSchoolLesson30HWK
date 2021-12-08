package crabster.rudakov.sberschoollesson30hwk.domain.converter

import crabster.rudakov.sberschoollesson30hwk.domain.models.DictionaryItem
import crabster.rudakov.sberschoollesson30hwk.domain.repositories.models.DictionaryItemModel

/**
 * Converter for model classes [DictionaryItem] and [DictionaryItemModel]
 */
class DictionaryConverterImpl : IDictionaryConverter {

    override fun convert(dictionaryItem: DictionaryItem) =
        DictionaryItemModel(
            keyword = dictionaryItem.keyword,
            translation = dictionaryItem.translation,
            logo = dictionaryItem.logo
        )


    override fun reverse(dictionaryItemModel: DictionaryItemModel) =
        DictionaryItem(
            keyword = dictionaryItemModel.keyword,
            translation = dictionaryItemModel.translation,
            logo = dictionaryItemModel.logo
        )

    override fun reverseList(dictionaryItemModels: List<DictionaryItemModel>) =
        dictionaryItemModels.map { reverse(it) }.toList()

}