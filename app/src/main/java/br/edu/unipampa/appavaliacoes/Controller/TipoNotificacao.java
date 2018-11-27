package br.edu.unipampa.appavaliacoes.Controller;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import br.edu.unipampa.appavaliacoes.R;


public class TipoNotificacao extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tipo_notificacao);

        Button conf = (Button) findViewById(R.id.btn_confirmar);
        conf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(TipoNotificacao.this, AdicionarAvaliacaoActivity.class);
                startActivity(intent);
            }
        });

        Button voltar = (Button) findViewById(R.id.btn_voltar);
        voltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(TipoNotificacao.this, AdicionarAvaliacaoActivity.class);
                startActivity(intent);
            }
        });
    }
}
