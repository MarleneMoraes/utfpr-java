package br.edu.utfpr.minhas_figurinhas;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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

    public static final String PREFERENCES = "br.edu.utfpr.minhas_figurinhas.PREFERENCES";

    public static final String ASCENDING_SORT = "KEY_ASCENDING_SORT";

    private boolean ascendingSort = true;

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
                editAlbum();
                return true;
            } else if (idMenuItem == R.id.menuItemExcluir) {
                deleteAlbum();
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

        setTitle(getString(R.string.album_list));

        listViewAlbuns = findViewById(R.id.listViewAlbuns);

        readPreferences();

        populateAlbumList();
    }

    private void populateAlbumList() {
        albuns = new ArrayList<>();

        albumAdapter = new AlbumAdapter(this, albuns);

        listViewAlbuns.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int index, long id) {
                position = index;
                editAlbum();
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

    public void openAbout() {
        Intent intent = new Intent(this, SobreActivity.class);
        startActivity(intent);
    }

    ActivityResultLauncher<Intent> launcherNewAlbum = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {

                @Override
                public void onActivityResult(ActivityResult result) {

                    if (result.getResultCode() == AlbumActivity.RESULT_OK) {

                        Bundle bundle = result.getData().getExtras();

                        if (bundle != null) {

                            String title = bundle.getString(CadastroActivity.TITLE);
                            int qtdSticker = Integer.parseInt(bundle.getString(CadastroActivity.QTDSTICKER));
                            String category = bundle.getString(CadastroActivity.CATEGORY);
                            String country = bundle.getString(CadastroActivity.COUNTRY);
                            boolean shiny = bundle.getBoolean(CadastroActivity.SHINY);

                            Album album = new Album(title, qtdSticker, country, shiny,
                                                                Category.valueOf(category));

                            albuns.add(album);

                            sortList();
                        }
                    }
                }
            });
    public void openNewAlbum() {
        Intent intent = new Intent(this, CadastroActivity.class);
        intent.putExtra(CadastroActivity.MODE, CadastroActivity.NEW_MODE);

        launcherNewAlbum.launch(intent);
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
            openNewAlbum();
            return true;
        } else if (idMenuItem == R.id.menuItemSobre) {
            openAbout();
            return true;
        } else if (idMenuItem == R.id.menuItemOrdenacao) {
            savePreferences(!ascendingSort);
            sortList();
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }

    private void deleteAlbum() {
        albuns.remove(position);
        albumAdapter.notifyDataSetChanged();
    }

    ActivityResultLauncher<Intent> launcherEditAlbum = registerForActivityResult(
                                                new ActivityResultContracts.StartActivityForResult(),
                                                new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {

                    if (result.getResultCode() == AlbumActivity.RESULT_OK) {

                        Intent intent = result.getData();

                        Bundle bundle = intent.getExtras();

                        if (bundle != null) {

                            String title = bundle.getString(CadastroActivity.TITLE);
                            int qtdStickers = Integer.parseInt(bundle.getString(
                                                                    CadastroActivity.QTDSTICKER));
                            String country = bundle.getString(CadastroActivity.COUNTRY);
                            boolean shiny = bundle.getBoolean(CadastroActivity.SHINY);
                            Category category = Category.valueOf(bundle.getString(
                                                                        CadastroActivity.CATEGORY));

                            Album album = albuns.get(position);

                            album.setTitle(title);
                            album.setQtdStickers(qtdStickers);
                            album.setCountry(country);
                            album.setShiny(shiny);
                            album.setCategory(category);

                            sortList();
                        }
                    }
                    position = -1;

                    if (actionMode != null) {
                        actionMode.finish();
                    }
                }
            });

    private void editAlbum(){
        Album album = albuns.get(position);

        Intent intent = new Intent(this, CadastroActivity.class);

        intent.putExtra(CadastroActivity.MODE, CadastroActivity.EDIT_MODE);

        intent.putExtra(CadastroActivity.TITLE, album.getTitle());
        intent.putExtra(CadastroActivity.QTDSTICKER, String.valueOf(album.getQtdStickers()));
        intent.putExtra(CadastroActivity.COUNTRY, album.getCountry());
        intent.putExtra(CadastroActivity.SHINY, album.isShiny());
        intent.putExtra(CadastroActivity.CATEGORY, album.getCategory().toString());

        launcherEditAlbum.launch(intent);
    }

    private void sortList() {
        if(ascendingSort) {
            Collections.sort(albuns, Album.sortAscending);
        } else {
            Collections.sort(albuns, Album.sortDescending);
        }

        albumAdapter.notifyDataSetChanged();
    }
    private void readPreferences() {
        SharedPreferences shared = getSharedPreferences(PREFERENCES, Context.MODE_PRIVATE);

        ascendingSort = shared.getBoolean(ASCENDING_SORT, ascendingSort);
    }

    private void savePreferences(boolean newValue) {
        SharedPreferences shared = getSharedPreferences(PREFERENCES, Context.MODE_PRIVATE);

        SharedPreferences.Editor editor = shared.edit();
        editor.putBoolean(ASCENDING_SORT, newValue);
        editor.commit();

        ascendingSort = newValue;
    }
}