package com.example.memoriesmap.fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.preference.PreferenceFragmentCompat;

import com.example.memoriesmap.R;

public class SettingsFragment extends PreferenceFragmentCompat {

    private FragmentsActions fragmentsActions;

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        setPreferencesFromResource(R.xml.root_preferences, rootKey);
        fragmentsActions.setDisplayHomeVisibility(true);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        fragmentsActions = (FragmentsActions) context;
    }
}
