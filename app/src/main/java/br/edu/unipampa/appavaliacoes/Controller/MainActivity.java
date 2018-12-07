package br.edu.unipampa.appavaliacoes.Controller;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import java.util.ArrayList;
import br.edu.unipampa.appavaliacoes.Persistencia.DataBasePersistencia;
import br.edu.unipampa.appavaliacoes.Model.Avaliacao;
import br.edu.unipampa.appavaliacoes.R;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    public FloatingActionButton adicionar;
    private ArrayList<Avaliacao> avaliacoes;
    private Bundle bundle;
    private View rootView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        adicionar = findViewById(R.id.adicionar);
        adicionar.setOnClickListener(this);

        ListView lw = (ListView) findViewById(R.id.listView);
        ArrayList<String> atividades = carregarLista();
        ArrayAdapter<String> exibir = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,atividades);
        lw.setAdapter(exibir);

    }

    @Override
    public void onStart() {
        super.onStart();
        apresentaAvaliacoes();

    }

    private ArrayList<String> carregarLista(){
        ArrayList<String> atividades = new ArrayList<>();
        ArrayList<Avaliacao> listAva = new ArrayList<>();
        DataBasePersistencia db = new DataBasePersistencia(this);
        listAva = db.consultaBase();


        for (int i =0; i<listAva.size();i++){
            atividades.add(listAva.get(i).getTitulo());

        }

        return atividades;
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

    public void apresentaAvaliacoes(){

    }
}