package com.example.guessthecharacterv2;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
// This class manages the SQLite database for the application
public class DatabaseHelper extends SQLiteOpenHelper {
    // Database Name
    public static final String DATABASE_NAME = "ElDataBase.db";
    // Database Version
    private static final int DATABASE_VERSION = 1;

    public static final String TABLE_USERS = "Users";

    // user data columns
    public static final String COLUMN_USER_NAME = "UserName";
    public static final String COLUMN_USER_PWD = "UserPassword";
    public static final String COLUMN_USER_EMAIL = "UserEmail";
    public static final String COLUMN_USER_PHONE = "UserPhone";

    //crates the table with the columns
    private static final String CREATE_TABLE_USERS = "CREATE TABLE "
            + TABLE_USERS + "("
            + COLUMN_USER_NAME + " TEXT, "
            + COLUMN_USER_PWD + " TEXT, "
            + COLUMN_USER_EMAIL + " TEXT"
            + ")";

    // Initializes a new DatabaseHelper object, which sets up the connection to the SQLite database with the specified name and version.
    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    // Creates the database table if it doesn't exist.
    @Override
    public void onCreate(SQLiteDatabase db) {
        // Creating the tables
        db.execSQL(CREATE_TABLE_USERS);
    }
    // Upgrades the database by dropping the old table and creating a new one.
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Dropping older table if exists and creating a new one
        if (oldVersion != newVersion) {
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);
            onCreate(db);
        }
    }
    // Inserts user details into the database.
    public void saveUserToDB(UserDetails userDetails)  {
        ContentValues cv = new ContentValues();
        cv.put(DatabaseHelper.COLUMN_USER_NAME, userDetails.getUserName());
        cv.put(DatabaseHelper.COLUMN_USER_PWD, userDetails.getUserPwd());
        cv.put(DatabaseHelper.COLUMN_USER_EMAIL,userDetails.getUserEmail());

        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(DatabaseHelper.TABLE_USERS, null, cv);
        db.close();

    }

    // Method to check if username exists in DB
    public boolean checkUsernameExists(String username) {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + TABLE_USERS + " WHERE " + COLUMN_USER_NAME + " = ?";
        Cursor cursor = db.rawQuery(query, new String[]{username});

        boolean exists = (cursor.getCount() > 0);
        cursor.close();
        return exists;
    }
    // Validates user login credentials against the database.
    public boolean validateLogin(String username, String password) {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + TABLE_USERS + " WHERE " + COLUMN_USER_NAME + " = ? AND " + COLUMN_USER_PWD + " = ?";
        Cursor cursor = db.rawQuery(query, new String[]{username, password});

        boolean isValid = cursor.getCount() > 0;
        cursor.close();
        return isValid;
    }


}
