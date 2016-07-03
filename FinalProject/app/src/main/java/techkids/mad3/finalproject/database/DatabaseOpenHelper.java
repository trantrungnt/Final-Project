package techkids.mad3.finalproject.database;

import android.content.Context;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

import techkids.mad3.finalproject.constants.Helper;

/**
 * Created by TrungNT on 7/4/2016.
 */
public class DatabaseOpenHelper extends SQLiteAssetHelper {
    public DatabaseOpenHelper(Context context) {
        super(context, Helper.DATABASE_NAME, null, Helper.DATABASE_VERSION);
    }
}
