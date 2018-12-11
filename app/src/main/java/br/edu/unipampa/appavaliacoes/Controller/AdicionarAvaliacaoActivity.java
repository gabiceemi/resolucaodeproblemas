package br.edu.unipampa.appavaliacoes.Controller;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

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
    public DataBasePersistencia dataBasePersistencia;
    public Avaliacao avaliacao;
    public TextView dataNotificacao, horarioNotificacao, tipoNotificacao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adicionar_avaliacao);

        avaliacao = new Avaliacao();
        dataBasePersistencia = new DataBasePersistencia(this);
        localizarCampos();

        salvar = findViewById(R.id.salvar_editar);
        cancelar = findViewById(R.id.cancelar_editar);


        salvar.setOnClickListener(this);
        cancelar.setOnClickListener(this);
        data.setOnClickListener(this);
        horario.setOnClickListener(this);
        dataNotificacao.setOnClickListener(this);
        horarioNotificacao.setOnClickListener(this);

    }

    public void localizarCampos(){

        titulo = findViewById(R.id.titulo_editar);
        descricao = findViewById(R.id.descricao_editar);
        data = findViewById(R.id.viewData_editar);
        horario = findViewById(R.id.viewHora_editar);

        tipoNotificacao = findViewById(R.id.switch_sonoro_editar);
        dataNotificacao = findViewById(R.id.viewData_notificacao_editar);
        horarioNotificacao = findViewById(R.id.viewHora_notificacao);

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
        if(v==dataNotificacao){
            final Calendar c = Calendar.getInstance();
            dia = c.get(Calendar.DAY_OF_MONTH);
            mes = c.get(Calendar.MONTH);
            ano = c.get(Calendar.YEAR);

            DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                    data.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                }
            }
                    , dia, mes, ano);
            datePickerDialog.show();

        }
        if(v==horarioNotificacao){
            final Calendar c = Calendar.getInstance();
            hora = c.get(Calendar.HOUR_OF_DAY);
            minutos = c.get(Calendar.MINUTE);

            TimePickerDialog timePickerDialog = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
                @Override
                public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                    horario.setText(hourOfDay + ":" + minute);
                }
            }, hora, minutos, false);
            timePickerDialog.show();

        }
        if(v==salvar){

            avaliacao.setTitulo(titulo.getText().toString());
            avaliacao.setDescricao(descricao.getText().toString());
            avaliacao.setDataDaAvaliacao(data.getText().toString());
            avaliacao.setHoraDaAvaliacao(horario.getText().toString());
            dataBasePersistencia.insertAvalicao(avaliacao, -1);

            Toast.makeText(AdicionarAvaliacaoActivity.this, "Salvo com sucesso", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(AdicionarAvaliacaoActivity.this, MainActivity.class);
            startActivity(intent);
            finish();

        }
        if(v==cancelar){
            finish();

        }
    }
}
