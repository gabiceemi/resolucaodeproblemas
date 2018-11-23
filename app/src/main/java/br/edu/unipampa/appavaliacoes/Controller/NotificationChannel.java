package br.edu.unipampa.appavaliacoes.Controller;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import br.edu.unipampa.appavaliacoes.R;
import br.edu.unipampa.appavaliacoes.Service.NotificationReceiver;

import static br.edu.unipampa.appavaliacoes.Service.Channel.CHANNEL_1_ID;

public class NotificationChannel extends AppCompatActivity {
    private NotificationManagerCompat notifitionManager;
    private EditText text;
    private EditText msn;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);
        notifitionManager = NotificationManagerCompat.from(this);

        text = findViewById(R.id.text_tittle);
        msn = findViewById(R.id.message);

    }

    public void sendOnChannel(View view){
        String title = text.getText().toString();
        String menssagem = msn.getText().toString();
       /* Intent activityIntent = new Intent(this,Notification.class );
        PendingIntent contentIntent = PendingIntent.getActivity(this,0, activityIntent,0);*/


        Intent intentNotification = new Intent (this, NotificationReceiver.class);
        /*broadCastIntent.putExtra("tostMessage", menssagem);*/
        PendingIntent actionIntent = PendingIntent.getActivity(this,0, intentNotification,0);



        Notification notification = new NotificationCompat.Builder(this, CHANNEL_1_ID)
                .setSmallIcon(R.mipmap.ic)
                .setContentTitle(title)
                .setContentText(menssagem)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setCategory(NotificationCompat.CATEGORY_MESSAGE)
                .setVibrate(new long[]{150,300, 150,300})
                .setSound(Settings.System.DEFAULT_NOTIFICATION_URI)
                .addAction(R.mipmap.ic, "OK", actionIntent)
                .addAction(R.mipmap.delete,"ADIAR",actionIntent)
                .setAutoCancel(true)
                .setOnlyAlertOnce(true)
                .build();

               notifitionManager.notify(1,notification);
    }
}
