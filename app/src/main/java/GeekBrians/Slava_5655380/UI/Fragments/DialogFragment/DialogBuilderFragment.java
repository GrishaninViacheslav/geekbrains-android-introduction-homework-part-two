package GeekBrians.Slava_5655380.UI.Fragments.DialogFragment;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

import GeekBrians.Slava_5655380.R;
import GeekBrians.Slava_5655380.UI.Activities.MainActivity;

public class DialogBuilderFragment extends DialogFragment {

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        final View contentView = requireActivity().getLayoutInflater().inflate(R.layout.dialog_custom, null);
        AlertDialog.Builder builder = new AlertDialog.Builder(requireActivity())
                .setTitle("Дата создания")
                .setView(contentView)
                .setPositiveButton("изменить", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
//                        EditText editText = contentView.findViewById(R.id.editText);
//                        String answer = editText.getText().toString();
                        dismiss();
                        //((MainActivity) requireActivity()).onDialogResult(answer);
                    }
                });
        return builder.create();
    }
}