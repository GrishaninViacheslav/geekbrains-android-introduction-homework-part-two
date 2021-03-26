package GeekBrians.Slava_5655380.UI.Fragments;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.List;

import GeekBrians.Slava_5655380.Note.Note;
import GeekBrians.Slava_5655380.Note.NotesDAO;
import GeekBrians.Slava_5655380.R;
import GeekBrians.Slava_5655380.UI.Activities.NoteEditorActivity;

public class NotesMetadataDisplayFragment extends Fragment {
    private boolean isLandscape;
    private Note selectedNote;
    private NotesDAO notes = new NotesDAO();

    private void showNoteDisplay(){
        if (isLandscape) {
            showLandNoteDisplay();
        } else {
            showPortNoteDisplay();
        }
    }

    private void showPortNoteDisplay(){
        Intent intent = new Intent();
        intent.setClass(getActivity(), NoteEditorActivity.class);
        startActivity(intent);
    }

    private void showLandNoteDisplay(){
        NoteContentDisplayFragment noteDisplay = NoteContentDisplayFragment.newInstance(selectedNote);
        FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.note_display, noteDisplay);  // замена фрагмента
        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        fragmentTransaction.commit();
    }

    private void  initBrowserList(View view){
        LinearLayout layoutView = (LinearLayout) view.findViewById(R.id.note_browser_root);
        List<Note> notes = this.notes.get();

        for(Note note : notes){
            final Note CURRENT_NOTE = note;
            View notePreview = getLayoutInflater().inflate(R.layout.note_metadata, layoutView, false);
            ((TextView)notePreview.findViewById(R.id.note_title)).setText(note.getMetadata().name);
            ((TextView)notePreview.findViewById(R.id.note_creation_date)).setText(new SimpleDateFormat("dd.MM.yyyy").format(note.getMetadata().creationDate));
            ((TextView)notePreview.findViewById(R.id.note_modification_date)).setText(new SimpleDateFormat("dd.MM.yyyy").format(note.getMetadata().modificationDate));
            ((TextView)notePreview.findViewById(R.id.note_tags)).setText(Arrays.toString(note.getMetadata().tags));
            ((TextView)notePreview.findViewById(R.id.note_description)).setText(note.getMetadata().description);
            layoutView.addView(notePreview);

            notePreview.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    selectedNote = CURRENT_NOTE;
                    showNoteDisplay();
                }
            });
        }
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        isLandscape = getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE;

        if (savedInstanceState != null) {
            selectedNote = savedInstanceState.getParcelable(NoteContentDisplayFragment.ARG_NOTE);
        } else {
            selectedNote = new Note();
        }

        if (isLandscape) {
            showLandNoteDisplay();
        }
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putParcelable(NoteContentDisplayFragment.ARG_NOTE, selectedNote);
        super.onSaveInstanceState(outState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_notes_metadata_browser, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        isLandscape = getResources().getConfiguration().orientation
                == Configuration.ORIENTATION_LANDSCAPE;
        initBrowserList(view);
    }
}