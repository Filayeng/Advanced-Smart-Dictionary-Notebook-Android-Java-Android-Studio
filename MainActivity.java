package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class MainActivity extends AppCompatActivity {
    public static ArrayList<String> email_adress = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button buttSingUp = (Button) findViewById(R.id.buttSingUp) ;
        Button buttLogin = (Button) findViewById(R.id.buttLogin) ;
        Button buttForgotPass = (Button) findViewById(R.id.buttForgotPass) ;
        EditText textLogMail = (EditText) findViewById(R.id.textLoginMail);
        EditText textLogPass = (EditText) findViewById(R.id.textLoginPassword);



        databaseControl dbControl = new databaseControl();
        email_adress = dbControl.getUser();

        String logMail = getIntent().getStringExtra("mail");
        String logPass = getIntent().getStringExtra("pass");
        if(true){
            textLogMail.setText(logMail);
            textLogPass.setText(logPass);
        }

        buttSingUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent change_Signup = new Intent(MainActivity.this, singup_screen.class);
                startActivity(change_Signup);
            }
        });

        buttForgotPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent change_ForgotPass = new Intent(MainActivity.this, forgot_pass_screen.class);
                if(!textLogMail.getText().toString().matches("") || textLogMail.getText().toString().contains(".com") || textLogMail.getText().toString().contains("@"))
                {change_ForgotPass.putExtra("mail",textLogMail.getText().toString().replace(" ",""));}
                startActivity(change_ForgotPass);
            }
        });


        buttLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                textLogPass.setText("omer123");
                textLogMail.setText("omaronaltrs@gmail.com");

                Integer i ;
                boolean emailDBCont = false;
                boolean emailCont = true;
                boolean textUserCont = true;

                if (textLogMail.getText().toString().matches("") || !textLogMail.getText().toString().contains(".com") || !textLogMail.getText().toString().contains("@")) {
                    Toast.makeText(getBaseContext(), "You did not enter a email", Toast.LENGTH_SHORT).show();textUserCont=false;
                }
                if (textLogPass.getText().toString().matches("")) {
                    Toast.makeText(getBaseContext(), "You did not enter a password", Toast.LENGTH_SHORT).show();textUserCont=false;
                }


                if(textUserCont){
                    for(i=0; i < email_adress.size(); i++){
                        String emailDBList_ = email_adress.get(i);
                        String[] emailDBList = emailDBList_.split(",");
                        String emailDB = emailDBList[1].replace("email=","").replace(" ","");
                        String emailEnt =  textLogMail.getText().toString().replace(" ","");
                        String passEnt =  textLogPass.getText().toString().replace(" ","");
                        if(emailDB.compareTo(emailEnt) == 0){
                            String passDB = emailDBList[0].replace("{password=","").replace(" ","");
                            if(passDB.compareTo(passEnt) == 0){
                                Toast.makeText(getBaseContext(), "Succeded. Wait ...", Toast.LENGTH_SHORT).show();
                                Intent change_Main = new Intent(


                                        MainActivity.this, app_main_screen.class);
                                startActivity(change_Main);}
                            else{Toast.makeText(getBaseContext(), "Wrong password. Please try again.", Toast.LENGTH_SHORT).show();}
                            emailCont = false;
                            break;}
                    }

                }

                }
         });
    }
}