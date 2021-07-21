package GeekBrians.Slava_5655380.Note.NotesDAO.NotesAsRoomDB;

import android.content.Context;
import androidx.room.Room;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.LinkedList;

import GeekBrians.Slava_5655380.Note.Note;
import GeekBrians.Slava_5655380.Note.NotesDAO.NotesSource;

public class NotesAsRoomDatabase implements NotesSource {
    private final static NotesAsRoomDatabase SINGLETON_INSTANCE = new NotesAsRoomDatabase();

    private Context appContext;
    private NotesDatabase db;
    private NotesDao notesDao;
    private LinkedList<NoteRoomEntity> notesToCommit;

    private NotesAsRoomDatabase() {
        appContext = null;
    }

    private static class SingletonInitializedButDifferentAppcontextPassed extends RuntimeException{
        public SingletonInitializedButDifferentAppcontextPassed(Context context) {
            super("This singleton was initialized by different application context: Passed context: " + context + ", but application context this singleton is using: " + SINGLETON_INSTANCE.appContext);
        }
    }

    public static NotesAsRoomDatabase getInstance(Context appContext) {
        if (SINGLETON_INSTANCE.appContext == null) {
            SINGLETON_INSTANCE.appContext = appContext;
            SINGLETON_INSTANCE.db = Room.databaseBuilder(SINGLETON_INSTANCE.appContext,
                    NotesDatabase.class, "notes").allowMainThreadQueries().build();
            SINGLETON_INSTANCE.notesDao = SINGLETON_INSTANCE.db.notesDao();
            SINGLETON_INSTANCE.notesToCommit = new LinkedList<>();
        } else if (!SINGLETON_INSTANCE.appContext.equals(appContext)){
            throw new SingletonInitializedButDifferentAppcontextPassed(appContext);
        }

        // TODO: УДАЛИТЬ ЭТОТ КУСОК
        // TODO: ######################################################################################################################################
        // этот кусок нужен только для отладки и будет удалён
        if(SINGLETON_INSTANCE.notesDao.getDataCount() == 0){
            try {
                SINGLETON_INSTANCE.addNote(
                        new Note(
                                new Note.MetaData("Вторая заметка", new SimpleDateFormat("dd-MM-yyyy").parse("24-03-2021"), new SimpleDateFormat("dd-MM-yyyy").parse("25-03-2021"), new String[]{"#lorem", "#ipsum", "#dolor", "#sit"}, "Описание второй заметки"),
                                "Содержимое второй заметки"));
                SINGLETON_INSTANCE.addNote(
                        new Note(
                                new Note.MetaData("Третья заметка", new SimpleDateFormat("dd-MM-yyyy").parse("24-03-2021"), new SimpleDateFormat("dd-MM-yyyy").parse("25-03-2021"), new String[]{"#lorem", "#ipsum"}, "Описание третьей заметки"),
                                "Содержимое третьей заметки"));
                SINGLETON_INSTANCE.addNote(
                        new Note(
                                new Note.MetaData("Четвёртая заметка", new SimpleDateFormat("dd-MM-yyyy").parse("24-03-2021"), new SimpleDateFormat("dd-MM-yyyy").parse("25-03-2021"), new String[]{"#lorem", "#ipsum", "#amet"}, "Описание четвёртой заметки"),
                                "Содержимое четвёертой заметки"));
                SINGLETON_INSTANCE.addNote(
                        new Note(
                                new Note.MetaData("Пятая заметка", new SimpleDateFormat("dd-MM-yyyy").parse("24-03-2021"), new SimpleDateFormat("dd-MM-yyyy").parse("25-03-2021"), new String[]{"#lorem", "#ipsum"}, "Описание пятой заметки"),
                                "Содержимое пятой заметки"));
                SINGLETON_INSTANCE.addNote(
                        new Note(
                                new Note.MetaData("Шестая заметка", new SimpleDateFormat("dd-MM-yyyy").parse("24-03-2021"), new SimpleDateFormat("dd-MM-yyyy").parse("25-03-2021"), new String[]{"#lorem", "#ipsum"}, "Описание шестой заметки"),
                                "Содержимое шестой заметки"));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            SINGLETON_INSTANCE.commit();
        }
        // TODO: ######################################################################################################################################


        return SINGLETON_INSTANCE;
    }


    @Override
    public Note getNoteData(int position) {
        return notesDao.getRow(position).get(0).convertToNote();
    }

    @Override
    public int size() {
        return notesDao.getDataCount();
    }

    @Override
    public void addNote(Note note) {
        notesToCommit.add(new NoteRoomEntity(note, notesDao));
    }

    @Override
    public void add(int position, Note note) {
        notesToCommit.add(position, new NoteRoomEntity(note, notesDao));
    }

    @Override
    public Note removeAt(int position) {
        Note note = notesDao.getRow(position).get(0).convertToNote();
        deleteNote(note);
        return note;
    }

    @Override
    public void deleteNote(Note note) {
        notesDao.delete(new NoteRoomEntity(note, notesDao));
    }

    @Override
    public void commit() {
        // Как по нормальному сделать обновление строки заметки?
        LinkedList<NoteRoomEntity> notesToInsert = new LinkedList<>();
        for (NoteRoomEntity noteRoomEntity : notesToCommit) {
            if (notesDao.isRowIsExist(noteRoomEntity.uid)) {
                notesDao.update(noteRoomEntity);
            } else {
                notesToInsert.add(noteRoomEntity);
            }
        }
        notesDao.insertAll(notesToInsert.toArray(new NoteRoomEntity[notesToInsert.size()]));
        notesToCommit.clear();
    }
}
