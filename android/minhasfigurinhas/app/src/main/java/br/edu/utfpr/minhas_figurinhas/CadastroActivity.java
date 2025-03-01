package br.edu.utfpr.minhas_figurinhas;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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

    public static final String TITULO = "KEY_TITULO";
    public static final String QTDSTICKER = "KEY_QTDSTICKER";
    public static final String CATEGORIA = "KEY_CATEGORIA";
    public static final String NACIONALITY = "KEY_NACIONALITY";
    public static final String SHINY = "KEY_SHINY";

    public static final String MODO = "MODO";
    public static final int MODO_NOVO = 0;
    public static final int MODO_EDITAR = 1;

    private EditText editAlbumTitle, editQtdSticker;
    private RadioButton radioButtonBR, radioButtonOT;
    private RadioGroup radioGroupCountry;
    private CheckBox checkboxShiny;
    private Spinner spinnerCategory;

    private int modo;

    private Album original;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        setTitle(getString(R.string.novo_album));

        editAlbumTitle = findViewById(R.id.inputTitle);
        editQtdSticker = findViewById(R.id.inputQtdSticker);
        radioGroupCountry = findViewById(R.id.radioGroupCountry);
        radioButtonBR = findViewById(R.id.radioButtonBR);
        radioButtonOT = findViewById(R.id.radioButtonOT);
        checkboxShiny = findViewById(R.id.checkboxShiny);
        spinnerCategory = findViewById(R.id.spinnerCategory);


        Intent intentAbertura = getIntent();

        Bundle bundle = intentAbertura.getExtras();

        if (bundle != null){
            modo = bundle.getInt(MODO);

            if (modo == MODO_NOVO) {
                setTitle(getString(R.string.novo_album));
            } else {
                setTitle(getString(R.string.editar));

                String albumTitle = bundle.getString(CadastroActivity.TITULO);
                String qtdSticker = bundle.getString(CadastroActivity.QTDSTICKER);
                boolean country = bundle.getBoolean(CadastroActivity.NACIONALITY);
                boolean shiny = bundle.getBoolean(CadastroActivity.SHINY);
                CategoriaAlbum categoriaAlbum = CategoriaAlbum.valueOf(bundle.getString(
                                                                        CadastroActivity.CATEGORIA));
                String countryText;
                if (country) {
                    radioButtonBR.setChecked(true);
                    countryText = "Brasil";
                } else {
                    radioButtonOT.setChecked(true);
                    countryText = "Outros";
                }

                original = new Album(albumTitle, Integer.parseInt(qtdSticker), countryText, shiny, categoriaAlbum);

                editAlbumTitle.setText(albumTitle);
                editQtdSticker.setText(qtdSticker);

                checkboxShiny.setChecked(shiny);
                spinnerCategory.setSelection(categoriaAlbum.ordinal());
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
                "Formulário limpo com sucesso",
                Toast.LENGTH_LONG).show();
    }

    private void saveForm() {
        String albumTitle = editAlbumTitle.getText().toString();
        String qtdStickerText = editQtdSticker.getText().toString();
        int selectedCategoryPosition = spinnerCategory.getSelectedItemPosition();
        boolean isRadioButtonSelected = radioButtonBR.isChecked() || radioButtonOT.isChecked();

        Log.d("saveForm", "qtdStickerText: " + qtdStickerText);

        CategoriaAlbum categoriaAlbum = verificarCategoria(selectedCategoryPosition);

        int qtdSticker = 0;
        try {
            qtdSticker = Integer.parseInt(qtdStickerText);
        } catch (NumberFormatException e) {
            Toast.makeText(this,
                    R.string.qtdStickersInteiro,
                    Toast.LENGTH_LONG).show();
            return;
        }

        if (qtdSticker < 0 || qtdSticker > 1000){

            Toast.makeText(this,
                    "Quantidade de figurinhas deve ser entre 0 e 1000",
                    Toast.LENGTH_LONG).show();

            editQtdSticker.requestFocus();
            editQtdSticker.setSelection(0, editQtdSticker.getText().toString().length());
            return;
        }

        String country = radioButtonBR.isChecked() ? "Brasil" : "Outro";
        Boolean shiny = checkboxShiny.isChecked();

        if (albumTitle.isEmpty()) {
            Toast.makeText(this, "O título do álbum é obrigatório.", Toast.LENGTH_SHORT).show();
            editAlbumTitle.requestFocus();
            return;
        } else if (qtdStickerText.isEmpty()) {
            Toast.makeText(this, "A quantidade de figurinhas é obrigatória.", Toast.LENGTH_SHORT).show();
            editQtdSticker.requestFocus();
            return;
        } else if (!isRadioButtonSelected) {
            Toast.makeText(this, "Selecione uma opção de país.", Toast.LENGTH_SHORT).show();
            radioButtonBR.requestFocus();
            return;
        } else if (selectedCategoryPosition == AdapterView.INVALID_POSITION) {
            Toast.makeText(this, "Selecione uma categoria.", Toast.LENGTH_SHORT).show();
            spinnerCategory.requestFocus();
            return;
        } else {
            Toast.makeText(this, "Dados salvos com sucesso!", Toast.LENGTH_SHORT).show();
        }

        if ((modo == MODO_EDITAR &&
                albumTitle.equalsIgnoreCase(original.getTitulo()) &&
                qtdSticker == original.getQtdFigurinhas() &&
                country.equalsIgnoreCase(original.getPais()) &&
                categoriaAlbum == original.getCategoria())) {

            setResult(AlbumActivity.RESULT_CANCELED);
            finish();
            return;
        }

        Intent intentResposta = new Intent();

        intentResposta.putExtra(TITULO, albumTitle);
        intentResposta.putExtra(QTDSTICKER, qtdStickerText);
        intentResposta.putExtra(CATEGORIA, categoriaAlbum.toString());
        intentResposta.putExtra(NACIONALITY, country);
        intentResposta.putExtra(SHINY, shiny);

        setResult(AlbumActivity.RESULT_OK, intentResposta);

        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.cadastro_opcoes, menu);
        return true;
    }

    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        int idMenuItem = item.getItemId();

        if(idMenuItem == R.id.menuItemSalvar) {
            saveForm();
            return true;
        } else if (idMenuItem == R.id.menuItemLimpar) {
            cleanForm();
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }
    private CategoriaAlbum verificarCategoria(int position) {
        switch (position) {
            case 0:
                return CategoriaAlbum.Esportes;
            case 1:
                return CategoriaAlbum.Filmes;
            case 2:
                return CategoriaAlbum.Series_de_TV;
            case 3:
                return CategoriaAlbum.Celebridades;
            case 4:
                return CategoriaAlbum.Desenhos_Animados;
            case 5:
                return CategoriaAlbum.Animes_Mangas;
            case 6:
                return CategoriaAlbum.Historia_Cultura;
            case 7:
                return CategoriaAlbum.Natureza_Ciencias;
            case 8:
                return CategoriaAlbum.Espaco_Astronomia;
            case 9:
                return CategoriaAlbum.Tematicos;
            case 10:
                return CategoriaAlbum.Edicoes_Comemorativas;
            case 11:
                return CategoriaAlbum.Jogos;
            default:
                return CategoriaAlbum.Nenhum;
        }
    }

}