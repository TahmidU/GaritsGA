package util;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class DBDateHelper
{
    public static final SimpleDateFormat DBDate = new SimpleDateFormat("yyyy-MM-dd");

    public static final String DB_DATE_FORMAT = "yyyy-MM-dd";
    public static final String UK = "dd/MM/yyyy";

    public static Date parseDate(String s)
    {
        return Date.valueOf(s);
    }
    
    public static String getInFormat(String format, Date date)
    {
        return new SimpleDateFormat(format).format(date);
    }

    public static Date parseCurrentDate()
    {
        return Date.valueOf(DBDate.format(Calendar.getInstance().getTime()));
    }
}
