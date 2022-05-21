package com.example.memoriesmap.main;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.example.memoriesmap.R;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
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
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            drawable = (DrawableCompat.wrap(drawable)).mutate();
        }

        Bitmap bitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(),
                drawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
        drawable.draw(canvas);

        return bitmap;
    }

    public void showAddingMemoryWindow() {

        AlertDialog.Builder dialog = new AlertDialog.Builder(getContext());
        dialog.setTitle(R.string.adding_memory_text);

        LayoutInflater inflater = LayoutInflater.from(getActivity());
        View memoryWindow = inflater.inflate(R.layout.add_memory_window, null);
        dialog.setView(memoryWindow);

        dialog.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        dialog.setPositiveButton(R.string.save, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                saveMemory();
            }
        });
        dialog.show();
    }

    public void saveMemory() {
        EditText memoryTitleEditText = view.findViewById(R.id.memoryTitleEditText);
        EditText memoryDateEditText = view.findViewById(R.id.memoryDateEditText);
        TextInputEditText memoryTextEditText = view.findViewById(R.id.memoryText);

        String memoryTitle = memoryTitleEditText.getText().toString();
        String memoryDate = memoryDateEditText.getText().toString();
        //String memoryText = memoryTextEditText.getText().toString();

        mapView
                .getMap()
                .getMapObjects()
                .addPlacemark(
                        tapPoint,
                        ImageProvider
                                .fromBitmap(
                                        getBitmapFromVectorDrawable(getContext(),
                                                R.drawable.ic_baseline_place_24)));

    }
}