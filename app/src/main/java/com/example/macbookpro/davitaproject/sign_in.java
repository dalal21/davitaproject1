package com.example.macbookpro.davitaproject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.view.View;
import android.widget.TextView;


public class sign_in extends Activity {
    public final static String EXTRA_MESSAGE = "com.example.macbookpro.davitaproject.MESSAGE";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);




        Button bb1 = (Button) findViewById(R.id.b1);
        TextView t1=(TextView) findViewById(R.id.ed1);
        TextView t2=(TextView) findViewById(R.id.textView2);




    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_sign_in, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void maybthlast(View view) {



        Intent intent = new Intent(this, MainActivity2Activity.class);

        EditText ee1=(EditText) findViewById(R.id.pass);
        String st=ee1.getText().toString();
        intent.putExtra(EXTRA_MESSAGE, st);
        if(st.equals("1075000579"))
        startActivity(intent);


    }
}
