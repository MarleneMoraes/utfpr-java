package br.edu.utfpr.alexandrefeitosa.spinner3;

import android.content.res.TypedArray;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class PrincipalActivity extends AppCompatActivity {

    private Spinner spinnerPaises;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);

        spinnerPaises = findViewById(R.id.spinnerPaises);

        popuplarSpinnerPaises();
    }

    private void popuplarSpinnerPaises(){

        String[]   nomes     = getResources().getStringArray(R.array.nomes_paises);
        TypedArray bandeiras = getResources().obtainTypedArray(R.array.bandeiras_paises);

        ArrayList<Pais> paises = new ArrayList();

        for (int cont = 0; cont < nomes.length; cont++){
            paises.add(new Pais(nomes[cont], bandeiras.getDrawable(cont)));
        }

        PaisAdapter paisAdapter = new PaisAdapter(this, paises);

        spinnerPaises.setAdapter(paisAdapter);
    }

    public void mostrarSelecionado(View view){

        Pais pais = (Pais) spinnerPaises.getSelectedItem();

        Toast.makeText(this,
                       getString(R.string.o_pais_foi_selecionado, pais.getNome()),
                       Toast.LENGTH_SHORT).show();
    }
}
