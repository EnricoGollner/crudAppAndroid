package dev.enricogollner.crudappandroidjava.CompostosQuimicos;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import dev.enricogollner.crudappandroidjava.Helper.CompostoDAO;
import dev.enricogollner.crudappandroidjava.R;
import dev.enricogollner.crudappandroidjava.models.CompostoQuimico;

public class CadastroCompostosActivity extends AppCompatActivity {
    private CompostoDAO compostoDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_componentes);

        compostoDAO = new CompostoDAO(this);

        Spinner spinnerUnidadeMedida = findViewById(R.id.selectUnidadeMedida);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this,
                R.array.unidades_medida,
                android.R.layout.simple_spinner_item
        );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerUnidadeMedida.setAdapter(adapter);


        if (CompostoQuimicoActivity.compostoSelecionado != null) {
            CompostoQuimico composto = CompostoQuimicoActivity.listaCompostos.get(CompostoQuimicoActivity.compostoSelecionado);
            ((EditText) findViewById(R.id.edtNome)).setText(composto.nome);
            ((EditText) findViewById(R.id.edtFormula)).setText(composto.formula);

            int posicaoUnidade = adapter.getPosition(composto.unidadeMedida);
            spinnerUnidadeMedida.setSelection(posicaoUnidade);
        }

        ((Button) findViewById(R.id.btnCadastrarComponente)).setOnClickListener(view -> {
            CompostoQuimico composto = new CompostoQuimico();
            composto.nome = ((EditText) findViewById(R.id.edtNome)).getText().toString();
            composto.formula = ((EditText) findViewById(R.id.edtFormula)).getText().toString();
            composto.unidadeMedida = ((Spinner) findViewById(R.id.selectUnidadeMedida)).getSelectedItem().toString();

            if (CompostoQuimicoActivity.compostoSelecionado == null) {
                composto.idComposto = compostoDAO.addComposto(composto.nome, composto.formula, composto.unidadeMedida);
            } else {
                compostoDAO.updateComposto(Long.valueOf(CompostoQuimicoActivity.compostoSelecionado), composto);
//                CompostoQuimicoActivity.listaCompostos.set(CompostoQuimicoActivity.compostoSelecionado, composto);
                CompostoQuimicoActivity.compostoSelecionado = null;
            }

            finish();
        });
    }
}
