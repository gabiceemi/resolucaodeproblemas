package br.edu.unipampa.appavaliacoes.Controller;


import android.app.Notification;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.ArrayList;

import br.edu.unipampa.appavaliacoes.Model.Notificacao;
import br.edu.unipampa.appavaliacoes.Persistencia.DataBasePersistencia;
import br.edu.unipampa.appavaliacoes.Model.Avaliacao;
import br.edu.unipampa.appavaliacoes.R;
import br.edu.unipampa.appavaliacoes.Service.TempoUtils;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    public FloatingActionButton adicionar;
    public ArrayList<Avaliacao> avaliacoes = new ArrayList<>();
    NotificationController notificao = new NotificationController(this);

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        adicionar = findViewById(R.id.adicionar);
        adicionar.setOnClickListener(this);
        apresentaAvaliacoes();


        Notificacao no = criarNotificacao(carregaNotificacao());
        if(no!=null) {

            //new TempoUtils().millisTempoNotificacao(no);
            new NotificationController(this).agendarNotificacao(carregaNotificacao().get(0));
            Log.i("info", "onCreate: looper");
        }


    }



    public Notificacao criarNotificacao(ArrayList<Notificacao> no){

        if(no.size()>0){

            return no.get(0);

        }
        return null;
    }

    public ArrayList<Notificacao> carregaNotificacao() {
        DataBasePersistencia db = new DataBasePersistencia(this);
        return db.consultaNotifition();

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
                Log.i("info", "onItemClick: Teste de click");
                finish();
            }
        });

    }



}