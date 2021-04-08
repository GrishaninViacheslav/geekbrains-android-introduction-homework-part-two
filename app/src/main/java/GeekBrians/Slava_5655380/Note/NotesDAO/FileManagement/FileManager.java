package GeekBrians.Slava_5655380.Note.NotesDAO.FileManagement;

import java.io.FileNotFoundException;

public interface FileManager {
    void saveIntoFile(String value, String fileName);
    String readFromFile(String fileName) throws FileNotFoundException;
    String[] fileList();
}
