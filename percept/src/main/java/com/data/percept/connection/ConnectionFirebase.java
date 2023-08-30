package com.data.percept.connection;

import java.io.FileInputStream;
import java.io.IOException;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;

public class ConnectionFirebase {

    public void conectaFire()throws IOException, InterruptedException{
    FileInputStream refreshToken = new FileInputStream("google-services.json");
    System.out.println(refreshToken);

    FirebaseOptions options = FirebaseOptions.builder()
        .setCredentials(GoogleCredentials.fromStream(refreshToken))
        .build();

        FirebaseApp.initializeApp(options);
    }

    public void initializeFirebase() {
        System.out.println("teste");
    }
}
