package com.example.memoriesmap.ui.login;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.fragment.app.Fragment;

import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.memoriesmap.databinding.AuthorizationFragmentBinding;

import com.example.memoriesmap.R;
import com.example.memoriesmap.fragments.FragmentsActions;
import com.google.firebase.auth.FirebaseAuth;

public class AuthorizationFragment extends Fragment {

    private LoginViewModel loginViewModel;
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

}