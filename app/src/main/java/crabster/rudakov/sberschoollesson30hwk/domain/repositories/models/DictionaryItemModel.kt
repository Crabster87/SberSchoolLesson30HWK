package crabster.rudakov.sberschoollesson30hwk.domain.repositories.models

/**
 * Model class
 */
data class DictionaryItemModel(
    val id: Long = 0,
    val keyword: String,
    val translation: String,
    val logo: String
)
