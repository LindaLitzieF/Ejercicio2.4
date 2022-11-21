package com.example.ejercicio24;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import com.example.ejercicio24.Objetos.Adap;
import com.example.ejercicio24.Objetos.Sig;
import com.example.ejercicio24.SQLiteConexion.SQLiteConexion;
import com.example.ejercicio24.Transacciones.Transacciones;

import java.util.ArrayList;
import java.util.List;

public class ActivityListaFirma extends AppCompatActivity {


    private RecyclerView recyclerView;
    private Adap reviewadapter;
    ArrayList<Sig> lista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_firma);

        recyclerView = findViewById(R.id.viewRe);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        reviewadapter = new Adap(ObtenerFirma());
        recyclerView.setAdapter(reviewadapter);
    }

    private List<Sig> ObtenerFirma() {
        SQLiteConexion conexion = new SQLiteConexion(this, Transacciones.NameDataBase,null,1);
        SQLiteDatabase db= conexion.getReadableDatabase();
        Sig firma;
        lista= new ArrayList<>();
        Cursor cursor = db.rawQuery("SELECT * FROM " + Transacciones.tablaFirma, null);

        while(cursor.moveToNext())
        {
            firma = new Sig();
            firma.setId(cursor.getInt(0));
            firma.setDescripcion(cursor.getString(2));
            firma.setFirma(cursor.getBlob(1));
            lista.add(firma);
        }
        cursor.close();
        return lista;
    }
}