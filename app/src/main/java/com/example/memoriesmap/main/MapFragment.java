package com.example.memoriesmap.main;

import android.app.AlertDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.example.memoriesmap.R;
import com.example.memoriesmap.model.Memory;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.yandex.mapkit.Animation;
import com.yandex.mapkit.MapKitFactory;
import com.yandex.mapkit.geometry.Point;
import com.yandex.mapkit.layers.GeoObjectTapEvent;
import com.yandex.mapkit.layers.GeoObjectTapListener;
import com.yandex.mapkit.map.CameraPosition;
import com.yandex.mapkit.map.InputListener;
import com.yandex.mapkit.map.Map;
import com.yandex.mapkit.mapview.MapView;
import com.yandex.runtime.image.ImageProvider;

import java.util.Objects;


public class MapFragment extends Fragment implements GeoObjectTapListener, InputListener {

    private final double latitudeMoscow = 55.751574;
    private final double longitudeMoscow = 37.573856;
    private final float zoom = 11.0f;
    private final float azimuth = 0.0f;
    private final float tilt = 0.0f;

    private MapView mapView;
    private View view;

    private Point tapPoint;

    private DatabaseReference memoriesDataBase;
    private final String memoriesDBName = "Memories";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        memoriesDataBase = FirebaseDatabase.getInstance().getReference(memoriesDBName);

        view = inflater.inflate(R.layout.map_fragment, container, false);

        mapView = view.findViewById(R.id.mapView);
        mapView.getMap().move(
                new CameraPosition(new Point(latitudeMoscow, longitudeMoscow), zoom, azimuth, tilt),
                new Animation(Animation.Type.SMOOTH, 0),
                null);

        mapView.getMap().addTapListener(this);
        mapView.getMap().addInputListener(this);
        updateMap();
        return view;
    }

    @Override
    public void onStop() {
        mapView.onStop();
        MapKitFactory.getInstance().onStop();
        super.onStop();
    }

    @Override
    public void onStart() {
        super.onStart();
        MapKitFactory.getInstance().onStart();
        mapView.onStart();
    }

    @Override
    public boolean onObjectTap(@NonNull GeoObjectTapEvent geoObjectTapEvent) {
        return false;
    }

    @Override
    public void onMapTap(@NonNull Map map, @NonNull Point point) {
        tapPoint = point;
        showAddingMemoryWindow();
    }

    @Override
    public void onMapLongTap(@NonNull Map map, @NonNull Point point) {

    }

    public static Bitmap getBitmapFromVectorDrawable(Context context, int drawableId) {
        Drawable drawable = ContextCompat.getDrawable(context, drawableId);

        assert drawable != null;
        Bitmap bitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(),
                drawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
        drawable.draw(canvas);

        return bitmap;
    }

    public void updateMap() {
        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
        ValueEventListener valueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot: snapshot.getChildren()) {
                    Memory memory = dataSnapshot.getValue(Memory.class);
                    assert memory != null;
                    assert currentUser != null;
                    if (memory.getUserKey().equals(currentUser.getUid())) {
                        Bitmap placeMarkBitmap = getBitmapFromVectorDrawable(
                                getContext(),
                                R.drawable.ic_baseline_place_24);

                        mapView
                                .getMap()
                                .getMapObjects()
                                .addPlacemark(
                                        memory.getPoint(),
                                        ImageProvider
                                                .fromBitmap(placeMarkBitmap)).
                                addTapListener((mapObject, point) -> {
                                    showDisplayMemoryWindow(memory);
                                    return true;
                                });
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        };
        memoriesDataBase.addValueEventListener(valueEventListener);
    }

    public void saveMemory(String memoryTitle, String memoryDate, String memoryText) {
        String userKey = Objects.requireNonNull(FirebaseAuth.getInstance().getCurrentUser()).getUid();
        Memory memory = new Memory(
                memoryTitle, memoryDate, memoryText, tapPoint, userKey,
                String.valueOf(tapPoint.getLatitude()).replace(".", "")
                        + String.valueOf(tapPoint.getLongitude()).replace(".", ""));
        memoriesDataBase.push().setValue(memory);
        updateMap();
    }

    public void showAddingMemoryWindow() {
        AlertDialog.Builder dialog = new AlertDialog.Builder(getContext());
        dialog.setTitle(R.string.adding_memory_text);

        LayoutInflater inflater = LayoutInflater.from(getActivity());
        View memoryWindow = inflater.inflate(R.layout.add_memory_window, null);
        dialog.setView(memoryWindow);

        final EditText memoryTitleEditText = memoryWindow.findViewById(R.id.memoryTitleEditText);
        final EditText memoryDateEditText = memoryWindow.findViewById(R.id.memoryDateEditText);
        final TextInputEditText memoryTextEditText = memoryWindow.findViewById(R.id.memoryText);

        dialog.setNegativeButton(R.string.cancel, (dialogInterface, i) -> dialogInterface.dismiss());

        dialog.setPositiveButton(R.string.save, (dialogInterface, i) -> {
            String memoryTitle = memoryTitleEditText.getText().toString();
            String memoryDate = memoryDateEditText.getText().toString();
            String memoryText = Objects.requireNonNull(memoryTextEditText.getText()).toString();
            saveMemory(memoryTitle, memoryDate, memoryText);
        });
        dialog.show();
    }

    public void showDisplayMemoryWindow(Memory memory) {
        AlertDialog.Builder dialog = new AlertDialog.Builder(getContext());
        dialog.setTitle(R.string.display_memory);

        LayoutInflater inflater = LayoutInflater.from(getActivity());
        View changeMemoryWindow = inflater.inflate(R.layout.memory_window, null);
        dialog.setView(changeMemoryWindow);

        final TextView memoryTitleTextView = changeMemoryWindow.findViewById(R.id.memoryTitleTextView);
        final TextView memoryDateTextView = changeMemoryWindow.findViewById(R.id.memoryDateTextView);
        final TextView memoryTextView = changeMemoryWindow.findViewById(R.id.memoryTextView);

        memoryTitleTextView.setText(memory.getTitle());
        memoryDateTextView.setText(memory.getDate());
        memoryTextView.setText(memory.getText());

        dialog.setNegativeButton(R.string.close, (dialogInterface, i)
                -> dialogInterface.dismiss());
        dialog.show();
    }
}