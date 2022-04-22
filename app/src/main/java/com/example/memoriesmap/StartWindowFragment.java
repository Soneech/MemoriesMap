package com.example.memoriesmap;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.memoriesmap.databinding.StartWindowFragmentBinding;

public class StartWindowFragment extends Fragment implements View.OnClickListener{
    private StartWindowFragmentBinding binding;
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
        //return inflater.inflate(R.layout.start_window_fragment, container, false);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.openRegistrationBtn:

                break;
            case R.id.openAuthorizationBtn:

                break;
            case R.id.openSettingsBtn:
                
                break;
        }
    }
}