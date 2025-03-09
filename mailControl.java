package com.example.myapplication;

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

public class mailControl {

    public void senderPasswordCode(String receiverEmail, String receiverUsename,Integer randomNum){
        try {

            String stringSenderEmail = "wordsapppasscenter@gmail.com";
            String stringReceiverEmail = receiverEmail;
            String stringPasswordSenderEmail = "cvux tkbb raug vqoz";

            String stringHost = "smtp.gmail.com";

            Properties properties = System.getProperties();

            properties.put("mail.smtp.host", stringHost);
            properties.put("mail.smtp.port", "465");
            properties.put("mail.smtp.ssl.enable", "true");
            properties.put("mail.smtp.auth", "true");
            javax.mail.Session session = Session.getInstance(properties, new Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(stringSenderEmail,stringPasswordSenderEmail);
                }
            });

            MimeMessage mimeMessage = new MimeMessage(session);
            mimeMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(stringReceiverEmail));

            String message = "<font size=4 color=black>Hello</font>";
            message += "<font size=6 color=black> %s !</font><br />";
            message = String.format(message,receiverUsename);
            message += "<font size=5 style=bold color=black>Your confirmed code : </font><br />";
            message += "<font size=8 style=bold color=green>%s</font><br />";
            message = String.format(message,randomNum);
            message += "<font size=4 style=bold color=black>Please enter the above verification code on the confirmation screen .</font><br />";
            message += "<font size=4 style=bold color=black>Thank you ...</font>";
            mimeMessage.setSubject("Words App Confirm Code");
            mimeMessage.setContent(message, "text/html");

            //"Your confirmed code : " + Integer.toString(randomNum)


            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Transport.send(mimeMessage);
                    } catch (MessagingException e) {
                        e.printStackTrace();
                    }
                }
            });
            thread.start();

        } catch (AddressException e) {
            e.printStackTrace();
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
