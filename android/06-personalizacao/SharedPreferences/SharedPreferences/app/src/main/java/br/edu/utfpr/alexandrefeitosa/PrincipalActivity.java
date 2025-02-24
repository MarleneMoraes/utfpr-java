package br.edu.utfpr.alexandrefeitosa;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class PrincipalActivity extends AppCompatActivity {

    private static final String ARQUIVO =
            "br.edu.utfpr.alexandrefeitosa.sharedpreferences.PREFERENCIAS_CORES";

    private static final String COR = "COR";

    private ConstraintLayout layout;

    private int opcao = Color.BLUE;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        layout = findViewById(R.id.layoutPrincipal);

        lerPreferenciaCor();
    }

    private void lerPreferenciaCor(){

        SharedPreferences shared = getSharedPreferences(ARQUIVO,
                                                        Context.MODE_PRIVATE);

        opcao = shared.getInt(COR, opcao);

        mudaCorFundo();
    }

    private void salvarPreferenciaCor(int novoValor){

        SharedPreferences shared = getSharedPreferences(ARQUIVO,
                                                        Context.MODE_PRIVATE);

        SharedPreferences.Editor editor = shared.edit();

        editor.putInt(COR, novoValor);

        editor.commit();

        opcao = novoValor;

        mudaCorFundo();
    }

    private void mudaCorFundo(){
        layout.setBackgroundColor(opcao);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.principal_opcoes, menu);
        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {

        MenuItem item;

        switch(opcao){

            case Color.YELLOW:
                item = menu.findItem(R.id.menuItemAmarelo);
                break;

            case Color.BLUE:
                item = menu.findItem(R.id.menuItemAzul);
                break;

            case Color.RED:
                item = menu.findItem(R.id.menuItemVermelho);
                break;

            default:
                return false;
        }

        item.setChecked(true);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        item.setChecked(true);

        switch(item.getItemId()){

            case R.id.menuItemAmarelo:
                salvarPreferenciaCor(Color.YELLOW);
                return true;

            case R.id.menuItemAzul:
                salvarPreferenciaCor(Color.BLUE);
                return true;

            case R.id.menuItemVermelho:
                salvarPreferenciaCor(Color.RED);
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}