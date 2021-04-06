package GeekBrians.Slava_5655380.UI.Fragments;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import GeekBrians.Slava_5655380.Note.FileManagement.AndroidAppSpecificFilesManager;
import GeekBrians.Slava_5655380.Note.Note;
import GeekBrians.Slava_5655380.Note.NotesDAO;
import GeekBrians.Slava_5655380.R;
import GeekBrians.Slava_5655380.UI.Activities.NoteContentDisplayActivity;
import GeekBrians.Slava_5655380.UI.Activities.NoteEditorActivity;
import GeekBrians.Slava_5655380.UI.Fragments.NotesMetadataBrowserRecyclerView.Adapter;

public class NotesMetadataDisplayFragment extends NoteFragment {
    private boolean isLandscape;
    private Note selectedNote;
    private NotesDAO notes;

    private void showNoteEditor() {
        Intent intent = new Intent();
        intent.setClass(getActivity(), NoteEditorActivity.class);
        intent.putExtra(ARG_SELECTED_NOTE, selectedNote);
        startActivityForResult(intent, REQUEST_CODE_EDITOR_ACTIVITY);
    }

    private void showNoteDisplay() {
        if (isLandscape) {
            showLandNoteDisplay();
        } else {
            showPortNoteDisplay();
        }
    }

    private void showPortNoteDisplay() {
        Intent intent = new Intent();
        intent.setClass(getActivity(), NoteContentDisplayActivity.class);
        intent.putExtra(ARG_SELECTED_NOTE, selectedNote);
        startActivityForResult(intent, REQUEST_CODE_CONTENT_DISPLAY_ACTIVITY);
    }

    private void showLandNoteDisplay() {
        NoteContentDisplayFragment noteDisplay = NoteContentDisplayFragment.newInstance(selectedNote);
        FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.note_display, noteDisplay);  // замена фрагмента
        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        fragmentTransaction.commit();
    }

    private void initBrowserRecycleView(RecyclerView recyclerView) {
        recyclerView.setHasFixedSize(true);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);

        Adapter adapter = new Adapter(notes);
        recyclerView.setAdapter(adapter);

        DividerItemDecoration itemDecoration = new DividerItemDecoration(getContext(), LinearLayoutManager.VERTICAL);
        itemDecoration.setDrawable(getResources().getDrawable(R.drawable.separator, null));
        recyclerView.addItemDecoration(itemDecoration);

        adapter.setOnMetadataPlaceholderClickListenerClickListener(new Adapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                selectedNote = notes.getNoteData(position);
                showNoteDisplay();
            }
        });

        adapter.setOnEditButtonClickListener(new Adapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                selectedNote = notes.getNoteData(position);
                showNoteEditor();
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case REQUEST_CODE_CONTENT_DISPLAY_ACTIVITY:
            case REQUEST_CODE_EDITOR_ACTIVITY:
                requireActivity().recreate();
                break;
        }
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        isLandscape = getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE;

        if (savedInstanceState != null) {
            selectedNote = savedInstanceState.getParcelable(ARG_SELECTED_NOTE);
        } else {
            selectedNote = new Note();
        }

        if (isLandscape) {
            showLandNoteDisplay();
        }
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putParcelable(ARG_SELECTED_NOTE, selectedNote);
        super.onSaveInstanceState(outState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        notes = new NotesDAO(new AndroidAppSpecificFilesManager(requireActivity()));
        View view = inflater.inflate(R.layout.fragment_notes_metadata_browser, container, false);
        RecyclerView recyclerView = view.findViewById(R.id.recycler_view_lines);
        initBrowserRecycleView(recyclerView);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        isLandscape = getResources().getConfiguration().orientation
                == Configuration.ORIENTATION_LANDSCAPE;
    }
}