package br.edu.unipampa.appavaliacoes.Persistencia;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import br.edu.unipampa.appavaliacoes.Model.Avaliacao;

public class DataBasePersistencia {

    private AvaliacaoDBHelper avaliacaoHelper;
    private SQLiteDatabase db;

    public static DataBasePersistencia instanciaRepositorio;

    public static synchronized DataBasePersistencia getInstance(Context context) {
        if (instanciaRepositorio == null) {
            instanciaRepositorio = new DataBasePersistencia(context);
        }
        return instanciaRepositorio;
    }

    public DataBasePersistencia(Context context) {
        avaliacaoHelper = new AvaliacaoDBHelper(context);
    }

    public void insertAvaliacao (Avaliacao avaliacao){

        db = avaliacaoHelper.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(DataBaseContract.Avaliacao.COLUNA_TITULO, avaliacao.getTitulo());
        values.put(DataBaseContract.Avaliacao.COLUNA_DESCRICAO, avaliacao.getDescricao());
        values.put(DataBaseContract.Avaliacao.COLUNA_DATA, avaliacao.getDataDaAvaliacao());
        values.put(DataBaseContract.Avaliacao.COLUNA_HORARIO, avaliacao.getHoraDaAvaliacao());

        long idavaliacao = db.insert(DataBaseContract.Avaliacao.NOME_TABELA, null, values);

        db.close();

    }


    public ArrayList<Avaliacao> consultaBase() {

        ArrayList<Avaliacao> avaliacaos = new ArrayList<>();
        Avaliacao avaliacao;

        db = avaliacaoHelper.getReadableDatabase();

        Cursor c = db.rawQuery("SELECT * FROM Avalicacao INNER JOIN Notificacao on Notificacao.ID == Avalicao.ID INNER JOIN  Tipo_Notificacao on Notificacao.Tipo_Notificao = Tipo_Notificacao.ID", null);
        if (c.moveToFirst()){
            do {

                int idAvaliacao = c.getInt(0);
                String tituloAvaliacao =c.getString(1);
                String descricaoAvaliacao = c.getString(2);
                String dataAvaliacao = c.getString(3);
                String horaAvaliacao = c.getString(4);

                avaliacao = new Avaliacao(idAvaliacao,tituloAvaliacao,descricaoAvaliacao,dataAvaliacao,horaAvaliacao);
                avaliacaos.add(avaliacao);

            } while(c.moveToNext());
        }
        c.close();
        db.close();

        return avaliacaos;

    }
}
