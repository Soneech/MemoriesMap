package com.example.memoriesmap;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, FragmentsActions {

    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;
    private String backStack = "Back";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        openFragment(new StartWindowFragment());
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.openSettingsBtn:
                openSettings();
                break;
            default:
                break;
        }
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
    }

    @Override
    public void openSettings() {
        createFragmentTransaction()
                .replace(R.id.fragmentBody, new SettingsFragment())
                .commit();

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }
}