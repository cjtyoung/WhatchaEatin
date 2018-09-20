package com.colinyoung.whatchaeatin.auth;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.colinyoung.whatchaeatin.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class ActivityAuth extends FragmentActivity implements FragmentCreateAccount.UserCreator {

    private FirebaseAuth auth;
    private FragmentCreateAccount fragCreateAccount;
    private FrameLayout fragContainer;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);

        auth = FirebaseAuth.getInstance();

        fragContainer = findViewById(R.id.auth_fragment_frame);
        final EditText fieldEmail = findViewById(R.id.auth_edittext_email);
        final EditText fieldPassword = findViewById(R.id.auth_edittext_password);
        Button buttonSignIn = findViewById(R.id.auth_button_signin);
        Button buttonCreate = findViewById(R.id.auth_button_create);

        buttonSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signInUser(fieldEmail.getText().toString(), fieldPassword.getText().toString());
            }
        });

        buttonCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                fragContainer.setVisibility(View.VISIBLE);
                fragCreateAccount = new FragmentCreateAccount();
                FragmentManager fm = getSupportFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                ft.add(R.id.auth_fragment_frame, fragCreateAccount);
                ft.commit();
            }
        });
    }

    @Override
    public void createUser(String email, String password) {

        fragCreateAccount.clearErrorState();

        // TODO : Validate inputs

        auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if (task.isSuccessful()) {

                            if (fragCreateAccount != null) {
                                FragmentManager fm = getSupportFragmentManager();
                                FragmentTransaction ft = fm.beginTransaction();
                                ft.remove(fragCreateAccount);
                                ft.commit();
                                fragContainer.setVisibility(View.GONE);
                            }
                        }
                        else {

                            if (fragCreateAccount != null) {

                                if (task.getException() != null) {
                                    fragCreateAccount.setErrorState(task.getException().getMessage());
                                }
                                else {
                                    fragCreateAccount.setErrorState("Failed to create account");
                                }
                            }
                        }
                    }
                });
    }

    private void signInUser(String email, String password) {

        auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if (task.isSuccessful()) {

                            setResult(RESULT_OK);
                            finish();
                        }
                        else {

                            Toast.makeText(ActivityAuth.this, "Failed to sign in", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}
