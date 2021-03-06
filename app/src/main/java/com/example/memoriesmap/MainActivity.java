package com.example.memoriesmap;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.memoriesmap.databinding.MainActivityBinding;

public class MainActivity extends AppCompatActivity implements FragmentsActions {

    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;
    private String backStack = "Back";
    private MainActivityBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        openFragment(new StartWindowFragment());
        binding = MainActivityBinding.inflate(getLayoutInflater());

        binding.imageStartBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // something
            }
        });
    }

    public FragmentTransaction createFragmentTransaction() {
        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.addToBackStack(backStack);
        return fragmentTransaction;
    }

    @Override
    public void openFragment(Fragment fragment) {

        createFragmentTransaction()
                .replace(R.id.fragmentBody, fragment)
                .commit();
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                fragmentManager.popBackStack();
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}