package com.example.myappsopa;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MenuJuego extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu);
    }
    public void ejecutarComenzar(View view){
        Intent i = new Intent(this, Comenzar.class);
        startActivity(i);
    }

    public void ejecutarContinuar(View view){
        Intent i = new Intent(this, Continuar.class);
        startActivity(i);
    }

    public void ejecutarCreditos(View view){
        Intent i = new Intent(this, Creditos.class);
        startActivity(i);
    }

    public void ejecutarSalir(View view){
        finish();
    }
}
