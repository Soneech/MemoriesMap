package com.example.memoriesmap.ui.login;

import android.app.Activity;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.memoriesmap.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class AuthorizationModel {

    private String email;
    private String password;
    private boolean rememberMe;
    private Activity context;

    private FirebaseAuth mAuth;

    public AuthorizationModel(String email, String password, boolean rememberMe, Activity context) {
        this.email = email;
        this.password = password;
        this.rememberMe = rememberMe;
        this.context = context;
        mAuth = FirebaseAuth.getInstance();
    }

    public boolean signIn() {
        final boolean[] isUserExist = {false};
        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(context, R.string.welcome, Toast.LENGTH_SHORT).show();
                    isUserExist[0] = true;
                }
                else {
                    Toast.makeText(context, R.string.user_doesnt_exist, Toast.LENGTH_LONG).show();
                }
            }
        });
        return isUserExist[0];
    }
}
