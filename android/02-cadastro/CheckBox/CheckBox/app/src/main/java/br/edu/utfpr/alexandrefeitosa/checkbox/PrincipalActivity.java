package br.edu.utfpr.alexandrefeitosa.checkbox;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.Toast;

public class PrincipalActivity extends AppCompatActivity {

    private CheckBox cbAluno, cbMonitor, cbProfessor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);

        cbAluno     = findViewById(R.id.checkBoxAluno);
        cbMonitor   = findViewById(R.id.checkBoxMonitor);
        cbProfessor = findViewById(R.id.checkBoxProfessor);
    }

    public void mostrarSelecionados(View view){

        String mensagem = "";

        if (cbAluno.isChecked()){
            mensagem += getString(R.string.aluno) + "\n";
        }
        if (cbMonitor.isChecked()){
            mensagem += getString(R.string.monitor) + "\n";
        }
        if (cbProfessor.isChecked()){
            mensagem += getString(R.string.professor) + "\n";
        }
        if (mensagem.equals("")){
            mensagem = getString(R.string.nenhuma_opcao_selecionada);
        }else{
            mensagem = getString(R.string.foram_selecionados) + "\n" + mensagem;
        }

        Toast.makeText(this, mensagem, Toast.LENGTH_LONG).show();
    }

    public void desmarcarTodos(View view){
        cbAluno.setChecked(false);
        cbMonitor.setChecked(false);
        cbProfessor.setChecked(false);
    }
}
