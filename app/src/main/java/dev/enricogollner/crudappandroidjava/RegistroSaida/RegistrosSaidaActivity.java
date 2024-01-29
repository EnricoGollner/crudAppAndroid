package dev.enricogollner.crudappandroidjava.RegistroSaida;

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

import dev.enricogollner.crudappandroidjava.CompostosQuimicos.CadastroCompostosActivity;
import dev.enricogollner.crudappandroidjava.Helper.CompostoDAO;
import dev.enricogollner.crudappandroidjava.Helper.RegistrosSaidaDAO;
import dev.enricogollner.crudappandroidjava.R;
import dev.enricogollner.crudappandroidjava.models.CompostoQuimico;
import dev.enricogollner.crudappandroidjava.models.RegistroSaida;

public class RegistrosSaidaActivity extends AppCompatActivity {

    public static List<RegistroSaida> listaRegistros = new LinkedList();

    private RegistrosSaidaDAO registrosSaidaDAO;

    public  static Integer posRegistro = null;
    public static Long idRegistroSelecionado = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registros_saida);

        ((Button) findViewById(R.id.btnAbreCadastro)).setOnClickListener(view -> {
            idRegistroSelecionado = null;
            posRegistro = null;
            startActivity(new Intent(this, CadastroRegistroSaidaActivity.class));
        });

        registrosSaidaDAO = new RegistrosSaidaDAO(this);
        atualizarLista();

        ((Button) findViewById(R.id.btnAbreCadastro)).setOnClickListener(view -> {
            startActivity(new Intent(this, CadastroRegistroSaidaActivity.class));
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        atualizarLista();
    }


    private void atualizarLista() {
        listaRegistros = registrosSaidaDAO.getAllRegistrosSaida();

        ArrayAdapter<RegistroSaida> arrayRegistros = new ArrayAdapter<RegistroSaida>(this, android.R.layout.simple_list_item_1, listaRegistros) {
            @NonNull
            @Override
            public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                View view = super.getView(position, convertView, parent);
                TextView textView = (TextView) view.findViewById(android.R.id.text1);
                textView.setTextSize(24);
                return view;
            }
        };

        ListView listViewCompostos = (ListView) findViewById(R.id.lvRegistrosSaida);
        listViewCompostos.setAdapter(arrayRegistros);

        // Navegando para editar composto ao clicar uma vez
        listViewCompostos.setOnItemClickListener((adapterView, view, i, l) -> {
            posRegistro = i;
            idRegistroSelecionado = listaRegistros.get(i).getIdRegistro();
            startActivity(new Intent(this, CadastroRegistroSaidaActivity.class));
        });

        // Deletar composto ao pressionar por um tempo maior
        listViewCompostos.setOnItemLongClickListener((adapterView, view, i, l) -> {
            RegistroSaida registro =  arrayRegistros.getItem(i);
            registrosSaidaDAO.deleteRegistroSaida(registro.getIdRegistro());
            atualizarLista();
            return true;
        });

    }
}