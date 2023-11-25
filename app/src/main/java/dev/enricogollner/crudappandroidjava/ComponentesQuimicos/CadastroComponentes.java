package dev.enricogollner.crudappandroidjava.ComponentesQuimicos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import dev.enricogollner.crudappandroidjava.R;

public class CadastroComponentes extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_componentes);

        ((Button) findViewById(R.id.btnCadastrarComponente)).setOnClickListener(view -> {
           // Cadastrar componente
        });
    }
}