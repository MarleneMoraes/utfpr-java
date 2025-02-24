package br.edu.utfpr.alexandrefeitosa.internacionalizacao;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class PrincipalActivity extends AppCompatActivity {

    private EditText editTextNome, editTextSobrenome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);

        editTextNome      = findViewById(R.id.editTextNome);
        editTextSobrenome = findViewById(R.id.editTextSobrenome);

        setTitle(getString(R.string.novo_cadastro));

        Toast.makeText(this, getString(R.string.frase), Toast.LENGTH_LONG).show();
    }

    public void enviar(View view){

        String nome      = editTextNome.getText().toString();
        String sobrenome = editTextSobrenome.getText().toString();

        Toast.makeText(this,
                       getString(R.string.frase_enviar, nome, sobrenome),
                       Toast.LENGTH_LONG).show();
    }

    public void limpar(View view){

        editTextNome.setText(null);
        editTextSobrenome.setText(null);

        editTextNome.requestFocus();
    }
}
