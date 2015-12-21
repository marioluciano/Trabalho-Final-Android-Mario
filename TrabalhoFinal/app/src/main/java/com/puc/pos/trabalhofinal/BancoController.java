package com.puc.pos.trabalhofinal;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;

public class BancoController extends AppCompatActivity {

    SQLiteDatabase db;

    public BancoController(){
        db= openOrCreateDatabase("CrudDB", Context.MODE_PRIVATE, null);
        db.execSQL("CREATE TABLE IF NOT EXISTS Cadastro(ID INT, Nome VARCHAR, Sobrenome VARCHAR, Email VARCHAR);");
    }

    public Cursor CarregarDados(){
        Cursor cursor;
        String[] campos = {"ID", "Nome", "Sobrenome", "Email"};
        cursor = db.query("Cadastro", campos, null, null, null, null, null, null);

        if(cursor!=null){
            cursor.moveToFirst();
        }

        db.close();
        return cursor;
    }
}