package com.puc.pos.trabalhofinal;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;

public class CrudActivity extends AppCompatActivity {

    SQLiteDatabase db;
    EditText txtNome, txtSobrenome, txtEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crud);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        txtNome=(EditText)findViewById(R.id.txtNome);
        txtSobrenome=(EditText)findViewById(R.id.txtSobrenome);
        txtEmail=(EditText)findViewById(R.id.txtEmail);

        db=openOrCreateDatabase("CrudDB", Context.MODE_PRIVATE, null);
        db.execSQL("CREATE TABLE IF NOT EXISTS Cadastro(_id INTEGER PRIMARY KEY AUTOINCREMENT, Nome VARCHAR, Sobrenome VARCHAR, Email VARCHAR);");

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(txtNome.getText().toString().trim().length()==0||
                        txtSobrenome.getText().toString().trim().length()==0||
                        txtEmail.getText().toString().trim().length()==0)
                {
                    Snackbar.make(view, "Preencha todos os campos.", Snackbar.LENGTH_LONG).setAction("Action", null).show();
                    return;
                }

                db.execSQL("INSERT INTO Cadastro (Nome, Sobrenome, Email) VALUES('" + txtNome.getText() + "','" + txtSobrenome.getText() + "','" + txtEmail.getText() + "');");
                Snackbar.make(view, "Registro salvo com sucesso.", Snackbar.LENGTH_LONG).setAction("Action", null).show();
                clearText();
            }
        });
    }

    public void clearText()
    {
        txtNome.setText("");
        txtSobrenome.setText("");
        txtEmail.setText("");
    }
}