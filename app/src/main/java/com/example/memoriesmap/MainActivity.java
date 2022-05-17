package com.example.memoriesmap;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.memoriesmap.databinding.MainActivityBinding;
import com.example.memoriesmap.main.SettingsFragment;

public class MainActivity extends AppCompatActivity implements FragmentsActions{

    private MainActivityBinding binding;
    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = MainActivityBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.bottomNavigationView2.setOnItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.settings:
                    openFragment(R.id.mainFragmentBody, new SettingsFragment());
            }
            return true;
        });

    }

    public FragmentTransaction createFragmentTransaction() {
        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.addToBackStack(null);
        return fragmentTransaction;
    }

    @Override
    public void openFragment(int fragmentBodyLayoutID, Fragment fragment) {
        createFragmentTransaction()
                .replace(fragmentBodyLayoutID, fragment)
                .commit();
    }

    @Override
    public void setDisplayHomeVisibility(boolean status) {

    }
}