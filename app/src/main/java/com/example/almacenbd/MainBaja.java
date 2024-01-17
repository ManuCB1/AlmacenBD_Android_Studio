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
            if (bdAdaptador.deleteById(id))
//                Toast.makeText(this, "Artículo Eliminado", Toast.LENGTH_SHORT).show();
                Log.i("Eliminado", "true");
        }
    }
}