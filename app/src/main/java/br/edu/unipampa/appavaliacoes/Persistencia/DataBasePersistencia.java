package br.edu.unipampa.appavaliacoes.Persistencia;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import br.edu.unipampa.appavaliacoes.Model.Avaliacao;
import br.edu.unipampa.appavaliacoes.Model.Notificacao;

public class DataBasePersistencia {

    private AvaliacaoDBHelper avaliacaoHelper;
    private SQLiteDatabase db;

    public static DataBasePersistencia instanciaRepositorio;

    public DataBasePersistencia(Context context) {
        avaliacaoHelper = new AvaliacaoDBHelper(context);
    }


//    public void insertAvalicao(Avaliacao avaliacao, int verificador) {
//        if (verificador == -1) {
//            insert(avaliacao);
//
//        } else {
//
//            // updateAvaliacao(Avalicao avalicao);
//        }
//
//
//    }



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

    public ArrayList<Avaliacao> consultaBase() {

        ArrayList<Avaliacao> avaliacaos = new ArrayList<>();
        ArrayList<Notificacao> notificacaos = new ArrayList<>();
        Avaliacao avaliacao;
        Notificacao notificacao;

        db = avaliacaoHelper.getReadableDatabase();

        //Cursor c = db.rawQuery("SELECT * FROM Avaliacao INNER JOIN Notificacao on Notificacao.avaliacao == Avaliacao._id INNER JOIN   TipoNotificacao on Notificacao.tiponotificacao = TipoNotificacao._id", null);

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

                    /*
                    int idNotificacao = c.getInt(5);
                    String dataNotificacao = c.getString(6);
                    String horaNotificacao = c.getString(7);
                    String mensagemNotificao = c.getString(8);
                    int idTipoNotificao = c.getInt(11);

                    notificacao = new Notificacao(idNotificacao, dataNotificacao,horaNotificacao, mensagemNotificao, idTipoNotificao);
                    notificacaos.add(notificacao);*/

                } while (c.moveToNext());
            }
            c.close();
            db.close();


        return avaliacaos;


    }


    public Avaliacao consultaBaseAvalicao(int id) {


        ArrayList<Notificacao> notificacaos = new ArrayList<>();
        Avaliacao avaliacao = null;

        db = avaliacaoHelper.getReadableDatabase();

        //Cursor c = db.rawQuery("SELECT * FROM Avaliacao INNER JOIN Notificacao on Notificacao.avaliacao == Avaliacao._id INNER JOIN   TipoNotificacao on Notificacao.tiponotificacao = TipoNotificacao._id", null);

        Cursor c = db.rawQuery("SELECT * FROM Avaliacao WHERE Avaliacao._id="+ id , null);
        if (c.moveToFirst()) {
            do {

                int idAvaliacao = c.getInt(0);
                String tituloAvaliacao = c.getString(1);
                String descricaoAvaliacao = c.getString(2);
                String dataAvaliacao = c.getString(3);
                String horaAvaliacao = c.getString(4);

                avaliacao = new Avaliacao(idAvaliacao, tituloAvaliacao, descricaoAvaliacao, dataAvaliacao, horaAvaliacao);


                    /*
                    int idNotificacao = c.getInt(5);
                    String dataNotificacao = c.getString(6);
                    String horaNotificacao = c.getString(7);
                    String mensagenNotificao = c.getString(8);
                    int idTipoNotificao = c.getInt(11);

                    notificacao = new Notificacao(idNotificacao, dataNotificacao,horaNotificacao,idTipoNotificao);
                    notificacaos.add(notificacao);
                    */

            } while (c.moveToNext());
        }
        c.close();
        db.close();


        return avaliacao;


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



    public void updateAdiarNotification(int id, String data, String hora, int tipoNotifcacao){



        try{}catch (Exception e){
            db = avaliacaoHelper.getWritableDatabase();

            ContentValues values = new ContentValues();

            values.put(DataBaseContract.Notificacao.COLUNA_DATA_NOTIFICACAO,data);
            values.put(DataBaseContract.Notificacao.COLUNA_HORARIO_NOTIFICACAO, hora);

            values.put(DataBaseContract.Notificacao.COLUNA_TIPO_NOTIFICACAO, tipoNotifcacao);
            db.update(DataBaseContract.Notificacao.NOME_TABELA, values, "_id = ?", new String[]{String.valueOf(id)});

            db.close();



            db.close();
        }


    }






}
