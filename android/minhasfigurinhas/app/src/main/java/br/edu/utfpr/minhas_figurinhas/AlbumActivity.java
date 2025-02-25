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
        // EdgeToEdge.enable(this);
        setContentView(R.layout.activity_list_view_albuns);
        /*
            ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
                Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
                v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
                return insets;
            });
        */
        setTitle(getString(R.string.lista_de_lbuns));

        listViewAlbuns = findViewById(R.id.listViewAlbuns);

        popularListaAlbuns();
    }

    private void popularListaAlbuns() {
        /*String[] titulos = getResources().getStringArray(R.array.titulo);
        int[] qtdFigurinhas = getResources().getIntArray(R.array.qtd_figurinhas);
        String[] paises = getResources().getStringArray(R.array.pais);
        int[] brilhantes = getResources().getIntArray(R.array.brilhantes);
        int[] categorias = getResources().getIntArray(R.array.categoria);

        CategoriaAlbum[] categoriaAlbums = CategoriaAlbum.values();*/

        albuns = new ArrayList<>();

//        for (int i = 0; i < titulos.length; i++) {
//            String titulo = titulos[i];
//            int qtdFigurinha = qtdFigurinhas[i];
//            String pais = paises[i];
//            boolean brilhante = (brilhantes[i] == 1);
//            CategoriaAlbum categoria = categoriaAlbums[categorias[i]];
//
//            albuns.add(new Album(titulo, qtdFigurinha, pais, brilhante, categoria));
//        }

        albumArrayAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1,
                albuns);
        listViewAlbuns.setAdapter(albumArrayAdapter);
    }

    public void abrirSobre(View view) {
        Intent intentAbertura = new Intent(this, SobreActivity.class);
        startActivity(intentAbertura);
    }

    ActivityResultLauncher<Intent> laucherNovoAlbum = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode() == AlbumActivity.RESULT_OK) {
                        Intent intent = result.getData();
                        if (intent != null && intent.getExtras() != null) {
                            Bundle bundle = intent.getExtras();

                            String titulo = bundle.getString(CadastroActivity.TITULO);
                            int qtdFigurinhas = bundle.getInt(CadastroActivity.QTDSTICKER);
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
        laucherNovoAlbum.launch(intentAbertura);
    }
}