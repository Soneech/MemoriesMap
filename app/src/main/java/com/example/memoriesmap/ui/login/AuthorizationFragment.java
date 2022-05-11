package com.example.memoriesmap.ui.login;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.example.memoriesmap.databinding.AuthorizationFragmentBinding;
import com.example.memoriesmap.fragments.FragmentsActions;
import com.example.memoriesmap.fragments.MainWindowFragment;

public class AuthorizationFragment extends Fragment {

    private AuthorizationFragmentBinding binding;
    private FragmentsActions fragmentsActions;

    private String email;
    private String password;
    private boolean rememberMe;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
                createAuthorizationModel();
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

    public void createAuthorizationModel() {
        email = binding.authEmailEditText.getText().toString();
        password = binding.authPasswordEditText.getText().toString();
        rememberMe = binding.rememberMeCheckBox.isChecked();

        AuthorizationModel authorizationModel = new AuthorizationModel(
                email,
                password,
                rememberMe,
                getActivity());
        authorizationModel.signIn();
        Log.d("RR2", String.valueOf(authorizationModel.isUserExist()));
        if (authorizationModel.isUserExist()) {
            Log.d("RRR", "sign in");
            fragmentsActions.openFragment(new MainWindowFragment());
            updateUI();
        }
    }

    public void updateUI() {
        // something actions...
    }
}