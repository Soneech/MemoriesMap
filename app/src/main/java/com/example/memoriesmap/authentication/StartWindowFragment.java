package com.example.memoriesmap.authentication;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.memoriesmap.NavigationActions;
import com.example.memoriesmap.R;
import com.example.memoriesmap.databinding.StartWindowFragmentBinding;
import com.example.memoriesmap.main.SettingsFragment;

public class StartWindowFragment extends Fragment implements View.OnClickListener{

    private StartWindowFragmentBinding binding;
    private NavigationActions navigationActions;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = StartWindowFragmentBinding.inflate(inflater, container, false);
        binding.openRegistrationBtn.setOnClickListener(this);
        binding.openAuthorizationBtn.setOnClickListener(this);
        binding.openSettingsBtn.setOnClickListener(this);
        navigationActions.setDisplayHomeVisibility(false);
        return binding.getRoot();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        navigationActions = (NavigationActions) context;
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.openRegistrationBtn:
                navigationActions.openFragment(R.id.authenticationFragmentBody, new RegistrationFragment());
                break;
            case R.id.openAuthorizationBtn:
                navigationActions.openFragment(R.id.authenticationFragmentBody, new AuthorizationFragment());
                break;
            case R.id.openSettingsBtn:
                navigationActions.openFragment(R.id.authenticationFragmentBody, new SettingsFragment());
                break;
        }
    }
}