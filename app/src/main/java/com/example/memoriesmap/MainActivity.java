package com.example.memoriesmap;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.memoriesmap.fragments.FragmentsActions;
import com.example.memoriesmap.fragments.StartWindowFragment;

public class MainActivity extends AppCompatActivity implements FragmentsActions {

    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;
    private ActionBar actionBar;
    private String backStack = "Back";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        openFragment(new StartWindowFragment());
        actionBar = getSupportActionBar();

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
    public void setDisplayHomeVisibility(boolean status) {
        if (actionBar != null)
            actionBar.setDisplayHomeAsUpEnabled(status);
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