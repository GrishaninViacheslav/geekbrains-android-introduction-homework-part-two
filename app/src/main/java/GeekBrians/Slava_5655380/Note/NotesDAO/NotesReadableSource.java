package GeekBrians.Slava_5655380.Note.NotesDAO;

import GeekBrians.Slava_5655380.Note.Note;

public interface NotesReadableSource {
    Note getNoteData(int position);
    int size();
}
