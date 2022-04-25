package com.example.memoriesmap;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.memoriesmap.databinding.StartWindowFragmentBinding;
import com.example.memoriesmap.ui.login.AuthorizationFragment;
import com.example.memoriesmap.ui.login.RegistrationFragment;

public class StartWindowFragment extends Fragment implements View.OnClickListener{

    private StartWindowFragmentBinding binding;
    private FragmentsActions fragmentsActions;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = StartWindowFragmentBinding.inflate(inflater, container, false);
        binding.openRegistrationBtn.setOnClickListener(this);
        binding.openAuthorizationBtn.setOnClickListener(this);
        binding.openSettingsBtn.setOnClickListener(this);
        return binding.getRoot();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        fragmentsActions = (FragmentsActions) context;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.openRegistrationBtn:
                fragmentsActions.openFragment(new RegistrationFragment());
                break;
            case R.id.openAuthorizationBtn:
                fragmentsActions.openFragment(new AuthorizationFragment());
                break;
            case R.id.openSettingsBtn:
                fragmentsActions.openSettings();
                break;
        }
    }
}