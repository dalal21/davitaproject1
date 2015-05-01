package com.example.macbookpro.davitaproject;


import android.app.AlertDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.ArrayList;
import java.lang.String;
import java.util.Calendar;
import java.util.GregorianCalendar;


public class MainActivity2Activity extends FragmentActivity {



    private ListView l1;
    private ArrayAdapter adapter;
    private ArrayList<String> mos;
    public static long timePicked = -1;
    static Calendar c;
    static int h;
    static int m;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_activity2);
        l1=(ListView) findViewById(R.id.listView);
        mos= new ArrayList<String>();
        adapter =new ArrayAdapter<String>(getApplicationContext(),R.layout.item,mos);
         l1.setAdapter(adapter);}





    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main_activity2, menu);return true;}
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;}
return super.onOptionsItemSelected(item);}








    public void adding(View view) {
        final AlertDialog.Builder alertSimple = new AlertDialog.Builder(MainActivity2Activity.this);
        alertSimple.setTitle("Set your alarm");




        alertSimple.setPositiveButton("OK", new DialogInterface.OnClickListener()
        { public void onClick(DialogInterface dialog, int which) {

        adapter.notifyDataSetChanged();
                if (timePicked != -1) {

                    AlarmReceiver alarm = new AlarmReceiver();

                    if(alarm != null) {
                     alarm.setOnetimeTimer(MainActivity2Activity.this, timePicked);
                        mos.add("Alarm is set on "+h+":"+m);

                        Toast.makeText(getApplicationContext(), "time is set", Toast.LENGTH_LONG).show();
                    }
                        else {
                    //warn the user for ALARM not set?
                    Toast.makeText(getApplicationContext(), "time is not set", Toast.LENGTH_LONG).show();
                }}

            }});




        alertSimple.setNegativeButton("cancel", null);
        alertSimple.setIcon(R.drawable.clock2);



        LayoutInflater INFLATER = (LayoutInflater) getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View tose = INFLATER.inflate(R.layout.spin,null);
        Button but1=(Button)tose.findViewById(R.id.dody);





        but1.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {

             DialogFragment newFragment = new TimePickerFragments();
             newFragment.show(getSupportFragmentManager(), "timePicker");

             }

         });










          alertSimple.setView(tose);
          alertSimple.create();
          alertSimple.show();
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
            m=minute;

        }

    }


}



