package in.alfdev.cdmp;

/**
 * Created by anupamchugh on 19/10/15.
 */
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class DBManager {

    private DatabaseHelper dbHelper;

    private Context context;

    private SQLiteDatabase database;

    public DBManager(Context c) {
        context = c;
    }

    public DBManager open() throws SQLException {
        dbHelper = new DatabaseHelper(context);
        database = dbHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        dbHelper.close();
    }

    public void insert(String office, String canvasser, String day, String formsurvey, String outlet, String telkomsel, String indosat, String axis, String xl, String three, String other) {
        ContentValues contentValue = new ContentValues();
        contentValue.put(DatabaseHelper.OFFICE, office);
        contentValue.put(DatabaseHelper.CANVASSER, canvasser);
		contentValue.put(DatabaseHelper.DAY, day);
        contentValue.put(DatabaseHelper.FORMSURVEY, formsurvey);
		contentValue.put(DatabaseHelper.OUTLET, outlet);
        contentValue.put(DatabaseHelper.TELKOMSEL, telkomsel);
		contentValue.put(DatabaseHelper.INDOSAT, indosat);
        contentValue.put(DatabaseHelper.AXIS, axis);
		contentValue.put(DatabaseHelper.XL, xl);
        contentValue.put(DatabaseHelper.THREE, three);
		contentValue.put(DatabaseHelper.OTHER, other);
        database.insert(DatabaseHelper.TABLE_NAME, null, contentValue);
    }

    public Cursor fetch() {
        String[] columns = new String[] { DatabaseHelper._ID,
										  DatabaseHelper.OFFICE,
										  DatabaseHelper.CANVASSER,
										  DatabaseHelper.DAY,
										  DatabaseHelper.FORMSURVEY,
										  DatabaseHelper.OUTLET,
										  DatabaseHelper.TELKOMSEL,
										  DatabaseHelper.INDOSAT,
										  DatabaseHelper.AXIS,
										  DatabaseHelper.XL,
										  DatabaseHelper.THREE,
										  DatabaseHelper.OTHER
										  };
        Cursor cursor = database.query(DatabaseHelper.TABLE_NAME, columns, null, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        return cursor;
    }

    public int update(long _id, String office, String canvasser, String day, String formsurvey, String outlet, String telkomsel, String indosat, String axis, String xl, String three, String other) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseHelper.OFFICE, office);
        contentValues.put(DatabaseHelper.CANVASSER, canvasser);
		contentValues.put(DatabaseHelper.DAY, day);
        contentValues.put(DatabaseHelper.FORMSURVEY, formsurvey);
		contentValues.put(DatabaseHelper.OUTLET, outlet);
        contentValues.put(DatabaseHelper.TELKOMSEL, telkomsel);
		contentValues.put(DatabaseHelper.INDOSAT, indosat);
        contentValues.put(DatabaseHelper.AXIS, axis);
		contentValues.put(DatabaseHelper.XL, xl);
        contentValues.put(DatabaseHelper.THREE, three);
		contentValues.put(DatabaseHelper.OTHER, other);
        int i = database.update(DatabaseHelper.TABLE_NAME, contentValues, DatabaseHelper._ID + " = " + _id, null);
        return i;
    }

    public void delete(long _id) {
        database.delete(DatabaseHelper.TABLE_NAME, DatabaseHelper._ID + "=" + _id, null);
    }

}
