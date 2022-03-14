package alterbrain.com;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.applandeo.materialcalendarview.CalendarView;
import com.applandeo.materialcalendarview.EventDay;
import com.applandeo.materialcalendarview.listeners.OnCalendarPageChangeListener;
import com.applandeo.materialcalendarview.listeners.OnDayClickListener;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.sundeepk.compactcalendarview.CompactCalendarView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Currency;
import java.util.Date;
import java.util.List;

import alterbrain.com.app.Constantes;

public class AgendaActivity extends AppCompatActivity {

    /*CompactCalendarView cal;*/
    /*CalendarView cal;*/
    Date currentDate = Calendar.getInstance().getTime();
    CalendarView cal;//applandeo MaterialCalendarView
    public static String day, month, year, cDay, cMonth, cYear;
    List<EventDay> events;
    //Calendar calendar = Calendar.getInstance();
    //private String URL_eventos_mes;
    private RequestQueue mQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agenda);
        cal = findViewById(R.id.calendarView);

        DateFormat obj = new SimpleDateFormat("dd:MM:yyyy");
        // we create instance of the Date and pass milliseconds to the constructor
        Date res = new Date(cal.getCurrentPageDate().getTimeInMillis());
        String date = obj.format(res);
        String separado[] = date.split(":");
        cDay = separado[0];
        cMonth = separado[1];
        cYear = separado[2];

        /*Toast.makeText(AgendaActivity.this, "Sin separar" + date
                + "Separadas" + cDay + ":" + cMonth + ":" + cYear,
                Toast.LENGTH_LONG).show();*/

        mQueue = Volley.newRequestQueue(AgendaActivity.this);
        jsonParse2();

        /*Toast.makeText(AgendaActivity.this, "Fecha " + currentDate.getYear(), Toast.LENGTH_LONG).show();*/
        /*TODO todo esto va en el JSONObject
            events = new ArrayList<>();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String dateStr = "2022-03-10";
            Date date2 = null;
            try {
                date2 = sdf.parse(dateStr);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            calendar.setTime(date2);
            events.add(new EventDay(calendar, R.drawable.whatsapp_ic));
            cal.setEvents(events);
            events.clear() elimina todo lo que contenga el array list
         */

        /*TODO MaterialCalendarView*/
        cal.setOnDayClickListener(new OnDayClickListener() {
            @Override
            public void onDayClick(EventDay eventDay) {
                Calendar clickedDayCalendar = eventDay.getCalendar();
                DateFormat obj = new SimpleDateFormat("dd:MM:yyyy");
                // we create instance of the Date and pass milliseconds to the constructor
                Date res = new Date(clickedDayCalendar.getTimeInMillis());
                String date = obj.format(res);
                String separado[] = date.split(":");
                day = separado[0];
                month = separado[1];
                year = separado[2];
                Constantes.DAY_SELECTED = String.valueOf(day);
                Constantes.MONTH_SELECTED = String.valueOf(month);
                Constantes.YEAR_SELECTED = String.valueOf(year);
                Intent i = new Intent(AgendaActivity.this, AgendaSelectedDayActivity.class);
                startActivity(i);
                // now we format the res by using SimpleDateFormat
                /*Toast.makeText(AgendaActivity.this, *//*res.getDay() + "/" + res.getMonth()
                        + "/" + res.getYear()*//* "Sin separar" + date
                                + "Separadas" + day + ":" + month + ":" + year,
                        Toast.LENGTH_LONG).show();*/
            }
        });

        cal.setOnForwardPageChangeListener(new OnCalendarPageChangeListener() {
            @Override
            public void onChange() {
                Date res = new Date(cal.getCurrentPageDate().getTimeInMillis());
                String date = obj.format(res);
                String separado[] = date.split(":");
                cDay = separado[0];
                cMonth = separado[1];
                cYear = separado[2];
                /*Toast.makeText(AgendaActivity.this, "Sin separar" + date
                                + "Separadas" + cDay + ":" + cMonth + ":" + cYear,
                        Toast.LENGTH_LONG).show();*/
                jsonParse2();
            }
        });

        cal.setOnPreviousPageChangeListener(new OnCalendarPageChangeListener() {
            @Override
            public void onChange() {
                Date res = new Date(cal.getCurrentPageDate().getTimeInMillis());
                String date = obj.format(res);
                String separado[] = date.split(":");
                cDay = separado[0];
                cMonth = separado[1];
                cYear = separado[2];
                /*Toast.makeText(AgendaActivity.this, "Sin separar" + date
                                + "Separadas" + cDay + ":" + cMonth + ":" + cYear,
                        Toast.LENGTH_LONG).show();*/
                jsonParse2();
            }
        });

        /*TODO CalendarView ya funciona pero no muestra eventos en el calendario!!!*/
        /*cal.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {

                String fecha= dayOfMonth + "/" + (month +1) + "/" + year;
                *//*Toast.makeText(AgendaActivity.this, fecha + "AgendaActivity", Toast.LENGTH_LONG).show();*//*
                Constantes.DAY_SELECTED = String.valueOf(dayOfMonth);
                Constantes.MONTH_SELECTED = String.valueOf(month + 1);
                Constantes.YEAR_SELECTED = String.valueOf(year);
                Intent i = new Intent(AgendaActivity.this, AgendaSelectedDayActivity.class);
                startActivity(i);
            }
        });*/
        /*TODO CompactCalendarView*/
        /*cal.setFirstDayOfWeek(Calendar.MONDAY);
        cal.setCurrentDate(currentDate);

        cal.setListener(new CompactCalendarView.CompactCalendarViewListener() {
            @Override
            public void onDayClick(Date dateClicked) {
                Toast.makeText(AgendaActivity.this, dateClicked.getDay()
                        + dateClicked.getMonth() + dateClicked.getYear() + "AgendaActivity", Toast.LENGTH_LONG).show();
                Constantes.DAY_SELECTED = String.valueOf(dateClicked.getDay());
                Constantes.MONTH_SELECTED = String.valueOf(dateClicked.getMonth());
                Constantes.YEAR_SELECTED = String.valueOf(dateClicked.getYear());
            }

            @Override
            public void onMonthScroll(Date firstDayOfNewMonth) {
                Toast.makeText(AgendaActivity.this, "Puro Scroll papi", Toast.LENGTH_LONG).show();
            }
        });*/
    }

    private void jsonParse2() {

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET,
                "https://missvecinos.com.mx/android/consultaEventosMes.php?fracc="+
                        Constantes.IDFRACC_USR + "&mes="+ cMonth + "&anio=" + cYear, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {

                            /*TODO ---------------------------EVENTOS DEL MES----------------------------------------*/

                            JSONArray eventosRes = response.getJSONArray("eventos");
                            //events.clear();

                            events = new ArrayList<>();
                            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

                            int tamRes1 = eventosRes.length();

                            for (int i = 0; i < tamRes1; i++) {

                                JSONObject jsonObject = new JSONObject(eventosRes.get(i).toString());

                                String dateStr = jsonObject.getString("fechap");

                                /*Toast.makeText(AgendaActivity.this, dateStr + cMonth + cYear,
                                        Toast.LENGTH_LONG).show();*/

                                try {
                                    Date date2 = sdf.parse(dateStr);
                                    Calendar calendar2 = Calendar.getInstance();
                                    calendar2.setTime(date2);
                                    events.add(new EventDay(calendar2, R.drawable.evento_ic));
                                    cal.setEvents(events);
                                } catch (ParseException e) {
                                    e.printStackTrace();
                                }
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
        mQueue.add(request);
    }
}