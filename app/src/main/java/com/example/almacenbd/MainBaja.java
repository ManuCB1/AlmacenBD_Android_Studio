package com.example.almacenbd;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.almacenbd.model.MyToolBar;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainBaja extends AppCompatActivity {

    private Toolbar myToolbar;
    private Button btnEliminar;
    private BDAdaptador bdAdaptador;
    private TextView textId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_baja);

        myToolbar = findViewById(R.id.myToolbar3);
        btnEliminar = findViewById(R.id.btnEliminar);
        textId = findViewById(R.id.textId);

//        Toolbar
        myToolbar.setOnMenuItemClickListener(item -> {
            return MyToolBar.goSelected(this, item);
        });

//        Eliminar
        btnEliminar.setOnClickListener(view -> {
            eliminarArticuloBD();
        });
    }

    private void eliminarArticuloBD() {
        String id = textId.getText().toString();

        if (!id.isEmpty()){
            bdAdaptador = new BDAdaptador(this);
            mostrarDialog(id);
        }
    }

    private void mostrarDialog(String id){
        String nombre = bdAdaptador.findById(id);
        if (!nombre.isEmpty()){
            AlertDialog.Builder dialogo = new AlertDialog.Builder(this);
            dialogo.setTitle("¿Borrar Artículo?");
            dialogo.setMessage("¿Borrar " + nombre + " de Artículos?");
            dialogo.setPositiveButton("Borrar", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    if (bdAdaptador.deleteById(id))
                        Toast.makeText(getApplicationContext(), "Artículo Eliminado", Toast.LENGTH_SHORT).show();
                    else
                        Toast.makeText(getApplicationContext(), "Error al eliminar artículo", Toast.LENGTH_SHORT).show();
                }
            });
            dialogo.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.dismiss();
                }
            });
            dialogo.create().show();
        }

    }
}