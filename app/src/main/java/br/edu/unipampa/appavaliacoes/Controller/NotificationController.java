package br.edu.unipampa.appavaliacoes.Controller;

import android.app.AlarmManager;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.support.annotation.NonNull;
import android.util.Log;

import java.util.List;

import br.edu.unipampa.appavaliacoes.Model.Notificacao;
import br.edu.unipampa.appavaliacoes.Persistencia.DataBasePersistencia;
import br.edu.unipampa.appavaliacoes.R;
import br.edu.unipampa.appavaliacoes.Service.AlarmReceiver;
import br.edu.unipampa.appavaliacoes.Service.NotificationReceiver;
import br.edu.unipampa.appavaliacoes.Service.TempoUtils;

public class NotificationController {


    private Context context;
    private DataBasePersistencia db;

   public NotificationController(Context context){
       this.context = context;
       this.db = new DataBasePersistencia(context);

   }

    public void showNotificacao(Notificacao notification) {
        if (notification != null) {
            prepararnotificacao(notification, true);
        }
    }

    public void showNotificacao(int id) {
        for (Notificacao ava : db.consultaNotifition()) {
            Log.i("info", "showNotificacao: Notificacao" + ava.toString());
            if (ava.getId() == id) {
                prepararnotificacao(ava, true);
            }
        }

    }

    public android.app.Notification.Builder prepararnotificacao(Notificacao notificacao, boolean isAgora) {
        int notificacaioId = notificacao.getId();
        android.app.Notification.Builder mBuilder;

        /**
         * Cancelar passando dado para AlarmReceiver
         */
        NotificationManager mNotificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        Intent intentCancel = new Intent(context.getApplicationContext(), AlarmReceiver.class);
        intentCancel.putExtra("id_Notificacao", notificacaioId);
        PendingIntent actionIntent = PendingIntent.getBroadcast(context.getApplicationContext(),0, intentCancel, PendingIntent.FLAG_UPDATE_CURRENT);

        // Inicializar channel para Oreo
        String CHANNEL_ID = context.getResources().getString(R.string.app_name);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            android.app.NotificationChannel mChannel = new NotificationChannel(CHANNEL_ID, CHANNEL_ID, NotificationManager.IMPORTANCE_HIGH);
            mNotificationManager.createNotificationChannel(mChannel);
            mBuilder = new android.app.Notification.Builder(context, CHANNEL_ID);
        } else {
            mBuilder = new android.app.Notification.Builder(context)
                    .setDefaults(android.app.Notification.PRIORITY_HIGH);
        }
        mBuilder.setLargeIcon(BitmapFactory.decodeResource(context.getResources(), R.mipmap.books));
        mBuilder.setSmallIcon(R.mipmap.books);
        mBuilder.setTicker("Notificação");
        mBuilder.setContentTitle("Notificação");
        mBuilder.setContentText(notificacao.getMenssagem());
        mBuilder.addAction(R.mipmap.ic, "Cancel", actionIntent);
        mBuilder.setOnlyAlertOnce(true);

        mBuilder.setContentTitle(notificacao.getMenssagem());
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            mBuilder.setColor(context.getResources().getColor(R.color.colorPrimary));
            mBuilder.setVisibility(android.app.Notification.VISIBILITY_PUBLIC);
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            mBuilder.setBadgeIconType(android.app.Notification.BADGE_ICON_SMALL);
        }
        mBuilder.setDefaults(android.app.Notification.DEFAULT_ALL);
        mBuilder.setAutoCancel(true);



        if (isAgora) {
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.JELLY_BEAN) {
                mNotificationManager.notify(notificacao.getId(), mBuilder.build());
            } else {
                mNotificationManager.notify(notificacao.getId(), mBuilder.getNotification());
            }
        }
        return mBuilder;
    }




    public void prepararNotificacoes() {
        AlarmReceiver alarmReceiver = new AlarmReceiver();

        for (Notificacao notificacao : db.consultaNotifition()) {
            alarmReceiver.agendarAlarme(notificacao, context);
        }


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

            new NotificationReceiver().agendarProximoJob(this, context);
        }
    }


    public void agendarNotificacao(@NonNull Notificacao a) {
        new AlarmReceiver().agendarAlarme(a, context);


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

            new  NotificationReceiver().agendarProximoJob(this, context);
        }
    }


    public void cancelarNotificacao(Notificacao a) {
        int id = a.getId();
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(context.getApplicationContext(), AlarmReceiver.class);
        PendingIntent alarmIntent = PendingIntent.getBroadcast(context.getApplicationContext(), id, intent, 0);
        alarmManager.cancel(alarmIntent);

        Log.i("info", "cancelarNotificacao:  Aqui rodou");

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            new NotificationReceiver().removerJob(a, context, this);
        }
    }


    public Notificacao getProximaNotificacao() {
        List<Notificacao> proxNotificacao = db.consultaNotifition();

        Notificacao prox = null;
        long notProxAula = Long.MAX_VALUE;

        long notTemp;
        for (Notificacao tempAula : proxNotificacao) {
            notTemp = TempoUtils.millisTempoNotificacao(tempAula);
            if (notTemp < notProxAula) {
                prox = tempAula;
                notProxAula = notTemp;
            }
        }
        return prox;
    }


    public Notificacao getNotificacaoSeguinte(@NonNull Notificacao Anterior) {
        List<Notificacao> atividadeNotificacao = db.consultaNotifition();

        long notAulaAnterior = TempoUtils.millisTempoNotificacao(Anterior);

        Notificacao prox = null;
        long notProxAula = Long.MAX_VALUE;

        long notTemp;
        for (Notificacao tempProx : atividadeNotificacao) {
            notTemp = TempoUtils.millisTempoNotificacao(tempProx);

            if (notTemp < notProxAula) {
                if (notAulaAnterior < notTemp) {
                    prox = tempProx;
                    notProxAula = notTemp;
                }
            }
        }
        return prox;
    }





}
