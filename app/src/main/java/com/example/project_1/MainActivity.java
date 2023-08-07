package com.example.project_1;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private ImageView email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        email=findViewById(R.id.img_email);

        email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openEmailClient();
            }
        });

    }

    @SuppressLint("QueryPermissionsNeeded")
    private void openEmailClient() {
        String emailAddress = "recipient@example.com";
        String subject = "Sample Subject";
        String body = "Sample Email Body";

        // Create the email Uri
        Uri uri = Uri.parse("mailto:" + emailAddress + "?subject=" + Uri.encode(subject) + "&body=" + Uri.encode(body));

        // Create the email intent
        Intent emailIntent = new Intent(Intent.ACTION_SENDTO, uri);

        // Verify that there is an email app to handle the intent
        if (emailIntent.resolveActivity(getPackageManager()) != null) {
            startActivity(emailIntent);
        } else {
            // Handle the case where no email app is available
            Toast.makeText(this, "No email app found.", Toast.LENGTH_SHORT).show();
        }
    }
}