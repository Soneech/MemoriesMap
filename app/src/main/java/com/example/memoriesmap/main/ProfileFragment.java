package com.example.memoriesmap.main;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.memoriesmap.NavigationActions;
import com.example.memoriesmap.R;
import com.example.memoriesmap.model.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Objects;

public class ProfileFragment extends Fragment {

    private View view;

    private FirebaseAuth auth;
    private DatabaseReference dataBase;
    private final String usersDatabaseName = "Users";

    private String username;
    private String email;

    private FirebaseUser currentUser;
    private User user;

    private NavigationActions navigationActions;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        auth = FirebaseAuth.getInstance();
        dataBase = FirebaseDatabase.getInstance().getReference(usersDatabaseName);
        currentUser = auth.getCurrentUser();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.profile_fragment, container, false);
        Button logOutBtn = view.findViewById(R.id.logOutBtn);  //                                   возможно, удастся переделать с binding...

        logOutBtn.setOnClickListener(view -> {
            auth.signOut();
            currentUser = null;
            navigationActions.goToOtherActivity();
        });

        getDataFromDatabase();
        return view;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        navigationActions = (NavigationActions) context;
    }

    public void getDataFromDatabase() {
        ValueEventListener valueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot: snapshot.getChildren()) {
                    if (Objects.equals(dataSnapshot.getKey(), currentUser.getUid())) {
                        user = dataSnapshot.getValue(User.class);
                        displayUserData();
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        };
        dataBase.addValueEventListener(valueEventListener);
    }

    public void displayUserData() {
        username = user.getName();
        email = user.getEmail();
        TextView usernameTV = view.findViewById(R.id.userNameTextView);
        TextView emailTV = view.findViewById(R.id.emailTextView);
        usernameTV.setText(username);
        emailTV.setText(email);
    }
}