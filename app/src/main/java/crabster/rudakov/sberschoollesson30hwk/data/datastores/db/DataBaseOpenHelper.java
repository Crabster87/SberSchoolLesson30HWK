package crabster.rudakov.sberschoollesson30hwk.data.datastores.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

import androidx.annotation.Nullable;

public class DataBaseOpenHelper extends SQLiteOpenHelper {

    public static final String CREATE_TABLE_SQL = "CREATE TABLE " + DBMetaData.TranslationTableInfo.TABLE_NAME
            + " ("
            + DBMetaData.TranslationTableInfo._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + DBMetaData.TranslationTableInfo.COLUMN_KEYWORD + " TEXT, "
            + DBMetaData.TranslationTableInfo.COLUMN_TRANSLATION + " TEXT"
            + " )";

    public DataBaseOpenHelper(@Nullable Context context) {
        super(context, DBMetaData.DB_NAME, null, DBMetaData.DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_TABLE_SQL);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
    }

    public static class DBMetaData {

        public static final String DB_NAME = "dictionary.db";
        public static final int DB_VERSION = 1;

        public static class TranslationTableInfo implements BaseColumns {

            public static String TABLE_NAME = "translations";
            public static String COLUMN_KEYWORD = "keyword";
            public static String COLUMN_TRANSLATION = "translation";
        }
    }

}
