package br.edu.utfpr.minhas_figurinhas;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class CadastroActivity extends AppCompatActivity {

    public static final String TITULO = "KEY_TITULO";
    public static final String QTDSTICKER = "KEY_QTDSTICKER";
    public static final String CATEGORIA = "KEY_CATEGORIA";
    public static final String NACIONALITY = "KEY_NACIONALITY";
    public static final String SHINY = "KEY_SHINY";
    private Button buttonClear, buttonSave;
    private EditText editAlbumTitle, inputQtdSticker;
    private RadioButton radioButtonBR, radioButtonOT;
    private RadioGroup radioGroupCountry;
    private CheckBox checkboxShiny;
    private Spinner spinnerCategory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        setTitle(getString(R.string.novo_lbum));
        /*
            (v, insets) -> {
                Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
                v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
                return insets;
            });
        */

        editAlbumTitle = findViewById(R.id.editAlbumTitle);
        inputQtdSticker = findViewById(R.id.inputQtdSticker);
        radioGroupCountry = findViewById(R.id.radioGroupCountry);
        radioButtonBR = findViewById(R.id.radioButtonBR);
        radioButtonOT = findViewById(R.id.radioButtonOT);
        checkboxShiny = findViewById(R.id.checkboxShiny);
        spinnerCategory = findViewById(R.id.spinnerCategory);

        buttonClear = findViewById(R.id.buttonClear);
        buttonSave = findViewById(R.id.buttonSave);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.categoria, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCategory.setAdapter(adapter);

        /*ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.listViewAlbuns), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
*/
        buttonClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cleanForm();
            }
        });

        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveForm();
            }
        });
    }

    private void cleanForm() {
        editAlbumTitle.setText("");
        inputQtdSticker.setText("");
        radioGroupCountry.clearCheck();
        checkboxShiny.setChecked(false);
        spinnerCategory.setSelection(0);
    }

    private void saveForm() {
        String albumTitle = editAlbumTitle.getText().toString();
        String qtdSticker = inputQtdSticker.getText().toString();
        int selectedCategoryPosition = spinnerCategory.getSelectedItemPosition();
        boolean isRadioButtonSelected = radioButtonBR.isChecked() || radioButtonOT.isChecked();

        CategoriaAlbum categoriaAlbum = verificarCategoria(selectedCategoryPosition);

        if (albumTitle.isEmpty()) {
            Toast.makeText(this, "O título do álbum é obrigatório.", Toast.LENGTH_SHORT).show();
            editAlbumTitle.requestFocus();
        } else if (qtdSticker.isEmpty()) {
            Toast.makeText(this, "A quantidade de figurinhas é obrigatória.", Toast.LENGTH_SHORT).show();
            inputQtdSticker.requestFocus();
        } else if (!isRadioButtonSelected) {
            Toast.makeText(this, "Selecione uma opção de país.", Toast.LENGTH_SHORT).show();
            radioButtonBR.requestFocus();
        } else if (selectedCategoryPosition == AdapterView.INVALID_POSITION) {
            Toast.makeText(this, "Selecione uma categoria.", Toast.LENGTH_SHORT).show();
            spinnerCategory.requestFocus();
        } else {
            Toast.makeText(this, "Dados salvos com sucesso!", Toast.LENGTH_SHORT).show();
        }

        String country = radioButtonBR.isChecked() ? "Brasil" : "Outro";
        Boolean shiny = checkboxShiny.isChecked();

        Intent intentResposta = new Intent();
        intentResposta.putExtra(TITULO, albumTitle);
        intentResposta.putExtra(QTDSTICKER, qtdSticker);
        intentResposta.putExtra(CATEGORIA, categoriaAlbum.toString());
        intentResposta.putExtra(NACIONALITY, country);
        intentResposta.putExtra(SHINY, shiny);

        setResult(AlbumActivity.RESULT_OK, intentResposta);
        finish();
    }

    private CategoriaAlbum verificarCategoria(int position) {
        switch (position) {
            case 1:
                return CategoriaAlbum.ESPORTES;
            case 2:
                return CategoriaAlbum.FILMES;
            case 3:
                return CategoriaAlbum.SERIES_TV;
            case 4:
                return CategoriaAlbum.CELEBRIDADES;
            case 5:
                return CategoriaAlbum.DESENHOS_ANIMADOS;
            case 6:
                return CategoriaAlbum.ANIMES_MANGAS;
            case 7:
                return CategoriaAlbum.HISTORIA_CULTURA;
            case 8:
                return CategoriaAlbum.NATUREZA_CIENCIAS;
            case 9:
                return CategoriaAlbum.ESPACO_ASTRONOMIA;
            case 10:
                return CategoriaAlbum.TEMATICOS;
            case 11:
                return CategoriaAlbum.EDICOES_COMEMORATIVAS;
            case 12:
                return CategoriaAlbum.JOGOS;
            default:
                return CategoriaAlbum.NENHUM;
        }

    }

}