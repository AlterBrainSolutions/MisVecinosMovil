package alterbrain.com;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;

import com.ebanx.swipebtn.SwipeButton;

import alterbrain.com.app.Constantes;
import alterbrain.com.ui.SeguridadMapActivity;

public class AyudaSeguridadActivity extends AppCompatActivity {

    int positionPager = 0, numeroItems;
    Button botonSlider;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ayuda_seguridad);
        ViewPager2 pager;

        /*pager = findViewById(R.id.detalleAyudavP);*/

        /*sigSlider = v.findViewById(R.id.buttonSiguienteSlider);*/
        pager = findViewById(R.id.SeguridadAyudavP);
        botonSlider = findViewById(R.id.buttonSiguienteSliderSeg);
        ViewPagerAdapter2 adapter = new ViewPagerAdapter2(AyudaSeguridadActivity.this);
        pager.setAdapter(adapter);
        /*botonPrueba = ViewPagerAdapter.pruebaButton;*/

        botonSlider.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                positionPager = pager.getCurrentItem();
                numeroItems = pager.getAdapter().getItemCount();
                /*Toast.makeText( ActivityDetalleAyuda.this,
                        "Los numeros son: "+ numeroItems , Toast.LENGTH_SHORT).show();*/
                if (positionPager < numeroItems) {
                    positionPager++;
                    pager.setCurrentItem(positionPager);
                }
                if (positionPager == 3) {
                    //TODO en este caso se pone en la pagina 4 que es la ultima, y es la que manda a llamar al activity siguiente
                    savePreferences();
                    Intent i = new Intent(AyudaSeguridadActivity.this, SeguridadMapActivity.class);
                    startActivity(i);
                    finish();
                }
                if(positionPager == numeroItems - 1){
                    botonSlider.setText("Finalizar");
                }
            }
        });

        pager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            // This method is triggered when there is any scrolling activity for the current page
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels);
                if(position == 2){
                    botonSlider.setText("Finalizar");
                }else{
                    botonSlider.setText("Siguiente");
                }
            }

            // triggered when you select a new page
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
            }

            // triggered when there is
            // scroll state will be changed
            @Override
            public void onPageScrollStateChanged(int state) {
                super.onPageScrollStateChanged(state);
            }
        });
    }

    //TODO metodo para guardar la variable booleana para la ayuda
    public void savePreferences(){
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(AyudaSeguridadActivity.this);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean("SeguridadAisOpenedfor"+ Constantes.ID_USR, true);
        editor.commit();
    }
}