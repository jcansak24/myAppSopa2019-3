package com.example.myappsopa;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class RegistroUser extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registro);
        Bundle datos = getIntent().getExtras();
        String nomb = datos.getString("nombre");
    }

    public void ejecutarMenu(View view){
        Intent i = new Intent(this, MenuJuego.class);
        startActivity(i);
    }
}
