package pro.sky.telegrambot.parser;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Locale;

public class Dates {
    private static SimpleDateFormat[] formats = new SimpleDateFormat[]{
            new SimpleDateFormat("dd.MM.yyyy HH:mm"),
            new SimpleDateFormat("dd-MM-yyyy HH:mm"),
            new SimpleDateFormat("dd/MM/yyyy HH:mm"),
            new SimpleDateFormat("dd.MM.yy HH:mm"),
            new SimpleDateFormat("dd-MM-yy HH:mm"),
            new SimpleDateFormat("dd/MM/yy HH:mm"),
            new SimpleDateFormat("MM.dd.yyyy HH:mm"),
            new SimpleDateFormat("MM-dd-yyyy HH:mm"),
            new SimpleDateFormat("MM/dd/yyyy HH:mm"),
            new SimpleDateFormat("yyyy.MM.dd HH:mm"),
            new SimpleDateFormat("yyyy-MM-dd HH:mm"),
            new SimpleDateFormat("yyyy/MM/dd HH:mm"),
            new SimpleDateFormat("dd.MMM.yyyy HH:mm", Locale.ENGLISH),
            new SimpleDateFormat("dd-MMM-yyyy HH:mm", Locale.ENGLISH),
            new SimpleDateFormat("dd/MMM/yyyy HH:mm", Locale.ENGLISH),
            new SimpleDateFormat("dd.MMM.yy HH:mm", Locale.ENGLISH),
            new SimpleDateFormat("dd-MMM-yy HH:mm", Locale.ENGLISH),
            new SimpleDateFormat("dd/MMM/yy HH:mm", Locale.ENGLISH),
            new SimpleDateFormat("MMM.dd.yyyy HH:mm", Locale.ENGLISH),
            new SimpleDateFormat("MMM-dd-yyyy HH:mm", Locale.ENGLISH),
            new SimpleDateFormat("MMM/dd/yyyy HH:mm", Locale.ENGLISH),
            new SimpleDateFormat("yyyy.MMM.dd HH:mm", Locale.ENGLISH),
            new SimpleDateFormat("yyyy-MMM-dd HH:mm", Locale.ENGLISH),
            new SimpleDateFormat("yyyy/MMM/dd HH:mm", Locale.ENGLISH)

    };

    public static LocalDateTime parse(String date) {
        for (SimpleDateFormat format :formats) {
            try {
                return format.parse(date).toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
            } catch (ParseException ignored) {}
        }
        return null;
    }
}
