package br.edu.unipampa.appavaliacoes.Controller;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.Calendar;

import br.edu.unipampa.appavaliacoes.R;

public class AdiarNotificacao extends AppCompatActivity implements View.OnClickListener {


    public TextView data, horario, notificacao;
    private int dia, mes, ano, hora, minutos;
    public Button salvar;
    public ImageButton cancelar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notificationafter);

        data = findViewById(R.id.viewData_adiar);
        horario = findViewById(R.id.viewHora_adiar);
        notificacao = findViewById(R.id.notification_adiar);
        salvar = findViewById(R.id.salvar_adiar);
        cancelar = findViewById(R.id.cancelar_adiar);
        salvar.setOnClickListener(this);
        cancelar.setOnClickListener(this);
        data.setOnClickListener(this);
        horario.setOnClickListener(this);
        notificacao.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        if (view == data) {
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
        if (view == horario) {
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
        if (view == notificacao) {
            setContentView(R.layout.activity_tipo_notificacao);
        }
        if (view == salvar) {

        }
        if (view == cancelar) {
            Intent intent = new Intent(AdiarNotificacao.this,
                    MainActivity.class);
            startActivity(intent);
            finish();

        }
    }


}
