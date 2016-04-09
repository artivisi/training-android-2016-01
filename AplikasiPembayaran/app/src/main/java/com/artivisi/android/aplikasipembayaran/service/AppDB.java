package com.artivisi.android.aplikasipembayaran.service;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by jimmy on 08/04/16.
 */
public class AppDB {

    private static final String dbName = "Pembayaran.db";
    private static final String tableProdukName = "produk";
    private static final String kolomIdProduk = "id";
    private static final String kolomKodeProduk = "kode";
    private static final String kolomNamaProduk = "nama";
    private static final String kolomLastUpdateProduk = "terakhir_update";
    private static final int dbVersion = 1;
    private Context ctx;
    private DBHelper dbHelper;
    SQLiteDatabase db;

    private static final String CREATE_TABLE="create table " + tableProdukName
            + " (" + kolomIdProduk + " text primary key, "
            + kolomKodeProduk +" text not null,"
            + kolomNamaProduk +" text not null,"
            + kolomLastUpdateProduk +" text not null)";

    public AppDB(Context context) {
        this.ctx = context;
        dbHelper = new DBHelper(ctx);
        Log.i("DB","App DB");
    }

    private class DBHelper extends SQLiteOpenHelper {

        public DBHelper(Context context) {
            super(context, dbName, null, dbVersion);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            try {
                Log.i("DB", "CREATE TABLE");
                db.execSQL(CREATE_TABLE);
            }catch (SQLException e){
                e.printStackTrace();
            }
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
//            db.execSQL(SQL_DELETE_ENTRIES);
//            onCreate(db);
        }

        @Override
        public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
//            onUpgrade(db, oldVersion, newVersion);
        }

    }

    public AppDB open() throws SQLException{
        db = dbHelper.getWritableDatabase();
        return this;
    }

    //close databse
    public void close(){
        dbHelper.close();
    }

    public long insertProduk(String id, String nama, String kode,String lastUpdate){
        ContentValues initialValues = new ContentValues();
        initialValues.put(kolomIdProduk, id);
        initialValues.put(kolomKodeProduk, nama);
        initialValues.put(kolomNamaProduk, kode);
        initialValues.put(kolomLastUpdateProduk, lastUpdate);

        return db.insert(tableProdukName, null, initialValues);
    }

    public Cursor getAllProduk(){
        return db.query(tableProdukName,
                new String[]{kolomIdProduk,
                        kolomKodeProduk,
                        kolomNamaProduk,
                        kolomLastUpdateProduk}, null, null, null, null, null);
    }
}
