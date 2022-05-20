package com.example.memoriesmap.main;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.preference.PreferenceFragmentCompat;

import com.example.memoriesmap.R;
import com.example.memoriesmap.NavigationActions;

public class SettingsFragment extends PreferenceFragmentCompat {

    private NavigationActions navigationActions;

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        setPreferencesFromResource(R.xml.root_preferences, rootKey);
        navigationActions.setDisplayHomeVisibility(true);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        navigationActions = (NavigationActions) context;
    }
}
