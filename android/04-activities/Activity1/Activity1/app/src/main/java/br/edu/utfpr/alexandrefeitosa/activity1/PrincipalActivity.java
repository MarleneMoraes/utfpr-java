package br.edu.utfpr.alexandrefeitosa.activity1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class PrincipalActivity extends AppCompatActivity {

    public static String KEY_NOME = "NOME";

    private EditText editTextNome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);

        editTextNome = findViewById(R.id.editTextNome);
    }

    public void enviar(View view){
        Intent intent = new Intent(this,
                                   MostrarDadosActivity.class);

        intent.putExtra(KEY_NOME, editTextNome.getText().toString());

        startActivity(intent);
    }
}
