package alterbrain.com;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.CalendarView;
import android.widget.Toast;

public class AgendaActivity extends AppCompatActivity {

    CalendarView cal;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agenda);
        cal = findViewById(R.id.calendarView);

        cal.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                String fecha= dayOfMonth + "/" + (month +1) + "/" + year;
                Toast.makeText(AgendaActivity.this, fecha, Toast.LENGTH_LONG).show();
            }
        });
    }
}