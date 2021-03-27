package GeekBrians.Slava_5655380.UI.Fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Arrays;

import GeekBrians.Slava_5655380.Note.Note;
import GeekBrians.Slava_5655380.R;

public class NoteContentDisplayFragment extends Fragment {
    private Note note;

    private void fillTitle(View view){
        ((TextView)view.findViewById(R.id.note_title)).setText(note.getMetadata().name);
        ((TextView)view.findViewById(R.id.note_creation_date)).setText(new SimpleDateFormat("yyyy.MM.dd").format(note.getMetadata().creationDate));
        ((TextView)view.findViewById(R.id.note_modification_date)).setText(new SimpleDateFormat("yyyy.MM.dd").format(note.getMetadata().modificationDate));
        ((TextView)view.findViewById(R.id.note_tags)).setText(Arrays.toString(note.getMetadata().tags));
        ((TextView)view.findViewById(R.id.note_description)).setText(note.getMetadata().description);
        ((TextView)view.findViewById(R.id.note_content)).setText(note.getContent());
    }

    public static NoteContentDisplayFragment newInstance(Note note) {
        NoteContentDisplayFragment fragment = new NoteContentDisplayFragment();
        Bundle args = new Bundle();
        args.putParcelable(ARG_NOTE, note);
        fragment.setArguments(args);
        return fragment;
    }

    public static final String ARG_NOTE = "NOTE";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            note = (Note)getArguments().getParcelable(ARG_NOTE);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_note_content_display, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        fillTitle(view);
    }
}