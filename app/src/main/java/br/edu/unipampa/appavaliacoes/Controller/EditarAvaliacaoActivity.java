package br.edu.unipampa.appavaliacoes.Controller;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.TextView;

import br.edu.unipampa.appavaliacoes.Model.Notificacao;
import br.edu.unipampa.appavaliacoes.R;

public class EditarAvaliacaoActivity extends AppCompatActivity {

    private int id;
    private String titulo_edit;
    private String descricao_edit;
    private String dataDaAvaliacao;
    private String horaDaAvaliacao;
    private Notificacao notification;
    private int tipoNotifi;
    private String dataNotificacao;
    private String horarioNotificacao;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_avaliacao);
        Bundle bundle;
        bundle = getIntent().getExtras();

        id = bundle.getInt("id");
        titulo_edit = bundle.getString("titulo");
        descricao_edit = bundle.getString("descricao");
        dataDaAvaliacao = bundle.getString("data");
        horaDaAvaliacao = bundle.getString("hora");


        tipoNotifi = bundle.putInt("tipoNotifi");
        dataNotificacao = bundle.getString("dataNotificacao");
        horarioNotificacao = bundle.getString("horarioNotificacao");

        visualizarDados();
    }


    private void visualizarDados(){
        EditText textViewTitulo = findViewById(R.id.titulo_editar);
        textViewTitulo.setText(titulo_edit);
        EditText textViewDescricao = findViewById(R.id.descricao);
        textViewDescricao.setText(descricao_edit);
        TextView textViewData = (TextView) findViewById(R.id.viewData);
        textViewData.setText(dataDaAvaliacao);
        TextView textViewHora = (TextView) findViewById(R.id.viewHora);
        textViewHora.setText(horaDaAvaliacao);

        TextView textViewData_notificacao = (TextView) findViewById(R.id.viewData_notificacao_editar);
        textViewData.setText(dataNotificacao);
        TextView textViewHora_notificacac = (TextView) findViewById(R.id.viewHora_notificacao_editar);
        textViewData.setText(horarioNotificacao);

    }

}
