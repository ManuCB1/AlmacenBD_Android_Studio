package com.example.almacenbd;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.almacenbd.model.Articulo;
import com.example.almacenbd.model.MiadaptadorSpinner;
import com.example.almacenbd.model.MyToolBar;

import java.util.ArrayList;
import java.util.List;

public class MainModificar extends AppCompatActivity {

    private Toolbar myToolbar5;
    private Button btnModificar;
    private TextView textNombre;
    private MiadaptadorSpinner miAdaptadorSpinner;
    private Spinner spinner;
    private BDAdaptador bdAdaptador = new BDAdaptador(this);
    private List<Articulo> listaArticulos = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_modificar);

        myToolbar5 = findViewById(R.id.myToolbar5);
        textNombre = findViewById(R.id.textNombre);
        btnModificar = findViewById(R.id.btnModificar);
        spinner = findViewById(R.id.spinner);

//        Toolbar
        myToolbar5.setOnMenuItemClickListener(item -> {
            return MyToolBar.goSelected(this, item);
        });

//        Spinner
        listaArticulos.addAll(bdAdaptador.findAll());
        miAdaptadorSpinner = new MiadaptadorSpinner(this,R.layout.spinner_items, listaArticulos);
        spinner.setAdapter(miAdaptadorSpinner);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Articulo seleccionado = (Articulo) miAdaptadorSpinner.getItem(i);
                textNombre.setText(seleccionado.getNombre());
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

//        Modificar
        btnModificar.setOnClickListener(view -> {
            Articulo seleccionado = (Articulo) spinner.getSelectedItem();
            String id = String.valueOf(seleccionado.getId());
            String nombre = textNombre.getText().toString();
            if (!nombre.isEmpty()){
                if (bdAdaptador.update(new String[]{nombre, id})){
                    cargarLista();
                    Toast.makeText(this, "Artículo Modificado", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }

    private void cargarLista(){
        listaArticulos.clear();
        listaArticulos.addAll(bdAdaptador.findAll());
        miAdaptadorSpinner.notifyDataSetChanged();
    }
}