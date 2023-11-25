package dev.enricogollner.crudappandroidjava;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import dev.enricogollner.crudappandroidjava.ComponentesQuimicos.CompostoQuimicoActivity;
import dev.enricogollner.crudappandroidjava.RegistroSaida.RegistrosSaidaActivity;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ((Button) findViewById(R.id.btnComponentesQuimicos)).setOnClickListener(view -> {
            startActivity(new Intent(this, CompostoQuimicoActivity.class));
        });

        ((Button) findViewById(R.id.btnRegistraSaida)).setOnClickListener(view -> {
            startActivity(new Intent(this, RegistrosSaidaActivity.class));
        });
    }
}