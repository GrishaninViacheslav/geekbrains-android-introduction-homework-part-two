package GeekBrians.Slava_5655380.Note;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.LinkedList;
import java.util.List;

public class NotesDAO {
    public List<Note> get(){
        LinkedList<Note> notes = new LinkedList<>();
        try {
            notes.add(
                    new Note(
                            new Note.MetaData(
                                    "Первая заметка", new SimpleDateFormat("dd-MM-yyyy").parse("24-03-2021"),
                                    new SimpleDateFormat("dd-MM-yyyy").parse("25-03-2021"), new String[]{"#lorem", "#sit", "#amet"},
                                    "Описание первой заметки"
                            ),
                            "Содержимое первой заметки"));
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
                            new Note.MetaData("Четвёртая заметка", new SimpleDateFormat("dd-MM-yyyy").parse("24-03-2021"), new SimpleDateFormat("dd-MM-yyyy").parse("25-03-2021"), new String[]{"#lorem", "#ipsum",  "#amet"}, "Описание четвёртой заметки"),
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
        return notes;
    }
}
