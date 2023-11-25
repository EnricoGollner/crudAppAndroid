package dev.enricogollner.crudappandroidjava.ComponentesQuimicos;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.LinkedList;
import java.util.List;

import dev.enricogollner.crudappandroidjava.MainActivity;
import dev.enricogollner.crudappandroidjava.R;
import dev.enricogollner.crudappandroidjava.models.CompostoQuimico;

public class CompostoQuimicoActivity extends AppCompatActivity {
    public static List<CompostoQuimico> listaCompostos = new LinkedList();
    public static Integer compostoSelecionado = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compostos_quimicos);

        atualizaLista();

        ((Button) findViewById(R.id.btnAbreCadastro)).setOnClickListener(view -> {
            compostoSelecionado = null;
            startActivity(new Intent(this, CadastroComponentes.class));
        });
    }


    @Override
    protected void onResume() {
        super.onResume();
        atualizaLista();
    }


    private void atualizaLista() {
        ArrayAdapter<CompostoQuimico> adf = new ArrayAdapter<CompostoQuimico>(this, android.R.layout.simple_list_item_1, listaCompostos) {
            @NonNull
            @Override
            public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                View view = super.getView(position, convertView, parent);
                TextView textView = (TextView) view.findViewById(android.R.id.text1);
                textView.setTextColor(Color.BLUE);
                textView.setTextSize(30);
                return view;
            }
        };
        ListView lf = (ListView) findViewById(R.id.lvComponentes);
        lf.setAdapter(adf);
        lf.setOnItemClickListener((adapterView, view, i, l) -> {
            compostoSelecionado = i;
            startActivity(new Intent(this, MainActivity.class));  // TO-DO - alterar para ir para a tela de cadastro de componentes
        });
    }
}