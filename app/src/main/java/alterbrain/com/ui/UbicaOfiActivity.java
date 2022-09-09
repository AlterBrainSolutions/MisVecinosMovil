package alterbrain.com.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import alterbrain.com.R;
import alterbrain.com.app.Constantes;

public class UbicaOfiActivity extends AppCompatActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    String coordenadas;
    LatLng sydney;
    double latitude, longitude;
    String a;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ubica_ofi);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        Bundle extras = getIntent().getExtras();
        coordenadas = extras.getString("coordenadas");
        a = String.valueOf(coordenadas.charAt(0));

        if (a.equals("!") || a.equals("1")){
            //Usa split(delimiter) para dividir la cadena de caracteres a un array en Java
            String[] split = coordenadas.split("d");
            String[] aux = split;
            String[] aux2 = new String[0];
            String[] aux3 = new String[0];
            for (int i=0; i<split.length; i++){
                if (i==2){
                    aux[0] = split[i];
                    aux2 = aux[0].split("!");
                }
                if (i==3){
                    aux[0] = split[i];
                    aux3 = aux[0].split("!");
                }
            }
            latitude = Double.parseDouble(aux3[0]);
            longitude = Double.parseDouble(aux2[0])+0.0022;

        }else if(coordenadas.contains("4d")){
            String[] split = coordenadas.split("!");
            String[] aux = split;
            String[] aux2 = new String[0];
            String[] aux3 = new String[0];
            for (int i=0; i<split.length; i++){
                if(split[i].contains("3d")){
                    aux[0]= split[i];
                    aux2 = aux[0].split("d");
                }
                if(split[i].contains("4d")){
                    aux[0]= split[i];
                    aux3 = aux[0].split("d");
                }
            }
            latitude = Double.parseDouble(aux2[1]);
            longitude = Double.parseDouble(aux3[1]);

        }else if(coordenadas.contains("@")){
            String[] split = coordenadas.split("@");
            String[] aux = split;
            String[] aux2 = new String[0];
            for (int i=0; i<split.length; i++){
                if(i == 1){
                    aux[0] = split[i];
                    aux2 = aux[0].split(",");
                }
                System.out.println(split[i]);

            }
            latitude = Double.parseDouble(aux2[0]);
            longitude = Double.parseDouble(aux2[1]);

        }

    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        mMap = googleMap;
        Toast.makeText(this, ""+a, Toast.LENGTH_SHORT).show();
        // Add a marker in Sydney and move the camera
        sydney = new LatLng(latitude, longitude);
        mMap.addMarker(new MarkerOptions()
                .position(sydney)
                .title("Marker in Oficio"));

        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));

        mMap.setMinZoomPreference(14.0f);
        mMap.setMaxZoomPreference(17.0f);
    }
}