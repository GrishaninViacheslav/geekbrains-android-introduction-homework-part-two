package GeekBrians.Slava_5655380.Note.NotesDAO;

import GeekBrians.Slava_5655380.Note.Note;

public interface NotesWritebleSource {
    void addNote(Note note);
    void commit();
}