package br.edu.utfpr.alexandrefeitosa.room1.utils;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.widget.EditText;

import br.edu.utfpr.alexandrefeitosa.room1.R;

public class UtilsGUI {

    public static void avisoErro(Context contexto, int idTexto){

        AlertDialog.Builder builder = new AlertDialog.Builder(contexto);

        builder.setTitle(R.string.aviso);
        builder.setIcon(android.R.drawable.ic_dialog_alert);
        builder.setMessage(idTexto);

        builder.setNeutralButton(R.string.ok,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });

        AlertDialog alert = builder.create();
        alert.show();
    }

    public static void confirmaAcao(Context contexto,
                                    String mensagem,
                                    DialogInterface.OnClickListener listener){

        AlertDialog.Builder builder = new AlertDialog.Builder(contexto);

        builder.setTitle(R.string.confirmacao);
        builder.setIcon(android.R.drawable.ic_dialog_alert);

        builder.setMessage(mensagem);

        builder.setPositiveButton(R.string.sim, listener);
        builder.setNegativeButton(R.string.nao, listener);

        AlertDialog alert = builder.create();
        alert.show();
    }

    public static String validaCampoTexto(Context contexto,
                                          EditText editText,
                                          int idMensagemErro){

        String texto = editText.getText().toString();

        if (UtilsString.stringVazia(texto)){
            UtilsGUI.avisoErro(contexto, idMensagemErro);
            editText.setText(null);
            editText.requestFocus();
            return null;
        }else{
            return texto.trim();
        }
    }
}
