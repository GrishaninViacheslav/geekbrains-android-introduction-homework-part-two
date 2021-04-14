package GeekBrians.Slava_5655380.Note.NotesDAO;

import GeekBrians.Slava_5655380.Note.Note;

public interface NotesSource {
    Note getNoteData(int position);
    int size();
    void addNote(Note note);
    void deleteNote(Note note);
    void commit();
}
