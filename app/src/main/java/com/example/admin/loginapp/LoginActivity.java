package com.example.admin.loginapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {
    private EditText Email,Password;
    private TextView regist;
    private Button Login_Button,HOME;
    private static String URL_LOGIN="https://haematoid-circulati.000webhostapp.com/login.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Email=(EditText) findViewById(R.id.email);
        Password=(EditText) findViewById(R.id.pass);
        regist=(TextView) findViewById(R.id.register);
        Login_Button=(Button) findViewById(R.id.login_btn);
        HOME=(Button) findViewById(R.id.home);


        Login_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              final  String myEmail=Email.getText().toString().trim();
              final  String myPassword=Password.getText().toString().trim();

                if(myEmail.isEmpty() ){
                    Email.setError("Please Enter Email-ID");

                }
                else if(!Patterns.EMAIL_ADDRESS.matcher(myEmail).matches()){
                    Email.setError("Please Enter Valid Email-ID");
                }
                else if(myPassword.isEmpty() || myPassword.length()!=3){
                    Password.setError("Please Enter Valid Password Of 3 Digits");
                }
                else{
                    login(myEmail,myPassword);
                }
            }
        });
        regist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1=new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(intent1);
            }
        });

    }

    private void login(final String myEmail, final String myPassword) {

        StringRequest stringRequest=new StringRequest(Request.Method.POST, URL_LOGIN, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    JSONObject jsonObject = new JSONObject(response);
                    boolean success= jsonObject.getBoolean("success");
                   String email=jsonObject.getString("email");
                    String name=jsonObject.getString("name");
                    //JSONArray jsonArray=jsonObject.getJSONArray("login");

                   if(success){
                       Toast.makeText(LoginActivity.this,"Logged in as : "+name,Toast.LENGTH_SHORT).show();
                       Intent intent=new Intent(LoginActivity.this,Home.class);
                       intent.putExtra("email",email);
                       LoginActivity.this.startActivity(intent);
                       finish();
                   }
                   else{
                       Toast.makeText(LoginActivity.this,"LOGIN FAILED!!",Toast.LENGTH_SHORT).show();
                   }
                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(LoginActivity.this,"ERRORR!!"+e.toString(),Toast.LENGTH_SHORT).show();
                }

            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(LoginActivity.this,"ERRORR!!!"+error.toString(),Toast.LENGTH_SHORT).show();
                    }


    })
        {
            protected Map<String, String> getParams() throws AuthFailureError{
                Map<String,String> params=new HashMap<>();
                params.put("email",myEmail);
                params.put("password",myPassword);
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

}
