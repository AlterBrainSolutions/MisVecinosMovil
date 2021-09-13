package alterbrain.com.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import alterbrain.com.AvisoActivity;
import alterbrain.com.LoginActivity;
import alterbrain.com.R;

public class UbicaLogActivity extends AppCompatActivity implements OnMapReadyCallback {

    Button btnDetalleLog;
    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ubica_log);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        btnDetalleLog = findViewById(R.id.buttonDetalleLogReci);
        btnDetalleLog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(UbicaLogActivity.this, DetalleLogActivity.class);
                startActivity(i);
            }
        });
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(19.354330782621, -99.18486166745);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Santa Monica"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));

        mMap.setMinZoomPreference(8.0f);
        mMap.setMaxZoomPreference(15.0f);
    }
}