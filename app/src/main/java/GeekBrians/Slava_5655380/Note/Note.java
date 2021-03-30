package GeekBrians.Slava_5655380.Note;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Note implements Parcelable {
    private MetaData metadata;
    private Content content;

    protected Note(Parcel in) {
    }

    public static final Creator<Note> CREATOR = new Creator<Note>() {
        @Override
        public Note createFromParcel(Parcel in) {
            return new Note(in);
        }

        @Override
        public Note[] newArray(int size) {
            return new Note[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
    }

    private static class Content {
        private String content;

        public Content(String content) {
            this.content = content;
        }
    }

    public static class MetaData {
        public String name;
        public Date creationDate;
        public Date modificationDate;
        public String[] tags;
        public String description;

        public MetaData(String name, Date creationDate, Date lastModificationDate, String[] tags, String description) {
            this.name = name;
            this.creationDate = creationDate;
            this.modificationDate = lastModificationDate;
            this.tags = tags;
            this.description = description;
        }
    }

    public Note() {
        Log.d("[ping]", "Empty note created");

        try {
            this.metadata = new MetaData(
                    "Безымянная заметка", new SimpleDateFormat("dd-MM-yyyy").parse("24-03-2021"),
                    new SimpleDateFormat("dd-MM-yyyy").parse("25-03-2021"), new String[]{"#lorem", "#sit", "#amet"},
                    "Это безымянная заметка"
            );
        } catch (ParseException e) {
            e.printStackTrace();
        }
        this.content = new Content("");
    }

    public Note(MetaData meta, String content) {
        this.metadata = meta;
        this.content = new Content(content);
    }

    public String getContent() {
        return content.content;
    }

    public MetaData getMetadata() {
        return metadata;
    }
}
