package com.example.almacenbd.model;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.almacenbd.R;

import java.util.List;

public class MiadaptadorSpinner extends ArrayAdapter {
    private Context ctx;
    private int miLayout;
    private List<Articulo> miLista;
    private int posicionActual;

    public int getPosicionActual() {
        return posicionActual;
    }

    public void setPosicionActual(int posicionActual) {
        this.posicionActual = posicionActual;
    }

    public MiadaptadorSpinner(@NonNull Context context, int resource, @NonNull List objects) {
        super(context, resource, objects);
        this.ctx = context;
        this.miLayout = resource;
        this.miLista = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
//        View v = LayoutInflater.from(ctx).inflate(miLayout, parent, false);
        View v = LayoutInflater.from(ctx).inflate(R.layout.spinner_items, parent, false);

//        TextView id = v.findViewById(R.id.textId_spinner);
        TextView nombre = v.findViewById(R.id.textNombre_spinner);
        Articulo elementoActual = miLista.get(position);
        if (elementoActual != null) {
//            id.setText(elementoActual.getId());
            nombre.setText(elementoActual.getNombre());
            setPosicionActual(position);
        }

        return v;
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return getView(position, convertView, parent);
    }
}
