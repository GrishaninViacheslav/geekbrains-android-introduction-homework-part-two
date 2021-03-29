package GeekBrians.Slava_5655380.UI.NotesMetadataBrowserRecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;

import GeekBrians.Slava_5655380.Note.Note;
import GeekBrians.Slava_5655380.Note.NotesSource;
import GeekBrians.Slava_5655380.R;

public class Adapter
        extends RecyclerView.Adapter<Adapter.ViewHolder> {

    private NotesSource dataSource;
    private OnItemClickListener itemClickListener;  // Слушатель будет устанавливаться извне

    // Передаём в конструктор источник данных
    // В нашем случае это массив, но может быть и запрос к БД
    public Adapter(NotesSource dataSource) {
        this.dataSource = dataSource;
    }

    // Создать новый элемент пользовательского интерфейса
    // Запускается менеджером
    @NonNull
    @Override
    public Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        Log.d("[PING X]", "onCreateViewHolder");
        // Создаём новый элемент пользовательского интерфейса
        // Через Inflater
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_note_metadata, viewGroup, false);
        // Здесь можно установить всякие параметры
        return new ViewHolder(v);
    }

    // Заменить данные в пользовательском интерфейсе
    // Вызывается менеджером
    @Override
    public void onBindViewHolder(@NonNull Adapter.ViewHolder viewHolder, int i) {
        // Получить элемент из источника данных (БД, интернет...)
        // Вынести на экран, используя ViewHolder
        Log.d("[PING X]", "onBindViewHolder");
        viewHolder.setData(dataSource.getNoteData(i));
    }

    // Вернуть размер данных, вызывается менеджером
    @Override
    public int getItemCount() {
        return dataSource.size();
    }

    // Сеттер слушателя нажатий
    public void SetOnItemClickListener(OnItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    // Интерфейс для обработки нажатий, как в ListView
    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

    // Этот класс хранит связь между данными и элементами View
    // Сложные данные могут потребовать несколько View на
    // один пункт списка
    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView title;
        private TextView creationData;
        private TextView modificationDate;
        private TextView notesTags;
        private TextView noteDescription;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.note_title);
            creationData = itemView.findViewById(R.id.note_creation_date);
            modificationDate = itemView.findViewById(R.id.note_modification_date);
            notesTags = itemView.findViewById(R.id.note_tags);
            noteDescription = itemView.findViewById(R.id.note_description);

            // Обработчик нажатий на этом ViewHolder
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (itemClickListener != null) {
                        itemClickListener.onItemClick(v, getAdapterPosition());
                    }
                }
            });
        }

        public void setData(Note note){
            title.setText(note.getMetadata().name);
            creationData.setText(new SimpleDateFormat("dd.MM.yyyy").format(note.getMetadata().creationDate));
            modificationDate.setText(new SimpleDateFormat("dd.MM.yyyy").format(note.getMetadata().modificationDate));
            notesTags.setText(Arrays.toString(note.getMetadata().tags));
            noteDescription.setText(note.getMetadata().description);
        }
    }
}