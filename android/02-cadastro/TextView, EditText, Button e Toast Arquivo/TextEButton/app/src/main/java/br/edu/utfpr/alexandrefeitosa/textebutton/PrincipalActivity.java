package br.edu.utfpr.alexandrefeitosa.textebutton;

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

        editTextNome = findViewById(R.id.editTextNome);

        editTextSobrenome = findViewById(R.id.editTextSobrenome);
    }

    public void limparCampos(View view) {
        editTextNome.setText(null);
        editTextSobrenome.setText(null);
        editTextNome.requestFocus();
    }

    public void mostrarNomeCompleto(View view) {

        String nome = editTextNome.getText().toString();

        String sobrenome = editTextSobrenome.getText().toString();

        Toast.makeText(this,
                       nome + " " + sobrenome,
                       Toast.LENGTH_LONG).show();
    }
}
