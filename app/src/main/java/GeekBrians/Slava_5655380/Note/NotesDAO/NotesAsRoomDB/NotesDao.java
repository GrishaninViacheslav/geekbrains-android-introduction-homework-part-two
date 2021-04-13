package GeekBrians.Slava_5655380.Note.NotesDAO.NotesAsRoomDB;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface NotesDao {
    @Query("SELECT * FROM NoteRoomEntity")
    List<NoteRoomEntity> getAll();

    @Query("SELECT * FROM NoteRoomEntity WHERE nid IN (:userIds)")
    List<NoteRoomEntity> loadAllByIds(int[] userIds);

    @Query("SELECT * FROM NoteRoomEntity WHERE name LIKE :name LIMIT 1")
    NoteRoomEntity findByName(String name);

    @Query("SELECT EXISTS(SELECT * FROM NoteRoomEntity WHERE nid = :nid)")
    boolean isRowIsExist(int nid);

    @Query("SELECT COUNT(*) FROM NoteRoomEntity")
    int getDataCount();

    @Insert
    void insertAll(NoteRoomEntity... userRoomEntities);

    @Update
    void update(NoteRoomEntity noteRoomEntity);

    @Delete
    void delete(NoteRoomEntity noteRoomEntity);
}
