package br.edu.unipampa.appavaliacoes.Service;

import android.util.Log;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import br.edu.unipampa.appavaliacoes.Model.Notificacao;

public class TempoUtils {



    public static long millisTempoNotificacao(Notificacao notificacao) {
        long a = 0;


        if(notificacao.getId()!= -1){
        String date = notificacao.getData()+" "+notificacao.getHora();

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        sdf.setTimeZone(TimeZone.getTimeZone("UTC"));


        Date dateMili = null;
        try {
            dateMili = sdf.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Log.i("info", "millisTempoNotificacao: " + dateMili.getTime() );
        return  dateMili.getTime();
        }
        return 0;
    }






}
