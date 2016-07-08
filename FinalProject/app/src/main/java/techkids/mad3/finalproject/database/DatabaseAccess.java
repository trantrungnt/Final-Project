package techkids.mad3.finalproject.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import techkids.mad3.finalproject.models.Question;

/**
 * Created by TrungNT on 7/4/2016.
 */
public class DatabaseAccess {
    private SQLiteOpenHelper openHelper;
    private SQLiteDatabase database;
    private static DatabaseAccess instance;

    /**
     * Private constructor to aboid object creation from outside classes.
     *
     * @param context
     */
    private DatabaseAccess(Context context) {
        this.openHelper = new DatabaseOpenHelper(context);
    }

    /**
     * Return a singleton instance of DatabaseAccess.
     *
     * @param context the Context
     * @return the instance of DabaseAccess
     */
    public static DatabaseAccess getInstance(Context context) {
        if (instance == null) {
            instance = new DatabaseAccess(context);
        }
        return instance;
    }

    /**
     * Open the database connection.
     */
    public void open() {
        this.database = openHelper.getWritableDatabase();
    }

    /**
     * Close the database connection.
     */
    public void close() {
        if (database != null) {
            this.database.close();
        }
    }

    /**
     * Read all quotes from the database.
     *
     * @return a List of quotes
     */
    public List<Question> getQuestions() {
        List<Question> list = new ArrayList<>();
        Cursor cursor = database.rawQuery("SELECT * FROM Question", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Question question = new Question();

            question.setId_question(cursor.getInt(0));
            question.setId_sub(cursor.getInt(1));
            question.setKind_of_math(cursor.getInt(2));
            question.setContent_question(cursor.getString(3));
            question.setAnswer_a(cursor.getString(4));
            question.setAnswer_b(cursor.getString(5));
            question.setAnswer_c(cursor.getString(6));
            question.setAnswer_d(cursor.getString(7));
            question.setAnswer_right(cursor.getString(8));
            question.setExplane(cursor.getString(10));

            list.add(question);

            cursor.moveToNext();
        }
        cursor.close();
        return list;
    }
}
