package br.edu.utfpr.alexandrefeitosa.activity2x;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.appcompat.app.AppCompatActivity;

public class PessoaActivity extends AppCompatActivity {

    public static final String NOME = "NOME";

    private EditText editTextNome;


    public static void novaPessoa(AppCompatActivity activity, ActivityResultLauncher<Intent> launcher){

        Intent intent = new Intent(activity, PessoaActivity.class);

        launcher.launch(intent);
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