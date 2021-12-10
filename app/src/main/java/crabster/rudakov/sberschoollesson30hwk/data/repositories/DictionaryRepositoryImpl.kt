package crabster.rudakov.sberschoollesson30hwk.data.repositories

import android.annotation.SuppressLint
import android.content.ContentResolver
import android.content.ContentValues
import android.database.Cursor
import android.provider.BaseColumns._ID
import crabster.rudakov.sberschoollesson30hwk.data.datastores.db.DataBaseOpenHelper.Columns.KEYWORD
import crabster.rudakov.sberschoollesson30hwk.data.datastores.db.DataBaseOpenHelper.Columns.LOGO
import crabster.rudakov.sberschoollesson30hwk.data.datastores.db.DataBaseOpenHelper.Columns.TRANSLATION
import crabster.rudakov.sberschoollesson30hwk.data.datastores.providers.DictionaryContentProvider.DictionaryMetaData.TRANSLATES_CONTENT_URI
import crabster.rudakov.sberschoollesson30hwk.domain.repositories.IDictionaryRepository
import crabster.rudakov.sberschoollesson30hwk.domain.repositories.models.DictionaryItemModel
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

class DictionaryRepositoryImpl(
    private val contentResolver: ContentResolver
) : IDictionaryRepository {

    override fun add(dictionaryItemModel: DictionaryItemModel): Completable {
        return Completable.fromAction {
            val contentValues = ContentValues().apply {
                put(KEYWORD, dictionaryItemModel.keyword)
                put(TRANSLATION, dictionaryItemModel.translation)
                put(LOGO, dictionaryItemModel.logo)
            }
            contentResolver.insert(TRANSLATES_CONTENT_URI, contentValues)
        }
    }

    override fun getList(): Single<List<DictionaryItemModel>> {
        return Single.fromCallable {
            val items = mutableListOf<DictionaryItemModel>()
            val sortOrder = "$_ID DESC"
            contentResolver.query(TRANSLATES_CONTENT_URI, null, null, null, sortOrder)
                ?.use { cursor ->
                    while (cursor.moveToNext()) {
                        items.add(
                            cursor.getDictionaryItemModel()
                        )
                    }
                }
            return@fromCallable items
        }
    }

    @SuppressLint("Range")
    private fun Cursor.getDictionaryItemModel() =
        DictionaryItemModel(
            id = getLong(getColumnIndex(_ID)),
            keyword = getString(getColumnIndex(KEYWORD)),
            translation = getString(getColumnIndex(TRANSLATION)),
            logo = getString(getColumnIndex(LOGO))
        )

    override fun delete(id: Long): Completable {
        return Completable.fromAction {
            contentResolver.delete(TRANSLATES_CONTENT_URI, "$_ID = $id", null)
        }
    }

}