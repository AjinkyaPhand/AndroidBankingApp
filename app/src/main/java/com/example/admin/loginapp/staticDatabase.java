package com.example.admin.loginapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


public class staticDatabase extends SQLiteOpenHelper {
    public static final String TABLE_NAME = "ACCOUNTDETAILS";
    public static final String DB_NAME="BANK";

    public static final String COL_2="ACCOUNTNO";
    public static final String COL_3="BANKNAME";
    public static final String COL_4="BANKCITY";
    public static final String COL_5="IFSC";

    public static final String TABLE_DEPOSIT = "DEPOSIT";
    public static final String COL_ACCOUNTNO="ACCOUNTNO";
    public static final String COL_DMONEY="BALANCE";


    public static final String TABLE_WITHDRAW = "WITHDRAW";
    public static final String COL_ACCNO="ACCTNO";
    public static final String COL_WMONEY="WITHDRAW_AMT";













    staticDatabase (Context context){
        super(context,DB_NAME,null,1);
    }



    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("CREATE TABLE "+TABLE_NAME+"( ACCOUNTNO INTEGER NOT NULL,BANKNAME TEXT NOT NULL,BANKCITY TEXT NOT NULL,IFSC INTEGER NOT NULL)");
        db.execSQL("CREATE TABLE "+TABLE_DEPOSIT+"(_ID INTEGER PRIMARY KEY AUTOINCREMENT,"+COL_ACCOUNTNO+" TEXT,"+COL_DMONEY+" TEXT, CONSTRAINT fk_account FOREIGN KEY ("+COL_ACCOUNTNO+")" + "REFERENCES "+TABLE_NAME+"("+COL_2+"))");
        db.execSQL("CREATE TABLE "+TABLE_WITHDRAW+"(_ID INTEGER PRIMARY KEY AUTOINCREMENT,"+COL_ACCNO+" TEXT,"+COL_WMONEY+" TEXT)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_DEPOSIT);
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_WITHDRAW);
        onCreate(db);
    }


    public boolean insertDeposit(int AccountNo,int Balance){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues =new ContentValues();
        contentValues.put( COL_ACCOUNTNO,AccountNo);
        contentValues.put(COL_DMONEY,Balance);



        long result_rem =db.insert(TABLE_DEPOSIT,null,contentValues);
        if(result_rem==-1){
            return false;
        }
        else {
            return true;
        }

    }



    public boolean withdrawCash(int AccountNo,int Withdraw){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues =new ContentValues();
        contentValues.put(COL_ACCNO,AccountNo);
        contentValues.put(COL_WMONEY,Withdraw);

        long result_rem =db.insert(TABLE_WITHDRAW,null,contentValues);
        if(result_rem==-1){
            return false;
        }
        else {
            return true;
        }

    }


    public boolean insertdetails(int AccountNo,String BankName,String BankCity,int Ifsc){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues =new ContentValues();
        contentValues.put(COL_2,AccountNo);
        contentValues.put(COL_3,BankName);
        contentValues.put(COL_4,BankCity);
        contentValues.put(COL_5,Ifsc);

        long result =db.insert(TABLE_NAME,null,contentValues);
        if(result==-1){
            return false;
        }
        else {
            return true;
        }
    }


    public Cursor getData(){
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor res=db.rawQuery("select * from "+TABLE_NAME,null);
        return res;
    }
//---Without Account number fetching-------------------------------
  /*  public List<AccountDetail> getAllAccount(String emailId){
        List<AccountDetail> accountDetailList=new ArrayList<>();
        SQLiteDatabase db=getReadableDatabase();


        Cursor c =db.rawQuery("SELECT * FROM ACCOUNTDETAILS where EMAILID='"+emailId+"';",null);

        if(c.moveToFirst()){
            do{
                AccountDetail accountDetail =new AccountDetail(c.getString(0),c.getInt(1),c.getString(2),c.getString(3),c.getInt(4));
                accountDetailList.add(accountDetail);
            }while(c.moveToNext());
        }

        return accountDetailList;
    }*/

    //With Account number fetching----------------------------------------------
    public List<AccountDetail> getAllAccountAccountNo(int AccountNo){
        List<AccountDetail> accountDetailList=new ArrayList<>();
        SQLiteDatabase db=getReadableDatabase();


        Cursor c =db.rawQuery("SELECT * FROM ACCOUNTDETAILS where ACCOUNTNO='"+AccountNo+"';",null);

        if(c.moveToFirst()){
            do{
                AccountDetail accountDetail =new AccountDetail(c.getInt(0),c.getString(1),c.getString(2),c.getInt(3));
                accountDetailList.add(accountDetail);
            }while(c.moveToNext());
        }

        return accountDetailList;
    }

    public Cursor getSum(int AccountNo)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor cursor=db.rawQuery("select sum(BALANCE) AS myTotal from DEPOSIT where ACCOUNTNO='"+AccountNo+"';",null);
        return cursor;
    }

    public Cursor getWithdrawAmt(int AccountNo)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor cursor=db.rawQuery("select sum(WITHDRAW_AMT) AS myTotalWithdraw from WITHDRAW where ACCTNO='"+AccountNo+"';",null);
        return cursor;
    }
    /*
    * Cursor hrem = myDb.getSum();
        Integer hresult = 0;
        if (hrem.moveToNext())
            hresult = (int) hrem.getDouble(hrem.getColumnIndex("myTotal"));*/




}