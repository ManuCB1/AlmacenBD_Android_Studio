package com.example.almacenbd;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.almacenbd.model.MyToolBar;

public class MainAlta extends AppCompatActivity {

    private Toolbar myToolbar;
    private Button btnInsertar;
    private BDAdaptador bdAdaptador;
    private TextView textNombre;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_alta);

        myToolbar = findViewById(R.id.myToolbar2);
        btnInsertar = findViewById(R.id.btnInsertar);
        textNombre = findViewById(R.id.textNombre);

//        Toolbar
        myToolbar.setOnMenuItemClickListener(item -> {
            return MyToolBar.goSelected(this, item);
        });

//        Guardar en la BD
        btnInsertar.setOnClickListener(view -> {
            añadirArticuloBD();
        });
    }

    private void añadirArticuloBD() {
        String nombre = textNombre.getText().toString();
        if (!nombre.isEmpty()){
            bdAdaptador = new BDAdaptador(this);
            long resultado = bdAdaptador.insertar(nombre);
            if (resultado!=-1){
                Toast.makeText(this, "Artículo guardado correctamente", Toast.LENGTH_SHORT).show();
                textNombre.setText("");
            }
            else Toast.makeText(this, "Error al guardar artículo", Toast.LENGTH_SHORT).show();
        }
        else Toast.makeText(this, "El campo Nombre está vacío", Toast.LENGTH_SHORT).show();
    }
}