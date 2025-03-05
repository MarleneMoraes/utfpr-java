package br.edu.utfpr.minhas_figurinhas;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.ScrollView;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.snackbar.Snackbar;

import br.edu.utfpr.minhas_figurinhas.model.Album;
import br.edu.utfpr.minhas_figurinhas.model.Category;
import br.edu.utfpr.minhas_figurinhas.persistence.AlbumDatabase;
import br.edu.utfpr.minhas_figurinhas.utils.UtilsAlert;

public class CadastroActivity extends AppCompatActivity {

    public static final String ID = "ID_KEY";

    public static final String SUGGEST_CATEGORY = "KEY_SUGGEST_CATEGORY";
    public static final String LAST_CATEGORY = "KEY_LAST_CATEGORY";

    public static final String MODE = "MODE_KEY";
    public static final int NEW_MODE = 0;
    public static final int EDIT_MODE = 1;

    private EditText editAlbumTitle, editQtdSticker;
    private RadioButton radioButtonBR, radioButtonOT;
    private RadioGroup radioGroupCountry;
    private CheckBox checkboxShiny;
    private Spinner spinnerCategory;

    private int mode;
    private Album original;

    private boolean suggestCategory = false;
    private int lastCategory = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        setTitle(getString(R.string.new_album));

        editAlbumTitle = findViewById(R.id.inputTitle);
        editQtdSticker = findViewById(R.id.inputQtdSticker);
        radioGroupCountry = findViewById(R.id.radioGroupCountry);
        radioButtonBR = findViewById(R.id.radioButtonBR);
        radioButtonOT = findViewById(R.id.radioButtonOT);
        checkboxShiny = findViewById(R.id.checkboxShiny);
        spinnerCategory = findViewById(R.id.spinnerCategory);

        readPreferences();

        Intent intent = getIntent();

        Bundle bundle = intent.getExtras();

        if (bundle != null){
            mode = bundle.getInt(MODE);

            if (mode == NEW_MODE) {
                setTitle(getString(R.string.new_album));

                if(suggestCategory) {
                    spinnerCategory.setSelection(lastCategory);
                }

            } else {
                setTitle(getString(R.string.edit));

                long id = bundle.getLong(ID);

                AlbumDatabase database = AlbumDatabase.getInstance(this);
                original = database.getAlbumDao().queryForId(id);

                editAlbumTitle.setText(original.getTitle());
                editQtdSticker.setText(original.getQtdStickers());

                checkboxShiny.setChecked(original.isShiny());
                spinnerCategory.setSelection(original.getCategory().getResourceId());

                if (original.getCountry().equals(R.string.brazil)) {
                    radioButtonBR.setChecked(true);
                } else {
                    radioButtonOT.setChecked(true);
                }

                editAlbumTitle.requestFocus();
                editAlbumTitle.setSelection(editAlbumTitle.getText().length());
            }
        }
    }

    private void cleanForm() {
        final String albumTitle = editAlbumTitle.getText().toString();
        final String qtdSticker = editQtdSticker.getText().toString();
        final int country = radioGroupCountry.getCheckedRadioButtonId();
        final boolean shiny = checkboxShiny.isChecked();
        final int category = spinnerCategory.getSelectedItemPosition();

        final ScrollView scrollView = findViewById(R.id.main);
        final View focusView = scrollView.findFocus();

        editAlbumTitle.setText(null);
        editQtdSticker.setText(null);
        radioGroupCountry.clearCheck();
        checkboxShiny.setChecked(false);
        spinnerCategory.setSelection(0);

        editAlbumTitle.requestFocus();

        Snackbar snackbar = Snackbar.make(scrollView,
                R.string.form_cleared_successfully,
                Snackbar.LENGTH_LONG);

        snackbar.setAction(R.string.undo, new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                editAlbumTitle.setText(albumTitle);
                editQtdSticker.setText(qtdSticker);
                checkboxShiny.setChecked(shiny);

                if (country == R.id.radioButtonBR){
                    radioButtonBR.setChecked(true);
                } else {
                    radioButtonOT.setChecked(true);
                }

                spinnerCategory.setSelection(category);

                if (focusView != null) {
                    focusView.requestFocus();
                }
            }
        });

        snackbar.show();
    }

    private void saveForm() {
        String albumTitle = editAlbumTitle.getText().toString();
        String qtdStickerText = editQtdSticker.getText().toString();
        int categoryIndex = spinnerCategory.getSelectedItemPosition();
        boolean isRadioButtonSelected = radioButtonBR.isChecked() || radioButtonOT.isChecked();

        int qtdSticker = 0;
        try {
            qtdSticker = Integer.parseInt(qtdStickerText);
        } catch (NumberFormatException e) {
            UtilsAlert.showAlert(this,  R.string.qtd_sticker_integer);
            editQtdSticker.requestFocus();
            return;
        }

        if (qtdSticker < 0 || qtdSticker > 1000) {
            UtilsAlert.showAlert(this,   R.string.qtd_sticker_limit);
            editQtdSticker.requestFocus();
            editQtdSticker.setSelection(0, editQtdSticker.getText().toString().length());
            return;
        }

        Category category = Category.fromPosition(categoryIndex);

        String country = radioButtonBR.isChecked() ? String.valueOf(R.string.brazil) :
                                                        String.valueOf(R.string.others);
        Boolean shiny = checkboxShiny.isChecked();

        if (albumTitle.isEmpty()) {
            UtilsAlert.showAlert(this, R.string.title_required);
            editAlbumTitle.requestFocus();
            return;
        } else if (qtdStickerText.isEmpty()) {
            UtilsAlert.showAlert(this, R.string.qtd_sticker_required);
            editQtdSticker.requestFocus();
            return;
        } else if (!isRadioButtonSelected) {
            UtilsAlert.showAlert(this, R.string.country_required);
            radioButtonBR.requestFocus();
            return;
        } else if (categoryIndex == AdapterView.INVALID_POSITION) {
            UtilsAlert.showAlert(this, R.string.category_required);
            spinnerCategory.requestFocus();
            return;
        } else {
            UtilsAlert.showAlert(this, R.string.data_saved_successfully);
        }

        Album album = new Album(albumTitle, qtdSticker, country, shiny, category);

        if(album.equals(original)){
            setResult(AlbumActivity.RESULT_CANCELED);
            finish();
            return;
        }

        Intent intentResponse = new Intent();
        AlbumDatabase database = AlbumDatabase.getInstance(this);

        if(mode == NEW_MODE) {
            long newId = database.getAlbumDao().insert(album);

            if(newId <= 0) {
                UtilsAlert.showAlert(this, R.string.insert_error);
                return;
            }

            album.setId(newId);

        } else {
            album.setId(original.getId());
            int updateline = database.getAlbumDao().update(album);

            if(updateline != 1) {
                UtilsAlert.showAlert(this, R.string.update_error);
            }
        }

        saveLastCategory(categoryIndex);

        intentResponse.putExtra(ID, album.getId());

        setResult(AlbumActivity.RESULT_OK, intentResponse);

        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.options_registry, menu);
        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        MenuItem item = menu.findItem(R.id.menuItemSuggestCategory);

        if (item != null) {
            item.setChecked(suggestCategory);
        }

        return super.onPrepareOptionsMenu(menu);
    }

    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        int idMenuItem = item.getItemId();

        if(idMenuItem == R.id.menuItemSaveForm) {
            saveForm();
            return true;
        } else if (idMenuItem == R.id.menuItemCleanForm) {
            cleanForm();
            return true;
        } else if (idMenuItem == R.id.menuItemSuggestCategory) {
            boolean value = !item.isChecked();

            saveSuggestCategory(value);
            item.setChecked(value);

            if(suggestCategory) {
                spinnerCategory.setSelection(lastCategory);
            }

            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }

    private void readPreferences() {
        SharedPreferences shared = getSharedPreferences(AlbumActivity.PREFERENCES, Context.MODE_PRIVATE);

        suggestCategory = shared.getBoolean(SUGGEST_CATEGORY, suggestCategory);
        lastCategory = shared.getInt(LAST_CATEGORY, lastCategory);
    }

    private void saveSuggestCategory(boolean newValue) {
        SharedPreferences shared = getSharedPreferences(AlbumActivity.PREFERENCES, Context.MODE_PRIVATE);

        SharedPreferences.Editor editor = shared.edit();
        editor.putBoolean(SUGGEST_CATEGORY,newValue);
        editor.commit();

        suggestCategory = newValue;
    }

    private void saveLastCategory(int newValue) {
        SharedPreferences shared = getSharedPreferences(AlbumActivity.PREFERENCES, Context.MODE_PRIVATE);

        SharedPreferences.Editor editor = shared.edit();
        editor.putInt(LAST_CATEGORY,newValue);
        editor.commit();

        lastCategory = newValue;
    }
}