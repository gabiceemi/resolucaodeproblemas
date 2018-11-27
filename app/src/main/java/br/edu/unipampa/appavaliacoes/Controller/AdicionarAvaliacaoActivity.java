package br.edu.unipampa.appavaliacoes.Controller;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.Calendar;

import br.edu.unipampa.appavaliacoes.Model.Avaliacao;
import br.edu.unipampa.appavaliacoes.Persistencia.DataBasePersistencia;
import br.edu.unipampa.appavaliacoes.R;

public class AdicionarAvaliacaoActivity extends AppCompatActivity implements View.OnClickListener {

    public TextView data, horario, notificacao;
    private  int dia,mes,ano,hora,minutos;
    public Button salvar;
    public EditText titulo, descricao;
    public ImageButton cancelar;
    DataBasePersistencia dataBasePersistencia;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adicionar_avaliacao);

        titulo = findViewById(R.id.titulo);
        descricao = findViewById(R.id.descricao);
        data = findViewById(R.id.viewData);
        horario = findViewById(R.id.viewHora);
        notificacao = findViewById(R.id.notification);
        salvar = findViewById(R.id.cancel);
        cancelar = findViewById(R.id.cancelar);
        salvar.setOnClickListener(this);
        cancelar.setOnClickListener(this);
        data.setOnClickListener(this);
        horario.setOnClickListener(this);
        notificacao.setOnClickListener(this);

        this.dataBasePersistencia = DataBasePersistencia.getInstance(this);

    }

    @Override
    public void onClick(View v) {
        if(v==data){
            final Calendar c= Calendar.getInstance();
            dia=c.get(Calendar.DAY_OF_MONTH);
            mes=c.get(Calendar.MONTH);
            ano=c.get(Calendar.YEAR);

            DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                    data.setText(dayOfMonth+"/"+(monthOfYear+1)+"/"+year);
                }
            }
                    ,dia,mes,ano);
            datePickerDialog.show();
        }
        if (v==horario){
            final Calendar c= Calendar.getInstance();
            hora=c.get(Calendar.HOUR_OF_DAY);
            minutos=c.get(Calendar.MINUTE);

            TimePickerDialog timePickerDialog = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
                @Override
                public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                    horario.setText(hourOfDay+":"+minute);
                }
            },hora,minutos,false);
            timePickerDialog.show();
        }
        if(v==notificacao){
            setContentView(R.layout.activity_tipo_notificacao);
        }
        if(v==salvar){
            Avaliacao avaliacao = new Avaliacao();
            String t = titulo.toString();
            String d = descricao.toString();
            String dt = data.toString();
            String h = horario.toString();
            avaliacao.setTitulo(t);
            avaliacao.setDescricao(d);
            avaliacao.setDataDaAvaliacao(dt);
            avaliacao.setHoraDaAvaliacao(h);


        }
        if(v==cancelar){
            Intent intent = new Intent(AdicionarAvaliacaoActivity.this,
                    MainActivity.class);
            startActivity(intent);
            finish();

        }
    }
}
