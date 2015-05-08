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
import android.widget.ImageButton;
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
    private clva adapter;





    public static long timePicked = -1;
    static Calendar c;
    static int h;
    static int m;


    List<String> platform = new ArrayList<String>();
    List<String> platform2 = new ArrayList<String>();
    public static String pill = "";
    public static String patient= "";

    ImageButton Edit;
    //private static String addingpill = "";
    //private static String addingpatient= "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_activity2);
        Edit=(ImageButton) findViewById(R.id.edit);
        l1 = (ListView) findViewById(R.id.listView);
        items = new ArrayList<>();
        adapter = new clva(this, items);
        l1.setAdapter(adapter);
        l1.setOnItemClickListener(this);
        platform.add(""); platform.add("sara"); platform.add("wafa"); platform.add("yasser"); platform.add("hana");
        platform2.add(""); platform2.add("asprien"); platform2.add("fefadol"); platform2.add("banadol"); platform2.add("bentagon");
    }






    public void adding(View view) {
        final AlertDialog.Builder alertSimple = new AlertDialog.Builder(MainActivity2Activity.this);
        alertSimple.setTitle("Set your alarm");








        LayoutInflater INFLATER = (LayoutInflater) getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View chooseView = INFLATER.inflate(R.layout.spin, null);
        Button but1 = (Button) chooseView.findViewById(R.id.dody);
        Spinner pills_spinner = (Spinner) chooseView.findViewById(R.id.spiner);
        Spinner patients_spinner=(Spinner) chooseView.findViewById(R.id.spinner);



        ArrayAdapter<String> patient_adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, platform);
        patient_adapter.setDropDownViewResource(R.layout.spinlay);
        patients_spinner.setAdapter(patient_adapter);
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





        ArrayAdapter<String> pills_adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, platform2);
        pills_adapter.setDropDownViewResource(R.layout.spinlay);
        pills_spinner.setAdapter(pills_adapter);


        pills_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                TextView tv = (TextView) view;
                pill =  tv.getText().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });




        but1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment newFragment = new TimePickerFragments();
                newFragment.show(getSupportFragmentManager(), "timePicker");
            }});


        alertSimple.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {

                adapter.notifyDataSetChanged();

                if (timePicked != -1) {


                    AlarmReceiver  alarm = new AlarmReceiver();

                    if (alarm != null) {

                        alarm.setOnetimeTimer(MainActivity2Activity.this, timePicked);

                        items.add(new Listviewitem() {{
                            bd = R.drawable.clock;
                            Title = "Alarm is set on " + h + ":" + m;
                            subti = "You should give " + pill + " ''Please be Specific about timing.'' " + "for Patient " + patient;
                        }});
                        Toast.makeText(getApplicationContext(), "time is set", Toast.LENGTH_LONG).show();
                    } else {
                        //warn the user for ALARM not set?
                        Toast.makeText(getApplicationContext(), "time is not set", Toast.LENGTH_LONG).show();
                    }
                }
            }
        }).setNegativeButton("cancel", null);


        alertSimple.setIcon(R.drawable.clock2);








        alertSimple.setView(chooseView);
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

        //alarm.delala(MainActivity2Activity.this);
        dle.create();
        dle.show();

      }


















    /*public void modify(View view) {
        final AlertDialog.Builder tomodify = new AlertDialog.Builder(MainActivity2Activity.this);
        tomodify.setTitle("Edit profile");
        tomodify.setIcon(R.drawable.edit);


        LayoutInflater INFLATER = (LayoutInflater) getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View edit = INFLATER.inflate(R.layout.toinsert, null);

        final Spinner addpill;
        final Spinner addpatient;


        addpill=(Spinner) edit.findViewById(R.id.pills);
        addpatient=(Spinner) edit.findViewById(R.id.patients);



        ArrayAdapter<CharSequence> adapt1 = ArrayAdapter.createFromResource(this, R.array.patients, android.R.layout.simple_spinner_dropdown_item);

        ArrayAdapter<CharSequence> adapt2 = ArrayAdapter.createFromResource(this, R.array.pills, android.R.layout.simple_spinner_dropdown_item);
        adapt1.setDropDownViewResource(R.layout.spinlay);
        adapt2.setDropDownViewResource(R.layout.spinlay);



        addpill.setAdapter(adapt2);
        addpatient.setAdapter(adapt1);



        tomodify.setPositiveButton("OK", new DialogInterface.OnClickListener() {





            public void onClick(DialogInterface dialog, int which) {




                addpill.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        TextView tv = (TextView) view;
                        addingpill = tv.getText().toString();
                        Toast.makeText(getApplicationContext(), addingpill, Toast.LENGTH_LONG).show();

                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {
                        addingpill = "";
                    }
                });




                addpatient.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        TextView tv = (TextView) view;
                        addingpatient = tv.getText().toString();
                        platform.add(addingpatient);
                        adapt1.notify();
                        Toast.makeText(getApplicationContext(), addingpatient, Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {
                        addingpatient = "";
                    }
                });


            }





        });







        tomodify.setView(edit);
        tomodify.create();
        tomodify.show();
    }*/



















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



