package GeekBrians.Slava_5655380.Note.NotesDAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import GeekBrians.Slava_5655380.Note.Note;

public class NotesReadableAsSQLiteDB extends SQLiteOpenHelper implements NotesReadableSource, NotesWritebleSource {
    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + NoteEntry.TABLE_NAME + " (" +
                    NoteEntry._ID + " INTEGER PRIMARY KEY," +
                    NoteEntry.COLUMN_NAME_NAME + " TEXT," +
                    NoteEntry.COLUMN_NAME_CONTENT + " TEXT)";

    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + NoteEntry.TABLE_NAME;

    public static class NoteEntry {
        public static final String _ID = "1";
        public static final String TABLE_NAME = "Notes";
        public static final String COLUMN_NAME_NAME = "name";
        public static final String COLUMN_NAME_CONTENT = "content";
    }

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "NotesSQLite.db";

    public NotesReadableAsSQLiteDB(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }


    @Override
    public Note getNoteData(int position) {
        SQLiteDatabase db = this.getReadableDatabase();

// Define a projection that specifies which columns from the database
// you will actually use after this query.
        String[] projection = {
                NoteEntry._ID,
                NoteEntry.COLUMN_NAME_NAME,
                NoteEntry.COLUMN_NAME_CONTENT
        };

// Filter results WHERE "title" = 'My Title'
        String selection = NoteEntry._ID + " = ?";
        String[] selectionArgs = {String.valueOf(position)};

// How you want the results sorted in the resulting Cursor
        String sortOrder =
                NoteEntry.COLUMN_NAME_NAME + " DESC";

        Cursor cursor = db.query(
                NoteEntry.TABLE_NAME,   // The table to query
                projection,             // The array of columns to return (pass null to get all)
                selection,              // The columns for the WHERE clause
                selectionArgs,          // The values for the WHERE clause
                null,                   // don't group the rows
                null,                   // don't filter by row groups
                sortOrder               // The sort order
        );

        cursor.moveToNext(); // Since the cursor starts at position -1, calling moveToNext() places the "read position" on the first entry in the results and returns whether or not the cursor is already past the last entry in the result set.
        Note note = null;
        try {
            note = new Note(
                    new Note.MetaData(cursor.getString(cursor.getColumnIndexOrThrow(NoteEntry.COLUMN_NAME_NAME)), new SimpleDateFormat("dd-MM-yyyy").parse("24-03-2021"), new SimpleDateFormat("dd-MM-yyyy").parse("25-03-2021"), new String[]{"#lorem", "#ipsum"}, "Описание шестой заметки"),
                    cursor.getString(cursor.getColumnIndexOrThrow(NoteEntry.COLUMN_NAME_CONTENT)));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        cursor.close();

        return note;
    }

    @Override
    public int size() {
        SQLiteDatabase db = this.getReadableDatabase();
        long count = DatabaseUtils.queryNumEntries(db, NoteEntry.TABLE_NAME);
        db.close();
        return (int)count;
    }


    @Override
    public void addNote(Note note) {
        // Gets the data repository in write mode
        SQLiteDatabase db = this.getWritableDatabase();

        // New value for one column
        ContentValues updValues = new ContentValues();
        updValues.put(NoteEntry.COLUMN_NAME_CONTENT, note.getContent());

// Which row to update, based on the title
        String selection = NoteEntry.COLUMN_NAME_NAME + " LIKE ?";
        String[] selectionArgs = { note.getMetadata().name };

        int count = db.update(
                NoteEntry.TABLE_NAME,
                updValues,
                selection,
                selectionArgs);

        if(count == 0){
// Create a new map of values, where column names are the keys
            ContentValues addValues = new ContentValues();
            addValues.put(NoteEntry.COLUMN_NAME_NAME, note.getMetadata().name);
            addValues.put(NoteEntry.COLUMN_NAME_CONTENT, note.getContent());

// Insert the new row, returning the primary key value of the new row
            long newRowId = db.insert(NoteEntry.TABLE_NAME, null, addValues);
        }
    }

    @Override
    public void commit() {

    }
}
