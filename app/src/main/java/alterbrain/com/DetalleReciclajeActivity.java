package alterbrain.com;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

public class DetalleReciclajeActivity extends AppCompatActivity {

    private PieChart pcCircular1, pcCircular2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_reciclaje);

        pcCircular1 = findViewById(R.id.pcCircular1);
        pcCircular2 = findViewById(R.id.pcCircular2);

        ArrayList<PieEntry> entries = new ArrayList<PieEntry>();
        entries.add(new PieEntry(60.0f, "Usado"));
        entries.add(new PieEntry(40.0f, "Restante"));

        PieDataSet pieDataSet = new PieDataSet(entries,"");
        pieDataSet.setColors(ColorTemplate.COLORFUL_COLORS);
        pieDataSet.setValueTextColor(Color.BLACK);
        pieDataSet.setValueTextSize(16f);

        PieData pieData = new PieData(pieDataSet);

        pcCircular1.setData(pieData);
        pcCircular1.getDescription().setEnabled(false);
        pcCircular1.setCenterText("CONTENEDOR PET");
        pcCircular1.animate();
/* -----------------------------------COMIENZA LA SEGUNDA GRAFICA---------------------------------*/
        ArrayList<PieEntry> entries2 = new ArrayList<PieEntry>();
        entries2.add(new PieEntry(80.0f, "Usado"));
        entries2.add(new PieEntry(20.0f, "Restante"));

        PieDataSet pieDataSet2 = new PieDataSet(entries2, "");
        pieDataSet2.setColors(ColorTemplate.COLORFUL_COLORS);
        pieDataSet2.setValueTextColor(Color.BLACK);
        pieDataSet2.setValueTextSize(16f);

        PieData pieData2 = new PieData(pieDataSet2);

        pcCircular2.setData(pieData2);
        pcCircular2.getDescription().setEnabled(false);
        pcCircular2.setCenterText("CONTENEDOR AL");
        pcCircular2.animate();
    }

    /*private void setupPieChart(){
        pcCircular1.setDrawHoleEnabled(true);
        pcCircular1.setUsePercentValues(true);
        pcCircular1.setEntryLabelTextSize(12);
        pcCircular1.setEntryLabelColor(Color.BLACK);
        pcCircular1.setCenterTextSize(24);
        pcCircular1.getDescription().setEnabled(true);

        Legend l = pcCircular1.getLegend();
        l.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT);
        l.setOrientation(Legend.LegendOrientation.VERTICAL);
        l.setDrawInside(false);
        l.setEnabled(true);
    }

    private void loadPieChartData(){
        ArrayList<PieEntry> entries = new ArrayList<PieEntry>();
        for (int i = 0; i<11; i++){
            entries.add(new PieEntry(0.60f, "Porcentaje usado"));
            entries.add(new PieEntry(0.40f, "Porcentaje restante"));
        }

        ArrayList<Integer> colors = new ArrayList<>();
        for (int color: ColorTemplate.MATERIAL_COLORS){
            colors.add(color);
        }

        for (int color: ColorTemplate.VORDIPLOM_COLORS){
            colors.add(color);
        }

        PieDataSet dataSet = new PieDataSet(entries, "Contenedor PET");
        dataSet.setColors(colors);

        PieData data = new PieData(dataSet);
        data.setDrawValues(true);
        data.setValueFormatter(new PercentFormatter());
        data.setValueTextSize(12f);
        data.setValueTextColor(Color.BLACK);
        pcCircular1.setData(data);
        pcCircular1.invalidate();
    }*/
}