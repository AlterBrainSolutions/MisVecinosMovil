package alterbrain.com.ui;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import alterbrain.com.R;
import alterbrain.com.app.Constantes;

public class SeguridadMapFragment extends Fragment {

    LatLng sydney, Mexico;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_seguridad_map, container, false);

        SupportMapFragment supportMapFragment =
                (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.FragmentGoogleMap);

        supportMapFragment.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap googleMap) {
                Mexico = new LatLng(19.097763,-99.589767);
                if (Constantes.LAT_FRACC == 0){
                    sydney = new LatLng(19.097763, -99.589767);
                }else{
                    sydney = new LatLng(Constantes.LAT_FRACC, Constantes.LONG_FRACC);
                }
                googleMap.clear();
                /*sydney = new LatLng(19.354330782621, -99.18486166745);*/
                /*
                googleMap.moveCamera(CameraUpdateFactory.zoomTo(4.36f));*/
                //TODO revisar set on map loaded para el mapa
                googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(sydney, 3));
                /*googleMap.moveCamera(CameraUpdateFactory.newLatLng(Mexico));*/
                googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(sydney, 15),5000, null);
                googleMap.addMarker(new MarkerOptions().position(sydney).title("Marker in La Joya"));

                googleMap.setMinZoomPreference(0.0f);
                googleMap.setMaxZoomPreference(40.0f);


                /*    googleMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
                    @Override
                    public void onMapClick(LatLng latLng) {
                        MarkerOptions markerOptions = new MarkerOptions();
                        markerOptions.position(latLng);
                        markerOptions.title(latLng.latitude + " : " + latLng.longitude);
                        googleMap.clear();
                        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(
                                latLng, 10
                        ));
                        googleMap.addMarker(markerOptions);
                    }
                });
            */
            }
        });

        return view;
    }
}