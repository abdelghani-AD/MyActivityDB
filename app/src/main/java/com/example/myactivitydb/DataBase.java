package com.example.myactivitydb;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DataBase extends SQLiteOpenHelper {

    public static final String nameDB = "data.db";
    public static final int versionDB=1;
    public DataBase(Context context){
        super(context,nameDB,null,versionDB);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table etudiant (id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "nom TEXT," +
                "prenom TEXT," +
                "age INTEGER)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS etudiant");
        this.onCreate(db);
    }

    public boolean Supprimer(int id){
        SQLiteDatabase data = this.getWritableDatabase();
        long deleteId = data.delete("etudiant","id = "+id,null);
        data.close();
        return deleteId != 0;
    }
    public boolean Ajouter(String name , String prenom , int age){
        SQLiteDatabase data = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("nom",name);
        values.put("prenom",prenom);
        values.put("age",age);
        long insertId = data.insert("etudiant",null,values);
        data.close();
        return insertId != 0;
    }
    public boolean Modifier(int id,String name , String prenom , int age){
        SQLiteDatabase data = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("nom",name);
        values.put("prenom",prenom);
        values.put("age",age);
        long update = data.update("etudiant",values,"id = "+id,null);
        return update != 0;
    }
    public ArrayList<String> getAll(){
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<String> data = new ArrayList<>();
        Cursor cursor = db.query("etudiant",null,null,null,null,null,null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            data.add(cursor.getInt(0)+ " : "+cursor.getString(1)+" : "
            +cursor.getString(2)+" : "+cursor.getInt(3));
            cursor.moveToNext();
        }
        cursor.close();
        return data;
    }



    /*public Boolean AjouterEtudiant(String nom, String prenom, String filiere){
        //Creer un objet de SQLiteDataBase pour l'ecriture
        //dans la base
        SQLiteDatabase db = this.getWritableDatabase();
        //créer un objet de ContentValues pour ajouter
        ContentValues cv = new ContentValues();
        cv.put("nom",nom);
        cv.put("prenom",prenom);
        cv.put("filiere",filiere);
        //execution de la requête d'ajout
        if (db.insert("etudiant",null,cv) == -1) {
            return false;
        }
        else {
            return true;
        }
    }
    public ArrayList<String> getEtudiants() {
        ArrayList<String> liste = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        // Cursor pour récupérer les résultats de la requête SELECT
        Cursor cs = db.rawQuery("select * from etudiant", null);

        if (cs.moveToFirst()) {
            do {
                String n = cs.getColumnName(Integer.parseInt("nom"));
                String p = cs.getColumnName(Integer.parseInt("prenom"));
                String f = cs.getColumnName(Integer.parseInt("filiere"));
                liste.add(n + "     " + p + "    " + f);
            } while (cs.moveToNext());
        }
        cs.close();
        return liste;
    }*/
}
