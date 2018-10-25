package com.example.admin.loginapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class depositMoney1 extends AppCompatActivity {

    EditText accountNo,depositCash;
    Button submit;
    staticDatabase myDb;
    String Anum,Dcash;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deposit_money1);

        myDb= new staticDatabase(this);
        accountNo=(EditText)findViewById(R.id.accountno);
        depositCash=(EditText)findViewById(R.id.depositcash);
        submit=(Button)findViewById(R.id.finish);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Anum=accountNo.getText().toString();
                Dcash=depositCash.getText().toString();
                if(Anum.isEmpty()){
                    accountNo.setError("Please Enter Valid Account Number");
                }
                else if(Dcash.isEmpty() || Integer.parseInt(Dcash)<0){
                    depositCash.setError("Please Enter Valid Amount");
                }
                else {
                    boolean isinserted = myDb.insertDeposit(Integer.parseInt(Anum), Integer.parseInt(Dcash));
                    if (isinserted) {
                        Toast.makeText(depositMoney1.this, "Money deposited Successfully", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(depositMoney1.this, Home.class);
                        startActivity(intent);
                        finish();
                    } else {
                        Toast.makeText(depositMoney1.this, "Error", Toast.LENGTH_SHORT).show();

                    }
                }
            }
        });
    }
}
