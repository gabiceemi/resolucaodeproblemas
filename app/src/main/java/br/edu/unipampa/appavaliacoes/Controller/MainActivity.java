package br.edu.unipampa.appavaliacoes.Controller;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.ArrayList;

import br.edu.unipampa.appavaliacoes.Persistencia.DataBasePersistencia;
import br.edu.unipampa.appavaliacoes.Model.Avaliacao;
import br.edu.unipampa.appavaliacoes.R;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    public FloatingActionButton adicionar;
    public ArrayList<Avaliacao> avaliacoes = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        adicionar = findViewById(R.id.adicionar);
        adicionar.setOnClickListener(this);
        apresentaAvaliacoes();
    }

    public ArrayList<Avaliacao> carregaAvaliacao() {
        DataBasePersistencia db = new DataBasePersistencia(this);
        return db.consultaAvaliacao();

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

    public void apresentaAvaliacoes() {
        avaliacoes = carregaAvaliacao();

        ListView listView = (ListView) findViewById(R.id.listView);
        ListAdapterAvaliacao adapterAvaliacao = new ListAdapterAvaliacao(getBaseContext(), avaliacoes);
        listView.setAdapter((ListAdapter) adapterAvaliacao);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(getBaseContext(), EditarAvaliacaoActivity.class);
                Bundle bundle = new Bundle();

                bundle.putInt("id", (int) avaliacoes.get(position).getId());
                bundle.putString("titulo", avaliacoes.get(position).getTitulo());
                bundle.putString("descricao", avaliacoes.get(position).getDescricao());
                bundle.putString("data", avaliacoes.get(position).getDataDaAvaliacao());
                bundle.putString("hora", avaliacoes.get(position).getHoraDaAvaliacao());



                i.putExtras(bundle);

                startActivity(i);
                finish();
            }
        });

    }
}