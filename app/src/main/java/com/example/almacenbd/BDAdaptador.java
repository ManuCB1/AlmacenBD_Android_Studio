package com.example.almacenbd;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.almacenbd.model.Articulo;

import java.util.ArrayList;
import java.util.List;

public class BDAdaptador {
    private Context contexto;
    private BaseDatos baseDatos;
    private SQLiteDatabase bd;
    public BDAdaptador(Context c){
        //Almacenamos el contexto
        contexto=c;
        //Creamos una instancia a la Base de Datos
        baseDatos=BaseDatos.getInstance(c);
    }
    //Método para insertar datos en la BD. Recibe los parámetros a insertar
    public long insertar(String nombre){
        //Abrimos la BD en modo lectura/escritura
        bd=baseDatos.getWritableDatabase();
        //Preparamos la información a insertar
        ContentValues contentValues = new ContentValues();
        contentValues.put("nombre",nombre);
        //Insertarmos los datos. Recogemos el resultado
        long resultado=bd.insert("articulos",null,contentValues);
        //Resultado devuelve el Id añadido
        //Cerramos la BD
        bd.close();
        //Devolvemos el resultado de la inserción
        return resultado;
    }

    public List<Articulo> findAll(){
        //Abrimos la BD en modo lectura
        bd=baseDatos.getReadableDatabase();
        //Consulta
        String query = "SELECT * FROM articulos";
        Cursor cursor = bd.rawQuery(query, null);
        List<Articulo> articulos = new ArrayList<>();
        if (cursor != null && cursor.moveToFirst()){
            do{
                int id = cursor.getInt(0);
                String nombre = cursor.getString(1);
                articulos.add(new Articulo(id,nombre));
            }while (cursor.moveToNext());
        }
        cursor.close();
        bd.close();
        return articulos;
    }

    public String findById(String id){
        //Abrimos la BD en modo lectura
        bd=baseDatos.getReadableDatabase();
        //Consulta
        String query = "SELECT nombre FROM articulos where id = ?";
        Cursor cursor = bd.rawQuery(query, new String[]{id});
        String nombre = "";
        if (cursor != null && cursor.moveToFirst()){
            nombre = cursor.getString(0);
        }
        cursor.close();
        bd.close();

        return nombre;
    }

    public boolean update(String[] parametros){
        //Abrimos la BD en modo lectura/escritura
        bd=baseDatos.getWritableDatabase();
        //Actualizar
        String query = "UPDATE articulos SET nombre = ? WHERE id = ?";
        bd.execSQL(query, parametros);
        bd.close();
        return true;
    }

    public boolean deleteById(String id) {
        //Abrimos la BD en modo lectura/escritura
        bd=baseDatos.getWritableDatabase();
        //Borrar
        String query = "DELETE  FROM articulos WHERE id = "+id;
        bd.execSQL(query);
        bd.close();
        return true;
    }
}
