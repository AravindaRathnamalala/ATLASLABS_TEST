package aravinda.atlaslabs.hotel_booking.data.local;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.HashMap;

import aravinda.atlaslabs.hotel_booking.model.Details;

/**
 * Created by aravinda_r on 10/26/2018.
 */

public class DBHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "Hotel.db";
    public static final String HOTEL_TABLE_NAME = "hotelDetails";
    public static final String HOTEL_COLUMN_ID = "id";
    public static final String HOTEL_COLUMN_NAME = "name";
    public static final String HOTEL_COLUMN_LONGITUDE = "longitude";
    public static final String HOTEL_COLUMN_LATITUDE = "latitude";
    public static final String HOTEL_COLUMN_CONTACT = "contact";
    public static final String HOTEL_COLUMN_EMAIL = "email";
    public static final String HOTEL_COLUMN_AMOUNT = "amount";
    public static final String HOTEL_COLUMN_LOCATION = "location";
    private HashMap hp;

    public DBHelper(Context context) {
        super(context, DATABASE_NAME , null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // TODO Auto-generated method stub
        db.execSQL(
                "create table hotelDetails " +
                        "(id integer primary key, name text,longitude text,latitude text, contact text,email text,amount text,location text)"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO Auto-generated method stub
        db.execSQL("DROP TABLE IF EXISTS hotelDetails");
        onCreate(db);
    }

    public boolean insertHotel (String name, String longitude, String latitude, String contact,String email,String location,String amount) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", name);
        contentValues.put("longitude", longitude);
        contentValues.put("latitude", latitude);
        contentValues.put("contact", contact);
        contentValues.put("email", email);
        contentValues.put("location", location);
        contentValues.put("amount", amount);
        db.insert("hotelDetails", null, contentValues);
        return true;
    }


    public Cursor getData(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from hotelDetails where id="+id+"", null );
        return res;
    }

    public int numberOfRows(){
        SQLiteDatabase db = this.getReadableDatabase();
        int numRows = (int) DatabaseUtils.queryNumEntries(db, HOTEL_TABLE_NAME);
        return numRows;
    }


    public void deleteAllRaws() {

        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(HOTEL_TABLE_NAME, null, null);
        db.close();

    }


    public ArrayList<String> getLocations() {
        ArrayList<String> array_list = new ArrayList<String>();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select distinct location from hotelDetails", null );
        res.moveToFirst();

        while(res.isAfterLast() == false){
            array_list.add(res.getString(res.getColumnIndex(HOTEL_COLUMN_LOCATION)));
            res.moveToNext();
        }
        return array_list;
    }

    public ArrayList<String> getHotels(String location) {
        ArrayList<String> array_listhotels = new ArrayList<String>();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select name from hotelDetails where location ='"+location+"'", null );
        res.moveToFirst();

        while(res.isAfterLast() == false){
            array_listhotels.add(res.getString(res.getColumnIndex(HOTEL_COLUMN_NAME)));
            res.moveToNext();
        }
        return array_listhotels;


    }

    public Details getHotelDetails(String name) {
        Details details = null;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select name, " +
                "longitude," +
                "latitude," +
                "contact," +
                "email," +
                "location," +
                "amount" +
                " from hotelDetails where name ='"+name+"'", null );
        res.moveToFirst();

        if(res.moveToFirst()){
            details = new Details(res.getString(0)
                    ,res.getString(1)
                    ,res.getString(2)
                    ,res.getString(3)
                    ,res.getString(4)
                    ,res.getString(5)
                    ,res.getString(6)
                    );
        }
        return details;


    }

    public ArrayList<String> getHotelsByPrice(String amount) {
        ArrayList<String> array_listhotelsbyprice = new ArrayList<String>();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select name from hotelDetails where amount <='"+amount+"'", null );
        res.moveToFirst();

        while(res.isAfterLast() == false){
            array_listhotelsbyprice.add(res.getString(res.getColumnIndex(HOTEL_COLUMN_NAME)));
            res.moveToNext();
        }
        return array_listhotelsbyprice;


    }

}
