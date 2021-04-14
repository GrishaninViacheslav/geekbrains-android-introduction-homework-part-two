package GeekBrians.Slava_5655380.Note.NotesDAO.NotesAsRoomDB;

import androidx.room.TypeConverter;

import java.util.Date;

public class Converters {
    @TypeConverter
    public static Date fromTimestamp(Long value) {
        return value == null ? null : new Date(value);
    }

    @TypeConverter
    public static Long dateToTimestamp(Date date) {
        return date == null ? null : date.getTime();
    }

    @TypeConverter
    public static String[] formString(String str){
        return str == null ? null : str.split(SEPARATOR);
    }

    @TypeConverter
    public static String StringFromStringArray(String[] arr) {
        return arr == null ? null : arrayToString(arr, SEPARATOR);
    }

    // TODO: запретить использование символа SEPARATOR в качестве значения
    // Как можно обойтись без запрета?
    private static final String SEPARATOR = ";";

    private static String arrayToString(Object[] arr, String separator){
        StringBuilder builder = new StringBuilder();
        for(Object el : arr){
            builder.append(el + separator);
        }
        return builder.toString();
    }
}
