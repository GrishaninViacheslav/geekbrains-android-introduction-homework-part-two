package GeekBrians.Slava_5655380.Note.NotesDAO;

import GeekBrians.Slava_5655380.Note.Note;

public interface NotesWritableSource {
    void addNote(Note note);
    void commit();
}
