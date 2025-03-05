package br.edu.utfpr.minhas_figurinhas;

import android.content.Context;
import android.content.DialogInterface;
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
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.ActionMode;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.google.android.material.snackbar.Snackbar;

import java.util.Collections;
import java.util.List;

import br.edu.utfpr.minhas_figurinhas.model.Album;
import br.edu.utfpr.minhas_figurinhas.persistence.AlbumDatabase;
import br.edu.utfpr.minhas_figurinhas.utils.UtilsAlert;

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

    public static final boolean INITIAL_DEFAULT_SORTING = true;
    private boolean ascendingSort = INITIAL_DEFAULT_SORTING;

    private MenuItem menuItemSorting;

    private final ActionMode.Callback actionCallback = new ActionMode.Callback() {

        @Override
        public boolean onCreateActionMode(ActionMode mode, Menu menu) {
            MenuInflater inflate = mode.getMenuInflater();
            inflate.inflate(R.menu.album_item_selected, menu);
            return true;
        }

        @Override
        public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
            return false;
        }

        @Override
        public boolean onActionItemClicked(ActionMode mode, MenuItem item) {

            int idMenuItem = item.getItemId();

            if (idMenuItem == R.id.menuItemEdit) {
                editAlbum();
                return true;
            } else if (idMenuItem == R.id.menuItemDelete) {
                deleteAlbum();
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

        AlbumDatabase database = AlbumDatabase.getInstance(this);

        if(ascendingSort) {
            albuns = database.getAlbumDao().queryAllAscending();
        } else {
            albuns = database.getAlbumDao().queryAllDownward();
        }

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

                            long id = bundle.getLong(CadastroActivity.ID);

                            AlbumDatabase database = AlbumDatabase.getInstance(AlbumActivity.this);
                            Album album = database.getAlbumDao().queryForId(id);

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
        getMenuInflater().inflate(R.menu.albuns_options, menu);

        menuItemSorting = menu.findItem(R.id.menuItemSorting);
        return true;
    }

    public boolean onPrepareOptionsMenu(Menu menu) {
        updateIconSorting();
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        int idMenuItem = item.getItemId();

        if (idMenuItem == R.id.menuItemAdd) {
            openNewAlbum();
            return true;
        } else if (idMenuItem == R.id.menuItemSorting) {
            savePreferencesAscSorting(!ascendingSort);
            updateIconSorting();
            sortList();
            return true;
        } else if (idMenuItem == R.id.menuItemRestore) {
            confirmRestoreDefault();
            return true;
        } else if (idMenuItem == R.id.menuItemAbout) {
            openAbout();
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }

    private void deleteAlbum() {
        final Album album = albuns.get(position);

        String message = getString(R.string.want_to_delete,album.getTitle());

        DialogInterface.OnClickListener listenerYes = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                AlbumDatabase database = AlbumDatabase.getInstance(AlbumActivity.this);
                int updateline = database.getAlbumDao().delete(album);

                if(updateline != 1) {
                    UtilsAlert.showAlert(AlbumActivity.this, R.string.delete_error);
                    return;
                }

                albuns.remove(position);
                albumAdapter.notifyDataSetChanged();
                actionMode.finish();
            }
        };

        UtilsAlert.confirmAction(this, message, listenerYes, null);
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
                            final Album original = albuns.get(position);

                            long id = bundle.getLong(CadastroActivity.ID);

                            final AlbumDatabase database = AlbumDatabase.getInstance(AlbumActivity.this);

                            final Album albumEdit = database.getAlbumDao().queryForId(id);

                            final Album clone;

                            albuns.set(position, albumEdit);

                            sortList();

                            final ConstraintLayout constraintLayout = findViewById(R.id.main);

                            Snackbar snackBar = Snackbar.make(constraintLayout,
                                    R.string.update_done,
                                    Snackbar.LENGTH_LONG);


                            snackBar.setAction(R.string.undo, new View.OnClickListener() {

                                @Override
                                public void onClick(View v) {

                                    int updateline = database.getAlbumDao().update(original);

                                    if(updateline != 1) {
                                        UtilsAlert.showAlert(AlbumActivity.this, R.string.update_error);
                                    }

                                    albuns.remove(albumEdit);
                                    albuns.add(original );

                                    sortList();
                                }
                            });

                            snackBar.show();
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

        intent.putExtra(CadastroActivity.ID, album.getId());

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

    private void updateIconSorting() {
        if(ascendingSort) {
            menuItemSorting.setIcon(R.drawable.ic_action_ascending_order);
        } else {
            menuItemSorting.setIcon(R.drawable.ic_action_descending_order);
        }
    }
    private void readPreferences() {
        SharedPreferences shared = getSharedPreferences(PREFERENCES, Context.MODE_PRIVATE);

        ascendingSort = shared.getBoolean(ASCENDING_SORT, ascendingSort);
    }

    private void savePreferencesAscSorting(boolean newValue) {
        SharedPreferences shared = getSharedPreferences(PREFERENCES, Context.MODE_PRIVATE);

        SharedPreferences.Editor editor = shared.edit();
        editor.putBoolean(ASCENDING_SORT, newValue);
        editor.commit();

        ascendingSort = newValue;
    }
    private void confirmRestoreDefault() {
        DialogInterface.OnClickListener listenerYes = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                restoreDefault();
                updateIconSorting();
                sortList();

                Toast.makeText(AlbumActivity.this, R.string.restored_installation_default,
                        Toast.LENGTH_LONG).show();
            }
        };
        UtilsAlert.confirmAction(this, R.string.confirm_restore_default,
                                    listenerYes, null);
    }
    private void restoreDefault() {
        SharedPreferences shared = getSharedPreferences(PREFERENCES, Context.MODE_PRIVATE);

        SharedPreferences.Editor editor = shared.edit();

        editor.clear();
        editor.commit();

        ascendingSort = INITIAL_DEFAULT_SORTING;
    }
}