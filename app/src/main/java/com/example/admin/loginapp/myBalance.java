package com.example.admin.loginapp;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class myBalance extends AppCompatActivity {
    TextView mybalance;
    EditText acno;
    Button showbtn,button;
    staticDatabase myDb;
    Integer Dresult=0,Wresult=0,FinalBal=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_balance);

        myDb = new staticDatabase(this);

        mybalance = (TextView) findViewById(R.id.balance);
        acno = (EditText) findViewById(R.id.acno);
        showbtn = (Button) findViewById(R.id.showbtn);
        button=(Button) findViewById(R.id.back);
        showbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                try {
                Cursor Drem = myDb.getSum(Integer.parseInt(acno.getText().toString()));
                Cursor Wrem = myDb.getWithdrawAmt(Integer.parseInt(acno.getText().toString()));

                    Dresult = 0;
                    Wresult = 0;
                    if (Drem.moveToNext() && Wrem.moveToNext())
                        Dresult = (int) Drem.getDouble(Drem.getColumnIndex("myTotal"));
                    Wresult = (int) Wrem.getDouble(Wrem.getColumnIndex("myTotalWithdraw"));
                    FinalBal = Dresult - Wresult;
                    mybalance.setText(String.valueOf(FinalBal));
                }catch (Exception e){
                    Toast.makeText(myBalance.this,"*Please Enter Account Number",Toast.LENGTH_SHORT).show();
                }
            }

        });
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(myBalance.this,Home.class);
                startActivity(intent);
                finish();
            }
        });
    }


}
