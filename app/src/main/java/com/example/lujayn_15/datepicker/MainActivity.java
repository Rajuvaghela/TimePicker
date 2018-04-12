package com.example.lujayn_15.datepicker;
import android.app.TimePickerDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TimePicker;
import android.widget.Toast;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button btn1;
    Button btn2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn1 = (Button) findViewById(R.id.btn1);
        btn2 = (Button) findViewById(R.id.btn2);
        btn1.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn1:
                pickTime();
                break;
            case R.id.btn2:

                break;
        }
    }

    private void pickTime() {
        Calendar mcurrentTime = Calendar.getInstance();
        int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
        int minute = mcurrentTime.get(Calendar.MINUTE);
        TimePickerDialog mTimePicker;
        mTimePicker = new TimePickerDialog(MainActivity.this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                if (selectedHour > 22 || selectedHour < 9) {
                    Toast.makeText(MainActivity.this, "Please select proper time", Toast.LENGTH_LONG).show();
                    pickTime();
                } else {
                    //
                    String time = String.valueOf(selectedHour) + ":" + String.valueOf(selectedMinute);
                    btn1.setText(convet24to12(time));
                }
                //eReminderTime.setText( selectedHour + ":" + selectedMinute);
            }

        }, hour, minute, false);//Yes 24 hour time
        mTimePicker.setTitle("Select Time");
        mTimePicker.show();
    }

    private String convet24to12(String time) {
        String return_time = "";
        try {
            final SimpleDateFormat sdf = new SimpleDateFormat("H:mm");
            final Date dateObj = sdf.parse(time);
            System.out.println(dateObj);
            return_time = String.valueOf(new SimpleDateFormat("K:mma").format(dateObj));
        } catch (final ParseException e) {
            e.printStackTrace();
        }
        return return_time;
    }
}
