package br.edu.utfpr.minhas_figurinhas;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class AlbumActivity extends AppCompatActivity {

    private ListView listViewAlbuns;
    private ArrayList<Album> albuns;
    private ArrayAdapter<Album> albumArrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view_albuns);

        setTitle(getString(R.string.lista_de_lbuns));

        listViewAlbuns = findViewById(R.id.listViewAlbuns);

        popularListaAlbuns();
    }

    private void popularListaAlbuns() {
        albuns = new ArrayList<>();

        albumArrayAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1,
                albuns);
        listViewAlbuns.setAdapter(albumArrayAdapter);
    }

    public void abrirSobre(View view) {
        Intent intentAbertura = new Intent(this, SobreActivity.class);
        startActivity(intentAbertura);
    }

    ActivityResultLauncher<Intent> launcherNovoAlbum = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode() == AlbumActivity.RESULT_OK) {
                        Intent intent = result.getData();
                        if (intent != null && intent.getExtras() != null) {
                            Bundle bundle = intent.getExtras();

                            String titulo = bundle.getString(CadastroActivity.TITULO);
                            int qtdFigurinhas = Integer.parseInt(bundle.getString(CadastroActivity.QTDSTICKER));
                            String categoria = bundle.getString(CadastroActivity.CATEGORIA);
                            String pais = bundle.getString(CadastroActivity.NACIONALITY);
                            boolean brilhante = bundle.getBoolean(CadastroActivity.SHINY);

                            Album album = new Album(titulo, qtdFigurinhas, pais, brilhante, CategoriaAlbum.valueOf(categoria));
                            albuns.add(album);
                            albumArrayAdapter.notifyDataSetChanged();
                        }
                    }
                }
            });
    public void abrirNovoAlbum(View view) {
        Log.d("AlbumActivity", "abrirNovoAlbum chamado");
        Intent intentAbertura = new Intent(this, CadastroActivity.class);
        launcherNovoAlbum.launch(intentAbertura);
    }
}