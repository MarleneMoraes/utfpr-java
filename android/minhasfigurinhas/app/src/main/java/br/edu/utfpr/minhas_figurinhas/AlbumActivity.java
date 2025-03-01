package br.edu.utfpr.minhas_figurinhas;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.ActionMode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AlbumActivity extends AppCompatActivity {

    private ListView listViewAlbuns;
    private List<Album> albuns;
    private AlbumAdapter albumAdapter;
    private int position = -1;
    private ActionMode actionMode;

    private View selectedView;
    private Drawable backgroundDrawable;

    private final ActionMode.Callback actionCallback = new ActionMode.Callback() {

        @Override
        public boolean onCreateActionMode(ActionMode mode, Menu menu) {
            MenuInflater inflate = mode.getMenuInflater();
            inflate.inflate(R.menu.album_item_selecionado, menu);
            return true;
        }

        @Override
        public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
            return false;
        }

        @Override
        public boolean onActionItemClicked(ActionMode mode, MenuItem item) {

            int idMenuItem = item.getItemId();

            if (idMenuItem == R.id.menuItemEditar) {
                editarAlbum();
                return true;
            } else if (idMenuItem == R.id.menuItemExcluir) {
                excluirAlbum();
                mode.finish();
                return true;
            } else {
                return false;
            }
        }

        @Override
        public void onDestroyActionMode(ActionMode mode) {

            if (selectedView != null){
                selectedView.setBackground(backgroundDrawable);
            }

            actionMode = null;
            selectedView = null;
            backgroundDrawable = null;

            listViewAlbuns.setEnabled(true);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_albuns);

        setTitle(getString(R.string.lista_de_lbuns));

        listViewAlbuns = findViewById(R.id.listViewAlbuns);

        popularListaAlbuns();
    }

    private void popularListaAlbuns() {
        albuns = new ArrayList<>();

        albumAdapter = new AlbumAdapter(this, albuns);

        listViewAlbuns.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int index, long id) {
                position = index;
                editarAlbum();
            }
        });

        listViewAlbuns.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int index, long id) {

                if (actionMode != null) {
                    return false;
                }

                position = index;

                selectedView = view;
                backgroundDrawable = view.getBackground();

                view.setBackgroundColor(Color.LTGRAY);

                listViewAlbuns.setEnabled(false);

                actionMode = startSupportActionMode(actionCallback);

                return true;
            }
        });

        listViewAlbuns.setAdapter(albumAdapter);
    }

    public void abrirSobre() {
        Intent intentAbertura = new Intent(this, SobreActivity.class);
        startActivity(intentAbertura);
    }

    ActivityResultLauncher<Intent> launcherNovoAlbum = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {

                @Override
                public void onActivityResult(ActivityResult result) {

                    if (result.getResultCode() == AlbumActivity.RESULT_OK) {

                        Bundle bundle = result.getData().getExtras();

                        if (bundle != null) {

                            String titulo = bundle.getString(CadastroActivity.TITULO);
                            int qtdFigurinhas = Integer.parseInt(bundle.getString(CadastroActivity.QTDSTICKER));
                            String categoria = bundle.getString(CadastroActivity.CATEGORIA);
                            String pais = bundle.getString(CadastroActivity.NACIONALITY);
                            boolean brilhante = bundle.getBoolean(CadastroActivity.SHINY);

                            Album album = new Album(titulo, qtdFigurinhas, pais, brilhante,
                                                                CategoriaAlbum.valueOf(categoria));

                            albuns.add(album);

                            Collections.sort(albuns, Album.sortAscending);

                            albumAdapter.notifyDataSetChanged();
                        }
                    }
                }
            });
    public void abrirNovoAlbum() {
        Intent intentAbertura = new Intent(this, CadastroActivity.class);
        intentAbertura.putExtra(CadastroActivity.MODO, CadastroActivity.MODO_NOVO);

        launcherNovoAlbum.launch(intentAbertura);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.albuns_opcoes, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        int idMenuItem = item.getItemId();

        if (idMenuItem == R.id.menuItemAdicionar) {
            abrirNovoAlbum();
            return true;
        } else if (idMenuItem == R.id.menuItemSobre) {
            abrirSobre();
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }

    private void excluirAlbum() {
        albuns.remove(position);
        albumAdapter.notifyDataSetChanged();
    }

    ActivityResultLauncher<Intent> launcherEditarAlbum = registerForActivityResult(
                                                new ActivityResultContracts.StartActivityForResult(),
                                                new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {

                    if (result.getResultCode() == AlbumActivity.RESULT_OK) {

                        Intent intent = result.getData();

                        Bundle bundle = intent.getExtras();

                        if (bundle != null) {

                            String titulo = bundle.getString(CadastroActivity.TITULO);
                            int qtdFigurinhas = Integer.parseInt(bundle.getString(
                                                                    CadastroActivity.QTDSTICKER));
                            String pais = bundle.getString(CadastroActivity.NACIONALITY);
                            boolean brilhante = bundle.getBoolean(CadastroActivity.SHINY);
                            CategoriaAlbum categoria = CategoriaAlbum.valueOf(bundle.getString(
                                                                        CadastroActivity.CATEGORIA));

                            Album album = albuns.get(position);

                            album.setTitulo(titulo);
                            album.setQtdFigurinhas(qtdFigurinhas);
                            album.setPais(pais);
                            album.setBrilhante(brilhante);
                            album.setCategoria(categoria);

                            Collections.sort(albuns, Album.sortAscending);

                            albumAdapter.notifyDataSetChanged();
                        }
                    }
                    position = -1;

                    if (actionMode != null) {
                        actionMode.finish();
                    }
                }
            });

    private void editarAlbum(){
        Album album = albuns.get(position);

        Intent intentAbertura = new Intent(this, CadastroActivity.class);

        intentAbertura.putExtra(CadastroActivity.MODO, CadastroActivity.MODO_EDITAR);

        intentAbertura.putExtra(CadastroActivity.TITULO, album.getTitulo());
        intentAbertura.putExtra(CadastroActivity.QTDSTICKER, String.valueOf(album.getQtdFigurinhas()));
        intentAbertura.putExtra(CadastroActivity.NACIONALITY, album.getPais());
        intentAbertura.putExtra(CadastroActivity.SHINY, album.isBrilhante());
        intentAbertura.putExtra(CadastroActivity.CATEGORIA, album.getCategoria().toString());

        launcherEditarAlbum.launch(intentAbertura);
    }
}