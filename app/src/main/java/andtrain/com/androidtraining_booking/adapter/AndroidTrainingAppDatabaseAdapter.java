package andtrain.com.androidtraining_booking.adapter;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.HashMap;

import andtrain.com.androidtraining_booking.databases.MyDatabaseActionClass;

/**
 * Created by jarvis on 5/25/16.
 */
public class AndroidTrainingAppDatabaseAdapter {
    public SQLiteDatabase db;
    public MyDatabaseActionClass dbhelper;
    private static String db_name = "andtrainingLogin.db";
    private static int db_version = 1;
    public static String tablename = "ANDTRAINING_LOGIN";
    public static String createdb = "CREATE TABLE "+tablename+"(" +
            " ID INTEGER PRIMARY KEY AUTOINCREMENT," +
            " USERNAME TEXT, PASSWORD TEXT, NAME TEXT, EMAIL TEXT, PHONENUM TEXT);";
    //constructor
    public AndroidTrainingAppDatabaseAdapter(Context ctx) {
        dbhelper = new MyDatabaseActionClass(ctx,db_name,null,db_version);
    }
    public AndroidTrainingAppDatabaseAdapter open() {
        db = dbhelper.getWritableDatabase();
        return this;
    }

    public SQLiteDatabase getDbInstance() {
        return db;
    }

    public void insertEntry(String username, String password, String name, String email, String phno) {
        ContentValues values = new ContentValues();
        values.put("USERNAME",username);
        values.put("PASSWORD",password);
        values.put("NAME", name);
        values.put("EMAIL", email);
        values.put("PHONENUM", phno);
        db.insert(tablename, null, values);
        System.out.println("Inserted into database successfully.");
    }

    public HashMap<String,String> getLoginCredentials(String username,String password) {
        Cursor csr = db.query(tablename,null," USERNAME=?",new String[]{username},null,null,null);
        if(csr.getCount()<1) {
            return null;
        }
        csr.moveToFirst();
        String password_db = csr.getString(csr.getColumnIndex("PASSWORD"));
        if(password!=null && !password.equals(password_db)) {
            csr.close();
            return null;
        } else {
            String name = csr.getString(csr.getColumnIndex("NAME"));
            String email = csr.getString(csr.getColumnIndex("EMAIL"));
            String phno = csr.getString(csr.getColumnIndex("PHONENUM"));
            csr.close();
            HashMap<String, String> valmap = new HashMap<>();
            valmap.put("name", name);
            valmap.put("email", email);
            valmap.put("phno", phno);
            valmap.put("username",username);
            return valmap;
        }
    }

    public void close() {
        db.close();
    }

}
