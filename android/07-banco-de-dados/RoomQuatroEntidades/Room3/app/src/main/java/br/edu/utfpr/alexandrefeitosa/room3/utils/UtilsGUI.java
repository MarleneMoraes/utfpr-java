package br.edu.utfpr.alexandrefeitosa.room3.utils;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;

import br.edu.utfpr.alexandrefeitosa.room3.R;

public class UtilsGUI {

    public static void avisoErro(Context contexto, int idTexto){
        avisoErro(contexto, contexto.getString(idTexto));
    }

    public static void avisoErro(Context contexto, String mensagem){

        AlertDialog.Builder builder = new AlertDialog.Builder(contexto);

        builder.setTitle(R.string.aviso);
        builder.setIcon(android.R.drawable.ic_dialog_alert);
        builder.setMessage(mensagem);

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

    public static boolean setListViewHeightBasedOnChildren(ListView listView, int alturaMinima) {

        ListAdapter listAdapter = listView.getAdapter();

        if (listAdapter != null) {

            int numberOfItems = listAdapter.getCount();

            // Get total height of all items.
            int totalItemsHeight = 0;

            for (int itemPos = 0; itemPos < numberOfItems; itemPos++) {

                View item = listAdapter.getView(itemPos, null, listView);

                float px = 500 * (listView.getResources().getDisplayMetrics().density);

                item.measure(View.MeasureSpec.makeMeasureSpec((int) px,
                             View.MeasureSpec.AT_MOST),
                             View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));

                totalItemsHeight += item.getMeasuredHeight();
            }

            // Get total height of all item dividers.
            int totalDividersHeight = listView.getDividerHeight() * (numberOfItems - 1);

            // Get padding
            int totalPadding = listView.getPaddingTop() + listView.getPaddingBottom();

            // Set list height.
            ViewGroup.LayoutParams params = listView.getLayoutParams();

            params.height = totalItemsHeight + totalDividersHeight + totalPadding;

            if (params.height < alturaMinima){
                params.height = alturaMinima;
            }

            listView.setLayoutParams(params);
            listView.requestLayout();

            return true;

        } else {
            return false;
        }
    }
}
