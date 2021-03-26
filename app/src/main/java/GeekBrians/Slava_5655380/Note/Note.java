package GeekBrians.Slava_5655380.Note;

import java.util.Date;

public class Note {
    private MetaData meta;
    private Content content;

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
    private static class Content {
        public StringBuilder content;
    }
}
