package com.pucp.pdm20181.view;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.pucp.pdm20181.R;

public class LoginActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private TextInputEditText userInput;
    private TextInputEditText passInput;
    private Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mAuth = FirebaseAuth.getInstance();
        if (mAuth.getCurrentUser() != null) {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            finish();
            return;
        }
        userInput = findViewById(R.id.login_user);
        passInput = findViewById(R.id.login_password);
        btnLogin = findViewById(R.id.btn_login);
        btnLogin.setOnClickListener((v) -> {
            String user = userInput.getText().toString();
            String pass = passInput.getText().toString();
            if (user.isEmpty() || pass.isEmpty()) {
                Toast.makeText(this, "Complete los campos", Toast.LENGTH_SHORT).show();
                return;
            }
            mAuth.signInWithEmailAndPassword(user, pass).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        Toast.makeText(
                                LoginActivity.this,
                                "Bienvenido, " + mAuth.getCurrentUser().getDisplayName(),
                                Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                        startActivity(intent);
                    } else {
                        Toast.makeText(LoginActivity.this, "Usuario y/o contraseña incorrectos", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        });
    }
}
