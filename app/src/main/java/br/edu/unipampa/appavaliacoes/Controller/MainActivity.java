package br.edu.unipampa.appavaliacoes.Controller;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import br.edu.unipampa.appavaliacoes.Persistencia.AvaliacaoDBHelper;
import br.edu.unipampa.appavaliacoes.Persistencia.DataBasePersistencia;
import br.edu.unipampa.appavaliacoes.R;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    public FloatingActionButton adicionar;
    AvaliacaoDBHelper db;
    DataBasePersistencia persistencia;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        adicionar = findViewById(R.id.adicionar);
        adicionar.setOnClickListener(this);
        this.db = AvaliacaoDBHelper.getInstance(this);
        this.db.getConexaoDataBase();
        this.persistencia = DataBasePersistencia.getInstance(this);
        this.persistencia.consultaBase();

    }

    @Override
    public void onClick(View v) {
        if (v == adicionar) {
            Intent intent = new Intent(MainActivity.this,
                    AdicionarAvaliacaoActivity.class);
            startActivity(intent);
            finish();
        }
    }
}