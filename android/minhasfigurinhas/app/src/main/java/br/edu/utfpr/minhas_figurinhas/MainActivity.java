package br.edu.utfpr.minhas_figurinhas;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private Button buttonClear, buttonSave;
    private EditText editAlbumTitle, inputQtdSticker;
    private RadioButton radioButtonBR, radioButtonOT;
    private RadioGroup radioGroupCountry;
    private CheckBox checkboxShiny;
    private Spinner spinnerCategory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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

        Spinner spinnerCategory = findViewById(R.id.spinnerCategory);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.spinnerOptions, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCategory.setAdapter(adapter);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

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

        if (albumTitle.isEmpty()) {
            Toast.makeText(this, "O título do álbum é obrigatório.", Toast.LENGTH_SHORT).show();
            editAlbumTitle.requestFocus();
        } else if (qtdSticker.isEmpty()) {
            Toast.makeText(this, "A quantidade de figurinhas é obrigatória.", Toast.LENGTH_SHORT).show();
            inputQtdSticker.requestFocus();
        } else if (!isRadioButtonSelected) {
            Toast.makeText(this, "Selecione uma opção de país.", Toast.LENGTH_SHORT).show();
            radioButtonBR.requestFocus();
        } else if (selectedCategoryPosition == 0) {
            Toast.makeText(this, "Selecione uma categoria.", Toast.LENGTH_SHORT).show();
            spinnerCategory.requestFocus();
        } else {
            Toast.makeText(this, "Dados salvos com sucesso!", Toast.LENGTH_SHORT).show();
        }
    }
}