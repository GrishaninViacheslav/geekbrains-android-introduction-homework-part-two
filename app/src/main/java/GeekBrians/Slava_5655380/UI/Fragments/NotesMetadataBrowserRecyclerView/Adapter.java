package GeekBrians.Slava_5655380.UI.Fragments.NotesMetadataBrowserRecyclerView;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.core.view.MotionEventCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.button.MaterialButton;

import java.text.SimpleDateFormat;
import java.util.Arrays;

import GeekBrians.Slava_5655380.Note.Note;
import GeekBrians.Slava_5655380.Note.NotesDAO.NotesSource;
import GeekBrians.Slava_5655380.R;

public class Adapter
        extends RecyclerView.Adapter<Adapter.ViewHolder> implements ItemTouchHelperAdapter {

    private final NotesSource dataSource;
    private final Fragment fragment;
    private OnItemClickListener metadataPlaceholderClickListener;
    private OnItemClickListener editButtonClickListener;
    private int contextMenuAdapterPosition;
    private OnStartDragListener dragListener;

    @Override
    public void onItemMove(int fromPosition, int toPosition) {
        Note note = dataSource.removeAt(fromPosition);
        int position = toPosition;
        double prevPriority = 0;
        if(position > 0){
            prevPriority = dataSource.getNoteData(position - 1).getMetadata().priority;
        }
        double currPriority = prevPriority + 2;
        if (position < dataSource.size()) {
            currPriority = dataSource.getNoteData(position).getMetadata().priority;
        }
        note.getMetadata().priority = prevPriority + (currPriority - prevPriority) / 2.d;
        dataSource.addNote(note);
        notifyItemMoved(fromPosition, toPosition);
    }

    @Override
    public void onItemDismiss(int position) {
        dataSource.removeAt(position);
        notifyItemRemoved(position);
    }

    public interface OnStartDragListener {
        void onStartDrag(RecyclerView.ViewHolder viewHolder);
    }

    public interface ItemTouchHelperViewHolder {
        void onItemSelected();

        void onItemClear();
    }

    public Adapter(NotesSource dataSource, Fragment fragment, OnStartDragListener dragListener) {
        this.dataSource = dataSource;
        this.fragment = fragment;
        this.dragListener = dragListener;
    }

    @NonNull
    @Override
    public Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_note_metadata_browser, viewGroup, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull Adapter.ViewHolder viewHolder, int i) {
        viewHolder.bind(dataSource.getNoteData(i));
    }

    @Override
    public int getItemCount() {
        return dataSource.size();
    }

    public void setOnMetadataPlaceholderClickListenerClickListener(OnItemClickListener metadataPlaceholderClickListenerClickListener) {
        this.metadataPlaceholderClickListener = metadataPlaceholderClickListenerClickListener;
    }

    public void setOnEditButtonClickListener(OnItemClickListener editButtonClickListener) {
        this.editButtonClickListener = editButtonClickListener;
    }

    public int getContextMenuAdapterPosition() {
        return contextMenuAdapterPosition;
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements ItemTouchHelperViewHolder {
        private TextView title;
        private TextView creationData;
        private TextView modificationDate;
        private TextView notesTags;
        private TextView noteDescription;
        private View metadataPlaceholder;
        private MaterialButton editButton;
        private AppCompatImageView dragHandleImageView;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.note_title);
            creationData = itemView.findViewById(R.id.note_creation_date);
            modificationDate = itemView.findViewById(R.id.note_modification_date);
            notesTags = itemView.findViewById(R.id.note_tags);
            noteDescription = itemView.findViewById(R.id.note_description);
            metadataPlaceholder = itemView.findViewById(R.id.note_metadata_placeholder);
            editButton = itemView.findViewById(R.id.button_edit);
            dragHandleImageView = itemView.findViewById(R.id.dragHandleImageView);
            fragment.registerForContextMenu(itemView);
        }

        @SuppressLint("ClickableViewAccessibility")
        public void bind(Note note) {
            // Обработчик нажатий на картинке
            metadataPlaceholder.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    contextMenuAdapterPosition = getAdapterPosition();
                    itemView.showContextMenu(0, 0);
                    return true;
                }
            });
            metadataPlaceholder.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (metadataPlaceholderClickListener != null) {
                        metadataPlaceholderClickListener.onItemClick(v, getAdapterPosition());
                    }
                }
            });
            editButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (editButtonClickListener != null) {
                        editButtonClickListener.onItemClick(v, getAdapterPosition());
                    }
                }
            });

            dragHandleImageView.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    if (MotionEventCompat.getActionMasked(event) == MotionEvent.ACTION_DOWN) {
                        dragListener.onStartDrag(ViewHolder.this);
                    }
                    return false;
                }
            });

            title.setText(note.getMetadata().name + " - " + note.getMetadata().priority);
            creationData.setText(new SimpleDateFormat("dd.MM.yyyy").format(note.getMetadata().creationDate));
            modificationDate.setText(new SimpleDateFormat("dd.MM.yyyy").format(note.getMetadata().modificationDate));
            notesTags.setText(Arrays.toString(note.getMetadata().tags));
            noteDescription.setText(note.getMetadata().description);
        }

        @Override
        public void onItemSelected() {
            itemView.setBackgroundColor(Color.LTGRAY);
        }

        @Override
        public void onItemClear() {
            itemView.setBackgroundColor(0);
        }
    }
}