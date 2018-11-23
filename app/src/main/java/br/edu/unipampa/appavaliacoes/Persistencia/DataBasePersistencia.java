package br.edu.unipampa.appavaliacoes.Persistencia;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public class DataBasePersistencia {

    private AvaliacaoDBHelper avaliacaoHelper;
    private SQLiteDatabase bd;

    public DataBasePersistencia(Context context) {
        avaliacaoHelper = new AvaliacaoDBHelper(context);
    }

}
