package br.edu.unipampa.appavaliacoes.Controller;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.Calendar;

import br.edu.unipampa.appavaliacoes.Model.Avaliacao;
import br.edu.unipampa.appavaliacoes.Model.Notificacao;
import br.edu.unipampa.appavaliacoes.Persistencia.DataBasePersistencia;
import br.edu.unipampa.appavaliacoes.R;

public class EditarAvaliacaoActivity extends AppCompatActivity {

    private int id;
    private String titulo_edit;
    private String descricao_edit;
    private String dataDaAvaliacao;
    private String horaDaAvaliacao;
    private Notificacao notification;


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

        visualizarDados();
    }


    private void visualizarDados(){
        EditText textViewTitulo = findViewById(R.id.titulo_editar);
        textViewTitulo.setText(titulo_edit);
        EditText textViewDescricao = findViewById(R.id.descricao_editar);
        textViewDescricao.setText(descricao_edit);
        TextView textViewData = (TextView) findViewById(R.id.viewData_editar);
        textViewData.setText(dataDaAvaliacao);
        TextView textViewHora = (TextView) findViewById(R.id.viewHora_editar);
        textViewHora.setText(horaDaAvaliacao);
    }

}
