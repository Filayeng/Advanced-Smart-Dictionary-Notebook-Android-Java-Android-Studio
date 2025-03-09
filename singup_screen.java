package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class singup_screen extends AppCompatActivity {

    public static ArrayList<String> email_adress = new ArrayList<String>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_singup_screen);
        EditText textRegisUsername = (EditText) findViewById(R.id.textUserName);
        EditText textRegisMail = (EditText) findViewById(R.id.textRegisMail);
        EditText textRegisPassword = (EditText) findViewById(R.id.textRegisPassword);
        Button buttRegister = (Button) findViewById(R.id.buttRegister);
        databaseControl dbControl = new databaseControl();
        email_adress = dbControl.getUser();


        buttRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Integer i ;
                boolean emailDBCont = false;
                boolean emailCont = true;
                boolean textUserCont = true;
                if (textRegisUsername.getText().toString().matches("")) {
                    Toast.makeText(getBaseContext(), "You did not enter a username", Toast.LENGTH_SHORT).show();textUserCont=false;
                }
                if (textRegisMail.getText().toString().matches("") || !textRegisMail.getText().toString().contains(".com") || !textRegisMail.getText().toString().contains("@")) {
                    Toast.makeText(getBaseContext(), "You did not enter a email", Toast.LENGTH_SHORT).show();textUserCont=false;
                }
                if (textRegisPassword.getText().toString().matches("")) {
                    Toast.makeText(getBaseContext(), "You did not enter a password", Toast.LENGTH_SHORT).show();textUserCont=false;
                }


                if(textUserCont){
                    for(i=0; i < email_adress.size(); i++){
                        String emailDBList_ = email_adress.get(i);
                        String[] emailDBList = emailDBList_.split(",");
                        String emailDB = emailDBList[1].replace("email=","").replace(" ","");
                        String emailEnt =  textRegisMail.getText().toString().replace(" ","");
                        if(emailDB.compareTo(emailEnt) == 0){
                            Toast.makeText(getBaseContext(), "This e-mail address has already been taken.", Toast.LENGTH_SHORT).show();
                            emailCont = false;
                            break;}
                        }
                    if(emailCont && (i != 0)){
                        dbControl.addUser(textRegisMail.getText().toString(),textRegisPassword.getText().toString(),textRegisUsername.getText().toString());
                        Toast.makeText(getBaseContext(), "Registration Successful :)", Toast.LENGTH_SHORT).show();
                        Intent change_Main = new Intent(singup_screen.this, MainActivity.class);
                        change_Main.putExtra("mail",textRegisMail.getText().toString().replace(" ",""));
                        change_Main.putExtra("pass",textRegisPassword.getText().toString());
                        startActivity(change_Main);}
                    else{Toast.makeText(getBaseContext(), "Failed Register !", Toast.LENGTH_SHORT).show();}
                }


            }
        });










    }
}