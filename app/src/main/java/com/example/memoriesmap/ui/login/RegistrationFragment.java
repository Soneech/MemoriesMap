package com.example.memoriesmap.ui.login;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.memoriesmap.R;
import com.example.memoriesmap.databinding.RegistrationFragmentBinding;
import com.example.memoriesmap.fragments.FragmentsActions;
import com.example.memoriesmap.fragments.MainWindowFragment;

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
        binding.registrationBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isDataNotNull()) {
                    startRegistration();
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

    public void startRegistration() {
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
            if (registrationModel.createUser()) {
                fragmentsActions.openFragment(new MainWindowFragment());
                Log.d("RRR", "create user");
            }
        }
    }
}