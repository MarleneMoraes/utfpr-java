package br.edu.utfpr.alexandrefeitosa.radiobutton;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.Toast;

public class PrincipalActivity extends AppCompatActivity {

    private RadioGroup radioGroupLinguagens;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);

        radioGroupLinguagens = findViewById(R.id.radioGroupLinguagens);
    }

    public void mostrarSelecionado(View view){

        String mensagem = "";

        switch(radioGroupLinguagens.getCheckedRadioButtonId()){

            case R.id.radioButtonJava:
                mensagem = getString(R.string.java) +
                           getString(R.string.foi_selecionado);
                break;

            case R.id.radioButtonPascal:
                mensagem = getString(R.string.pascal) +
                           getString(R.string.foi_selecionado);
                break;

            case R.id.radioButtonCobol:
                mensagem = getString(R.string.cobol) +
                           getString(R.string.foi_selecionado);
                break;

            default:
                mensagem = getString(R.string.ninguem_foi_selecionado);

        }

        Toast.makeText(this, mensagem, Toast.LENGTH_SHORT).show();
    }

    public void desmarcar(View view){
        radioGroupLinguagens.clearCheck();
    }
}
