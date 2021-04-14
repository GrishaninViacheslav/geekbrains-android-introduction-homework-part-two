package GeekBrians.Slava_5655380.Note;

import android.content.Context;
import android.view.inputmethod.ExtractedTextRequest;
import android.view.inputmethod.InputConnection;
import android.widget.Toast;
import GeekBrians.Slava_5655380.Note.NotesDAO.NotesSource;

public class NoteEditorPresenter {

    private Context context;
    private Note note;
    private NotesSource notesSource;
    private InputConnection inputConnection;


    public NoteEditorPresenter(Context context, Note note, NotesSource notesSource, InputConnection inputConnection) {
        this.context = context;
        this.note = note;
        this.notesSource = notesSource;
        this.inputConnection = inputConnection;
    }

    public Note getNote() {
        return note;
    }

    public void actionSave(){
        notesSource.addNote(note);
        notesSource.commit();
        Toast.makeText(context, "Note saved: " + note.getContent(), Toast.LENGTH_SHORT).show();
    }

    public void afterTextChangedEvent(){
        note.setContent(inputConnection.getExtractedText(new ExtractedTextRequest(), 0).text.toString());
    }
}
