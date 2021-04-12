package GeekBrians.Slava_5655380.Note.NotesDAO.NotesAsRoomDB;

import android.content.Context;

import androidx.room.Room;

import java.util.LinkedList;

import GeekBrians.Slava_5655380.Note.Note;
import GeekBrians.Slava_5655380.Note.NotesDAO.NotesReadableSource;
import GeekBrians.Slava_5655380.Note.NotesDAO.NotesWritableSource;

public class NotesAsRoomDatabase implements NotesReadableSource, NotesWritableSource {
    private NotesDatabase db;
    private NotesDao notesDao;
    private LinkedList<NoteRoomEntity> notesToCommit;

    public NotesAsRoomDatabase(Context context) {
        NotesDatabase db = Room.databaseBuilder(context,
                NotesDatabase.class, "notes").allowMainThreadQueries().build();
        notesDao = db.notesDao();
    }

    @Override
    public Note getNoteData(int position) {
        return notesDao.loadAllByIds(new int[]{position+1}).get(0).convertToNote();
    }

    @Override
    public int size() {
        return notesDao.getDataCount();
    }

    @Override
    public void addNote(Note note) {
        notesToCommit.add(new NoteRoomEntity(note));
    }

    @Override
    public void commit() {
        notesDao.insertAll((NoteRoomEntity[])notesToCommit.toArray());
    }
}
