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
        db = Room.databaseBuilder(context,
                NotesDatabase.class, "notes").allowMainThreadQueries().build();
        notesDao = db.notesDao();
        notesToCommit = new LinkedList<>();
    }

    @Override
    public Note getNoteData(int position) {
        return notesDao.loadAllByIds(new int[]{position + 1}).get(0).convertToNote();
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
        LinkedList<NoteRoomEntity> notesToInsert = new LinkedList<>();
        for(NoteRoomEntity noteRoomEntity : notesToCommit){
            NoteRoomEntity originEntity = notesDao.findByName(noteRoomEntity.name);
            if(originEntity != null){
                NoteRoomEntity updatedEntity = new NoteRoomEntity(originEntity.nid, noteRoomEntity.name, noteRoomEntity.creationDate, noteRoomEntity.modificationDate, noteRoomEntity.tags, noteRoomEntity.description, noteRoomEntity.content);
                notesDao.update(updatedEntity);
            }
            else{
                notesToInsert.add(noteRoomEntity);
            }
        }
        notesDao.insertAll(notesToInsert.toArray(new NoteRoomEntity[notesToInsert.size()]));
        notesToCommit.clear();
    }
}
