package com.example.myappsopa;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void ejecutarRegistro(View view){
        EditText cuadro1 = (EditText) findViewById(R.id.nombre);
        //int n1 = Integer.parseInt(cuadro1.getText().toString()); //convertimos a int el string del texto
        //String name = cuadro1.getText().toString();
        Intent i = new Intent(this, RegistroUser.class);
        i.putExtra("nombre","name");
        startActivity(i);
    }
}
