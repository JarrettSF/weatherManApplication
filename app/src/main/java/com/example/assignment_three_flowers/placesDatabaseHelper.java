package com.example.assignment_three_flowers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;
import java.util.ArrayList;

public class placesDatabaseHelper extends SQLiteOpenHelper {


    public placesDatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;

        createStatement = "CREATE TABLE " + TABLE_NAME + " " +
                "(id integer primary key," +
                "Country text," +
                "City text," +
                "Climate text," +
                "Attire text)";

        dropStatement = "DROP TABLE IF EXISTS Places";
    }



    private Context context;

    private String createStatement;
    private String dropStatement;

    private static String DATABASE_NAME = "Places_To_Go";
    private static String TABLE_NAME = "Places";
    private static int DATABASE_VERSION = 1;


    /*
    next two methods must be created when using sqliteOpenHelper
     */
    @Override
    public void onCreate(SQLiteDatabase placeDB) {
        // This method is executed if the table does not exist
        Log.i("DBHelper-Query", createStatement);
        placeDB.execSQL(createStatement);
    }

    /*
    onUpgrade is called if there is a database present. it will take the old version
    and replace it with the new version
     */

    @Override
    public void onUpgrade(SQLiteDatabase placeDB, int oldVersionNumber, int newVersionNumber) {
        // This method is executed when the version number changes
        Log.i("DBHelper-Version", "Going from version " + oldVersionNumber + " to version " + newVersionNumber);
        Log.i("DBHelper-Query", dropStatement);
        placeDB.execSQL(dropStatement);
        onCreate(placeDB);
    }

    /*
    returns contacts in the database as an array list
     */
    public ArrayList<Place> getPlaces() {
        SQLiteDatabase DB = this.getReadableDatabase();
        Cursor res = DB.rawQuery("SELECT * FROM " + TABLE_NAME, null);

        int numRows = res.getCount();

        int col_index_id = res.getColumnIndex("id");
        int col_index_Country = res.getColumnIndex("Country");
        int col_index_City = res.getColumnIndex("City");
        int col_index_Climate = res.getColumnIndex("Climate");
        int col_index_Attire = res.getColumnIndex("Attire");

        ArrayList<Place> place = new ArrayList<>();

        if ( numRows > 0 ) {
            res.moveToFirst();

            for (int i = 0; i < numRows; i++ ) {
                Place place1 = new Place();
                place1.setId(res.getInt(col_index_id));
                place1.setCountry(res.getString(col_index_Country));
                place1.setCity(res.getString(col_index_City));
                place1.setClimate(res.getString(col_index_Climate));
                place1.setAttire(res.getString(col_index_Attire));
                place.add(place1);
                res.moveToNext();
            }
        }

        return place;
    }

    /*
  adding a contact to the list / returns true if it was stored
     */
    public boolean addPlace(Place newPlace) {
        SQLiteDatabase placeDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("Id", newPlace.getId());
        contentValues.put("Country", newPlace.getCountry());
        contentValues.put("City", newPlace.getCity());
        contentValues.put("Climate", newPlace.getClimate());
        contentValues.put("Attire", newPlace.getAttire());
        placeDB.insert(TABLE_NAME, null, contentValues);
        return true;
    }

    /*
     returns the place by Id
     */
    public Place getPlace(int placeId) {
        SQLiteDatabase placeDB = this.getReadableDatabase();
        String query = "SELECT * FROM "+TABLE_NAME+" WHERE id="+placeId;
        Log.i("DBHelper-Query", query);
        Cursor res = placeDB.rawQuery(query, null );
        if ( res.getCount() == 1 ) {
            res.moveToFirst();

            int col_index_id = res.getColumnIndex("id");
            int col_index_firstName = res.getColumnIndex("Country");
            int col_index_lastName = res.getColumnIndex("City");
            int col_index_email = res.getColumnIndex("Climate");
            int col_index_phone = res.getColumnIndex("Attire");

            Place toReturn = new Place();
            toReturn.setId(res.getInt(col_index_id));
            toReturn.setCountry(res.getString(col_index_firstName));
            toReturn.setCity(res.getString(col_index_lastName));
            toReturn.setClimate(res.getString(col_index_email));
            toReturn.setAttire(res.getString(col_index_phone));
            return toReturn;
        }
        return null;
    }

    public boolean updatePlace(Place place) {
        SQLiteDatabase placeDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("Country", place.getCountry());
        contentValues.put("City", place.getCity());
        contentValues.put("Climate", place.getClimate());
        contentValues.put("Attire", place.getAttire());
        placeDB.update("places", contentValues, "id = ? ", new String[] { Integer.toString(place.getId()) } );
        return true;
    }

    /*
    removes a place from the database
     */
    public int deletePlace(int placeId) {
        SQLiteDatabase placeDb = this.getReadableDatabase();
        return placeDb.delete(TABLE_NAME, "id = ?", new String[] {String.valueOf(placeId)} );
    }

    /*
    get new id for new place by searching the index to and picking the highest available id
     * @return the new ID
     */
    public int getNewId() {
        SQLiteDatabase placeDB = this.getReadableDatabase();
        String query = "SELECT id FROM "+TABLE_NAME;
        Log.i("DBHelper-Query", query);
        Cursor res = placeDB.rawQuery(query, null );
        int rows = res.getCount();
        if ( rows == 0 )
            return 0;

        int maxId = 0;
        res.moveToFirst();
        for ( int c = 0; c < rows; c++ ) {
            int colId = res.getColumnIndex("id");
            int currentId = res.getInt(colId);
            if ( currentId > maxId )
                maxId = currentId;
            res.moveToNext();
        }
        return (maxId+1);
    }

    /*
     * load contacts from the JSON file
     */
    public void loadJSONContacts() {
        ArrayList<Place> places = new ArrayList<>();

        // Reading the data from the JSON file
        try {
            InputStream inputStream = context.getResources().openRawResource(R.raw.places);
            int size = inputStream.available();
            byte[] buffer = new byte[size];
            inputStream.read(buffer);
            inputStream.close();
            String jsonData = new String(buffer, "UTF-8");
            JSONArray jsonArray = new JSONArray(jsonData);
            for ( int i = 0; i < jsonArray.length(); i++ ) {
                JSONObject contactObject = jsonArray.getJSONObject(i);
                Place p = new Place();
                p.setCountry(contactObject.getString("Country"));
                p.setCity(contactObject.getString("City"));
                p.setClimate(contactObject.getString("Climate"));
                p.setAttire(contactObject.getString("Attire"));
                places.add(p);
            }
        } catch (Exception e) {
            //e.printStackTrace();
            Log.i("JSON", e.toString());
            Place aPlace = new Place();
            aPlace.setCountry("USA");
            aPlace.setCity("Philadelphia");
            aPlace.setClimate("Atlantic Coast");
            aPlace.setAttire("Light Jacket");
            places.add(aPlace);
        }

        // Loading the data in the database
        SQLiteDatabase placeDB = this.getWritableDatabase();
        for ( int i = 0; i < places.size(); i++ ) {
            Place onePlace = places.get(i);
            ContentValues values = new ContentValues();
            values.put("id", i); // We are using the position within the JSON file as ID
            values.put("Country", onePlace.getCountry());
            values.put("City", onePlace.getCity());
            values.put("Climate", onePlace.getClimate());
            values.put("Attire", onePlace.getAttire());
            placeDB.insert(TABLE_NAME, null, values);
        }
    }

    /*
     * Gets the number of records in the table and returns the # of records in the table
     */
    public int getNumberOfPlaces() {
        SQLiteDatabase placeDB = this.getReadableDatabase();
        int numRows = (int) DatabaseUtils.queryNumEntries(placeDB, TABLE_NAME);
        return numRows;
    }

    /*
     * Clears all places in the database
     */
    public void clearRecords() {
        SQLiteDatabase placeDB = this.getReadableDatabase();
        Log.i("", dropStatement);
        placeDB.execSQL(dropStatement);
        onCreate(placeDB);
    }
}

