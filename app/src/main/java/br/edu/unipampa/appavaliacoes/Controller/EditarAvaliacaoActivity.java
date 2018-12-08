package br.edu.unipampa.appavaliacoes.Controller;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.TextView;

import br.edu.unipampa.appavaliacoes.Model.Notificacao;
import br.edu.unipampa.appavaliacoes.R;

public class EditarAvaliacaoActivity extends AppCompatActivity {

    private int id;
    private String titulo;
    private String descricao;
    private String dataDaAvaliacao;
    private String horaDaAvaliacao;
    private Notificacao notificacao;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_avaliacao);
        Bundle bundle;
        bundle = getIntent().getExtras();

        id = bundle.getInt("id");
        titulo = bundle.getString("titulo");
        descricao = bundle.getString("descricao");
        dataDaAvaliacao  = bundle.getString("data");
        horaDaAvaliacao = bundle.getString("hora");

        visualizarDados();

    }

    private void visualizarDados(){
        EditText textViewTitulo = findViewById(R.id.titulo_editar);
        textViewTitulo.setText(titulo);
        EditText textViewDescricao = findViewById(R.id.descricao_editar);
        textViewDescricao.setText(descricao);
        TextView textViewData = (TextView) findViewById(R.id.viewData_editar);
        textViewData.setText(dataDaAvaliacao);
        TextView textViewHora = (TextView) findViewById(R.id.viewHora_editar);
        textViewHora.setText(horaDaAvaliacao);
    }

}
