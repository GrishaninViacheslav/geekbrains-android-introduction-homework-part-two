package GeekBrians.Slava_5655380.UI.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.Arrays;

import GeekBrians.Slava_5655380.Note.Note;
import GeekBrians.Slava_5655380.R;
import GeekBrians.Slava_5655380.UI.Fragments.NoteFragment;

public class NoteContentDisplayActivity extends AppCompatActivity {
    private Note note;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_display);
        note = getIntent().getExtras().getParcelable(NoteFragment.ARG_SELECTED_NOTE);
        setContent();
        findViewById(R.id.button_edit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(NoteContentDisplayActivity.this, NoteEditorActivity.class);
                intent.putExtra(NoteFragment.ARG_SELECTED_NOTE, note);
                startActivity(intent);
            }
        });
    }

    private void setContent() {
        ((TextView)findViewById(R.id.note_title)).setText(note.getMetadata().name);
        ((TextView)findViewById(R.id.note_creation_date)).setText(new SimpleDateFormat("dd-MM-yyyy").format(note.getMetadata().creationDate));
        ((TextView)findViewById(R.id.note_modification_date)).setText(new SimpleDateFormat("dd-MM-yyyy").format(note.getMetadata().modificationDate));
        ((TextView)findViewById(R.id.note_tags)).setText(Arrays.toString(note.getMetadata().tags));
        ((TextView)findViewById(R.id.note_description)).setText(note.getMetadata().description);
        ((TextView)findViewById(R.id.note_content)).setText(note.getContent());
        ((TextView)findViewById(R.id.note_content)).setText(note.getContent());
    }
}
