package br.edu.unipampa.appavaliacoes.Persistencia;

import android.provider.BaseColumns;

public class DataBaseContract {

	public static abstract class Avaliacao implements BaseColumns {
        public static final String NOME_TABELA = "Avaliacao";
        public static final String COLUNA_TITULO = "titulo";
        public static final String COLUNA_DESCRICAO = "descricao";
        public static final String COLUNA_DATA = "dia";
        public static final String COLUNA_HORARIO = "horario";
    }

    public static abstract class TipoNotificacao implements BaseColumns {
        public static final String NOME_TABELA = "AdicionarNotificacaoActivity";
        public static final String COLUNA_TIPO = "tipo";
        
    }

    public static abstract class Notificacao implements BaseColumns {
        public static final String NOME_TABELA = "Notificacao";
        public static final String COLUNA_DATA_NOTIFICACAO = "datanotificacao";
        public static final String COLUNA_HORARIO_NOTIFICACAO = "horarionotificacao";
        public static final String COLUNA_MENSAGEM = "mensagem";
        public static final String COLUNA_FK_AVALIACAO = "avaliacao";
        public static final String COLUNA_FK_TIPO_NOTIFICACAO = "tiponotificacao";

    }

}
