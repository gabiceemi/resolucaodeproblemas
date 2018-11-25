package br.edu.unipampa.appavaliacoes.Persistencia;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

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


    public void consultaBase() {
    }
}
