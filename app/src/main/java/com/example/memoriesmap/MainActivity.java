package com.example.memoriesmap;

import android.os.Bundle;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.memoriesmap.databinding.MainActivityBinding;
import com.example.memoriesmap.main.MapFragment;
import com.example.memoriesmap.main.NotesListFragment;
import com.example.memoriesmap.main.ProfileFragment;
import com.example.memoriesmap.main.SettingsFragment;

public class MainActivity extends AppCompatActivity implements FragmentsActions{

    private MainActivityBinding binding;
    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;
    private ActionBar actionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = MainActivityBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        openFragment(R.id.mainFragmentBody, new ProfileFragment());
//        actionBar = getSupportActionBar();
//        actionBar.show();

        binding.bottomNavigationView.setOnItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.profile:
                    openFragment(R.id.mainFragmentBody, new ProfileFragment());
                    break;
                case R.id.notes:
                    openFragment(R.id.mainFragmentBody, new NotesListFragment());
                    break;
                case R.id.map:
                    openFragment(R.id.mainFragmentBody, new MapFragment());
                    break;
                case R.id.settings:
                    openFragment(R.id.mainFragmentBody, new SettingsFragment());
                    break;
                default:
                    break;
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