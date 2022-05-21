package com.example.memoriesmap;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.memoriesmap.api.MapKitInitializer;
import com.example.memoriesmap.databinding.MainActivityBinding;
import com.example.memoriesmap.main.MapFragment;
import com.example.memoriesmap.main.NotesListFragment;
import com.example.memoriesmap.main.ProfileFragment;
import com.example.memoriesmap.main.SettingsFragment;
import com.yandex.mapkit.MapKitFactory;

public class MainActivity extends AppCompatActivity implements NavigationActions {

    private MainActivityBinding binding;
    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;
    private final String yandexMapsAPIKey = "5566d26e-22a9-43ad-b081-d76a3c12b22d";


    @SuppressLint("NonConstantResourceId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = MainActivityBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        openFragment(R.id.mainFragmentBody, new ProfileFragment());

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
        initializeMapKit();
    }

    public void initializeMapKit() {
        MapKitInitializer mapKitInitializer = new MapKitInitializer();

        if (!mapKitInitializer.getInitialize()) {
            MapKitFactory.setApiKey(yandexMapsAPIKey);
            MapKitFactory.initialize(this);
        }
        mapKitInitializer.setInitialize();
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
    public void goToOtherActivity() {
        Intent intent = new Intent(this, AuthenticationActivity.class);
        this.finish();
        startActivity(intent);
    }

    @Override
    public void setDisplayHomeVisibility(boolean status) {

    }
}