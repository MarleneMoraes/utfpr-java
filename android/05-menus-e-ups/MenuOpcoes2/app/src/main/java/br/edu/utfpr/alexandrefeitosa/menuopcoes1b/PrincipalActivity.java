package br.edu.utfpr.alexandrefeitosa.menuopcoes1b;

import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

public class PrincipalActivity extends AppCompatActivity {

    private ConstraintLayout layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);

        layout = findViewById(R.id.layoutPrincipal);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.principal_opcoes, menu);
        return true;
    }

    private void mostrarMensagem(String texto){

        Toast.makeText(this,
                texto,
                Toast.LENGTH_LONG).show();
    }

    public void usarDados(MenuItem item){
        item.setChecked(!item.isChecked());
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int botao = item.getItemId();

        if (botao == R.id.menuItemSalvar){
            mostrarMensagem(getString(R.string.salvar) + getString(R.string.foi_clicado));
            return true;
        }else
            if (botao == R.id.menuItemExcluir){
                mostrarMensagem(getString(R.string.excluir) + getString(R.string.foi_clicado));
                return true;
            }else
                if (botao == R.id.menuItemAzul){
                    item.setChecked(true);
                    layout.setBackgroundColor(Color.BLUE);
                    return true;
                }else
                    if (botao == R.id.menuItemVermelho){
                        item.setChecked(true);
                        layout.setBackgroundColor(Color.RED);
                        return true;
                    }else {
                        return super.onOptionsItemSelected(item);
                    }
    }
}