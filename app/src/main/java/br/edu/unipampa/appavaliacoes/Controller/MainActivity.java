package br.edu.unipampa.appavaliacoes.Controller;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import org.w3c.dom.ls.LSInput;

import java.util.ArrayList;
import java.util.List;

import br.edu.unipampa.appavaliacoes.Persistencia.AvaliacaoDBHelper;
import br.edu.unipampa.appavaliacoes.Persistencia.DataBaseContract;
import br.edu.unipampa.appavaliacoes.Persistencia.DataBasePersistencia;
import br.edu.unipampa.appavaliacoes.Model.Avaliacao;
import br.edu.unipampa.appavaliacoes.R;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    public FloatingActionButton adicionar;
    public ArrayList<Avaliacao> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        adicionar = findViewById(R.id.adicionar);
        adicionar.setOnClickListener(this);


        ListView lw = (ListView) findViewById(R.id.listView);
       /* ArrayList<String> atividades = carregarLista();
        ArrayAdapter<String> exibir = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,atividades);
        lw.setAdapter(exibir);*/

        list = carregaAvaliacao();
        AtividadeAdapter ap = new AtividadeAdapter(getApplicationContext(),R.layout.list_perso, list);
        lw.setAdapter(ap);

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

    public ArrayList<Avaliacao> carregaAvaliacao(){

        DataBasePersistencia db = new DataBasePersistencia(this);
        return db.consultaBase();

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


    public class AtividadeAdapter extends ArrayAdapter{


        private List<Avaliacao> listAvaliacao;
        private int resource;
        private LayoutInflater inflater;

        public AtividadeAdapter(Context context, int resource, List<Avaliacao> objects) {
            super(context, resource, objects);
            listAvaliacao = objects;
            this.resource = resource;
            inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        }



        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            if(convertView == null){

                convertView = inflater.inflate(resource,null);

            }

            ImageView isbookicone;
            TextView  titulo;
            TextView data;
            TextView hora;

            isbookicone = (ImageView) convertView.findViewById(R.id.isbookicone);
            titulo = (TextView)convertView.findViewById(R.id.titulo);
            data = (TextView)convertView.findViewById(R.id.data);
            hora = (TextView)convertView.findViewById(R.id.hora);


            titulo.setText(listAvaliacao.get(position).getTitulo());
            data.setText(listAvaliacao.get(position).getDataDaAvaliacao());
            hora.setText(listAvaliacao.get(position).getHoraDaAvaliacao());
            return convertView;
        }
    }

}