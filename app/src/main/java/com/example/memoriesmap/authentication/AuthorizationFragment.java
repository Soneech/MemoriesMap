package com.example.memoriesmap.authentication;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;


import com.example.memoriesmap.FragmentsActions;
import com.example.memoriesmap.MainActivity;
import com.example.memoriesmap.R;
import com.example.memoriesmap.databinding.AuthorizationFragmentBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class AuthorizationFragment extends Fragment {

    private AuthorizationFragmentBinding binding;
    private FragmentsActions fragmentsActions;
    private FirebaseAuth mAuth;

    private String email;
    private String password;
    private boolean rememberMe;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAuth = FirebaseAuth.getInstance();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        binding = AuthorizationFragmentBinding.inflate(inflater, container, false);
        binding.authorizationBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signIn();
            }
        });

        fragmentsActions.setDisplayHomeVisibility(true);
        return binding.getRoot();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        fragmentsActions = (FragmentsActions) context;
    }

    public void signIn() {
        email = binding.authEmailEditText.getText().toString();
        password = binding.authPasswordEditText.getText().toString();
        rememberMe = binding.rememberMeCheckBox.isChecked();

        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(getActivity(), R.string.welcome, Toast.LENGTH_SHORT).show();
                    //fragmentsActions.openFragment(new MainWindowFragment());
                    Intent intent = new Intent(getContext(), MainActivity.class);
                    startActivity(intent);
                }
                else {
                    Log.d("RRR", task.getException().getMessage().toString());
                    Toast.makeText(getActivity(), R.string.user_doesnt_exist, Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}