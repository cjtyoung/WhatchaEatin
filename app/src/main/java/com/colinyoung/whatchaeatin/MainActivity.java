package com.colinyoung.whatchaeatin;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.colinyoung.whatchaeatin.auth.ActivityAuth;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    private final static int REQUEST_CODE_AUTH = 42;

    private FirebaseAuth auth;

    private TextView mainMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        auth = FirebaseAuth.getInstance();

        mainMessage = findViewById(R.id.main_message);
    }

    @Override
    protected void onStart() {
        super.onStart();

        // Check if user is signed in (non-null response)
        FirebaseUser currentUser = auth.getCurrentUser();
        if (currentUser == null) {

            // Launch sign-in flow
            Intent intent = new Intent(this, ActivityAuth.class);
            startActivityForResult(intent, REQUEST_CODE_AUTH);
        }
        else {

            loadMainView();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CODE_AUTH && resultCode == RESULT_OK) {

            loadMainView();
        }
    }

    private void loadMainView() {

        mainMessage.setText("You are logged in!");
    }
}
