package com.example.admin.loginapp;

import android.content.Intent;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
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

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class createAccount extends AppCompatActivity {
    Button button,button1;
    Spinner BankName, BankCity;
    EditText Acc_Num, IFSC_Code_BranchName;
    String bankname,bank_city,myString;
   // private static String URL_ACCOUNT_DETAILS = "https://haematoid-circulati.000webhostapp.com/account_details.php";
    staticDatabase myDb;
    int accountnumber=0;
    int ifsc_code_branchName=0;
    int number;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);

        myDb = new staticDatabase(this);
        Intent intent = getIntent();
        final String email = intent.getStringExtra("email");
        BankName = (Spinner) findViewById(R.id.bankNames);
        BankCity = (Spinner) findViewById(R.id.cityNames);
        IFSC_Code_BranchName = (EditText) findViewById(R.id.ifscCode_branchname);
        button = (Button) findViewById(R.id.saveandnext);
        button1=(Button) findViewById(R.id.randomnumber);



        button.setOnClickListener(  new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bankname = BankName.getSelectedItem().toString();
                bank_city = BankCity.getSelectedItem().toString();

                try {
                    ifsc_code_branchName = Integer.parseInt(IFSC_Code_BranchName.getText().toString());
                    boolean isinserted = myDb.insertdetails( accountnumber, bankname, bank_city, ifsc_code_branchName);

                    if (isinserted) {
                        Toast.makeText(createAccount.this, "Data Saved Successfully", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(createAccount.this, depositMoney1.class);
                        createAccount.this.startActivity(intent);
                        finish();
                        // Toast.makeText(createAccount.this, BankName.getSelectedItem().toString()+"\n"+IFSC_Code_BranchName.getText()+"\n"+BankCity.getSelectedItem().toString()+"\n"+Acc_Num.getText(), Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(createAccount.this, "Error", Toast.LENGTH_SHORT).show();
                    }
                }catch (Exception e){
                    Toast.makeText(createAccount.this,"*Please Fill Out Valid Information",Toast.LENGTH_SHORT).show();
                }
            }
        });

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Random random=new Random();
                int num=random.nextInt(10000)+1;
                Acc_Num = (EditText) findViewById(R.id.accounNumber);
                Acc_Num.setText(String.valueOf(num));
                accountnumber=Integer.parseInt(Acc_Num.getText().toString());


            }
        });




    }


}























             /*  button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {



                        if(!ifsc_code_branchName.isEmpty() || !accountnumber.isEmpty()) {
                            account_details_func(email, bankname, bank_city, ifsc_code_branchName, accountnumber);
                        } else{
                            Acc_Num.setError("Please Enter Account Number");
                            IFSC_Code_BranchName.setError("Please Enter IFSC code");
                        }
                    }
                });
            }


    private void account_details_func(final String email, final String bankname, final String bank_city, final String ifsc_code_branchName, final String accountnumber) {
        StringRequest stringRequest=new StringRequest(Request.Method.POST, URL_ACCOUNT_DETAILS, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject =new JSONObject(response);
                    boolean success= jsonObject.getBoolean("success");
                   // int account_number=jsonObject.getInt("account_number");

                    if(success){
                        Toast.makeText(createAccount.this,"Saved Successfully !",Toast.LENGTH_LONG).show();
                        Intent intent=new Intent(createAccount.this,depositMoney1.class);
                        //intent.putExtra("account_number",account_number);
                        createAccount.this.startActivity(intent);
                    }
                    else{
                        Toast.makeText(createAccount.this,"Error !!!" ,Toast.LENGTH_LONG).show();
                    }

                } catch (JSONException e) {
                    Toast.makeText(createAccount.this," Error is : " +e.toString(),Toast.LENGTH_LONG).show();
                    e.printStackTrace();
                }
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(createAccount.this," Error is ::: " +error.toString(),Toast.LENGTH_SHORT);

                    }
                })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params=new HashMap<>();

                params.put("email",email);
                params.put("bankname",bankname);
                params.put("bank_city",bank_city);
                params.put("accountnumber",accountnumber);
                params.put("ifsc_code_branchName",ifsc_code_branchName);
                return params ;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);

    }
    }
        //------------------------------------Reading Input Data and Displaying using TOAST-------------------------------------------------
   /* private void addListenerOnButton() {
        BankName = (Spinner) findViewById(R.id.bankNames);
        BankCity = (Spinner) findViewById(R.id.cityNames);
        Acc_Num = (EditText) findViewById(R.id.accounNumber);
        IFSC_Code_BranchName = (EditText) findViewById(R.id.ifscCode_branchname);
        button = (Button) findViewById(R.id.saveandnext);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast toast = new Toast(getApplicationContext());
                toast.setGravity(Gravity.TOP|Gravity.LEFT, 0, 0);
                toast.makeText(createAccount.this, BankName.getSelectedItem().toString()+"\n"+IFSC_Code_BranchName.getText()+"\n"+BankCity.getSelectedItem().toString()+"\n"+Acc_Num.getText(), toast.LENGTH_LONG).show();
            }
        });
    }*/

