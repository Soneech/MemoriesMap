package com.example.memoriesmap;

import androidx.fragment.app.Fragment;

public interface FragmentsActions {
    void openFragment(int fragmentBodyLayoutID, Fragment fragment);
    void setDisplayHomeVisibility(boolean status);
}
