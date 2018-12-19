package br.edu.unipampa.appavaliacoes.Service;
import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.NotificationCompat;

import br.edu.unipampa.appavaliacoes.Controller.AdicionarAvaliacaoActivity;
import br.edu.unipampa.appavaliacoes.Controller.EditarAvaliacaoActivity;
import br.edu.unipampa.appavaliacoes.Controller.MainActivity;
import br.edu.unipampa.appavaliacoes.Controller.NotificationChannel;
import br.edu.unipampa.appavaliacoes.R;

import static br.edu.unipampa.appavaliacoes.Service.Channel.CHANNEL_1_ID;

public class NotificationService extends Service {


    @Override
    public void onCreate() {
        super.onCreate();
    }


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Intent intentNotification = new Intent (this,EditarAvaliacaoActivity.class);
        Intent intent2 = new Intent (this,AdicionarAvaliacaoActivity.class);
        PendingIntent actionIntent = PendingIntent.getActivity(this,0, intentNotification,0);
        PendingIntent action = PendingIntent.getActivity(this,0, intent2,0);


        try {
            Thread.sleep(30000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Notification notification = new NotificationCompat.Builder(this, CHANNEL_1_ID)
                .setSmallIcon(R.mipmap.ic)
                .setContentTitle("OkOKOK")
                .setContentText("Quase lá")
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setCategory(NotificationCompat.CATEGORY_MESSAGE)
                .setVibrate(new long[]{150,300, 150,300})
                .setSound(Settings.System.DEFAULT_NOTIFICATION_URI)
                .addAction(R.mipmap.ic, "OK", action)
                .addAction(R.mipmap.clock,"ADIAR",actionIntent)
                .setAutoCancel(true)
                .setOnlyAlertOnce(true)
                .build();

        startForeground(1,notification);


        return START_NOT_STICKY;

    }



    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
