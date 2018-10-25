package com.example.admin.loginapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class accountDetails extends AppCompatActivity {

    TextView email1,acno,bname,bcity,ifsc;
    staticDatabase myDb;
    EditText gacno;
    Button showbtn,button;
    List<AccountDetail> accountDetailList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_details);
        Intent intent = getIntent();
        String email = intent.getStringExtra("email");


        myDb = new staticDatabase(this);

       // email1=(TextView)findViewById(R.id.email);
        acno=(TextView)findViewById(R.id.acno);
        bname=(TextView)findViewById(R.id.bname);
        bcity=(TextView)findViewById(R.id.bcity);
        ifsc=(TextView)findViewById(R.id.ifscno);
        gacno = (EditText) findViewById(R.id.getAccountNo);
        showbtn = (Button) findViewById(R.id.showbtn);
        button=(Button) findViewById(R.id.back);


        accountDetailList=new ArrayList<>();

        showbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    accountDetailList = myDb.getAllAccountAccountNo(Integer.parseInt(gacno.getText().toString()));
                   // email1.setText(accountDetailList.get(0).getEmail());
                    acno.setText(String.valueOf(accountDetailList.get(0).getAccountNo()));
                    bname.setText(accountDetailList.get(0).getBankName());
                    bcity.setText(accountDetailList.get(0).getBankCity());
                    ifsc.setText(String.valueOf(accountDetailList.get(0).getIfsc()));
                }catch(Exception e){
                    Toast.makeText(accountDetails.this,"*Please Enter Account Number",Toast.LENGTH_SHORT).show();
                }
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(accountDetails.this,Home.class);
                startActivity(intent);
                finish();
            }
        });




    }
}
