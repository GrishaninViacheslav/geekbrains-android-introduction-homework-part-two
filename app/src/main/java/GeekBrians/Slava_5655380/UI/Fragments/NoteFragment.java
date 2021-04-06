package GeekBrians.Slava_5655380.UI.Fragments;

import androidx.fragment.app.Fragment;

// TODO: вынести в класс констант
public abstract class NoteFragment extends Fragment {
    public static final String ARG_SELECTED_NOTE = "SELECTED_NOTE";

    // TODO: вынести в enum
    public static final int REQUEST_CODE_EDITOR_ACTIVITY = 1;
    public static final int REQUEST_CODE_CONTENT_DISPLAY_ACTIVITY = 2;
    public static final int RESULT_CODE_CONTENT_EDITED = 3;
    public static final int RESULT_CODE_CONTENT_NOT_EDITED = 4;
}
