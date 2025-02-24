package br.edu.utfpr.alexandrefeitosa.menuopcoes;

import android.graphics.Color;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

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

        switch(item.getItemId()){

            case R.id.menuItemSalvar:
                mostrarMensagem(getString(R.string.salvar) +
                                getString(R.string.foi_clicado));
                return true;

            case R.id.menuItemExcluir:
                mostrarMensagem(getString(R.string.excluir) +
                                getString(R.string.foi_clicado));
                return true;

            case R.id.menuItemAzul:
                item.setChecked(true);
                layout.setBackgroundColor(Color.BLUE);
                return true;

            case R.id.menuItemVermelho:
                item.setChecked(true);
                layout.setBackgroundColor(Color.RED);
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
