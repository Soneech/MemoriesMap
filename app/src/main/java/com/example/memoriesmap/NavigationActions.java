package com.example.memoriesmap;

import android.app.Activity;

import androidx.fragment.app.Fragment;

public interface NavigationActions {
    void openFragment(int fragmentBodyLayoutID, Fragment fragment);
    void goToOtherActivity();
    void setDisplayHomeVisibility(boolean status);
}
