package andtrain.com.androidtraining_booking.databases;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import andtrain.com.androidtraining_booking.adapter.AndroidTrainingAppDatabaseAdapter;

/**
 * Created by jarvis on 5/25/16.
 */
public class MyDatabaseActionClass extends SQLiteOpenHelper {

    public MyDatabaseActionClass(Context ctx, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(ctx,name,factory,version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(AndroidTrainingAppDatabaseAdapter.createdb);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //executed when there is a version mismatch
        db.execSQL("DROP TABLE IF EXISTS " + "TEMPLATE");
        onCreate(db);
    }
}
