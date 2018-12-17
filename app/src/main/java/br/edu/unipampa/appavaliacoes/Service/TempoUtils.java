package br.edu.unipampa.appavaliacoes.Service;

import java.util.Calendar;

public class TempoUtils {



    public static long millisTempoNotificacao(Aula aula) {
        Calendar notificacao = momentoNotificacao(aula);
        logHorario(notificacao, "horario que vai notificar ", aula);
        return notificacao.getTimeInMillis();
    }



}
