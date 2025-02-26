package br.edu.utfpr.minhas_figurinhas;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class AlbumActivity extends AppCompatActivity {

    private ListView listViewAlbuns;

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

        listViewAlbuns = findViewById(R.id.listViewAlbuns);

        popularListaAlbuns();
    }

    private void popularListaAlbuns() {
        String[] titulos = getResources().getStringArray(R.array.titulo);
        int[] qtdFigurinhas = getResources().getIntArray(R.array.qtd_figurinhas);
        String[] paises = getResources().getStringArray(R.array.pais);
        int[] brilhantes = getResources().getIntArray(R.array.brilhantes);
        int[] categorias = getResources().getIntArray(R.array.categoria);

        CategoriaAlbum[] categoriaAlbums = CategoriaAlbum.values();

        ArrayList<Album> albuns = new ArrayList<>();

        for (int i = 0; i < titulos.length; i++) {
            String titulo = titulos[i];
            int qtdFigurinha = qtdFigurinhas[i];
            String pais = paises[i];
            boolean brilhante = (brilhantes[i] == 1);
            CategoriaAlbum categoria = categoriaAlbums[categorias[i]];

            albuns.add(new Album(titulo, qtdFigurinha, pais, brilhante, categoria));
        }

        ArrayAdapter<Album> albumArrayAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1,
                albuns);
        listViewAlbuns.setAdapter(albumArrayAdapter);
    }
}