package com.puc.pos.trabalhofinal;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

public class ListaActivity extends AppCompatActivity {

    SQLiteDatabase db;
    private ListView lista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG).setAction("Action", null).show();
            }
        });

        //BancoController crud = new BancoController();
        //Cursor cursor = crud.CarregarDados();
        db=openOrCreateDatabase("CrudDB", Context.MODE_PRIVATE, null);
        db.execSQL("CREATE TABLE IF NOT EXISTS Cadastro(_id INTEGER PRIMARY KEY AUTOINCREMENT, Nome VARCHAR, Sobrenome VARCHAR, Email VARCHAR);");
        String[] campos = {"_id", "Nome", "Sobrenome", "Email"};
        Cursor cursor = db.query("Cadastro", campos, null, null, null, null, null, null);

        if(cursor!=null){
            cursor.moveToFirst();
        }

        db.close();

        String[] nomeCampos = new String[] {"_id", "Nome", "Email"};
        int[] idViews = new int[] {R.id.idCadastro, R.id.nome, R.id.Email};
        SimpleCursorAdapter adaptador = new SimpleCursorAdapter(getBaseContext(), R.layout.lista_layout, cursor, nomeCampos, idViews, 0);
        lista = (ListView)findViewById(R.id.listView);
        lista.setAdapter(adaptador);
    }
}