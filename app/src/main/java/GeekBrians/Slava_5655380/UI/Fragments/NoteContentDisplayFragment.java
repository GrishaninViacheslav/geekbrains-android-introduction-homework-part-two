package GeekBrians.Slava_5655380.UI.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import GeekBrians.Slava_5655380.R;

public class NoteContentDisplayFragment extends Fragment {

    public static NoteContentDisplayFragment newInstance() {
        NoteContentDisplayFragment fragment = new NoteContentDisplayFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_note_content_display, container, false);
    }
}