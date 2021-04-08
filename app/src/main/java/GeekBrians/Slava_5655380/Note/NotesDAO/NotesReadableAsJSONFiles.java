package GeekBrians.Slava_5655380.Note.NotesDAO;

import com.google.gson.GsonBuilder;

import java.io.FileNotFoundException;
import java.text.ParseException;
import java.util.ArrayList;

import GeekBrians.Slava_5655380.Note.NotesDAO.FileManagement.FileManager;
import GeekBrians.Slava_5655380.Note.Note;
import GeekBrians.Slava_5655380.SimpleDateFormats;

public class NotesReadableAsJSONFiles implements NotesReadableSource, NotesWritebleSource {
    private ArrayList<Note> notes;
    private FileManager fileManager;
    private GsonBuilder builder;


    // TODO: данный метод нужен только для отладки и будет удалён после того как этот класс будет реализованн
    private void addTestNotes() {
        try {
            notes.add(
                    new Note(
                            new Note.MetaData(
                                    "Первая заметка", SimpleDateFormats.DISPLAYED_VALUE_FORMAT.parse("24-03-2021"),
                                    SimpleDateFormats.DISPLAYED_VALUE_FORMAT.parse("25-03-2021"), new String[]{"#lorem", "#sit", "#amet"},
                                    "Описание первой заметки"
                            ),
                            "Сооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооодержимое пеeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeрвой заметки"));
            notes.add(
                    new Note(
                            new Note.MetaData("Вторая заметка", SimpleDateFormats.DISPLAYED_VALUE_FORMAT.parse("24-03-2021"), SimpleDateFormats.DISPLAYED_VALUE_FORMAT.parse("25-03-2021"), new String[]{"#lorem", "#ipsum", "#dolor", "#sit"}, "Описание второй заметки"),
                            "Содержимое второй заметки"));
            notes.add(
                    new Note(
                            new Note.MetaData("Третья заметка", SimpleDateFormats.DISPLAYED_VALUE_FORMAT.parse("24-03-2021"), SimpleDateFormats.DISPLAYED_VALUE_FORMAT.parse("25-03-2021"), new String[]{"#lorem", "#ipsum"}, "Описание третьей заметки"),
                            "Содержимое третьей заметки"));
            notes.add(
                    new Note(
                            new Note.MetaData("Четвёртая заметка", SimpleDateFormats.DISPLAYED_VALUE_FORMAT.parse("24-03-2021"), SimpleDateFormats.DISPLAYED_VALUE_FORMAT.parse("25-03-2021"), new String[]{"#lorem", "#ipsum", "#amet"}, "Описание четвёртой заметки"),
                            "Содержимое четвёертой заметки"));
            notes.add(
                    new Note(
                            new Note.MetaData("Пятая заметка", SimpleDateFormats.DISPLAYED_VALUE_FORMAT.parse("24-03-2021"), SimpleDateFormats.DISPLAYED_VALUE_FORMAT.parse("25-03-2021"), new String[]{"#lorem", "#ipsum"}, "Описание пятой заметки"),
                            "Содержимое пятой заметки"));
            notes.add(
                    new Note(
                            new Note.MetaData("Шестая заметка", SimpleDateFormats.DISPLAYED_VALUE_FORMAT.parse("24-03-2021"), SimpleDateFormats.DISPLAYED_VALUE_FORMAT.parse("25-03-2021"), new String[]{"#lorem", "#ipsum"}, "Описание шестой заметки"),
                            "Содержимое шестой заметки"));
            notes.add(
                    new Note(
                            new Note.MetaData("Седьмая заметка", SimpleDateFormats.DISPLAYED_VALUE_FORMAT.parse("24-03-2021"), SimpleDateFormats.DISPLAYED_VALUE_FORMAT.parse("25-03-2021"), new String[]{"#lorem", "#ipsum"}, "Описание седьмой заметки"),
                            "Содержимое седьмой заметки"));
            notes.add(
                    new Note(
                            new Note.MetaData("Восьмая заметка", SimpleDateFormats.DISPLAYED_VALUE_FORMAT.parse("24-03-2021"), SimpleDateFormats.DISPLAYED_VALUE_FORMAT.parse("25-03-2021"), new String[]{"#lorem", "#ipsum"}, "Описание восьмой заметки"),
                            "Содержимое восьмой заметки"));
            notes.add(
                    new Note(
                            new Note.MetaData("Девятая заметка", SimpleDateFormats.DISPLAYED_VALUE_FORMAT.parse("24-03-2021"), SimpleDateFormats.DISPLAYED_VALUE_FORMAT.parse("25-03-2021"), new String[]{"#lorem", "#ipsum"}, "Описание девятой заметки"),
                            "Содержимое девятой заметки"));
            notes.add(
                    new Note(
                            new Note.MetaData("Десятая заметка", SimpleDateFormats.DISPLAYED_VALUE_FORMAT.parse("24-03-2021"), SimpleDateFormats.DISPLAYED_VALUE_FORMAT.parse("25-03-2021"), new String[]{"#lorem", "#ipsum"}, "Описание десятой заметки"),
                            "Содержимое десятой заметки"));
            notes.add(
                    new Note(
                            new Note.MetaData("Одинадцатая заметка", SimpleDateFormats.DISPLAYED_VALUE_FORMAT.parse("24-03-2021"), SimpleDateFormats.DISPLAYED_VALUE_FORMAT.parse("25-03-2021"), new String[]{"#lorem", "#ipsum"}, "Описание одинадцатой заметки"),
                            "Содержимое одинадцатой заметки"));
            notes.add(
                    new Note(
                            new Note.MetaData("двенадцатая заметка", SimpleDateFormats.DISPLAYED_VALUE_FORMAT.parse("24-03-2021"), SimpleDateFormats.DISPLAYED_VALUE_FORMAT.parse("25-03-2021"), new String[]{"#lorem", "#ipsum"}, "Описание двенадцатой заметки"),
                            "Содержимое двенадцатой заметки"));
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public NotesReadableAsJSONFiles(FileManager fileManager) {
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

    @Override
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

    @Override
    public void commit() {
        for (Note note : notes) {
            fileManager.saveIntoFile(builder.create().toJson(note), note.getMetadata().name);
        }
    }
}
