package GeekBrians.Slava_5655380.UI.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

import GeekBrians.Slava_5655380.Note.NotesDAO.FileManagement.AndroidAppSpecificFilesManager;
import GeekBrians.Slava_5655380.Note.Note;
import GeekBrians.Slava_5655380.Note.NoteEditorPresenter;
import GeekBrians.Slava_5655380.Note.NotesDAO.NotesReadableAsJSONFiles;
import GeekBrians.Slava_5655380.R;
import GeekBrians.Slava_5655380.UI.Fragments.NoteFragment;

public class NoteEditorActivity extends AppCompatActivity {
    private Note note;
    private NoteEditorPresenter editorPresenter;
    private TextView titleView;
    private TextView creationDateView;
    private TextView modificationDateView;
    private TextView tagsView;
    private TextView descriptionView;
    private EditText editableContentView;

    public class ViewsSetter{
        public void setTitle(String value){
            // pass
        }
        public void setCreationDate(Date date){
            creationDateView.setText(new SimpleDateFormat("dd-MM-yyyy").format(date));
        }
        public void setModificationDate(String value){
            // pass
        }
        public void setTags(String value){
            // pass
        }
        public void setDescription(String value){
            // pass
        }
        public void setEditableContent(String value){
            // pass
        }
    }

    private void initViews() {
        titleView = findViewById(R.id.note_title);
        creationDateView = findViewById(R.id.note_creation_date);
        creationDateView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editorPresenter.creationDateEditEvent((TextView) v, getSupportFragmentManager());
            }
        });
        modificationDateView = findViewById(R.id.note_modification_date);
        tagsView = findViewById(R.id.note_tags);
        descriptionView = findViewById(R.id.note_description);
        editableContentView = findViewById(R.id.note_editable_content);
        editableContentView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                editorPresenter.afterTextChangedEvent();
            }
        });

        setViews();
    }
    private void setViews(){
        titleView.setText(note.getMetadata().name);
        creationDateView.setText(new SimpleDateFormat("dd-MM-yyyy").format(note.getMetadata().creationDate));
        modificationDateView.setText(new SimpleDateFormat("dd-MM-yyyy").format(note.getMetadata().modificationDate));
        tagsView.setText(Arrays.toString(note.getMetadata().tags));
        descriptionView.setText(note.getMetadata().description);
        editableContentView.setText(note.getContent());
    }

    private void initToolbar() {
        Toolbar toolbar = findViewById(R.id.editor_toolbar);
        setSupportActionBar(toolbar);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setResult(NoteFragment.RESULT_CODE_CONTENT_NOT_EDITED);
        setContentView(R.layout.activity_note_editor);
        initToolbar();
        note = getIntent().getExtras().getParcelable(NoteFragment.ARG_SELECTED_NOTE);
        editorPresenter = new NoteEditorPresenter(this, note, new NotesReadableAsJSONFiles(new AndroidAppSpecificFilesManager(this)), ((EditText) findViewById(R.id.note_editable_content)).onCreateInputConnection(new EditorInfo()), new ViewsSetter());
        initViews();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id) {
            case R.id.action_share:
                Toast.makeText(NoteEditorActivity.this, "action_share", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.action_save:
                editorPresenter.actionSave();

                Intent intentResult = new Intent();
                intentResult.putExtra(NoteFragment.ARG_SELECTED_NOTE, editorPresenter.getNote());
                setResult(NoteFragment.RESULT_CODE_CONTENT_EDITED, intentResult);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.note_editor_toolbar, menu);
        MenuItem search = menu.findItem(R.id.action_search);
        SearchView searchText = (SearchView) search.getActionView();
        searchText.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                Toast.makeText(NoteEditorActivity.this, query, Toast.LENGTH_SHORT).show();
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return true;
            }
        });
        return true;
    }
}
