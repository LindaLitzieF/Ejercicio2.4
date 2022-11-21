package com.example.ejercicio24;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.ejercicio24.Objetos.CapBitmap;
import com.example.ejercicio24.SQLiteConexion.SQLiteConexion;
import com.example.ejercicio24.Transacciones.Transacciones;

public class MainActivity extends AppCompatActivity {
    EditText Descripcion;
    CapBitmap firma;
    Button btnsave, btnlista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();

        btnlista.setOnClickListener(this::onClickLista);
        btnsave.setOnClickListener(this::onClickSave);
    }

    private void guardarImagen() {
        try {
            SQLiteConexion conexion = new SQLiteConexion(this, Transacciones.NameDataBase, null, 1);
            SQLiteDatabase db = conexion.getWritableDatabase();

            ContentValues valores = new ContentValues();
            valores.put(Transacciones.descripcion, Descripcion.getText().toString());
            valores.put(Transacciones.firma,firma.getBytes());

            Long resultado = db.insert(Transacciones.tablaFirma,Transacciones.id,valores);
            Toast.makeText(getApplicationContext(),"Registro Ingresado! COD:"+resultado.toString(),Toast.LENGTH_LONG).show();

            db.close();

            Descripcion.setText("");
            firma.ClearCanvas();
        }catch (SQLiteException ex){
            Toast.makeText(getApplicationContext(), "Error: "+ex.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    private void onClickLista(View v) {
        Intent intent = new Intent(getApplicationContext(), ActivityListaFirma.class);
        startActivity(intent);
        finish();
    }

    private void onClickSave(View v) {
        if (!Descripcion.getText().toString().isEmpty()) {
            guardarImagen();
        } else {
            Toast.makeText(getApplicationContext(), "Debe ingresar la descripción", Toast.LENGTH_LONG).show();
        }
    }

    private void init(){
        firma = findViewById(R.id.linearLayout);
        Descripcion = findViewById(R.id.Descripcion);
        btnsave = findViewById(R.id.btnguardar);
        btnlista = findViewById(R.id.btnlista);
    }
}