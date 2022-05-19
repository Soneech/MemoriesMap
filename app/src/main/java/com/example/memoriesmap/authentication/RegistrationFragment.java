package com.example.memoriesmap.authentication;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.memoriesmap.FragmentsActions;
import com.example.memoriesmap.R;
import com.example.memoriesmap.databinding.RegistrationFragmentBinding;
import com.example.memoriesmap.model.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegistrationFragment extends Fragment{

    private RegistrationFragmentBinding binding;
    private FragmentsActions fragmentsActions;

    private FirebaseAuth auth;
    private FirebaseDatabase database;
    private DatabaseReference users;

    private final String usersDatabaseName = "Users";

    private String username;
    private String email;
    private String password;
    private String repeatPassword;

    private final int usernameMinLength = 3;
    private final int usernameMaxLength = 16;

    private final int passwordMinLength = 16;
    private final int passwordMaxLength = 32;

    private final String invalidEmailExceptionMessage = "The email address is already in use by another account.";

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        auth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        users = database.getReference(usersDatabaseName);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = RegistrationFragmentBinding.inflate(inflater, container, false);
        fragmentsActions.setDisplayHomeVisibility(true);
        binding.registrationBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isDataNotNull()) {
                    setData();
                    if (isDataValid())
                        signUp();
                }
                else {
                    nullDataError();
                }
            }
        });
        return binding.getRoot();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        fragmentsActions = (FragmentsActions) context;
    }

    public boolean isDataNotNull() {
        if (binding.usernameEditText.getText().length() != 0
        && binding.emailEditText.getText().length() != 0
        && binding.passwordEditText.getText().length() != 0
        && binding.repeatPasswordEditText.getText().length() != 0) {
            return true;
        }
        return false;
    }

    public void nullDataError() {
        Toast.makeText(getActivity(), R.string.null_data_error, Toast.LENGTH_LONG).show();
    }

    public void setData() {
        username = binding.usernameEditText.getText().toString();
        email = binding.emailEditText.getText().toString();
        password = binding.passwordEditText.getText().toString();
        repeatPassword = binding.repeatPasswordEditText.getText().toString();
    }

    public boolean isDataValid() {
        if (!(username.length() >= usernameMinLength && username.length() <= usernameMaxLength)) {
            Toast.makeText(getActivity(), R.string.invalid_username, Toast.LENGTH_LONG).show();
            return false;
        }
        if (!(password.length() >= passwordMinLength && password.length() <= passwordMaxLength)) {
            Toast.makeText(getActivity(), R.string.invalid_password, Toast.LENGTH_LONG).show();
            return false;
        }
        if (!password.equals(repeatPassword)) {
            Toast.makeText(getActivity(), R.string.invalid_repeat_password, Toast.LENGTH_LONG).show();
            return false;
        }
        return true;
    }

    public void signUp() {
        auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    User user = new User(username, email, password);
                    users.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).setValue(user);
                    Toast.makeText(
                            getActivity(),
                            R.string.successful_registration_toast_text,
                            Toast.LENGTH_SHORT).show();
                    fragmentsActions.openFragment(R.id.authenticationFragmentBody, new AuthorizationFragment());
                }
                else {
                    String exception = task.getException().getMessage();
                    if (exception.equals(invalidEmailExceptionMessage)) {
                        Toast.makeText(getActivity(), R.string.user_already_exist, Toast.LENGTH_LONG).show();
                    }
                    else {
                        Toast.makeText(
                                getActivity(),
                                R.string.unsuccessful_registration_toast_text,
                                Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
    }
}