package database.domain.backup;

import java.sql.Date;

public class BackUp
{

    public static final String TABLE_BACKUP = "backup";
    public static final String COLUMN_DIR = "dir";
    public static final String COLUMN_FILENAME = "file_name";
    public static final String COLUMN_TIME_CREATED = "time_created";
    public static final String COLUMN_DATE_CREATED = "date_created";

    public static final int INDEX_DIR = 1;
    public static final int INDEX_FILENAME = 2;
    public static final int INDEX_TIME_CREATED = 3;
    public static final int INDEX_DATE_CREATED = 4;


    public static final String CREATE_BACKUP_TABLE = "CREATE TABLE " + TABLE_BACKUP + " (" + COLUMN_DIR + " varchar(255) NOT NULL PRIMARY KEY, " +
            COLUMN_FILENAME + " varchar(50) NOT NULL," + COLUMN_TIME_CREATED + " varchar(10) NOT NULL," + COLUMN_DATE_CREATED + " varchar(15) NOT NULL" + ");";

    private String dir;
    private String fileName;
    private String timeCreated;
    private String dateCreated;



    public BackUp(){}

    public BackUp(String dir, String fileName, String dateCreated, String timeCreated) {
        this.dir = dir;
        this.fileName = fileName;
        this.dateCreated = dateCreated;
        this.timeCreated = timeCreated;

    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(String dateCreated) {
        this.dateCreated = dateCreated;
    }

    public String getTimeCreated() {
        return timeCreated;
    }

    public void setTimeCreated(String timeCreated) {
        this.timeCreated = timeCreated;
    }

    public String getDir() {
        return dir;
    }

    public void setDir(String dir) {
        this.dir = dir;
    }
}
