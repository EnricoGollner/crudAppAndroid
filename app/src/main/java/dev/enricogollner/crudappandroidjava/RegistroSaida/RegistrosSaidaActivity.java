package dev.enricogollner.crudappandroidjava.RegistroSaida;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import dev.enricogollner.crudappandroidjava.R;

public class RegistrosSaidaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registros_saida);

        ((Button) findViewById(R.id.btnAbreCadastro)).setOnClickListener(view -> {
            startActivity(new Intent(this, CadastroRegistroSaidaActivity.class));
        });
    }
}