package com.example.project_1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static final int REQUEST_CALL_PERMISSION = 1;
    private ImageView email, call, chat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        email = findViewById(R.id.img_email);
        call = findViewById(R.id.call);
        chat = findViewById(R.id.img_chat);

        email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                email();
            }
        });
        call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                call();
            }
        });

        chat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMessagingApp();
            }
        });
    }

    private void email() {
        // Create a new intent to send data
        Intent intent = new Intent(Intent.ACTION_SEND);
        // Set the type of data to be sent (wildcard for any type)
        intent.setType("*/*");
        // Add the recipient email address
        intent.putExtra(Intent.EXTRA_EMAIL, new String[]{"test@gmail.com"});
        // Add the email subject
        intent.putExtra(Intent.EXTRA_SUBJECT, "Feedback");
        // Start the activity that can handle the intent (email client)
        startActivity(intent);
    }

    private void call() {
        Intent callIntent = new Intent(Intent.ACTION_CALL);
        callIntent.setData(Uri.parse("tel:123456789"));
        // Check if the CALL_PHONE permission is granted
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            // Request the permission if not granted
            ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.CALL_PHONE}, REQUEST_CALL_PERMISSION);
        } else {
            // Permission already granted, you can proceed with making the call
        }
        startActivity(callIntent);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_CALL_PERMISSION) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permission granted, you can proceed with making the call
            } else {
                // Permission denied, handle it gracefully
            }
        }
    }

    private void openMessagingApp() {
        // Create a Uri with the scheme "smsto:" to indicate sending an SMS
        Uri uri = Uri.parse("smsto:");
        // Create a new intent with the action ACTION_SENDTO and the Uri
        Intent intent = new Intent(Intent.ACTION_SENDTO, uri);
        // Start the activity that can handle the intent (default messaging app)
        startActivity(intent);
    }
}
