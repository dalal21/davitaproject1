package com.example.macbookpro.davitaproject;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.view.View;
import android.widget.Toast;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;


public class sign_in extends Activity {
    public final static String EXTRA_MESSAGE = "com.example.macbookpro.davitaproject.MESSAGE";
    Button bb1;
    EditText ee1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        ee1 = (EditText) findViewById(R.id.pass);
        bb1 = (Button) findViewById(R.id.b1);


    }


    class tesfir extends AsyncTask<String, String, Void> {

        private ProgressDialog prdi = new ProgressDialog(sign_in.this);
        InputStream is = null;
        String result = "";

        @Override
        protected void onPreExecute() {
            prdi.setMessage("please wait");
            prdi.show();
            prdi.setOnCancelListener(new DialogInterface.OnCancelListener() {
                @Override
                public void onCancel(DialogInterface arg0) {
                    tesfir.this.cancel(true);
                }
            });

        }

        @Override
        protected Void doInBackground(String... params) {
            String url = "http://davitacare.byethost24.com/demo.php";
            HttpClient hc = new DefaultHttpClient();
            HttpPost hp = new HttpPost(url);

            ArrayList<NameValuePair> param = new ArrayList<NameValuePair>();

            try {
                hp.setEntity(new UrlEncodedFormEntity(param));
                HttpResponse hr = hc.execute(hp);
                HttpEntity he = hr.getEntity();
                is = he.getContent();


            } catch (Exception e) {
                Log.e("Log_tag", "Error  " + e.toString());
                Toast.makeText(sign_in.this, "please try again", Toast.LENGTH_LONG).show();
            }
            try {
                BufferedReader br = new BufferedReader(new InputStreamReader(is));
                StringBuilder sb = new StringBuilder();
                String line = "";
                while ((line = br.readLine()) != null) {
                    sb.append(line + "\n");
                }
                is.close();
                result = sb.toString();

            } catch (Exception e) {
                // TODO: handle exception
                Log.e("log_tag", "Error converting result " + e.toString());

            }

            return null;
        }

        @Override
        protected void onPostExecute(Void v) {


            try {
                JSONArray Jarray = new JSONArray(result);
                for (int i = 0; i < Jarray.length(); i++) {

                    JSONObject Jasonobject = null;

                    Jasonobject = Jarray.getJSONObject(i);


                    String id = Jasonobject.getString("pid");

                    if (ee1.getText().toString().equals(id)) {

                        Intent intent = new Intent(sign_in.this, MainActivity2Activity.class);

                        intent.putExtra(EXTRA_MESSAGE, id);
                        startActivity(intent);
                        break;

                    }
                }
                this.prdi.dismiss();
            } catch (Exception e) {
                // TODO: handle exception
                Toast.makeText(sign_in.this, "error parsing data", Toast.LENGTH_LONG).show();
            }
        }
    }


    public void onClick(View v) {
        /*// TODO Auto-generated method stub
        switch (v.getId()) {
            case R.id.b1:
                new tesfir().execute();
                break;*/

        Intent intent = new Intent(sign_in.this, MainActivity2Activity.class);
        startActivity(intent);


    }
}

