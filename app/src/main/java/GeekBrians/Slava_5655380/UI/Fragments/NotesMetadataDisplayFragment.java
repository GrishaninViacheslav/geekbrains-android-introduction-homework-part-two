package GeekBrians.Slava_5655380.UI.Fragments;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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

    private void initBrowserRecycleView(RecyclerView recyclerView){
        recyclerView.setHasFixedSize(true);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);

        GeekBrians.Slava_5655380.UI.NotesMetadataBrowserRecyclerView.Adapter adapter = new GeekBrians.Slava_5655380.UI.NotesMetadataBrowserRecyclerView.Adapter(notes.get());
        recyclerView.setAdapter(adapter);

        adapter.SetOnItemClickListener(new GeekBrians.Slava_5655380.UI.NotesMetadataBrowserRecyclerView.Adapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                selectedNote = notes.get().get(position);
                showNoteDisplay();
            }
        });
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