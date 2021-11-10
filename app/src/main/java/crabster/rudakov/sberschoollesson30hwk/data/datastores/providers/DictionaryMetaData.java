package crabster.rudakov.sberschoollesson30hwk.data.datastores.providers;

import android.net.Uri;

public class DictionaryMetaData {

    // content://<authority>/<data_type>/<id>
    // content://ru.bilchuk.wordtranslate/translates/2

    public static final String AUTHORITY = "ru.bilchuk.wordtranslate";
    public static final Uri AUTHORITY_URI = Uri.parse("content://" + AUTHORITY);
    public static final String TRANSLATES_CONTENT_PATH = "translates";
    public static final Uri TRANSLATES_CONTENT_URI = Uri.withAppendedPath(AUTHORITY_URI, TRANSLATES_CONTENT_PATH);
    public static final String TRANSLATION_CONTENT_TYPE = AUTHORITY + "." + TRANSLATES_CONTENT_PATH;

}
