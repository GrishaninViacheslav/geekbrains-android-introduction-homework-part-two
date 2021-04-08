package GeekBrians.Slava_5655380.Note;

import android.content.Context;
import android.view.inputmethod.ExtractedTextRequest;
import android.view.inputmethod.InputConnection;
import android.widget.Toast;

import androidx.fragment.app.DialogFragment;

import GeekBrians.Slava_5655380.Note.NotesDAO.NotesReadableAsJSONFiles;
import GeekBrians.Slava_5655380.UI.Fragments.DialogFragment.DialogBuilderFragment;

public class NoteEditorPresenter {

    private Context context;
    private Note note;
    private NotesReadableAsJSONFiles notesAsJSONFiles;
    private InputConnection inputConnection;


    public NoteEditorPresenter(Context context, Note note, NotesReadableAsJSONFiles notesAsJSONFiles, InputConnection inputConnection) {
        this.context = context;
        this.note = note;
        this.notesAsJSONFiles = notesAsJSONFiles;
        this.inputConnection = inputConnection;
    }

    public Note getNote() {
        return note;
    }

    public void setNote(Note note) {
        this.note = note;
    }

    public void actionSave(){
        notesAsJSONFiles.addNote(note);
        notesAsJSONFiles.commit();
        Toast.makeText(context, "Note saved: " + note.getContent(), Toast.LENGTH_SHORT).show();
    }

    public void afterTextChangedEvent(){
        note.setContent(inputConnection.getExtractedText(new ExtractedTextRequest(), 0).text.toString());
    }

    public void creationDateEditEvent(androidx.fragment.app.FragmentManager fragmentManager){
        DialogFragment dlgBuilder = new DialogBuilderFragment();
        dlgBuilder.show(fragmentManager, "transactionTag");
    }
}
