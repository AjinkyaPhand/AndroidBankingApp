package com.example.admin.loginapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class TransferMoney extends AppCompatActivity {
Button button;
EditText FromAccNo,ToAccNo,TransferCash;
String faccno,taccno,transfercash;
    staticDatabase myDb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        myDb= new staticDatabase(this);
        setContentView(R.layout.activity_transfer_money);
        button=(Button) findViewById(R.id.transfer);
        FromAccNo=(EditText)findViewById(R.id.FromAccountNum);
        ToAccNo=(EditText) findViewById( R.id.ToAccountNum);
        TransferCash=(EditText) findViewById(R.id.transfercash);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                faccno = FromAccNo.getText().toString();
                taccno = ToAccNo.getText().toString();
                transfercash = TransferCash.getText().toString();
                if (faccno.isEmpty())
                    FromAccNo.setError("Please Enter Valid Account Number");
                else if (taccno.isEmpty())
                    ToAccNo.setError("Please Enter Valid Account Number");
                else if (transfercash.isEmpty())
                    TransferCash.setError("Please Enter Valid Amount");
                else{
                    boolean isinserted = myDb.withdrawCash(Integer.parseInt(faccno), Integer.parseInt(transfercash));
                boolean isinserted1 = myDb.insertDeposit(Integer.parseInt(taccno), Integer.parseInt(transfercash));
                if (isinserted && isinserted1) {
                    Toast.makeText(TransferMoney.this, "Money Transfered Successfully", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(TransferMoney.this, Home.class);
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(TransferMoney.this, "Error", Toast.LENGTH_SHORT).show();

                }
            }
            }
        });


    }
}
