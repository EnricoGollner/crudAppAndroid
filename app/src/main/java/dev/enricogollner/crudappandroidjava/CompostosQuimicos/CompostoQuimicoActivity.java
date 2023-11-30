package dev.enricogollner.crudappandroidjava.CompostosQuimicos;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.LinkedList;
import java.util.List;

import dev.enricogollner.crudappandroidjava.Helper.CompostoDAO;
import dev.enricogollner.crudappandroidjava.R;
import dev.enricogollner.crudappandroidjava.models.CompostoQuimico;

public class CompostoQuimicoActivity extends AppCompatActivity {
    public static List<CompostoQuimico> listaCompostos = new LinkedList();

    private CompostoDAO compostoDAO;
    public static Integer compostoSelecionado = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compostos_quimicos);

        compostoDAO = new CompostoDAO(this);

        atualizarLista();

        ((Button) findViewById(R.id.btnAbreCadastro)).setOnClickListener(view -> {
            compostoSelecionado = null;
            startActivity(new Intent(this, CadastroCompostosActivity.class));
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        atualizarLista();
    }


    private void atualizarLista() {
        listaCompostos = compostoDAO.getAllCompostos();

        ArrayAdapter<CompostoQuimico> arrayCompostos = new ArrayAdapter<CompostoQuimico>(this, android.R.layout.simple_list_item_1, listaCompostos) {
            @NonNull
            @Override
            public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                View view = super.getView(position, convertView, parent);
                TextView textView = (TextView) view.findViewById(android.R.id.text1);
                textView.setTextSize(27);
                return view;
            }
        };

        ListView listViewCompostos = (ListView) findViewById(R.id.lvComponentes);
        listViewCompostos.setAdapter(arrayCompostos);

        listViewCompostos.setOnItemClickListener((adapterView, view, i, l) -> {
            compostoSelecionado = i;
            startActivity(new Intent(this, CadastroCompostosActivity.class));
        });

        listViewCompostos.setOnItemLongClickListener((adapterView, view, i, l) -> {
            CompostoQuimico composto =  arrayCompostos.getItem(i);
            compostoDAO.deleteComposto(composto.idComposto);
            atualizarLista();
            return true;
        });

    }




}