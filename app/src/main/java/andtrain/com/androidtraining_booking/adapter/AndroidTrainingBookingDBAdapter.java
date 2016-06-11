package andtrain.com.androidtraining_booking.adapter;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.Set;

import andtrain.com.androidtraining_booking.databases.MyDatabaseActionClass;

/**
 * Created by jarvis on 6/11/16.
 */
public class AndroidTrainingBookingDBAdapter {
    public SQLiteDatabase db;
    private static String db_name = "andtrainingLogin.db";
    private static int version = 1;
    public MyDatabaseActionClass dbhelper;
    public static String tablename = "RESERVEDSLOTS";
    public static String createdb = "CREATE TABLE "+tablename+" (ID INTEGER PRIMARY KEY AUTOINCREMENT, USERNAME TEXT,SLOTDATE TEXT,SLOTTIME TEXT)"; //TODO - Not able to set  PRIMARY KEY on SLOTTIME (Multiple PKs Warning)

    public AndroidTrainingBookingDBAdapter(Context context) {
        dbhelper = new MyDatabaseActionClass(context,db_name,null,version);
    }

    public void open() {
        db = dbhelper.getWritableDatabase();
    }

    public void close() {
        db.close();
    }

    public void insertReservedSlot(String username, String date, Set<String> slots) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("SLOTDATE",date);
        contentValues.put("USERNAME",username);
        for(String slot : slots) {
            contentValues.put("SLOTTIME", slot);
            open();
            db.insert(tablename, null, contentValues);
            close();
        }
    }
    //TODO - IMPLEMENT THE UPDATE FUNCTION TO UPDATE SLOTS IN CASE OF CANCELLATION ETC..

    public ArrayList<String> getReservedSlotList(String date) {
        ArrayList<String> reservedList = new ArrayList<String>();
        open();
        Cursor csr = db.query(tablename,null,"SLOTDATE=?",new String[]{date},null,null,null);
        if(csr.getCount()<1){
            return null;
        }
        csr.moveToFirst();
        reservedList.add(csr.getString(csr.getColumnIndex("SLOTTIME")));
        while(csr.moveToNext()) {
            reservedList.add(csr.getString(csr.getColumnIndex("SLOTTIME")));
            //System.out.println("RESERVED SLOT - " + csr.getString(csr.getColumnIndex("SLOTTIME")));
        }
        close();
        return reservedList;
    }
}
