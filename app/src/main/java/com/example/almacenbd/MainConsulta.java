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

public class MainConsulta extends AppCompatActivity {

    private Toolbar myToolbar;
    private Button btnConsultar;
    private BDAdaptador bdAdaptador;
    private TextView textId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_consulta);

        myToolbar = findViewById(R.id.myToolbar4);
        btnConsultar = findViewById(R.id.btnConsultar);
        textId = findViewById(R.id.textId);

//        Toolbar
        myToolbar.setOnMenuItemClickListener(item -> {
            return MyToolBar.goSelected(this, item);
        });

//        Consulta
        btnConsultar.setOnClickListener(view -> {
            consultaArticuloBD();
        });

    }

    private void consultaArticuloBD() {
        String id = textId.getText().toString();

        if (!id.isEmpty()){
            bdAdaptador = new BDAdaptador(this);
            String nombreRecogido = bdAdaptador.findById(id);
            Toast.makeText(this, "Artículo: "+nombreRecogido, Toast.LENGTH_SHORT).show();
                Log.i("Artículo", nombreRecogido);
        }
    }
}