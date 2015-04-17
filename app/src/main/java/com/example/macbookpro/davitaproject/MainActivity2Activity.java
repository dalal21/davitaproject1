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
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_activity2);
        l1=(ListView) findViewById(R.id.listView);
        mos= new ArrayList<String>();
         adapter= new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_list_item_1,mos);
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
        alertSimple.setTitle("set your alarm");
        alertSimple.setMessage("set the time for your pills");




        alertSimple.setPositiveButton("OK", new DialogInterface.OnClickListener()
        { public void onClick(DialogInterface dialog, int which) {
        mos.add("hjsdlk");
        adapter.notifyDataSetChanged();
                if (timePicked != -1) {

                    AlarmReceiver alarm = new AlarmReceiver();

                    if(alarm != null) {
                     alarm.setOnetimeTimer(MainActivity2Activity.this, timePicked);
                     Toast.makeText(getBaseContext(), "set sety", Toast.LENGTH_LONG).show();
                     Log.d("test", "ALARM SET!!!");}}
                 else {
                    //warn the user for ALARM not set?
                    Log.w("test", "ALARM not SET!!!");
                }

            }});




        alertSimple.setNegativeButton("cancel", null);
        alertSimple.setIcon(R.drawable.vo);



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

            timePicked = c.getTimeInMillis();
            Toast.makeText(this.getActivity(), "time is set"+timePicked, Toast.LENGTH_LONG).show();
            Log.d("test", "timePicked: " + timePicked);
            Log.d("test", "current: " + System.currentTimeMillis() );


        }

    }


}



