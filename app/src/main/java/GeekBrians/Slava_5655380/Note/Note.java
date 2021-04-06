package GeekBrians.Slava_5655380.Note;

import android.os.Parcel;
import android.os.Parcelable;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Note implements Parcelable {
    private MetaData metadata;
    private Content content;

    private static class Content implements Parcelable {
        private String content;

        public Content(String content) {
            this.content = content;
        }

        protected Content(Parcel in) {
            content = in.readString();
        }

        public static final Creator<Content> CREATOR = new Creator<Content>() {
            @Override
            public Content createFromParcel(Parcel in) {
                return new Content(in);
            }

            @Override
            public Content[] newArray(int size) {
                return new Content[size];
            }
        };

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(content);
        }
    }

    protected Note(Parcel in) {
        metadata = in.readParcelable(MetaData.class.getClassLoader());
        content = in.readParcelable(Content.class.getClassLoader());
    }

    public static class MetaData implements Parcelable {
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

        protected MetaData(Parcel in) {
            name = in.readString();
            try {
                creationDate = new SimpleDateFormat("dd-MM-yyyy").parse(in.readString());
                modificationDate = new SimpleDateFormat("dd-MM-yyyy").parse(in.readString());
            } catch (ParseException e) {
                e.printStackTrace();
            }
            tags = in.createStringArray();
            description = in.readString();
        }

        public static final Creator<MetaData> CREATOR = new Creator<MetaData>() {
            @Override
            public MetaData createFromParcel(Parcel in) {
                return new MetaData(in);
            }

            @Override
            public MetaData[] newArray(int size) {
                return new MetaData[size];
            }
        };

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(name);
            dest.writeString(new SimpleDateFormat("dd-MM-yyyy").format(creationDate));
            dest.writeString(new SimpleDateFormat("dd-MM-yyyy").format(modificationDate));
            dest.writeStringArray(tags);
            dest.writeString(description);
        }
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
        dest.writeParcelable(metadata, flags);
        dest.writeParcelable(content, flags);
    }

    public Note() {
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

    public void setContent(String value){
        content.content = value;
    }

    public MetaData getMetadata() {
        return metadata;
    }
}
