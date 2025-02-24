package br.edu.utfpr.alexandrefeitosa.activity2s;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class PessoaActivity extends AppCompatActivity {

    public static final String NOME = "NOME";

    public static final int NOVO = 1;
    private EditText editTextNome;

    public static void novaPessoa(AppCompatActivity activity){

        Intent intent = new Intent(activity, PessoaActivity.class);

        activity.startActivityForResult(intent, NOVO);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pessoa);

        editTextNome = findViewById(R.id.editTextTextNome);
    }

    public void salvar(View view){

        String nome = editTextNome.getText().toString();

        if (nome == null || nome.trim().isEmpty()){
            Toast.makeText(this,
                           R.string.nome_n_o_pode_ser_vazio,
                           Toast.LENGTH_LONG).show();
            return;
        }

        Intent intent = new Intent();

        intent.putExtra(NOME, nome);

        setResult(Activity.RESULT_OK, intent);

        finish();
    }

    public void limpar(View view){
        editTextNome.setText(null);
    }

    public void cancelar(View view){
        setResult(Activity.RESULT_CANCELED);
        finish();
    }
}