package com.example.nicoleteo.angelwings;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    Button loginButton;
    ProgressBar loginProgress;

    FirebaseAuth mAuth;
    EditText login_email, login_password;
    ProgressBar login_progressbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mAuth = FirebaseAuth.getInstance();

        login_email = (EditText) findViewById(R.id.login_email);
        login_password = (EditText) findViewById(R.id.login_password);
        login_progressbar = (ProgressBar) findViewById(R.id.login_progressbar);

        findViewById(R.id.to_signup).setOnClickListener(this);
        findViewById(R.id.signup_button).setOnClickListener(this);

    }

    private void userLogin() {


    }


    mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {

        @Override
        public void onComplete (@NonNull Task < AuthResult > task) {
            login_progressbar.setVisibility(View.GONE);
            if (task.isSuccessful()) {
                finish();
                Intent intent = new Intent(LoginActivity.this, ProfileActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            } else {
                Toast.makeText(getApplicationContext(), task.getException().getMessage(), Toast.LENGTH_SHORT).show();
            }

        }
    });


    @Override
    protected void onStart() {
        super.onStart();

        if (mAuth.getCurrentUser() != null) {
            finish();
            startActivity(new Intent(this, ProfileActivity.class));
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.to_signup:
                finish();
                startActivity(new Intent(this, SignupActivity.class));
                break;

            case R.id.login_button:
                userLogin();
                break;








        /*

        loginButton = findViewById(R.id.login_button);
        loginProgress = findViewById(R.id.login_progressbar);
        loginProgress.setVisibility(View.INVISIBLE);

        loginButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                loginProgress.setVisibility(View.VISIBLE);
                loginButton.setVisibility(View.INVISIBLE);

            }

        });

        */


        }
    }

}
