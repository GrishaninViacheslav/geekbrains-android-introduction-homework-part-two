package GeekBrians.Slava_5655380.Note;

import android.content.Context;
import android.view.inputmethod.ExtractedTextRequest;
import android.view.inputmethod.InputConnection;
import android.widget.Toast;

import GeekBrians.Slava_5655380.Note.NotesDAO.NotesJSONFilesSource;
import GeekBrians.Slava_5655380.Note.NotesDAO.NotesWritebleSource;

public class NoteEditorPresenter {

    private Context context;
    private Note note;
    private NotesWritebleSource notesWritebleSource;
    private InputConnection inputConnection;


    public NoteEditorPresenter(Context context, Note note, NotesWritebleSource notesAsJSONFiles, InputConnection inputConnection) {
        this.context = context;
        this.note = note;
        this.notesWritebleSource = notesAsJSONFiles;
        this.inputConnection = inputConnection;
    }

    public Note getNote() {
        return note;
    }

    public void setNote(Note note) {
        this.note = note;
    }

    public void actionSave(){
        notesWritebleSource.addNote(note);
        notesWritebleSource.commit();
        Toast.makeText(context, "Note saved: " + note.getContent(), Toast.LENGTH_SHORT).show();
    }

    public void afterTextChangedEvent(){
        note.setContent(inputConnection.getExtractedText(new ExtractedTextRequest(), 0).text.toString());
    }
}
