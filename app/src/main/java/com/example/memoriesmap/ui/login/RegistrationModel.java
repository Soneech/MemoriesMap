package com.example.memoriesmap.ui.login;

import android.app.Activity;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.memoriesmap.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegistrationModel {

    private FirebaseAuth mAuth;

    private String username;
    private String email;
    private String password;
    private String repeatPassword;
    private Activity context;

    private final int usernameMinLength = 3;
    private final int usernameMaxLength = 16;

    private final int passwordMinLength = 16;
    private final int passwordMaxLength = 32;

    private final String invalidUsername =
            ". Имя должно иметь длину от " + usernameMinLength + " до " + usernameMaxLength;
    private final String invalidPassword =
            ". Пароль должен иметь длину от " + passwordMinLength + " до " + passwordMaxLength;

    public RegistrationModel(String username, String email, String password, String repeatPassword, Activity context) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.repeatPassword = repeatPassword;
        this.context = context;
        mAuth = FirebaseAuth.getInstance();
    }

    public boolean isDataValid() {
        if (!(username.length() >= usernameMinLength && username.length() <= usernameMaxLength)) {
            Toast.makeText(context, R.string.invalid_username + invalidUsername, Toast.LENGTH_LONG).show();
            return false;
        }
        if (!(password.length() >= passwordMinLength && password.length() <= passwordMaxLength)) {
            Toast.makeText(context, R.string.invalid_password + invalidPassword, Toast.LENGTH_LONG).show();
            return false;
        }
        if (!password.equals(repeatPassword)) {
            Toast.makeText(context, R.string.invalid_repeat_password, Toast.LENGTH_LONG).show();
            return false;
        }
        return true;
    }

    public void createUser() {
        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(
                            context,
                            R.string.successful_registration_toast_text,
                            Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(
                            context,
                            R.string.unsuccessful_registration_toast_text,
                            Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
