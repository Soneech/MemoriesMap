package com.example.memoriesmap.ui.login;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.memoriesmap.databinding.RegistrationFragmentBinding;
import com.example.memoriesmap.fragments.FragmentsActions;

public class RegistrationFragment extends Fragment{

    private RegistrationFragmentBinding binding;
    private FragmentsActions fragmentsActions;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = RegistrationFragmentBinding.inflate(inflater, container, false);
        fragmentsActions.setDisplayHomeVisibility(true);
        return binding.getRoot();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        fragmentsActions = (FragmentsActions) context;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.registrationBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String username = binding.usernameEditText.getText().toString();
                final String email = binding.emailEditText.getText().toString();
                final String password = binding.passwordEditText.getText().toString();
                final String repeatPassword = binding.repeatPasswordEditText.getText().toString();

                RegistrationModel registrationModel = new RegistrationModel(
                        username,
                        email,
                        password,
                        repeatPassword,
                        getActivity());
                if (registrationModel.isDataValid()) {
                    registrationModel.createUser();
                }
            }
        });
    }
}