package alterbrain.com.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

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
import alterbrain.com.app.Constantes;

public class UbicaLogActivity extends AppCompatActivity implements OnMapReadyCallback {

    Button btnDetalleLog;
    private GoogleMap mMap;
    LatLng sydney;

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
                Intent i = new Intent(UbicaLogActivity.this, DetalleLogActivity2.class);
                startActivity(i);
            }
        });
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        //Toast.makeText(this, ""+Constantes.LAT_FRACC, Toast.LENGTH_SHORT).show();
        if (Constantes.LAT_FRACC == 0){
            sydney = new LatLng(19.097763, -99.589767);
        }else{
            sydney = new LatLng(Constantes.LAT_FRACC, Constantes.LONG_FRACC);
        }
        mMap.addMarker(new MarkerOptions()
                .position(sydney)
                .title("Marker in Fraccionamiento"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));

        mMap.setMinZoomPreference(14.0f);
        mMap.setMaxZoomPreference(17.0f);
    }
}