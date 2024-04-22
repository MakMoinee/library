package com.github.MakMoinee.library.services;

import android.content.Context;
import android.util.Log;

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

public class Demail {
    Context mContext;
    String emailHost;
    String emailPort;
    String emailClass;
    String emailAuth;
    String emailAdd;
    String emailPass;

    public Demail(Context mContext, String emailHost, String emailPort, String emailClass, String emailAuth, String emailAdd, String emailPass) {
        this.mContext = mContext;
        this.emailHost = emailHost;
        this.emailPort = emailPort;
        this.emailClass = emailClass;
        this.emailAuth = emailAuth;
        this.emailAdd = emailAdd;
        this.emailPass = emailPass;
    }

    public void sendEmail(String emailTo, String subject, String msg) {
        try {
            Properties properties = System.getProperties();
            properties.put("mail.smtp.host", emailHost);
            properties.put("mail.smtp.socketFactory.port", emailPort);
            properties.put("mail.smtp.socketFactory.class", emailClass);
            properties.put("mail.smtp.auth", emailAuth);


            Session mailSession = Session.getInstance(properties, new Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(emailAdd, emailPass);
                }
            });

            MimeMessage message = new MimeMessage(mailSession);

            message.addRecipient(Message.RecipientType.TO, new InternetAddress(emailTo));
            message.setSubject(subject);
            message.setText(msg);

            Thread thread = new Thread(() -> {
                try {
                    Transport.send(message);
                } catch (MessagingException e) {
                    Log.e("MessagingException >>", e.getLocalizedMessage());
                }
            });
//
            thread.start();


        } catch (AddressException e) {
            Log.e("AddressException >>", e.getLocalizedMessage());
        } catch (MessagingException e) {
            Log.e("MessagingException >>", e.getLocalizedMessage());
        }
    }
}
