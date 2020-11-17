package com.example.jsonloaddataurl;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private static final String URL_DATA ="https://jsonplaceholder.typicode.com/users";
    private RecyclerView recyclerView ;
    private UserAdapter user_adapter;
    ArrayList<user> listuser;

    private void loadlist(){
        StringRequest stringRequest = new  StringRequest(Request.Method.POST, URL_DATA,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        JSONArray array = null;
                        try {
                            array = new JSONArray(response);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        for(int i =0;i<array.length();i++){
                            JSONObject object = null;
                            try {
                                object = array.getJSONObject(i);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            String name = null;
                            try {
                                name = object.getString("name");
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            String username = null;
                            try {
                                username = object.getString("username");
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            String email = null;
                            try {
                                email = object.getString("email");
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            user User = new user(name,username,email);
                            listuser.add(User);

                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        Toast.makeText(MainActivity.this,error.getMessage()+" error Loading Users "
                                ,Toast.LENGTH_LONG).show();
                    }
                });
        Volley.newRequestQueue(this).add(stringRequest);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.UserRecycler);
        listuser = new ArrayList<>();
        loadlist();


        user_adapter = new UserAdapter(this,listuser);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(user_adapter);
    }
}
