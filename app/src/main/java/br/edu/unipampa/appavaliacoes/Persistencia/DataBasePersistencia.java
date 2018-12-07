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

    public void insertAvaliacao(Avaliacao avaliacao) {

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

    public void insertTipoNotoficacao(DataBaseContract.TipoNotificacao tipoNotificacao) {

        try {
            db = avaliacaoHelper.getWritableDatabase();

            ContentValues values = new ContentValues();


            values.put(DataBaseContract.TipoNotificacao.COLUNA_TIPO, Notificacao.getTipoNotifi());


            long idNotificacao = db.insert(DataBaseContract.TipoNotificacao.NOME_TABELA, null, values);

//            tipoNotificacao.setId(idNotificacao);

            db.close();
        } catch (Exception e) {
            db.close();
        }
    }


    public ArrayList<Avaliacao> consultaBase() {

        ArrayList<Avaliacao> avaliacaos = new ArrayList<>();
        Avaliacao avaliacao;

        db = avaliacaoHelper.getReadableDatabase();

        //Cursor c = db.rawQuery("SELECT * FROM Avaliacao INNER JOIN Notificacao on Notificacao.avaliacao == Avaliacao._id" +
        // " INNER JOIN  AdicionarNotificacaoActivity on AdicionarNotificacaoActivity._id = Notificacao.tiponotificacao", null);

        Cursor c = db.rawQuery("SELECT * FROM Avaliacao",null);
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

                } while (c.moveToNext());
            }
            c.close();
            db.close();
        }

            return avaliacaos;


        }



   /* public void deletAvaliacao(int idAvaliacao, int idNotiticacao){

        db = avaliacaoHelper.getWritableDatabase();
        db.delete("Notificacao", "Notificacao._id" + "='" + idNotiticacao + "'", null);
        db.delete("Avaliacao", "Avaliacao._id" + "='" + idAvaliacao + "'", null);

    }


    public void updateAvaliacao(int idAvaliacao, int idNotiticacao){

        db = avaliacaoHelper.getReadableDatabase();

    }
*/

}
