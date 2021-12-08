package crabster.rudakov.sberschoollesson30hwk.presentation.recyclerview.dictionary.diffutil

import androidx.recyclerview.widget.DiffUtil
import crabster.rudakov.sberschoollesson30hwk.domain.repositories.models.DictionaryItemModel


/**
 * Diff Util for [DictionaryItemModel] class
 */
class DictionaryItemDiffUtils(
) : DiffUtil.ItemCallback<DictionaryItemModel>() {

    override fun areItemsTheSame(
        oldItem: DictionaryItemModel,
        newItem: DictionaryItemModel
    ): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(
        oldItem: DictionaryItemModel,
        newItem: DictionaryItemModel
    ): Boolean {
        return oldItem.keyword == newItem.keyword &&
                oldItem.translation == newItem.translation &&
                oldItem.logo == newItem.logo
    }

}