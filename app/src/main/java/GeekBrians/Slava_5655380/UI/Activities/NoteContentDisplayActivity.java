package GeekBrians.Slava_5655380.UI.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import java.util.Arrays;

import GeekBrians.Slava_5655380.Note.Note;
import GeekBrians.Slava_5655380.R;
import GeekBrians.Slava_5655380.SimpleDateFormats;
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
                startActivityForResult(intent, NoteFragment.REQUEST_CODE_EDITOR_ACTIVITY);
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case NoteFragment.REQUEST_CODE_EDITOR_ACTIVITY:
                if(resultCode == NoteFragment.RESULT_CODE_CONTENT_EDITED){
                    note = data.getExtras().getParcelable(NoteFragment.ARG_SELECTED_NOTE);
                    setContent();
                }
                break;
        }
    }

    private void setContent() {
        ((TextView)findViewById(R.id.note_title)).setText(note.getMetadata().name);
        ((TextView)findViewById(R.id.note_creation_date)).setText(SimpleDateFormats.DISPLAYED_VALUE_FORMAT.format(note.getMetadata().creationDate));
        ((TextView)findViewById(R.id.note_modification_date)).setText(SimpleDateFormats.DISPLAYED_VALUE_FORMAT.format(note.getMetadata().modificationDate));
        ((TextView)findViewById(R.id.note_tags)).setText(Arrays.toString(note.getMetadata().tags));
        ((TextView)findViewById(R.id.note_description)).setText(note.getMetadata().description);
        ((TextView)findViewById(R.id.note_content)).setText(note.getContent());
        ((TextView)findViewById(R.id.note_content)).setText(note.getContent());
    }
}
