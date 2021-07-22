package GeekBrians.Slava_5655380.Note.NotesDAO.NotesAsRoomDB;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface NotesDao {
    @Query("SELECT * FROM NoteRoomEntity ORDER BY priority ASC LIMIT 1 OFFSET :row")
    List<NoteRoomEntity> getRow(int row);

    @Query("SELECT * FROM NoteRoomEntity ORDER BY priority DESC LIMIT 1")
    List<NoteRoomEntity> getLowestPriority();

    @Query("SELECT EXISTS(SELECT * FROM NoteRoomEntity WHERE uid = :uid)")
    boolean isRowIsExist(long uid);

    @Query("SELECT COUNT(*) FROM NoteRoomEntity")
    int getDataCount();

    @Insert
    void insertAll(NoteRoomEntity... userRoomEntities);

    @Update
    void update(NoteRoomEntity noteRoomEntity);

    @Delete
    void delete(NoteRoomEntity noteRoomEntity);
}
