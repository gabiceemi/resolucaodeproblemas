package br.edu.unipampa.appavaliacoes.Controller;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import br.edu.unipampa.appavaliacoes.Model.Avaliacao;
import br.edu.unipampa.appavaliacoes.Model.Notificacao;
import br.edu.unipampa.appavaliacoes.Persistencia.DataBasePersistencia;
import br.edu.unipampa.appavaliacoes.R;
import static br.edu.unipampa.appavaliacoes.Service.Channel.CHANNEL_1_ID;

public class NotificationChannel extends AppCompatActivity {
    private NotificationManagerCompat notifitionManager;
    private EditText text;
    private EditText msn;
    private String tipoNotification;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);
        notifitionManager = NotificationManagerCompat.from(this);
        text = findViewById(R.id.text_tittle);
        msn = findViewById(R.id.message);


        sendOnChannelTest();
        Log.i("Notificacao", "onCreate: Acordou, disparou? ");


    }


    public void notifica(View view){
        /*
        * Carregar o tipo de notificação que será execcultado
        *
        */

        tipoNotification = "sonora";

        if(tipoNotification == "sonora"){

            notificationSound();

        }else if (tipoNotification == "vibrate"){
            notificationVibrate();


        }else if (tipoNotification=="light"){

            notificatinLight();
        }


    }




    public void notificatinLight(){
        String title = text.getText().toString();
        String menssagem = msn.getText().toString();

        Intent intentNotification = new Intent (this,AdiarNotificacao.class);
        Intent intent = new Intent (this,AdicionarAvaliacaoActivity.class);
        PendingIntent actionIntent = PendingIntent.getActivity(this,0, intentNotification,0);
        PendingIntent action = PendingIntent.getActivity(this,0, intent,0);



        Notification notification = new NotificationCompat.Builder(this, CHANNEL_1_ID)
                .setSmallIcon(R.mipmap.ic)
                .setContentTitle(title)
                .setContentText(menssagem)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setCategory(NotificationCompat.CATEGORY_MESSAGE)
                .setSound(Settings.System.DEFAULT_NOTIFICATION_URI)
                .addAction(R.mipmap.ic, "OK", action)
                .addAction(R.mipmap.delete,"ADIAR",actionIntent)
                .setAutoCancel(true)
                .setOnlyAlertOnce(true)
                .build();

        notifitionManager.notify(1001,notification);
    }




    public void notificationVibrate(){
        String title = text.getText().toString();
        String menssagem = msn.getText().toString();
        Intent intentNotification = new Intent (this,EditarAvaliacaoActivity.class);
        Intent intent = new Intent (this,AdicionarAvaliacaoActivity.class);
        PendingIntent actionIntent = PendingIntent.getActivity(this,0, intentNotification,0);
        PendingIntent action = PendingIntent.getActivity(this,0, intent,0);



        Notification notification = new NotificationCompat.Builder(this, CHANNEL_1_ID)
                .setSmallIcon(R.mipmap.ic)
                .setContentTitle(title)
                .setContentText(menssagem)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setCategory(NotificationCompat.CATEGORY_MESSAGE)
                .setVibrate(new long[]{150,300, 150,300, 150, 300})
                .addAction(R.mipmap.ic, "OK", action)
                .addAction(R.mipmap.delete,"ADIAR",actionIntent)
                .setAutoCancel(true)
                .setOnlyAlertOnce(true)
                .build();

        notifitionManager.notify(1002,notification);
    }



    public void notificationSound(){
        String title = text.getText().toString();
        String menssagem = msn.getText().toString();
        Intent intentNotification = new Intent (this,EditarAvaliacaoActivity.class);
        Intent intent = new Intent (this,AdicionarAvaliacaoActivity.class);
        PendingIntent actionIntent = PendingIntent.getActivity(this,0, intentNotification,0);
        PendingIntent action = PendingIntent.getActivity(this,0, intent,0);


        Notification notification = new NotificationCompat.Builder(this, CHANNEL_1_ID)
                .setSmallIcon(R.mipmap.ic)
                .setContentTitle(title)
                .setContentText(menssagem)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setCategory(NotificationCompat.CATEGORY_MESSAGE)
                .setSound(Settings.System.DEFAULT_NOTIFICATION_URI)
                .addAction(R.mipmap.ic, "OK", action)
                .addAction(R.mipmap.delete,"ADIAR",actionIntent)
                .setAutoCancel(true)
                .setOnlyAlertOnce(true)
                .build();

        notifitionManager.notify(1003,notification);
    }



    /**
     * Metodo basico para testes. Deve ser removido da versão final do projeto
     */

    public void sendOnChannel(View view){
        sendOnChannelTest();
    }



    public void sendOnChannelTest(){
        String title = "TesteNotificação";
        String menssagem = "TesteOk";
        Intent intentNotification = new Intent (this,EditarAvaliacaoActivity.class);
        Intent intent = new Intent (this,AdicionarAvaliacaoActivity.class);
        PendingIntent actionIntent = PendingIntent.getActivity(this,0, intentNotification,0);
        PendingIntent action = PendingIntent.getActivity(this,0, intent,0);



        Notification notification = new NotificationCompat.Builder(this, CHANNEL_1_ID)
                .setSmallIcon(R.mipmap.ic)
                .setContentTitle(title)
                .setContentText(menssagem)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setCategory(NotificationCompat.CATEGORY_MESSAGE)
                .setVibrate(new long[]{150,300, 150,300})
                .setSound(Settings.System.DEFAULT_NOTIFICATION_URI)
                .addAction(R.mipmap.ic, "OK", action)
                .addAction(R.mipmap.clock,"ADIAR",actionIntent)
                .setAutoCancel(true)
                .setOnlyAlertOnce(true)
                .build();

               notifitionManager.notify(1,notification);
    }





}
