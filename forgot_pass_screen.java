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
import java.util.Random;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class forgot_pass_screen extends AppCompatActivity {
    public static ArrayList<String> email_adress = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_pass_screen);
        EditText textPassMail = (EditText) findViewById(R.id.textPassMail);
        EditText textVrfyCode = (EditText) findViewById(R.id.textVrfyCode);
        EditText textNewPass1 = (EditText) findViewById(R.id.textNewPass1);
        EditText textNewPass2 = (EditText) findViewById(R.id.textNewPass2);
        Button buttSendMail = (Button) findViewById(R.id.buttSendMail);
        Button buttVrfyCode = (Button) findViewById(R.id.buttVrfyCode);
        Button buttChangePass = (Button) findViewById(R.id.buttChangePass);
        textVrfyCode.setVisibility(View.INVISIBLE);
        buttVrfyCode.setVisibility(View.INVISIBLE);
        textNewPass2.setVisibility(View.INVISIBLE);
        buttChangePass.setVisibility(View.INVISIBLE);
        textNewPass1.setVisibility(View.INVISIBLE);

        mailControl mailCont = new mailControl();
        //mailCont.senderPasswordCode();
        databaseControl dbControl = new databaseControl();
        email_adress = dbControl.getUser();

        String logMail = getIntent().getStringExtra("mail");
        if(true){
            textPassMail.setText(logMail);
        }

        buttSendMail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean textUserCont = true;
                boolean emailCont = true;
                Random rn = new Random();
                int randomNum = rn.nextInt(999999-100000);

                if (textPassMail.getText().toString().matches("") || !textPassMail.getText().toString().contains(".com") || !textPassMail.getText().toString().contains("@")) {
                    Toast.makeText(getBaseContext(), "You did not enter a email", Toast.LENGTH_SHORT).show();textUserCont=false;}
                if(textUserCont){
                    for(int i=0; i < email_adress.size(); i++){
                        String emailDBList_ = email_adress.get(i);
                        String[] emailDBList = emailDBList_.split(",");
                        String emailDB = emailDBList[1].replace("email=","").replace(" ","");
                        String usernameDB = emailDBList[2].replace("username=","").replace(" ","").replace("}","");
                        String userIDDB = emailDBList[3].replace("username=","").replace(" ","").replace("}","");
                        String emailEnt =  textPassMail.getText().toString().replace(" ","");
                        if(emailDB.compareTo(emailEnt) == 0){
                            mailCont.senderPasswordCode(emailDB, usernameDB,randomNum);
                            Toast.makeText(getBaseContext(), "The code has been sent to your email address. Please check your inbox...", Toast.LENGTH_SHORT).show();
                            emailCont = false;
                            textPassMail.setVisibility(View.INVISIBLE);
                            textVrfyCode.setVisibility(View.VISIBLE);
                            buttSendMail.setVisibility(View.INVISIBLE);
                            buttVrfyCode.setVisibility(View.VISIBLE);

                            buttVrfyCode.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    if(textVrfyCode.getText().toString().compareTo(Integer.toString(randomNum)) == 0) {
                                        Toast.makeText(getBaseContext(), userIDDB, Toast.LENGTH_SHORT).show();
                                        textNewPass2.setVisibility(View.VISIBLE);
                                        buttChangePass.setVisibility(View.VISIBLE);
                                        textNewPass1.setVisibility(View.VISIBLE);
                                        textVrfyCode.setVisibility(View.INVISIBLE);
                                        buttVrfyCode.setVisibility(View.INVISIBLE);

                                        buttChangePass.setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View view) {
                                                if(textNewPass1.getText().toString().compareTo(textNewPass2.getText().toString()) == 0   && !textNewPass1.getText().toString().matches("")) {
                                                dbControl.changedPasword(textNewPass1.getText().toString(),userIDDB,emailDB,usernameDB);
                                                    Toast.makeText(getBaseContext(), "Your password has been successfully changed", Toast.LENGTH_SHORT).show();
                                                    Intent change_Main = new Intent(forgot_pass_screen.this, MainActivity.class);
                                                    change_Main.putExtra("mail",emailDB);
                                                    change_Main.putExtra("pass",textNewPass1.getText().toString());
                                                    startActivity(change_Main);
                                                }
                                                else{Toast.makeText(getBaseContext(), "Your password could not be changed.", Toast.LENGTH_SHORT).show();
                                                }
                                            }
                                        });


                                    }
                                    else{Toast.makeText(getBaseContext(), "Code not verified !", Toast.LENGTH_SHORT).show();}
                                }
                            });


                            break;
                        }}
                        if(emailCont){
                            Toast.makeText(getBaseContext(), "The registered email address could not be found.", Toast.LENGTH_SHORT).show();
                        }
                }
            }
    });
}
}
