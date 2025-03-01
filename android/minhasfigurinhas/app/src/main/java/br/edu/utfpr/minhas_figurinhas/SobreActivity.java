package br.edu.utfpr.minhas_figurinhas;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class SobreActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sobre);

        setTitle(getString(R.string.sobre));
    }

    public void openSiteAuthor(View view){
        openSite(String.valueOf(R.string.siteAuthor));
    }

    private void openSite(String url){

        Intent intentAbertura = new Intent(Intent.ACTION_VIEW);

        intentAbertura.setData(Uri.parse(url));

        if (intentAbertura.resolveActivity(getPackageManager()) != null) {
            startActivity(intentAbertura);
        } else {
            Toast.makeText(this,
                    R.string.noAppToOpen,
                    Toast.LENGTH_LONG).show();
        }
    }

    public void sendAuthorEmail(View view){
        sendEmail(new String[]{String.valueOf(R.string.email)}, String.valueOf(R.string.appContact));
    }

    private void sendEmail (String[] extraEmails, String subject){

        Intent intentAbertura = new Intent(Intent.ACTION_SENDTO);

        intentAbertura.setData(Uri.parse("mailto:"));
        intentAbertura.putExtra(Intent.EXTRA_EMAIL, extraEmails);
        intentAbertura.putExtra(Intent.EXTRA_SUBJECT, subject);

        if (intentAbertura.resolveActivity(getPackageManager()) != null) {
            startActivity(intentAbertura);
        } else {
            Toast.makeText(this,
                    R.string.noAppToSend,
                    Toast.LENGTH_LONG).show();
        }
    }
}