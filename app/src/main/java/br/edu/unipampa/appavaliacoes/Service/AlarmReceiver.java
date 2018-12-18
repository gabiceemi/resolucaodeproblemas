package br.edu.unipampa.appavaliacoes.Service;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.annotation.NonNull;
import android.util.Log;

import java.util.concurrent.TimeUnit;

import br.edu.unipampa.appavaliacoes.Controller.NotificationController;
import br.edu.unipampa.appavaliacoes.Model.Notificacao;
import br.edu.unipampa.appavaliacoes.Persistencia.DataBasePersistencia;

public class AlarmReceiver extends BroadcastReceiver {

    public static String NOTIFICATION = "Notitification_id";
    public DataBasePersistencia db;

    @Override
    public void onReceive(final Context context, Intent intent) {
        int id_notificacao_cancel = intent.getIntExtra("id_Notificacao",-1);
        if (!isBoot(context, intent)) { // se não é boot é porque está na hora de despertar
            NotificationController notification = new NotificationController(context);
            int id = intent.getIntExtra(NOTIFICATION, -1);
            Log.i("Info", "onReceive: ID" + id);
            if (id > -1) {
                notification.showNotificacao(id);
            } else {
                notification.showNotificacao(notification.getProximaNotificacao());
            }

        }
        if(id_notificacao_cancel>-1){

            Log.i("info", "onReceive: "+ id_notificacao_cancel + "Chegou aki");

            NotificationController notificationController = new NotificationController(context);
            db = new DataBasePersistencia(context);
            Notificacao cancelNotification  =  db.consultaNotificacao(id_notificacao_cancel);

            Log.i("info", "onReceive: "+ cancelNotification.getId() + "Chegou aki");

            notificationController.cancelarNotificacao(cancelNotification);

        }

        
    }



    public boolean isBoot(Context context, Intent intent) {
        NotificationController controller = new NotificationController(context);
        if (intent.getAction() != null) {
            if (intent.getAction().equals("android.intent.action.BOOT_COMPLETED")
                    || intent.getAction().equals("android.intent.action.REBOOT")
                    || intent.getAction().equals("android.intent.action.QUICKBOOT_POWERON")) {
                controller.prepararNotificacoes(); // deixa todas as notificações agendadas
                return true;
            }
        }
        return false;
    }


    public void agendarAlarme(@NonNull Notificacao notificacao, Context context) {

        ComponentName receiver = new ComponentName(context, AlarmReceiver.class);
        PackageManager pm = context.getPackageManager();
        pm.setComponentEnabledSetting(receiver,
                PackageManager.COMPONENT_ENABLED_STATE_ENABLED,
                PackageManager.DONT_KILL_APP);

        int notificationId = notificacao.getId();

        Intent intent = new Intent(context, AlarmReceiver.class);
        intent.putExtra(AlarmReceiver.NOTIFICATION, notificationId);

        AlarmManager alarmMgr = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        PendingIntent alarmIntent;
        if (PendingIntent.getBroadcast(context, notificationId, intent, PendingIntent.FLAG_NO_CREATE) == null) {
            alarmIntent = PendingIntent.getBroadcast(context, notificationId, intent, 0);
        } else {
            alarmIntent = PendingIntent.getBroadcast(context, notificationId, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        }

        long tempo = TempoUtils.millisTempoNotificacao(notificacao);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            alarmMgr.setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, tempo, alarmIntent);
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            alarmMgr.setExact(AlarmManager.RTC_WAKEUP, tempo, alarmIntent);
        } else {
            alarmMgr.set(AlarmManager.RTC_WAKEUP, tempo, alarmIntent);
        }
        //alarmMgr.setRepeating(AlarmManager.RTC_WAKEUP, tempo, TimeUnit.DAYS.toMillis(7), alarmIntent); // repetir a cada 7 dias
    }
    
    
    
}
