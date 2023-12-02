package dev.enricogollner.crudappandroidjava.RegistroSaida;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.List;

import dev.enricogollner.crudappandroidjava.CompostosQuimicos.CompostoQuimicoActivity;
import dev.enricogollner.crudappandroidjava.Helper.CompostoDAO;
import dev.enricogollner.crudappandroidjava.Helper.RegistrosSaidaDAO;
import dev.enricogollner.crudappandroidjava.R;
import dev.enricogollner.crudappandroidjava.models.CompostoQuimico;
import dev.enricogollner.crudappandroidjava.models.RegistroSaida;

public class CadastroRegistroSaidaActivity extends AppCompatActivity {

    private CompostoDAO compostoDAO;
    private RegistrosSaidaDAO registrosSaidaDAO;

    private List<CompostoQuimico> listaCompostos;

    private Spinner compostosSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_registro_saida);

        compostoDAO = new CompostoDAO(this);
        registrosSaidaDAO = new RegistrosSaidaDAO(this);
        compostosSpinner = findViewById(R.id.dynamicSpinner);
        listaCompostos = compostoDAO.getAllCompostos();
        ArrayAdapter<CompostoQuimico> compostosArrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, listaCompostos);
        compostosArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        compostosSpinner.setAdapter(compostosArrayAdapter);

        if (RegistrosSaidaActivity.posRegistro != null && RegistrosSaidaActivity.idRegistroSelecionado != null) {
            RegistroSaida registro = RegistrosSaidaActivity.listaRegistros.get(RegistrosSaidaActivity.posRegistro);
            setInformationsToUpdate(registro);

            int posCompostoInSpinner = compostosArrayAdapter.getPosition(registro.getCompostoQuimico());
            compostosSpinner.setSelection(posCompostoInSpinner);
        }

        ((Button) findViewById(R.id.btnRegistrarSaida)).setOnClickListener(view -> {
            cadastrarRegistro();
        });
    }

    private void setInformationsToUpdate(RegistroSaida registro) {
        ((EditText) findViewById(R.id.edtQuantidade)).setText(String.valueOf(registro.getQuantidade()));
        ((EditText) findViewById(R.id.edtObservacoes)).setText(registro.getObservacoes());
    }

    private void cadastrarRegistro() {
        RegistroSaida novoRegistro = new RegistroSaida();
        novoRegistro.setCompostoQuimico((CompostoQuimico) compostosSpinner.getSelectedItem());

        Double quantidade = Double.valueOf(((EditText) findViewById(R.id.edtQuantidade)).getText().toString());
        novoRegistro.setQuantidade(quantidade);
        novoRegistro.setObservacoes( ((EditText) findViewById(R.id.edtObservacoes)).getText().toString() );

        if (RegistrosSaidaActivity.idRegistroSelecionado == null && RegistrosSaidaActivity.posRegistro == null) {
            registrosSaidaDAO.addRegistroSaida(novoRegistro);
        } else {
            registrosSaidaDAO.updateRegistroSaida(Long.valueOf(RegistrosSaidaActivity.idRegistroSelecionado), ((CompostoQuimico) compostosSpinner.getSelectedItem()).idComposto, novoRegistro);
            RegistrosSaidaActivity.idRegistroSelecionado = null;
            RegistrosSaidaActivity.posRegistro = null;
        }

        finish();
    }

}