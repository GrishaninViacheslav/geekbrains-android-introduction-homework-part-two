package GeekBrians.Slava_5655380.Note.NotesDAO.NotesAsRoomDB;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

@Database(entities = {NoteRoomEntity.class}, version = 1)
@TypeConverters({Converters.class})
public abstract class NotesDatabase extends RoomDatabase {
    public abstract NotesDao notesDao();
}