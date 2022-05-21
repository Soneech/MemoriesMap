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


import com.example.memoriesmap.NavigationActions;
import com.example.memoriesmap.R;
import com.example.memoriesmap.databinding.AuthorizationFragmentBinding;
import com.google.firebase.auth.FirebaseAuth;


public class AuthorizationFragment extends Fragment {

    private AuthorizationFragmentBinding binding;
    private NavigationActions navigationActions;
    private FirebaseAuth mAuth;

    private String email;
    private String password;

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
        binding.authorizationBtn.setOnClickListener(view -> signIn());

        navigationActions.setDisplayHomeVisibility(true);
        return binding.getRoot();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        navigationActions = (NavigationActions) context;
    }

    public void signIn() {
        email = binding.authEmailEditText.getText().toString();
        password = binding.authPasswordEditText.getText().toString();

        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                Toast.makeText(getActivity(), R.string.welcome, Toast.LENGTH_SHORT).show();
                navigationActions.goToOtherActivity();
            }
            else {
                Toast.makeText(getActivity(), R.string.user_doesnt_exist, Toast.LENGTH_LONG).show();
            }
        });
    }
}