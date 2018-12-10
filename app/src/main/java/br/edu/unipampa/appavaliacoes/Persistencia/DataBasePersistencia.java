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


    public void insertAvalicao(Avaliacao avaliacao, int verificador) {
        if (verificador == -1) {
            insert(avaliacao);

        } else {

            // updateAvaliacao(Avalicao avalicao);
        }


    }

    public void insertNotificacao(Notificacao notificacao, int verificador) {
        if (verificador == -1) {
            insert(notificacao);

        } else {


        }


    }


    public void insert(Avaliacao avaliacao) {

        try {
            db = avaliacaoHelper.getWritableDatabase();

            ContentValues values = new ContentValues();

            values.put(DataBaseContract.Avaliacao.COLUNA_TITULO, avaliacao.getTitulo());
            values.put(DataBaseContract.Avaliacao.COLUNA_DESCRICAO, avaliacao.getDescricao());
            values.put(DataBaseContract.Avaliacao.COLUNA_DATA, avaliacao.getDataDaAvaliacao());
            values.put(DataBaseContract.Avaliacao.COLUNA_HORARIO, avaliacao.getHoraDaAvaliacao());

            long idAvaliacao = db.insert(DataBaseContract.Avaliacao.NOME_TABELA, null, values);

            avaliacao.setId(idAvaliacao);
            System.out.print("deu certo");
            db.close();
        } catch (Exception e) {
            db.close();
        }
    }


    public void insert(Notificacao notificacao) {

        try {
            db = avaliacaoHelper.getWritableDatabase();

            ContentValues values = new ContentValues();

            values.put(DataBaseContract.TipoNotificacao.COLUNA_TIPO, notificacao.getTipoNotifi());

            long idAvaliacao = db.insert(DataBaseContract.TipoNotificacao.NOME_TABELA, null, values);

            notificacao.setId((int) idAvaliacao);
            System.out.print("deu certo");
            db.close();
        } catch (Exception e) {
            db.close();
        }
    }

    /**
     * NÂO FAZ SENTIDO ESSE METODO, TIPONOTIFICAÇAO = ENUM NO BANCO
     * @param tipoNotificacao
     */
    /**
     * public void insertTipoNotoficacao(DataBaseContract.TipoNotificacao tipoNotificacao) {
     * <p>
     * try {
     * db = avaliacaoHelper.getWritableDatabase();
     * <p>
     * ContentValues values = new ContentValues();
     * <p>
     * <p>
     * values.put(DataBaseContract.TipoNotificacao.COLUNA_TIPO, Notificacao.getTipoNotifi());
     * <p>
     * <p>
     * long idNotificacao = db.insert(DataBaseContract.TipoNotificacao.NOME_TABELA, null, values);
     * <p>
     * //            tipoNotificacao.setId(idNotificacao);
     * <p>
     * db.close();
     * } catch (Exception e) {
     * db.close();
     * }
     * }
     */


    public ArrayList<Avaliacao> consultaBase() {

        ArrayList<Avaliacao> avaliacaos = new ArrayList<>();
        ArrayList<Notificacao> notificacaos = new ArrayList<>();
        Avaliacao avaliacao;
        Notificacao notificacao;

        db = avaliacaoHelper.getReadableDatabase();

        //Cursor c = db.rawQuery("SELECT * FROM Avaliacao INNER JOIN Notificacao on Notificacao.avaliacao == Avaliacao._id INNER JOIN   TipoNotificacao on Notificacao.tiponotificacao = TipoNotificacao._id", null);

        Cursor c = db.rawQuery("SELECT * FROM Avaliacao", null);
        if (c.moveToFirst()) {
            c = db.rawQuery("SELECT * FROM Avaliacao", null);
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
                    String mensagenNotificao = c.getString(8);
                    int idTipoNotificao = c.getInt(11);

                    notificacao = new Notificacao(idNotificacao, dataNotificacao,horaNotificacao,idTipoNotificao);
                    notificacaos.add(notificacao);
                    */

                } while (c.moveToNext());
            }
            c.close();
            db.close();
        }

        return avaliacaos;


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

            values.put(DataBaseContract.Notificacao.COLUNA_FK_TIPO_NOTIFICACAO, notificacao.getTipoNotifi());
            db.update(DataBaseContract.Notificacao.NOME_TABELA, values, "_id = ?", new String[]{String.valueOf(notificacao.getId())});

            db.close();
        } catch (Exception e) {

            db.close();
        }
    }


}
