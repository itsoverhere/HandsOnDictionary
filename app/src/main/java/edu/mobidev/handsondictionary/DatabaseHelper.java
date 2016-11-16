package edu.mobidev.handsondictionary;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

/**
 * Created by student on 12/11/2016.
 */

public class DatabaseHelper extends SQLiteOpenHelper{

    public static final String SCHEMA = "dictionary";
    public static final int VERSION = 1;

    public DatabaseHelper(Context context) {
        super(context, SCHEMA, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE " + Word.TABLE + " ( "
                    + Word.COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + Word.COLUMN_WORD + " TEXT, "
                    + Word.COLUMN_DESCRIPTION + " TEXT);";
        db.execSQL(sql);
        db.insert(Word.TABLE, null,
                convertWordToContentValues(new Word("petrichor", "a pleasant smell that frequently accompanies the first rain after a long period of warm, dry weather")));
        db.insert(Word.TABLE, null,
                convertWordToContentValues(new Word("dysania", "difficulty getting out of bed in the morning")));
        db.insert(Word.TABLE, null,
                convertWordToContentValues(new Word("semordnilap", "words that one way forward, and another backward, eg diaper-repaid")));
        db.insert(Word.TABLE, null,
                convertWordToContentValues(new Word("susurrus", "whispering or rustling")));
    }

    public ContentValues convertWordToContentValues(Word w){
        ContentValues cv = new ContentValues();
        cv.put(Word.COLUMN_WORD, w.getWord());
        cv.put(Word.COLUMN_DESCRIPTION, w.getDescription());
        return cv;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql = "DROP TABLE IF EXISTS " + Word.TABLE + ";";
        db.execSQL(sql);
        onCreate(db);
    }

    public long addWord(Word w){
        ContentValues cv = new ContentValues();
        cv.put(Word.COLUMN_WORD, w.getWord());
        cv.put(Word.COLUMN_DESCRIPTION, w.getDescription());
        return getWritableDatabase().insert(Word.TABLE, null, cv);
    }

    public Word getWord(int id){
        Word w = null;
        Cursor c = getReadableDatabase().query(
                Word.TABLE, null,
                Word.COLUMN_ID + "=?",
                new String[]{String.valueOf(id)},
                null, null, null
        );
        if(c.moveToFirst()){
            w = new Word();
            w.setWord(c.getString(c.getColumnIndex(Word.COLUMN_WORD)));
            w.setDescription(c.getString(c.getColumnIndex(Word.COLUMN_DESCRIPTION)));
            w.setId(id);
        }
        return w;
    }

    public ArrayList<Word> getAllWords(){
        ArrayList<Word> words = new ArrayList<>();
        Cursor c = getReadableDatabase().query(
                Word.TABLE, null, null, null, null, null, null
        );
        if(c.moveToFirst()){
            do {
                Word w = new Word();
                w.setWord(c.getString(c.getColumnIndex(Word.COLUMN_WORD)));
                w.setDescription(c.getString(c.getColumnIndex(Word.COLUMN_DESCRIPTION)));
                w.setId(c.getLong(c.getColumnIndex(Word.COLUMN_ID)));
                words.add(w);
            }while(c.moveToNext());
        }
        return words;
    }

    public Cursor getAllWordsCursor(){
        return getReadableDatabase().query(Word.TABLE, null, null, null, null, null, null);
    }

    public int updateWord(Word w){
        ContentValues cv = new ContentValues();
        cv.put(Word.COLUMN_WORD, w.getWord());
        cv.put(Word.COLUMN_DESCRIPTION, w.getDescription());
        return getWritableDatabase().update(
                Word.TABLE,
                cv,
                Word.COLUMN_ID + "=?",
                new String[]{String.valueOf(w.getId())}
        );
    }

    public int deleteWord(long id){
        return getWritableDatabase().delete(
                Word.TABLE,
                Word.COLUMN_ID + "=?",
                new String[]{String.valueOf(id)}
        );
    }
}
