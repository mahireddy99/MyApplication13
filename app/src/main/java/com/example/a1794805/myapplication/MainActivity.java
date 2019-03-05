package com.example.a1794805.myapplication;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import javax.net.ssl.HttpsURLConnection;

import static android.content.ContentValues.TAG;

public class MainActivity extends AppCompatActivity {

    private Button btnSubmit;
    String responseText;
    StringBuffer response;
    URL url;
    Activity activity;
    ArrayList<Member> members=new ArrayList<Member>();
    private ProgressDialog progressDialog;
    ListView listView;


    //Direct Web services URL
    private String path = "https://api.myjson.com/bins/1dvu1q";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        activity = this;
        btnSubmit = (Button) findViewById(R.id.btnSubmit);
        listView = (ListView) findViewById(android.R.id.list);
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                members.clear();

                new GetServerData().execute();
            }
        });
    }

    class GetServerData extends AsyncTask
    {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            progressDialog = new ProgressDialog(MainActivity.this);
            progressDialog.setMessage("Fetching conntry data");
            progressDialog.setCancelable(false);
            progressDialog.show();

        }

        @Override
        protected Object doInBackground(Object[] objects) {
            return getWebServiceResponseData();
        }

        @Override
        protected void onPostExecute(Object o) {
            super.onPostExecute(o);

            if (progressDialog.isShowing())
                progressDialog.dismiss();
            // For populating list data
            CustomMemberList customCountryList = new  CustomMemberList (activity, members);
            listView.setAdapter(customCountryList);

            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                    Toast.makeText(getApplicationContext(),"You Selected "+members.get(position).getMemberName()+ " as Member",Toast.LENGTH_SHORT).show();        }
            });
        }
    }
    protected Void getWebServiceResponseData() {

        try {

            url = new URL(path);
            Log.d(TAG, "ServerData: " + path);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setReadTimeout(15000);
            conn.setConnectTimeout(15000);
            conn.setRequestMethod("GET");

            int responseCode = conn.getResponseCode();

            Log.d(TAG, "Response code: " + responseCode);
            if (responseCode == HttpsURLConnection.HTTP_OK) {
                // Reading response from input Stream
                BufferedReader in = new BufferedReader(
                        new InputStreamReader(conn.getInputStream()));
                String output;
                response = new StringBuffer();

                while ((output = in.readLine()) != null) {
                    response.append(output);
                }
                in.close();
            }}
        catch(Exception e){
            e.printStackTrace();
        }

        responseText = response.toString();

        Log.d(TAG, "data:" + responseText);
        try {
            JSONArray jsonarray = new JSONArray(responseText);
            for (int i = 0; i < jsonarray.length(); i++) {
                JSONObject jsonobject = jsonarray.getJSONObject(i);
                String  id = jsonobject.getString("id");
                String member = jsonobject.getString("name");
                String email = jsonobject.getString("email");
                String profession = jsonobject.getString("profession");
                String phonenumber= jsonobject.getString("phonenumber");
                Log.d(TAG, "id:" + id);
                Log.d(TAG, "name:" + member);
                Log.d(TAG, "email:" + email);
                Log.d(TAG, "profession:" + profession);
                Log.d(TAG, "phonenumber:" + phonenumber);

                Member memberObj=new Member(id,member,email,profession,phonenumber);
                members.add(memberObj);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }
}
