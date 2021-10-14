package alterbrain.com.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.ebanx.swipebtn.OnStateChangeListener;
import com.ebanx.swipebtn.SwipeButton;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.material.bottomappbar.BottomAppBar;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import alterbrain.com.AyudaSeguridadActivity;
import alterbrain.com.MainActivity1;
import alterbrain.com.R;
import alterbrain.com.SeguridadCounterActivity;


public class SeguridadMapActivity extends AppCompatActivity {

    BottomNavigationView bnvMenuSeg;
    BottomAppBar babMenuSeg;
    FloatingActionButton fabMenuSeg;
    SwipeButton swipeButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seguridad_map);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.

        Fragment fragment = new SeguridadMapFragment();

        getSupportFragmentManager().beginTransaction().replace(R.id.llSeguridadMap, fragment).commit();

        swipeButton = findViewById(R.id.swipeButtonSeg);

        swipeButton.setOnStateChangeListener(new OnStateChangeListener() {
            @Override
            public void onStateChange(boolean active) {
                Intent detail = new Intent(SeguridadMapActivity.this, SeguridadCounterActivity.class);
                startActivity(detail);
                finish();
            }
        });
    }
}