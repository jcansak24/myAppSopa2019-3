package com.example.myappsopa;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import androidx.lifecycle.ViewModelStore;

public class Continuar extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.continuar);
        Bundle datos = getIntent().getExtras();
        String nomb = datos.getString("nombre");
    }


    @Override public boolean onCreateOptionsMenu(Menu mimenu){
        getMenuInflater().inflate(R.menu.menu, mimenu);
        return true;
    }

    @Override public boolean onOptionsItemSelected(MenuItem optionMenu){
        MenuJuego mj = new MenuJuego();
        RegistroUser ru = new RegistroUser();
        int id = optionMenu.getItemId();
        if(id==R.id.configuracion){
            return true;
        }
        if(id==R.id.creditos){
            mj.ejecutarCreditos(null);
            return true;
        }
        if(id==R.id.salirJuego){
            ru.ejecutarMenu(null);
            return true;
        }
        return super.onOptionsItemSelected(optionMenu);
    }
}
