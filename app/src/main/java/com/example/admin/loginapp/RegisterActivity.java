package com.example.admin.loginapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class RegisterActivity extends AppCompatActivity {
    private EditText Name,Email,Password;
    private Button reg_button;
    private static String URL_REGIST="https://haematoid-circulati.000webhostapp.com/register.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        Name=(EditText) findViewById(R.id.name);
        Email=(EditText) findViewById(R.id.email);
        Password=(EditText) findViewById(R.id.password);
        reg_button=(Button) findViewById(R.id.register);

        reg_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String name=Name.getText().toString().trim();
                final String password=Password.getText().toString().trim();
                final String email=Email.getText().toString().trim();
                if(name.isEmpty()){
                    Name.setError("Please Enter Name");
                }
                else if(!name.matches("[a-zA-Z ]+"))
                {
                    Name.requestFocus();
                    Name.setError("ENTER ONLY ALPHABETICAL CHARACTER");
                }

                else if(email.isEmpty()){
                    Email.setError("Please Enter Email-ID");
                }

                else if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                    Email.setError("Please Enter Valid Email-ID");
                }
                else if(password.isEmpty() || password.length()!=3){
                    Password.setError("Please Enter Valid Password Of 3 Digits");
                }
                else{
                    regist(name,password,email);
                }
            }
        });
    }


    private void regist(final String name, final String password, final String email){


        StringRequest stringRequest=new StringRequest(Request.Method.POST, URL_REGIST, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {

                    JSONObject jsonObject =new JSONObject(response);
                    boolean success= jsonObject.getBoolean("success");

                    if(success){
                        Toast.makeText(RegisterActivity.this,"Registered Successfully !",Toast.LENGTH_SHORT).show();
                        Intent intent=new Intent(RegisterActivity.this,LoginActivity.class);
                        RegisterActivity.this.startActivity(intent);
                        finish();
                    }
                    else{
                        Toast.makeText(RegisterActivity.this,"Register Error !" ,Toast.LENGTH_SHORT).show();
                    }

                } catch (JSONException e) {
                    Toast.makeText(RegisterActivity.this,"Register Error !" +e.toString(),Toast.LENGTH_SHORT).show();
                }
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(RegisterActivity.this,"Register Error !" +error.toString(),Toast.LENGTH_SHORT);

                    }
    })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params=new HashMap<>();
                params.put("name",name);
                params.put("email",email);
                params.put("password",password);
                return params ;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
}
