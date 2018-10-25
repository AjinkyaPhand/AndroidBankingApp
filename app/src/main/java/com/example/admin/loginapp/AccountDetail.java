package com.example.admin.loginapp;

public class AccountDetail {
    private String BankName,BankCity;
    private int AccountNo,Ifsc;


    public AccountDetail( int accountNo, String bankName, String bankCity, int ifsc) {

        BankName = bankName;
        BankCity = bankCity;
        AccountNo = accountNo;
        Ifsc = ifsc;
    }



    public String getBankName() {
        return BankName;
    }

    public String getBankCity() {
        return BankCity;
    }

    public int getAccountNo() {
        return AccountNo;
    }

    public int getIfsc() {
        return Ifsc;
    }
}
