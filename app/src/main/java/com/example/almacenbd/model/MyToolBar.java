package com.example.almacenbd.model;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.MenuItem;

import com.example.almacenbd.MainActivity;
import com.example.almacenbd.MainAlta;
import com.example.almacenbd.MainBaja;
import com.example.almacenbd.MainConsulta;
import com.example.almacenbd.MainModificar;
import com.example.almacenbd.R;

public class MyToolBar {
    public static boolean goSelected(Context context, MenuItem item){
        Intent cambiarPagina = null;
        if (item.getItemId() == R.id.item_alta && !(context instanceof MainAlta)){
            cambiarPagina = new Intent(context, MainAlta.class);
        }
        if (item.getItemId() == R.id.item_consulta && !(context instanceof MainConsulta)){
            cambiarPagina = new Intent(context, MainConsulta.class);
        }
        if (item.getItemId() == R.id.item_baja && !(context instanceof MainBaja)){
            cambiarPagina = new Intent(context, MainBaja.class);
        }
        if (item.getItemId() == R.id.item_modificar && !(context instanceof MainModificar)){
            cambiarPagina = new Intent(context, MainModificar.class);
        }
        if (cambiarPagina != null){
            context.startActivity(cambiarPagina);
//            Cerrar Paginas para no cerrrar Inicio
            if (!(context instanceof MainActivity)) {
                ((Activity) context).finish();
            }
            return true;
        }
        return false;
    }

}
