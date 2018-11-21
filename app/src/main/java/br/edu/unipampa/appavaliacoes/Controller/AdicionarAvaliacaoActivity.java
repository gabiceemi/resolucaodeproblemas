package br.edu.unipampa.appavaliacoes.Controller;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.Calendar;

import br.edu.unipampa.appavaliacoes.R;

public class AdicionarAvaliacaoActivity extends AppCompatActivity implements View.OnClickListener {

    public TextView data, horario, notificacao;
    private  int dia,mes,ano,hora,minutos;
    public Button salvar;
    public ImageButton cancelar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adicionar_avaliacao);

        data = findViewById(R.id.viewData);
        horario = findViewById(R.id.viewHora);
        notificacao = findViewById(R.id.textViewNotificacao);
        salvar = findViewById(R.id.salvar);
        cancelar = findViewById(R.id.cancelar);
        salvar.setOnClickListener(this);
        cancelar.setOnClickListener(this);
        data.setOnClickListener(this);
        horario.setOnClickListener(this);
        notificacao.setOnClickListener(this);

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

        }
        if(v==cancelar){

        }
    }
}
