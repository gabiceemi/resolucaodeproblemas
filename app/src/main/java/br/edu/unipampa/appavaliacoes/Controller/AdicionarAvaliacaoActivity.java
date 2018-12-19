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
import android.widget.Switch;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

import br.edu.unipampa.appavaliacoes.Model.Avaliacao;
import br.edu.unipampa.appavaliacoes.Model.Notificacao;
import br.edu.unipampa.appavaliacoes.Persistencia.DataBasePersistencia;
import br.edu.unipampa.appavaliacoes.R;

public class AdicionarAvaliacaoActivity extends AppCompatActivity implements View.OnClickListener {

    public TextView data, horario, dataNotificacao, horarioNotificacao;
    private  int dia,mes,ano,hora,minutos;
    public Button salvar;
    public EditText titulo, descricao, mensagem;
    public ImageButton cancelar;
    public DataBasePersistencia dataBasePersistencia;
    public Avaliacao avaliacao;
    public Notificacao notificacao;
    public Switch tipoSonoro, tipoLuminoso, tipoMensagem;
    public boolean camposPreenchidos = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adicionar_avaliacao);

        avaliacao = new Avaliacao();
        notificacao = new Notificacao();
        dataBasePersistencia = new DataBasePersistencia(this);
        localizarCampos();

        salvar = findViewById(R.id.salvar);
        cancelar = findViewById(R.id.cancelar);


        salvar.setOnClickListener(this);
        cancelar.setOnClickListener(this);
        data.setOnClickListener(this);
        horario.setOnClickListener(this);
        dataNotificacao.setOnClickListener(this);
        horarioNotificacao.setOnClickListener(this);

    }

    public void localizarCampos(){

        titulo = findViewById(R.id.titulo);
        descricao = findViewById(R.id.descricao);
        data = findViewById(R.id.viewData);
        horario = findViewById(R.id.viewHora);

        tipoSonoro = findViewById(R.id.switch_sonoro);
        tipoLuminoso = findViewById(R.id.switch_luminoso);
        tipoMensagem = findViewById(R.id.switch_mensagem);

        mensagem = findViewById(R.id.mensagem_notificacao);

        dataNotificacao = findViewById(R.id.viewData_notificacao);
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
            c.set(Calendar.YEAR, 2018);
            c.set(Calendar.MONTH, Calendar.DECEMBER);
            c.set(Calendar.DAY_OF_MONTH, 19);

            DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                    dataNotificacao.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
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
                    horarioNotificacao.setText(hourOfDay + ":" + minute);
                }
            }, hora, minutos, false);
            timePickerDialog.show();

        }
        if(v==salvar){
            isValida();

            if(camposPreenchidos == true) {

                avaliacao.setTitulo(titulo.getText().toString());
                avaliacao.setDescricao(descricao.getText().toString());
                avaliacao.setDataDaAvaliacao(data.getText().toString());
                avaliacao.setHoraDaAvaliacao(horario.getText().toString());

                notificacao.setData(dataNotificacao.getText().toString());
                notificacao.setHora(horarioNotificacao.getText().toString());
                if (tipoLuminoso.isChecked()) {
                    notificacao.setTipoNotifi(1);
                } else if (tipoSonoro.isChecked()) {
                    notificacao.setTipoNotifi(2);
                } else if (tipoMensagem.isChecked()) {
                    notificacao.setTipoNotifi(3);
                    notificacao.setMenssagem(mensagem.getText().toString());
                }

                try {

                    dataBasePersistencia.insert(avaliacao, notificacao);

                    Toast.makeText(AdicionarAvaliacaoActivity.this, "Salvo com sucesso", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(AdicionarAvaliacaoActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();

                } catch (Exception e) {
                    Toast.makeText(AdicionarAvaliacaoActivity.this, "Não foi possível salvar", Toast.LENGTH_SHORT).show();
                }
            }
        }
        if(v==cancelar){
            Intent intent = new Intent(AdicionarAvaliacaoActivity.this, MainActivity.class);
            startActivity(intent);
            finish();

        }
    }

    public void isValida(){

        if(titulo.getText().toString() == null || descricao.getText().toString() == null ||
                data.getText().toString() == null || horario.getText().toString() == null){
            Toast.makeText(AdicionarAvaliacaoActivity.this, "Preencha todos os campos obrigatórios", Toast.LENGTH_SHORT).show();
            camposPreenchidos = false;
        }
    }
}
