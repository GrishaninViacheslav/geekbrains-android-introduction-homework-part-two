package GeekBrians.Slava_5655380.Note;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.LinkedList;
import java.util.List;

public class NotesDAO {
    public List<Note.MetaData> getMetaData(){
        LinkedList<Note.MetaData> notesMeta = new LinkedList<>();
        try {
            notesMeta.add(new Note.MetaData("Первая заметка", new SimpleDateFormat("dd-MM-yyyy").parse("24-03-2021"), new SimpleDateFormat("dd-MM-yyyy").parse("25-03-2021"), new String[]{"#lorem", "#sit", "#amet"}, "Описание первой заметки"));
            notesMeta.add(new Note.MetaData("Вторая заметка", new SimpleDateFormat("dd-MM-yyyy").parse("24-03-2021"), new SimpleDateFormat("dd-MM-yyyy").parse("25-03-2021"), new String[]{"#lorem", "#ipsum", "#dolor", "#sit"}, "Описание второй заметки"));
            notesMeta.add(new Note.MetaData("Третья заметка", new SimpleDateFormat("dd-MM-yyyy").parse("24-03-2021"), new SimpleDateFormat("dd-MM-yyyy").parse("25-03-2021"), new String[]{"#lorem", "#ipsum"}, "Описание третьей заметки"));
            notesMeta.add(new Note.MetaData("Четвёртая заметка", new SimpleDateFormat("dd-MM-yyyy").parse("24-03-2021"), new SimpleDateFormat("dd-MM-yyyy").parse("25-03-2021"), new String[]{"#lorem", "#ipsum",  "#amet"}, "Описание четвёртой заметки"));
            notesMeta.add(new Note.MetaData("Пятая заметка", new SimpleDateFormat("dd-MM-yyyy").parse("24-03-2021"), new SimpleDateFormat("dd-MM-yyyy").parse("25-03-2021"), new String[]{"#lorem", "#ipsum"}, "Описание пятой заметки"));
            notesMeta.add(new Note.MetaData("Шестая заметка", new SimpleDateFormat("dd-MM-yyyy").parse("24-03-2021"), new SimpleDateFormat("dd-MM-yyyy").parse("25-03-2021"), new String[]{"#lorem", "#ipsum"}, "Описание шестой заметки"));
            notesMeta.add(new Note.MetaData("Седьмая заметка", new SimpleDateFormat("dd-MM-yyyy").parse("24-03-2021"), new SimpleDateFormat("dd-MM-yyyy").parse("25-03-2021"), new String[]{"#lorem", "#ipsum"}, "Описание седьмой заметки"));
            notesMeta.add(new Note.MetaData("Восьмая заметка", new SimpleDateFormat("dd-MM-yyyy").parse("24-03-2021"), new SimpleDateFormat("dd-MM-yyyy").parse("25-03-2021"), new String[]{"#lorem", "#ipsum"}, "Описание восьмой заметки"));
            notesMeta.add(new Note.MetaData("Девятая заметка", new SimpleDateFormat("dd-MM-yyyy").parse("24-03-2021"), new SimpleDateFormat("dd-MM-yyyy").parse("25-03-2021"), new String[]{"#lorem", "#ipsum"}, "Описание девятой заметки"));
            notesMeta.add(new Note.MetaData("Десятая заметка", new SimpleDateFormat("dd-MM-yyyy").parse("24-03-2021"), new SimpleDateFormat("dd-MM-yyyy").parse("25-03-2021"), new String[]{"#lorem", "#ipsum"}, "Описание десятой заметки"));
            notesMeta.add(new Note.MetaData("Одинадцатая заметка", new SimpleDateFormat("dd-MM-yyyy").parse("24-03-2021"), new SimpleDateFormat("dd-MM-yyyy").parse("25-03-2021"), new String[]{"#lorem", "#ipsum"}, "Описание одинадцатой заметки"));
            notesMeta.add(new Note.MetaData("двенадцатая заметка", new SimpleDateFormat("dd-MM-yyyy").parse("24-03-2021"), new SimpleDateFormat("dd-MM-yyyy").parse("25-03-2021"), new String[]{"#lorem", "#ipsum"}, "Описание двенадцатой заметки"));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return notesMeta;
    }
}
