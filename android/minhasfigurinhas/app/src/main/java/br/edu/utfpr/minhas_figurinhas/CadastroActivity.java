package br.edu.utfpr.minhas_figurinhas;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class CadastroActivity extends AppCompatActivity {

    public static final String TITLE = "KEY_TITLE";
    public static final String QTDSTICKER = "KEY_QTDSTICKER";
    public static final String CATEGORY = "KEY_CATEGORY";
    public static final String COUNTRY = "KEY_COUNTRY";
    public static final String SHINY = "KEY_SHINY";
    public static final String SUGGEST_CATEGORY = "KEY_SUGGEST_CATEGORY";
    public static final String LAST_CATEGORY = "KEY_LAST_CATEGORY";

    public static final String MODE = "MODE";
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

                String albumTitle = bundle.getString(CadastroActivity.TITLE);
                String qtdSticker = bundle.getString(CadastroActivity.QTDSTICKER);
                boolean country = bundle.getBoolean(CadastroActivity.COUNTRY);
                boolean shiny = bundle.getBoolean(CadastroActivity.SHINY);
                Category category = Category.valueOf(bundle.getString(
                                                                        CadastroActivity.CATEGORY));
                String countryText;
                if (country) {
                    radioButtonBR.setChecked(true);
                    countryText = String.valueOf(R.string.brazil);
                } else {
                    radioButtonOT.setChecked(true);
                    countryText = String.valueOf(R.string.others);
                }

                original = new Album(albumTitle, Integer.parseInt(qtdSticker), countryText, shiny, category);

                editAlbumTitle.setText(albumTitle);
                editQtdSticker.setText(qtdSticker);

                checkboxShiny.setChecked(shiny);
                spinnerCategory.setSelection(category.ordinal());
            }
        }
    }

    private void cleanForm() {
        editAlbumTitle.setText(null);
        editQtdSticker.setText(null);
        radioGroupCountry.clearCheck();
        checkboxShiny.setChecked(false);
        spinnerCategory.setSelection(0);

        editAlbumTitle.requestFocus();

        Toast.makeText(this,
                R.string.form_cleared_successfully,
                Toast.LENGTH_LONG).show();
    }

    private void saveForm() {
        String albumTitle = editAlbumTitle.getText().toString();
        String qtdStickerText = editQtdSticker.getText().toString();
        int category = spinnerCategory.getSelectedItemPosition();
        boolean isRadioButtonSelected = radioButtonBR.isChecked() || radioButtonOT.isChecked();

        int qtdSticker = 0;
        try {
            qtdSticker = Integer.parseInt(qtdStickerText);
        } catch (NumberFormatException e) {
            Toast.makeText(this,
                    R.string.qtd_sticker_integer,
                    Toast.LENGTH_LONG).show();
            return;
        }

        if (qtdSticker < 0 || qtdSticker > 1000) {

            Toast.makeText(this,
                    R.string.qtd_sticker_limit,
                    Toast.LENGTH_LONG).show();

            editQtdSticker.requestFocus();
            editQtdSticker.setSelection(0, editQtdSticker.getText().toString().length());
            return;
        }

        Category categoriaAlbum = verifyCategory(category);

        String country = radioButtonBR.isChecked() ? String.valueOf(R.string.brazil) :
                                                        String.valueOf(R.string.others);
        Boolean shiny = checkboxShiny.isChecked();

        if (albumTitle.isEmpty()) {
            Toast.makeText(this, R.string.title_required, Toast.LENGTH_SHORT).show();
            editAlbumTitle.requestFocus();
            return;
        } else if (qtdStickerText.isEmpty()) {
            Toast.makeText(this, R.string.qtd_sticker_required, Toast.LENGTH_SHORT).show();
            editQtdSticker.requestFocus();
            return;
        } else if (!isRadioButtonSelected) {
            Toast.makeText(this, R.string.country_required, Toast.LENGTH_SHORT).show();
            radioButtonBR.requestFocus();
            return;
        } else if (category == AdapterView.INVALID_POSITION) {
            Toast.makeText(this, R.string.category_required, Toast.LENGTH_SHORT).show();
            spinnerCategory.requestFocus();
            return;
        } else {
            Toast.makeText(this, R.string.data_saved_successfully, Toast.LENGTH_SHORT).show();
        }

        if ((mode == EDIT_MODE &&
                albumTitle.equalsIgnoreCase(original.getTitle()) &&
                qtdSticker == original.getQtdStickers() &&
                country.equalsIgnoreCase(original.getCountry()) &&
                categoriaAlbum == original.getCategory())) {

            setResult(AlbumActivity.RESULT_CANCELED);
            finish();
            return;
        }

        saveLastCategory(category);

        Intent intentResponse = new Intent();

        intentResponse.putExtra(TITLE, albumTitle);
        intentResponse.putExtra(QTDSTICKER, qtdStickerText);
        intentResponse.putExtra(CATEGORY, categoriaAlbum.toString());
        intentResponse.putExtra(COUNTRY, country);
        intentResponse.putExtra(SHINY, shiny);

        setResult(AlbumActivity.RESULT_OK, intentResponse);

        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.cadastro_opcoes, menu);
        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        MenuItem item = menu.findItem(R.id.menuItemSugerirCategoria);

        if (item != null) {
            item.setChecked(suggestCategory);
        }

        return super.onPrepareOptionsMenu(menu);
    }

    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        int idMenuItem = item.getItemId();

        if(idMenuItem == R.id.menuItemSalvar) {
            saveForm();
            return true;
        } else if (idMenuItem == R.id.menuItemLimpar) {
            cleanForm();
            return true;
        } else if (idMenuItem == R.id.menuItemSugerirCategoria) {
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

    private Category verifyCategory(int position) {
        switch (position) {
            case 0:
                return Category.Sports;
            case 1:
                return Category.Movies;
            case 2:
                return Category.TV_Shows;
            case 3:
                return Category.Celebrities;
            case 4:
                return Category.Cartoons;
            case 5:
                return Category.Anime_and_Manga;
            case 6:
                return Category.History_and_Culture;
            case 7:
                return Category.Nature_and_Science;
            case 8:
                return Category.Space_and_Astronomy;
            case 9:
                return Category.Themed;
            case 10:
                return Category.Commemorative_Editions;
            case 11:
                return Category.Games;
            default:
                return Category.None;
        }
    }

}