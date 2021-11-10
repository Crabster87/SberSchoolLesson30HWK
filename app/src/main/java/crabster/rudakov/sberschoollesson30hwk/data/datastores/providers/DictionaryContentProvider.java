package crabster.rudakov.sberschoollesson30hwk.data.datastores.providers;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import crabster.rudakov.sberschoollesson30hwk.data.datastores.db.*;

public class DictionaryContentProvider extends ContentProvider {

    private static final UriMatcher uriMatcher;

    private static final int PATH_ROOT = 0;
    private static final int PATH_TRANSLATIONS = 1;

    private DataBaseOpenHelper dataBaseOpenHelper;

    static {
        uriMatcher = new UriMatcher(PATH_ROOT);
        uriMatcher.addURI(DictionaryMetaData.AUTHORITY, DictionaryMetaData.TRANSLATES_CONTENT_PATH, PATH_TRANSLATIONS);
    }

    @Override
    public boolean onCreate() {
        dataBaseOpenHelper = new DataBaseOpenHelper(getContext());
        return true;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {
        switch (uriMatcher.match(uri)) {
            case PATH_TRANSLATIONS:
                return dataBaseOpenHelper.getReadableDatabase().query(DataBaseOpenHelper.DBMetaData.TranslationTableInfo.TABLE_NAME,
                        projection, selection, selectionArgs, null, null, sortOrder);
            default:
                throw new IllegalArgumentException("Unknown URI: " + uri);
        }
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        switch (uriMatcher.match(uri)) {
            case PATH_TRANSLATIONS:
                return DictionaryMetaData.TRANSLATION_CONTENT_TYPE;
            default:
                throw new IllegalArgumentException("Unknown URI: " + uri);
        }
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues contentValues) {
        switch (uriMatcher.match(uri)) {
            case PATH_TRANSLATIONS:
                 dataBaseOpenHelper.getWritableDatabase().insert(DataBaseOpenHelper.DBMetaData.TranslationTableInfo.TABLE_NAME,
                        null, contentValues);
                 break;
            default:
                throw new IllegalArgumentException("Unknown URI: " + uri);
        }
        return null;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String s, @Nullable String[] strings) {
        return 0;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues contentValues, @Nullable String s, @Nullable String[] strings) {
        return 0;
    }

}
