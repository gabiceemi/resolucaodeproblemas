package br.edu.unipampa.appavaliacoes.Controller;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import br.edu.unipampa.appavaliacoes.R;

public class TipoNotificacao extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tipo_notificacao);

    getSupportActionBar().setTitle("Tipo de Notificação");
    }
}
