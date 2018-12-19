package br.edu.unipampa.appavaliacoes.Persistencia;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import br.edu.unipampa.appavaliacoes.Model.Avaliacao;
import br.edu.unipampa.appavaliacoes.Model.Notificacao;

public class DataBasePersistencia {

    private AvaliacaoDBHelper avaliacaoHelper;
    private SQLiteDatabase db;

    public static DataBasePersistencia instanciaRepositorio;

    public DataBasePersistencia(Context context) {
        avaliacaoHelper = new AvaliacaoDBHelper(context);
    }

    public void insert(Avaliacao avaliacao, Notificacao notificacao) {

        try {
            db = avaliacaoHelper.getWritableDatabase();

            ContentValues values = new ContentValues();

            values.put(DataBaseContract.Avaliacao.COLUNA_TITULO, avaliacao.getTitulo());
            values.put(DataBaseContract.Avaliacao.COLUNA_DESCRICAO, avaliacao.getDescricao());
            values.put(DataBaseContract.Avaliacao.COLUNA_DATA, avaliacao.getDataDaAvaliacao());
            values.put(DataBaseContract.Avaliacao.COLUNA_HORARIO, avaliacao.getHoraDaAvaliacao());

            long idAvaliacao = db.insert(DataBaseContract.Avaliacao.NOME_TABELA, null, values);
            avaliacao.setId(idAvaliacao);

            ContentValues notifi = new ContentValues();

            notifi.put(DataBaseContract.Notificacao.COLUNA_DATA_NOTIFICACAO, notificacao.getData());
            notifi.put(DataBaseContract.Notificacao.COLUNA_HORARIO_NOTIFICACAO, notificacao.getHora());
            notifi.put(DataBaseContract.Notificacao.COLUNA_MENSAGEM, notificacao.getMenssagem());
            notifi.put(DataBaseContract.Notificacao.COLUNA_FK_AVALIACAO, idAvaliacao);
            notifi.put(DataBaseContract.Notificacao.COLUNA_TIPO_NOTIFICACAO, notificacao.getTipoNotifi());

            db.insert(DataBaseContract.Notificacao.NOME_TABELA, null, notifi);

            db.close();
        } catch (Exception e) {
            db.close();
        }
    }

    public ArrayList<Avaliacao> consultaAvaliacao() {

        ArrayList<Avaliacao> avaliacaos = new ArrayList<>();
        Avaliacao avaliacao;

        db = avaliacaoHelper.getReadableDatabase();

        Cursor c = db.rawQuery("SELECT * FROM Avaliacao", null);
           if (c.moveToFirst()) {
                do {

                    int idAvaliacao = c.getInt(0);
                    String tituloAvaliacao = c.getString(1);
                    String descricaoAvaliacao = c.getString(2);
                    String dataAvaliacao = c.getString(3);
                    String horaAvaliacao = c.getString(4);

                    avaliacao = new Avaliacao(idAvaliacao, tituloAvaliacao, descricaoAvaliacao, dataAvaliacao, horaAvaliacao);
                    avaliacaos.add(avaliacao);

                } while (c.moveToNext());
            }
            c.close();
            db.close();


        return avaliacaos;


    }

    public Notificacao consultaNotificacao(int id){

        Notificacao notificacao = new Notificacao();

        db = avaliacaoHelper.getReadableDatabase();

        Cursor c = db.rawQuery("SELECT * FROM Notificacao INNER JOIN Avaliacao on Notificacao.avaliacao == " + id +"", null);

        if (c.moveToFirst()) {
            do {
        int idNotificacao = c.getInt(0);
        String dataNotificacao = c.getString(1);
        String horaNotificacao = c.getString(2);
        String mensagemNotificao = c.getString(3);
        int idTipoNotificao = c.getInt(5);

        notificacao = new Notificacao(idNotificacao, dataNotificacao, horaNotificacao, mensagemNotificao, idTipoNotificao);
            } while (c.moveToNext());
        }

        c.close();
        db.close();

        return notificacao;
    }

    public void deletAvaliacao(int idAvaliacao, int idNotiticacao) {

        db = avaliacaoHelper.getWritableDatabase();
        db.delete("Notificacao", "Notificacao._id" + "='" + idNotiticacao + "'", null);
        db.delete("Avaliacao", "Avaliacao._id" + "='" + idAvaliacao + "'", null);

    }


    public void updateAvaliacao(Avaliacao avaliacao, Notificacao notificacao) {

        try {
            db = avaliacaoHelper.getWritableDatabase();
            ContentValues values = new ContentValues();

            values.put(DataBaseContract.Avaliacao.COLUNA_TITULO, avaliacao.getTitulo());
            values.put(DataBaseContract.Avaliacao.COLUNA_DESCRICAO, avaliacao.getDescricao());
            values.put(DataBaseContract.Avaliacao.COLUNA_DATA, avaliacao.getDataDaAvaliacao());
            values.put(DataBaseContract.Avaliacao.COLUNA_HORARIO, avaliacao.getHoraDaAvaliacao());
            db.update(DataBaseContract.Avaliacao.NOME_TABELA, values, "_id = ?", new String[]{String.valueOf(avaliacao.getId())});

            values.put(DataBaseContract.Notificacao.COLUNA_DATA_NOTIFICACAO, notificacao.getData());
            values.put(DataBaseContract.Notificacao.COLUNA_HORARIO_NOTIFICACAO, notificacao.getHora());

            values.put(DataBaseContract.Notificacao.COLUNA_TIPO_NOTIFICACAO, notificacao.getTipoNotifi());
            db.update(DataBaseContract.Notificacao.NOME_TABELA, values, "_id = ?", new String[]{String.valueOf(notificacao.getId())});

            db.close();
        } catch (Exception e) {

            db.close();
        }
    }


    /**
     * Alterar consulta para data e hora maior que hora atual do sistema
     * @return
     */



    public ArrayList<Notificacao> consultaNotifition(){


        Calendar hoje = Calendar.getInstance();
        String data =  hoje.get(hoje.DAY_OF_MONTH)+"/"+ hoje.get(hoje.MONTH)+ "/"+hoje.get(hoje.YEAR);
        String hora = hoje.get(hoje.HOUR_OF_DAY)+":"+hoje.get(hoje.MINUTE);


        ArrayList<Notificacao> notifi = new ArrayList<>();
        Notificacao notificacao = new Notificacao();

        db = avaliacaoHelper.getReadableDatabase();

        Cursor c = db.rawQuery("SELECT * FROM Notificacao WHERE Notificacao.datanotificacao >=" + data+ " and Notificacao.horarionotificacao >" + hora + "    ORDER by Notificacao.datanotificacao,Notificacao.horarionotificacao", null);

        Log.i("Info", "consultaNotifition: " + data);

        if (c.moveToFirst()) {
            do {
                int idNotificacao = c.getInt(0);
                String dataNotificacao = c.getString(1);
                String horaNotificacao = c.getString(2);
                String mensagemNotificao = c.getString(3);
                int idTipoNotificao = c.getInt(5);

                notificacao = new Notificacao(idNotificacao, dataNotificacao, horaNotificacao, mensagemNotificao, idTipoNotificao);
                notifi.add(notificacao);
            } while (c.moveToNext());
        }

        c.close();
        db.close();

        return notifi;
    }



}
