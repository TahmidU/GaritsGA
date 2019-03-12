package util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Log
{
    private static String location = "logs";
    private static String fileName = "/logs.txt";

    /**
     * @param args write argument to log.
     */
    @SuppressWarnings("ResultOfMethodCallIgnored")
    public static void write(String args)
    {
        //Time formatter
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss");
        File file = new File(location);

        if(!file.exists())
        {
            file.mkdir();
        }

        //Write to logs.txt
        try {
            PrintWriter printWriter = new PrintWriter(new FileOutputStream(new File(location+fileName), true));
            printWriter.write(simpleDateFormat.format(calendar.getTime()) + " : " +  args + "\n");
            System.out.println(simpleDateFormat.format(calendar.getTime()) + " : " +  args);
            printWriter.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static String getLocation() {
        return location;
    }

    public static void setLocation(String location) {
        Log.location = location;
    }

    public static String getFileName() {
        return fileName;
    }

    public static void setFileName(String fileName) {
        Log.fileName = fileName;
    }
}
