package util;

import java.sql.Date;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class DBDateHelper
{
    private static SimpleDateFormat DBDate = new SimpleDateFormat("yyyy-MM-dd");

    public static Date parseDate(String s)
    {
        return Date.valueOf(s);
    }

    public static Date parseCurrentDate()
    {
        return Date.valueOf(DBDate.format(Calendar.getInstance().getTime()));
    }
}
