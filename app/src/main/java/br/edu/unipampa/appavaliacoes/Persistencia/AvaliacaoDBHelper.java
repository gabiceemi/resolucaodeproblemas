package br.edu.unipampa.appavaliacoes.Persistencia;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class AvaliacaoDBHelper extends SQLiteOpenHelper {

    private static final String BANCO_NOME = "avaliacoes.db";
    private static final int BANCO_VERSAO = 1;

    public AvaliacaoDBHelper(Context context) {
        super(context, BANCO_NOME, null, BANCO_VERSAO);
    }

    private static final String SQL_CREATE_TABLE_AVALIACAO =
            "CREATE TABLE " + DataBaseContract.Avaliacao.NOME_TABELA + " (" +
                    DataBaseContract.Avaliacao._ID + " INTEGER PRIMARY KEY," +
                    DataBaseContract.Avaliacao.COLUNA_TITULO+ " TEXT, "+
                    DataBaseContract.Avaliacao.COLUNA_DESCRICAO + " TEXT, " +
                    DataBaseContract.Avaliacao.COLUNA_DATA + " TEXT, " +
                    DataBaseContract.Avaliacao.COLUNA_HORARIO + " TEXT);";

    private static final String SQL_CREATE_TABLE_TIPO_NOTIFICACAO =
            "CREATE TABLE " + DataBaseContract.TipoNotificacao.NOME_TABELA + " (" +
                    DataBaseContract.TipoNotificacao._ID + " INTEGER PRIMARY KEY," +
                    DataBaseContract.TipoNotificacao.COLUNA_TIPO+ " TEXT);";

    private static final String SQL_CREATE_TABLE_NOTIFICACAO =
            "CREATE TABLE " + DataBaseContract.Notificacao.NOME_TABELA + " (" +
                    DataBaseContract.Notificacao._ID + " INTEGER PRIMARY KEY," +
                    DataBaseContract.Notificacao.COLUNA_DATA_NOTIFICACAO+ " TEXT, "+
                    DataBaseContract.Notificacao.COLUNA_HORARIO_NOTIFICACAO+ " TEXT, "+
                    DataBaseContract.Notificacao.COLUNA_MENSAGEM+ " TEXT, "+
                    DataBaseContract.Notificacao.COLUNA_FK_AVALIACAO+" int not null," +
                    DataBaseContract.Notificacao.COLUNA_FK_TIPO_NOTIFICACAO+" int not null," +
                    "FOREIGN KEY ("+ DataBaseContract.Notificacao.COLUNA_FK_AVALIACAO+") REFERENCES "+ DataBaseContract.Avaliacao.NOME_TABELA+"("+DataBaseContract.Avaliacao._ID+"), " +
                    "FOREIGN KEY ("+ DataBaseContract.Notificacao.COLUNA_FK_TIPO_NOTIFICACAO+") REFERENCES "+ DataBaseContract.TipoNotificacao.NOME_TABELA+"("+DataBaseContract.Avaliacao._ID+"));";


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_TABLE_AVALIACAO);
        db.execSQL(SQL_CREATE_TABLE_TIPO_NOTIFICACAO);
        db.execSQL(SQL_CREATE_TABLE_NOTIFICACAO);
        Log.d("Banco de Dados","Criando banco de dados.");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
