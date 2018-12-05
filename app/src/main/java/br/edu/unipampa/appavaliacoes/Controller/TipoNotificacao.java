package br.edu.unipampa.appavaliacoes.Controller;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

import br.edu.unipampa.appavaliacoes.Model.Notificacao;
import br.edu.unipampa.appavaliacoes.Persistencia.DataBasePersistencia;
import br.edu.unipampa.appavaliacoes.R;

public class TipoNotificacao extends AppCompatActivity implements View.OnClickListener {

    public Button confirmar;
    public ImageButton voltar;
    public String tipo;
    public Notificacao notificacao;
    public DataBasePersistencia dataBasePersistencia;
    public TextView data, horario, tipoNotificacao;
    private int dia, mes, ano, hora, minutos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tipo_notificacao);
        localizarCampos();

        notificacao = new Notificacao();
        dataBasePersistencia = new DataBasePersistencia(this);

    }

    public void localizarCampos() {
        confirmar = findViewById(R.id.btn_confirmar);
        voltar = findViewById(R.id.btn_voltar);
        voltar.setOnClickListener(this);
        tipoNotificacao = findViewById(R.id.switch_sonoro);
        data = findViewById(R.id.btnData);
        horario = findViewById(R.id.btnHora);


    }

    public void eventClick(View view) {
        final Calendar c = Calendar.getInstance();

        switch (view.getId()) {
            case R.id.btnData:
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

                break;

            case R.id.btnHora:
                hora = c.get(Calendar.HOUR_OF_DAY);
                minutos = c.get(Calendar.MINUTE);

                TimePickerDialog timePickerDialog = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        horario.setText(hourOfDay + ":" + minute);
                    }
                }, hora, minutos, false);
                timePickerDialog.show();
                break;

            case R.id.btn_confirmar:

//                String t = tipo.toString();
//                notificacao.setTipoNotifi(t);

              Toast.makeText(TipoNotificacao.this, "Salvo com sucesso", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    @Override
    public void onClick(View v) {
        if(v==voltar){
            finish();
        }
    }
}
