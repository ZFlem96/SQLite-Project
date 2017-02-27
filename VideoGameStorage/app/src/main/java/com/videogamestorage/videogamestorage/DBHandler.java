package com.videogamestorage.videogamestorage;

/**
 * Created by ZFleezy on 2/21/2017.
 */

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;


public class DBHandler extends SQLiteOpenHelper {
    // Database Version
    private static final int DATABASE_VERSION = 1;
    // Database Name
    private static final String DATABASE_NAME = "gamesInfo";
    // Contacts table name
    private static final String TABLE_GAMES = "games";

    public static String getKeyId() {
        return KEY_ID;
    }

    public static String getKeyName() {
        return KEY_NAME;
    }
    public static String getKeyGRy() {
        return KEY_G_RY;
    }

    // games Table Columns names
    private static final String KEY_ID = "_id";
    private static final String KEY_NAME = "name";
    private static final String KEY_G_RY = "game_relase_year";


    public DBHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_GAMES + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_NAME + " TEXT,"
                + KEY_G_RY + " TEXT" + ")";
        db.execSQL(CREATE_CONTACTS_TABLE);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
// Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_GAMES);
// Creating tables again
        onCreate(db);
    }
    // Adding new game
    public void addGame(Game game) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, game.getName()); // game Name
        values.put(KEY_G_RY, game.getReleaseYear()); // game Phone Number

// Inserting Row
        db.insert(TABLE_GAMES, null, values);
        db.close(); // Closing database connection
    }
    // Getting one game
    public Game getGame(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_GAMES, new String[]{KEY_ID,
                        KEY_NAME, KEY_G_RY}, KEY_ID + "=?",
                new String[]{String.valueOf(id)}, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        Game item = new Game(Integer.parseInt(cursor.getString(0)),
                cursor.getString(1), cursor.getString(2));
// return game
        return item;
    }
    // Getting All games
    public List<Game> getAllGames() {
        List<Game> gameList = new ArrayList<Game>();
// Select All Query
        String selectQuery = "SELECT * FROM " + TABLE_GAMES;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

// looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Game game = new Game();
                game.setId(Integer.parseInt(cursor.getString(0)));
                game.setName(cursor.getString(1));
                game.setReleaseYear(cursor.getString(2));
// Adding contact to list
                gameList.add(game);
            } while (cursor.moveToNext());
        }

// return contact list
        return gameList;
    }
    public Cursor getAllGamesCursor(){
        String selectQuery = "SELECT * FROM " + TABLE_GAMES;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        return cursor;
    }
    // Getting games Count
    public int getGamesCount() {
        String countQuery = "SELECT * FROM " + TABLE_GAMES;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();

// return count
        return cursor.getCount();
    }
    // Updating a game
    public int updateGame(Game game) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_NAME, game.getName());
        values.put(KEY_G_RY, game.getReleaseYear());

// updating row
        return db.update(TABLE_GAMES, values, KEY_ID + " = ?",
                new String[]{String.valueOf(game.getId())});
    }

    // Deleting a game
    public void deleteGame(Game game) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_GAMES, KEY_ID + " = ?",
                new String[] { String.valueOf(game.getId()) });
        db.close();
    }


}