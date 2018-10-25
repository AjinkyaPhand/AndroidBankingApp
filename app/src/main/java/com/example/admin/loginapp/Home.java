package com.example.admin.loginapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Home extends AppCompatActivity {
    Button button,button1,button2,button3,button4,button5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        button=(Button) findViewById(R.id.createaccount);
        button1=(Button) findViewById(R.id.depositmoney);
        button2=(Button) findViewById(R.id.mybalance);
        button3=(Button) findViewById(R.id.accountdetails);
        button4=(Button) findViewById(R.id.help);
        button5=(Button) findViewById(R.id.transfermoney);


        //----------------fetching Name of user-----------------------------
        Intent intent=getIntent();
        final String email=intent.getStringExtra("email");

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Home.this, createAccount.class);
                intent.putExtra("email",email);
                startActivity(intent);
            }
        });


        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1=new Intent(Home.this,depositMoney1.class);
                startActivity(intent1);
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2=new Intent(Home.this,myBalance.class);
                startActivity(intent2);
            }
        });

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent3=new Intent(Home.this,accountDetails.class);
                intent3.putExtra("email",email);
                startActivity(intent3);
            }
        });
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent4=new Intent(Home.this,help.class);
                startActivity(intent4);
            }
        });
        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Home.this, TransferMoney.class);
                intent.putExtra("email",email);
                startActivity(intent);
            }
        });
    }
}
