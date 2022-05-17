package com.example.memoriesmap;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.memoriesmap.authentication.StartWindowFragment;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class AuthenticationActivity extends AppCompatActivity implements FragmentsActions {

    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;
    private ActionBar actionBar;

    private FirebaseUser currentUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.authentication_activity);

        openFragment(R.id.authenticationFragmentBody, new StartWindowFragment());
        actionBar = getSupportActionBar();
    }

    @Override
    protected void onStart() {
        super.onStart();
        currentUser = FirebaseAuth.getInstance().getCurrentUser();
        if (currentUser != null) {
            //                                                   !!!!!!!!!!!!!!
        }
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