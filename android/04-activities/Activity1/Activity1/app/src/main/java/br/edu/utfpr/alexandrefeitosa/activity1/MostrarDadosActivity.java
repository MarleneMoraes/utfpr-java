package br.edu.utfpr.alexandrefeitosa.activity1;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

public class MostrarDadosActivity extends AppCompatActivity {

    private TextView textViewDados;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mostrar_dados);

        setTitle(getString(R.string.dados_recebidos));

        textViewDados = findViewById(R.id.textViewDados);

        Intent intent = getIntent();

        Bundle bundle = intent.getExtras();

        if (bundle != null){

            String nome = bundle.getString(PrincipalActivity.KEY_NOME);

            textViewDados.setText(nome);
        }

    }

    public void voltar(View view){
        finish();
    }
}
