package com.example.almacenbd;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.Menu;


import com.example.almacenbd.model.MyToolBar;
public class MainActivity extends AppCompatActivity {

    private Toolbar myToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        Toolbar
        myToolbar = findViewById(R.id.myToolbar);
        myToolbar.setOnMenuItemClickListener(item -> {
            return MyToolBar.goSelected(this, item);
        });
    }
}