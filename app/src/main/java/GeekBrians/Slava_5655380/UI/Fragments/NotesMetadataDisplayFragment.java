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

import java.text.ParseException;
import java.text.SimpleDateFormat;

import GeekBrians.Slava_5655380.Note.NotesDAO.FileManagement.AndroidAppSpecificFilesManager;
import GeekBrians.Slava_5655380.Note.Note;
import GeekBrians.Slava_5655380.Note.NotesDAO.NotesJSONFilesSource;
import GeekBrians.Slava_5655380.Note.NotesDAO.NotesSQLiteDBSource;
import GeekBrians.Slava_5655380.R;
import GeekBrians.Slava_5655380.UI.Activities.NoteContentDisplayActivity;
import GeekBrians.Slava_5655380.UI.Activities.NoteEditorActivity;
import GeekBrians.Slava_5655380.UI.Fragments.NotesMetadataBrowserRecyclerView.Adapter;

public class NotesMetadataDisplayFragment extends NoteFragment {
    private boolean isLandscape;
    private Note selectedNote;
    private NotesSQLiteDBSource notes;

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

    private void addTestNotes(NotesSQLiteDBSource notes) {
        try {
            notes.addNote(
                    new Note(
                            new Note.MetaData(
                                    "Первая заметка", new SimpleDateFormat("dd-MM-yyyy").parse("24-03-2021"),
                                    new SimpleDateFormat("dd-MM-yyyy").parse("25-03-2021"), new String[]{"#lorem", "#sit", "#amet"},
                                    "Описание первой заметки"
                            ),
                            "Сооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооодержимое пеeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeрвой заметки"));
            notes.addNote(
                    new Note(
                            new Note.MetaData("Вторая заметка", new SimpleDateFormat("dd-MM-yyyy").parse("24-03-2021"), new SimpleDateFormat("dd-MM-yyyy").parse("25-03-2021"), new String[]{"#lorem", "#ipsum", "#dolor", "#sit"}, "Описание второй заметки"),
                            "Содержимое второй заметки"));
            notes.addNote(
                    new Note(
                            new Note.MetaData("Третья заметка", new SimpleDateFormat("dd-MM-yyyy").parse("24-03-2021"), new SimpleDateFormat("dd-MM-yyyy").parse("25-03-2021"), new String[]{"#lorem", "#ipsum"}, "Описание третьей заметки"),
                            "Содержимое третьей заметки"));
            notes.addNote(
                    new Note(
                            new Note.MetaData("Четвёртая заметка", new SimpleDateFormat("dd-MM-yyyy").parse("24-03-2021"), new SimpleDateFormat("dd-MM-yyyy").parse("25-03-2021"), new String[]{"#lorem", "#ipsum", "#amet"}, "Описание четвёртой заметки"),
                            "Содержимое четвёертой заметки"));
            notes.addNote(
                    new Note(
                            new Note.MetaData("Пятая заметка", new SimpleDateFormat("dd-MM-yyyy").parse("24-03-2021"), new SimpleDateFormat("dd-MM-yyyy").parse("25-03-2021"), new String[]{"#lorem", "#ipsum"}, "Описание пятой заметки"),
                            "Содержимое пятой заметки"));
            notes.addNote(
                    new Note(
                            new Note.MetaData("Шестая заметка", new SimpleDateFormat("dd-MM-yyyy").parse("24-03-2021"), new SimpleDateFormat("dd-MM-yyyy").parse("25-03-2021"), new String[]{"#lorem", "#ipsum"}, "Описание шестой заметки"),
                            "Содержимое шестой заметки"));
            notes.addNote(
                    new Note(
                            new Note.MetaData("Седьмая заметка", new SimpleDateFormat("dd-MM-yyyy").parse("24-03-2021"), new SimpleDateFormat("dd-MM-yyyy").parse("25-03-2021"), new String[]{"#lorem", "#ipsum"}, "Описание седьмой заметки"),
                            "Содержимое седьмой заметки"));
            notes.addNote(
                    new Note(
                            new Note.MetaData("Восьмая заметка", new SimpleDateFormat("dd-MM-yyyy").parse("24-03-2021"), new SimpleDateFormat("dd-MM-yyyy").parse("25-03-2021"), new String[]{"#lorem", "#ipsum"}, "Описание восьмой заметки"),
                            "Содержимое восьмой заметки"));
            notes.addNote(
                    new Note(
                            new Note.MetaData("Девятая заметка", new SimpleDateFormat("dd-MM-yyyy").parse("24-03-2021"), new SimpleDateFormat("dd-MM-yyyy").parse("25-03-2021"), new String[]{"#lorem", "#ipsum"}, "Описание девятой заметки"),
                            "Содержимое девятой заметки"));
            notes.addNote(
                    new Note(
                            new Note.MetaData("Десятая заметка", new SimpleDateFormat("dd-MM-yyyy").parse("24-03-2021"), new SimpleDateFormat("dd-MM-yyyy").parse("25-03-2021"), new String[]{"#lorem", "#ipsum"}, "Описание десятой заметки"),
                            "Содержимое десятой заметки"));
            notes.addNote(
                    new Note(
                            new Note.MetaData("Одинадцатая заметка", new SimpleDateFormat("dd-MM-yyyy").parse("24-03-2021"), new SimpleDateFormat("dd-MM-yyyy").parse("25-03-2021"), new String[]{"#lorem", "#ipsum"}, "Описание одинадцатой заметки"),
                            "Содержимое одинадцатой заметки"));
            notes.addNote(
                    new Note(
                            new Note.MetaData("двенадцатая заметка", new SimpleDateFormat("dd-MM-yyyy").parse("24-03-2021"), new SimpleDateFormat("dd-MM-yyyy").parse("25-03-2021"), new String[]{"#lorem", "#ipsum"}, "Описание двенадцатой заметки"),
                            "Содержимое двенадцатой заметки"));
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        notes = new NotesSQLiteDBSource(requireActivity());

        if(notes.size() == 0){
            addTestNotes(notes);
        }

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