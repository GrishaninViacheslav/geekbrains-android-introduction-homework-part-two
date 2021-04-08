package GeekBrians.Slava_5655380.UI.Fragments.DialogFragment;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TimePicker;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

import java.text.ParseException;

import GeekBrians.Slava_5655380.R;
import GeekBrians.Slava_5655380.SimpleDateFormats;

public class NoteCreationTimePicker extends DialogFragment {
    private TimePickerDialogListener timePickerDialogListener;

    public NoteCreationTimePicker(TimePickerDialogListener timePickerDialogListener) {
        this.timePickerDialogListener = timePickerDialogListener;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        final View contentView = requireActivity().getLayoutInflater().inflate(R.layout.item_time_picker, null);

        AlertDialog.Builder builder = new AlertDialog.Builder(requireActivity())
                .setTitle(getResources().getString(R.string.creation_time_picker_title))
                .setView(contentView)
                .setNeutralButton(getResources().getString(R.string.time_picker_neutral_button_string), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dismiss();
                    }
                })
                .setPositiveButton(getResources().getString(R.string.time_picker_positive_button_string), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dismiss();
                        DatePicker datePicker = contentView.findViewById(R.id.datePicker);
                        TimePicker timePicker = contentView.findViewById(R.id.timePicker);
                        try {
                            timePickerDialogListener.onDateAssign(SimpleDateFormats.INNER_VALUE_FORMAT.parse(datePicker.getYear() + "-"+ (datePicker.getMonth()+1) + "-" + datePicker.getDayOfMonth() + " " + timePicker.getHour() + ":" + timePicker.getMinute()) );
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                    }
                });
        return builder.create();
    }
}