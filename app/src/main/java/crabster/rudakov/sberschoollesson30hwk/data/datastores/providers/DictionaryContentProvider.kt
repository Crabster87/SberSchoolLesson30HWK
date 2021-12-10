package crabster.rudakov.sberschoollesson30hwk.data.datastores.providers

import android.content.ContentProvider
import android.content.ContentValues
import android.content.UriMatcher
import android.database.Cursor
import android.net.Uri
import crabster.rudakov.sberschoollesson30hwk.appComponent
import crabster.rudakov.sberschoollesson30hwk.data.datastores.db.DataBaseOpenHelper
import crabster.rudakov.sberschoollesson30hwk.data.datastores.db.DataBaseOpenHelper.Companion.TABLE_NAME
import crabster.rudakov.sberschoollesson30hwk.data.datastores.providers.DictionaryContentProvider.DictionaryMetaData.TRANSLATION_CONTENT_TYPE
import javax.inject.Inject

class DictionaryContentProvider : ContentProvider() {

    @Inject
    lateinit var dataBaseOpenHelper: DataBaseOpenHelper

    companion object {
        private const val PATH_ROOT = 0
        const val PATH_TRANSLATIONS = 1
        val uriMatcher = UriMatcher(PATH_ROOT).also {
            it.addURI(
                DictionaryMetaData.AUTHORITY,
                DictionaryMetaData.TRANSLATES_CONTENT_PATH,
                PATH_TRANSLATIONS
            )
        }
    }

    override fun onCreate(): Boolean {
        context!!.appComponent.dictionaryContentProviderComponent.inject(this)
        return true
    }

    override fun query(
        uri: Uri,
        projection: Array<out String>?,
        selection: String?,
        selectionArgs: Array<out String>?,
        sortOrder: String?
    ): Cursor? {
        return when (uriMatcher.match(uri)) {
            PATH_TRANSLATIONS -> {
                dataBaseOpenHelper.readableDatabase.query(
                    TABLE_NAME,
                    projection,
                    selection,
                    selectionArgs,
                    null,
                    null,
                    sortOrder
                )
            }
            else -> throw IllegalArgumentException("Unknown URI: $uri")
        }
    }

    override fun getType(uri: Uri): String {
        return when (uriMatcher.match(uri)) {
            PATH_TRANSLATIONS -> TRANSLATION_CONTENT_TYPE
            else -> throw IllegalArgumentException("Unknown URI: $uri")
        }
    }

    override fun insert(uri: Uri, contentValues: ContentValues?): Uri? {
        when (uriMatcher.match(uri)) {
            PATH_TRANSLATIONS -> dataBaseOpenHelper.writableDatabase.insert(
                TABLE_NAME,
                null,
                contentValues
            )
            else -> throw IllegalArgumentException("Unknown URI: $uri")
        }
        return null
    }

    override fun delete(uri: Uri, whereClause: String?, whereArgs: Array<out String>?): Int {
        when (uriMatcher.match(uri)) {
            PATH_TRANSLATIONS -> dataBaseOpenHelper.writableDatabase.delete(
                TABLE_NAME,
                whereClause,
                whereArgs
            )
            else -> throw IllegalArgumentException("Unknown URI: $uri")
        }
        return 0
    }

    override fun update(p0: Uri, p1: ContentValues?, p2: String?, p3: Array<out String>?): Int {
        TODO("Not yet implemented")
    }

    object DictionaryMetaData {
        const val AUTHORITY = "crabster.rudakov.sberschoollesson30hwk"
        private val AUTHORITY_URI = Uri.parse("content://$AUTHORITY")
        const val TRANSLATES_CONTENT_PATH = "translates"
        val TRANSLATES_CONTENT_URI: Uri =
            Uri.withAppendedPath(AUTHORITY_URI, TRANSLATES_CONTENT_PATH)
        const val TRANSLATION_CONTENT_TYPE = "$AUTHORITY.$TRANSLATES_CONTENT_PATH"
    }

}

