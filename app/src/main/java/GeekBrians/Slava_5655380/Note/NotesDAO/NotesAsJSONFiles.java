package GeekBrians.Slava_5655380.Note.NotesDAO;

import com.google.gson.GsonBuilder;

import java.io.FileNotFoundException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import GeekBrians.Slava_5655380.Note.FileManagement.FileManager;
import GeekBrians.Slava_5655380.Note.Note;
import GeekBrians.Slava_5655380.Note.NotesSource;

public class NotesAsJSONFiles implements NotesSource {
    private ArrayList<Note> notes;
    private FileManager fileManager;
    private GsonBuilder builder;

    private void addTestNotes() {
        try {
            notes.add(
                    new Note(
                            new Note.MetaData(
                                    "Первая заметка", new SimpleDateFormat("dd-MM-yyyy").parse("24-03-2021"),
                                    new SimpleDateFormat("dd-MM-yyyy").parse("25-03-2021"), new String[]{"#lorem", "#sit", "#amet"},
                                    "Описание первой заметки"
                            ),
                            "Сооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооодержимое пеeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeрвой заметки"));
            notes.add(
                    new Note(
                            new Note.MetaData("Вторая заметка", new SimpleDateFormat("dd-MM-yyyy").parse("24-03-2021"), new SimpleDateFormat("dd-MM-yyyy").parse("25-03-2021"), new String[]{"#lorem", "#ipsum", "#dolor", "#sit"}, "Описание второй заметки"),
                            "Содержимое второй заметки"));
            notes.add(
                    new Note(
                            new Note.MetaData("Третья заметка", new SimpleDateFormat("dd-MM-yyyy").parse("24-03-2021"), new SimpleDateFormat("dd-MM-yyyy").parse("25-03-2021"), new String[]{"#lorem", "#ipsum"}, "Описание третьей заметки"),
                            "Содержимое третьей заметки"));
            notes.add(
                    new Note(
                            new Note.MetaData("Четвёртая заметка", new SimpleDateFormat("dd-MM-yyyy").parse("24-03-2021"), new SimpleDateFormat("dd-MM-yyyy").parse("25-03-2021"), new String[]{"#lorem", "#ipsum", "#amet"}, "Описание четвёртой заметки"),
                            "Содержимое четвёертой заметки"));
            notes.add(
                    new Note(
                            new Note.MetaData("Пятая заметка", new SimpleDateFormat("dd-MM-yyyy").parse("24-03-2021"), new SimpleDateFormat("dd-MM-yyyy").parse("25-03-2021"), new String[]{"#lorem", "#ipsum"}, "Описание пятой заметки"),
                            "Содержимое пятой заметки"));
            notes.add(
                    new Note(
                            new Note.MetaData("Шестая заметка", new SimpleDateFormat("dd-MM-yyyy").parse("24-03-2021"), new SimpleDateFormat("dd-MM-yyyy").parse("25-03-2021"), new String[]{"#lorem", "#ipsum"}, "Описание шестой заметки"),
                            "Содержимое шестой заметки"));
            notes.add(
                    new Note(
                            new Note.MetaData("Седьмая заметка", new SimpleDateFormat("dd-MM-yyyy").parse("24-03-2021"), new SimpleDateFormat("dd-MM-yyyy").parse("25-03-2021"), new String[]{"#lorem", "#ipsum"}, "Описание седьмой заметки"),
                            "Содержимое седьмой заметки"));
            notes.add(
                    new Note(
                            new Note.MetaData("Восьмая заметка", new SimpleDateFormat("dd-MM-yyyy").parse("24-03-2021"), new SimpleDateFormat("dd-MM-yyyy").parse("25-03-2021"), new String[]{"#lorem", "#ipsum"}, "Описание восьмой заметки"),
                            "Содержимое восьмой заметки"));
            notes.add(
                    new Note(
                            new Note.MetaData("Девятая заметка", new SimpleDateFormat("dd-MM-yyyy").parse("24-03-2021"), new SimpleDateFormat("dd-MM-yyyy").parse("25-03-2021"), new String[]{"#lorem", "#ipsum"}, "Описание девятой заметки"),
                            "Содержимое девятой заметки"));
            notes.add(
                    new Note(
                            new Note.MetaData("Десятая заметка", new SimpleDateFormat("dd-MM-yyyy").parse("24-03-2021"), new SimpleDateFormat("dd-MM-yyyy").parse("25-03-2021"), new String[]{"#lorem", "#ipsum"}, "Описание десятой заметки"),
                            "Содержимое десятой заметки"));
            notes.add(
                    new Note(
                            new Note.MetaData("Одинадцатая заметка", new SimpleDateFormat("dd-MM-yyyy").parse("24-03-2021"), new SimpleDateFormat("dd-MM-yyyy").parse("25-03-2021"), new String[]{"#lorem", "#ipsum"}, "Описание одинадцатой заметки"),
                            "Содержимое одинадцатой заметки"));
            notes.add(
                    new Note(
                            new Note.MetaData("двенадцатая заметка", new SimpleDateFormat("dd-MM-yyyy").parse("24-03-2021"), new SimpleDateFormat("dd-MM-yyyy").parse("25-03-2021"), new String[]{"#lorem", "#ipsum"}, "Описание двенадцатой заметки"),
                            "Содержимое двенадцатой заметки"));
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public NotesAsJSONFiles(FileManager fileManager) {
        this.fileManager = fileManager;

        builder = new GsonBuilder();
        notes = new ArrayList<>();
        if (fileManager.fileList().length > 0) {
            for (String fileName : fileManager.fileList()) {
                try {
                    notes.add(builder.create().fromJson(fileManager.readFromFile(fileName), Note.class));
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
        } else {
            addTestNotes();
        }

    }

    @Override
    public Note getNoteData(int position) {
        return notes.get(position);
    }

    @Override
    public int size() {
        return notes.size();
    }

    public void addNote(Note note) {
        for(int i = 0; i < notes.size(); i++){
            if(notes.get(i).getMetadata().name.equals(note.getMetadata().name)){
                // TODO: определить изменённые поля, для изменённых полей добавить запрос(выполняймый в .commit) на изменение значения этого поля в SQLite базе
                notes.set(i, note);
                return;
            }
        }
        notes.add(note);
    }

    public void commit() {
        for (Note note : notes) {
            fileManager.saveIntoFile(builder.create().toJson(note), note.getMetadata().name);
        }
    }
}
