package com.example.macbookpro.davitaproject;


import android.app.AlertDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;
import java.util.ArrayList;
import java.lang.String;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;



public class MainActivity2Activity extends FragmentActivity implements OnItemClickListener {


    protected ListView l1;
    protected List<Listviewitem> items;
    private String pill = "";
    private String patient= "";
    private clva adapter;
    public String[] Platform;
    public String[] Platform2;
    public static long timePicked = -1;
    static Calendar c;
    static int h;
    static int m;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_activity2);

        l1 = (ListView) findViewById(R.id.listView);
        items = new ArrayList<>();
        adapter = new clva(this, items);
        l1.setAdapter(adapter);
        l1.setOnItemClickListener(this);}






    public void adding(View view) {
        final AlertDialog.Builder alertSimple = new AlertDialog.Builder(MainActivity2Activity.this);
        alertSimple.setTitle("Set your alarm");










        alertSimple.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {

                adapter.notifyDataSetChanged();
                if (timePicked != -1) {
                    AlarmReceiver alarm = new AlarmReceiver();

                    if (alarm != null) {
                        alarm.setinfo(pill,patient);
                        alarm.setOnetimeTimer(MainActivity2Activity.this, timePicked);

                        items.add(new Listviewitem() {{
                          bd = R.drawable.clock;
                          Title = "Alarm is set on " + h + ":" + m;
                          subti = "You should give "+pill+" ''Please be Specific about timing.'' "+"for Patient "+patient;
                                  }});
                        Toast.makeText(getApplicationContext(), "time is set", Toast.LENGTH_LONG).show();
                    } else {
                        //warn the user for ALARM not set?
                        Toast.makeText(getApplicationContext(), "time is not set", Toast.LENGTH_LONG).show();}}    }
        });


        alertSimple.setNegativeButton("cancel", null);
        alertSimple.setIcon(R.drawable.clock2);






        Spinner pills_spinner;
        Spinner patients_spinner;
        LayoutInflater INFLATER = (LayoutInflater) getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View tose = INFLATER.inflate(R.layout.spin, null);
        Button but1 = (Button) tose.findViewById(R.id.dody);
        pills_spinner = (Spinner) tose.findViewById(R.id.spiner);
        patients_spinner=(Spinner) tose.findViewById(R.id.spinner);


        Platform = getResources().getStringArray(R.array.patients);
        ArrayAdapter<CharSequence> adapter3 = ArrayAdapter.createFromResource(this, R.array.patients, android.R.layout.simple_spinner_dropdown_item);
        adapter3.setDropDownViewResource(R.layout.spinlay);
        patients_spinner.setAdapter(adapter3);
        patients_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                TextView tv = (TextView) view;
                patient = tv.getText().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                patient = "";
            }
        });





        Platform2 = getResources().getStringArray(R.array.pills);
        ArrayAdapter<CharSequence> pills_adapter = ArrayAdapter.createFromResource(this, R.array.pills, android.R.layout.simple_spinner_dropdown_item);
        pills_adapter.setDropDownViewResource(R.layout.spinlay);
        pills_spinner.setAdapter(pills_adapter);


        pills_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                TextView tv = (TextView) view;
                pill = tv.getText().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                pill = "";
            }
        });




                but1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment newFragment = new TimePickerFragments();
                newFragment.show(getSupportFragmentManager(), "timePicker");
            }});

        alertSimple.setView(tose);
        alertSimple.create();
        alertSimple.show();}





    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        final Listviewitem it = items.get(position);
        final AlertDialog.Builder dle = new AlertDialog.Builder(MainActivity2Activity.this);
        dle.setTitle("DELETE ALERT");
        dle.setIcon(R.drawable.clock2);
        dle.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                items.remove(it);
                adapter = new clva(MainActivity2Activity.this, items);
                l1.setAdapter(adapter);
        }});


        dle.create();
        dle.show();

      }








    public static class TimePickerFragments extends DialogFragment implements TimePickerDialog.OnTimeSetListener {

        @NonNull
        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
// Use the current time as the default values for the picker
            c = new GregorianCalendar();
            c = Calendar.getInstance();
            int hour = c.get(Calendar.HOUR_OF_DAY);
            int minute = c.get(Calendar.MINUTE);

// Create a new instance of TimePickerDialog and return it
            return new TimePickerDialog(getActivity(), this, hour, minute, DateFormat.is24HourFormat(getActivity()));
        }
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            // Do something with the time chosen by the user
            //Calendar n = new GregorianCalendar();
            c.set(Calendar.HOUR_OF_DAY, hourOfDay);
            c.set(Calendar.MINUTE, minute);
            final long time=c.getTimeInMillis();
            timePicked = time;
            h=hourOfDay;
            m=minute;}}









    class Listviewitem {
        public int bd;
        public String Title;
        public String subti;
    }




}



