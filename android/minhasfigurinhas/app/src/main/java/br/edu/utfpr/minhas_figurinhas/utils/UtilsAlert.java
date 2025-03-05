package br.edu.utfpr.minhas_figurinhas.utils;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.widget.Button;

import br.edu.utfpr.minhas_figurinhas.R;

public final class UtilsAlert {
    private UtilsAlert() { }

    public static void showAlert(Context context, int idMessage){
        showAlert(context, context.getString(idMessage), null);
    }
    public static void showAlert(Context context, int idMessage,
                                 DialogInterface.OnClickListener listener){
        showAlert(context, context.getString(idMessage), listener);
    }
    public static void showAlert(Context context, String message,
                                 DialogInterface.OnClickListener listener) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);

        builder.setTitle(R.string.warning);
        builder.setIcon(android.R.drawable.ic_dialog_info);
        builder.setMessage(message);

        builder.setNeutralButton(R.string.ok, listener);

        AlertDialog alert = builder.create();
        alert.show();
    }

    public static void confirmAction(Context context, int idMessage,
                                     DialogInterface.OnClickListener listenerYes,
                                     DialogInterface.OnClickListener listenerNo) {
        confirmAction(context, context.getString(idMessage), listenerYes, listenerNo);
    }
    public static void confirmAction(Context context, String message,
                                     DialogInterface.OnClickListener listenerYes,
                                     DialogInterface.OnClickListener listenerNo) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);

        builder.setTitle(R.string.confirmation);
        builder.setIcon(android.R.drawable.ic_dialog_alert);

        builder.setMessage(message);

        builder.setPositiveButton(R.string.yes, listenerYes);
        builder.setNegativeButton(R.string.no, listenerNo);

        AlertDialog alert = builder.create();
        alert.show();

        Button yesButton = alert.getButton(DialogInterface.BUTTON_POSITIVE);
        Button noButton = alert.getButton(DialogInterface.BUTTON_NEGATIVE);

        if (yesButton != null) {
            yesButton.setTextColor(Color.GREEN);
        }

        if (noButton != null) {
            noButton.setTextColor(Color.RED);
        }
    }
}
