package br.edu.unipampa.appavaliacoes.Service;

import android.app.Application;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;

public class Channel extends Application {

    public static final String CHANNEL_1_ID = "Channel_Notification";
    @Override
    public void onCreate() {
        super.onCreate();

        createNotificationChannel();

    }

    private void createNotificationChannel(){

        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.O){

            NotificationChannel channel1 = new NotificationChannel(
                    CHANNEL_1_ID,
                    "Channel Notification",
                    NotificationManager.IMPORTANCE_HIGH

            );
            channel1.setDescription("Esse é o canal principal");

            NotificationManager mannager = getSystemService(NotificationManager.class);
            mannager.createNotificationChannel(channel1);

        }

    }

}
