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
import android.widget.Switch;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

import br.edu.unipampa.appavaliacoes.Model.Avaliacao;
import br.edu.unipampa.appavaliacoes.Model.Notificacao;
import br.edu.unipampa.appavaliacoes.Persistencia.DataBasePersistencia;
import br.edu.unipampa.appavaliacoes.R;

public class EditarAvaliacaoActivity extends AppCompatActivity implements View.OnClickListener {

    private int id, dia,mes,ano,hora,minutos;
    private String titulo_edit,descricao_edit,dataDaAvaliacao, horaDaAvaliacao;
    private EditText textViewTitulo, textViewDescricao, textoMensagem;
    private TextView textViewData, textViewHora, textViewDataNotificacao, textViewHoraNotificacao;
    private Switch luminoso, sonoro, mensagem;
    public Button salvar;
    public ImageButton cancelar;
    public Avaliacao avaliacao;
    public Notificacao notificacao;
    public DataBasePersistencia db;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_avaliacao);
        db = new DataBasePersistencia(this);

        Bundle bundle;
        bundle = getIntent().getExtras();

        id = bundle.getInt("id");
        titulo_edit = bundle.getString("titulo");
        descricao_edit = bundle.getString("descricao");
        dataDaAvaliacao = bundle.getString("data");
        horaDaAvaliacao = bundle.getString("hora");

        localizarCampos();
        apresentarDados();

        salvar = findViewById(R.id.salvar_editar);
        cancelar = findViewById(R.id.cancelar_editar);
        textViewHora.setOnClickListener(this);
        textViewData.setOnClickListener(this);
        textViewHoraNotificacao.setOnClickListener(this);
        textViewDataNotificacao.setOnClickListener(this);
        salvar.setOnClickListener(this);
        cancelar.setOnClickListener(this);

    }

    private void localizarCampos() {
        textViewTitulo = findViewById(R.id.titulo_editar);
        textViewDescricao = findViewById(R.id.descricao_editar);
        textViewData = findViewById(R.id.viewData_editar);
        textViewHora = findViewById(R.id.viewHora_editar);
        textViewDataNotificacao = findViewById(R.id.viewData_notificacao_editar);
        textViewHoraNotificacao = findViewById(R.id.viewHora_notificacao_editar);
        luminoso = findViewById(R.id.switch_luminoso_editar);
        sonoro = findViewById(R.id.switch_sonoro_editar);
        mensagem = findViewById(R.id.switch_mensagem_editar);
        textoMensagem = findViewById(R.id.mensagem_notificacao_editar);
    }


    private void apresentarDados(){
        notificacao = db.consultaNotificacao(id);
        textViewTitulo.setText(titulo_edit);
        textViewDescricao.setText(descricao_edit);
        textViewData.setText(dataDaAvaliacao);
        textViewHora.setText(horaDaAvaliacao);
        textViewDataNotificacao.setText(notificacao.getData());
        textViewHoraNotificacao.setText(notificacao.getHora());
        if(notificacao.getTipoNotifi() == 1){
            luminoso.setChecked(true);
        }else if (notificacao.getTipoNotifi() == 2){
            sonoro.setChecked(true);
        }else if(notificacao.getTipoNotifi() == 3){
            mensagem.setChecked(true);
            textoMensagem.setText(notificacao.getMenssagem());
        }

    }

    @Override
    public void onClick(View v) {

        if(v==textViewData){
            final Calendar c= Calendar.getInstance();
            dia=c.get(Calendar.DAY_OF_MONTH);
            mes=c.get(Calendar.MONTH);
            ano=c.get(Calendar.YEAR);

            DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                    textViewData.setText(dayOfMonth+"/"+(monthOfYear+1)+"/"+year);
                }
            }
                    ,dia,mes,ano);
            datePickerDialog.show();
        }
        if (v==textViewHora){
            final Calendar c= Calendar.getInstance();
            hora=c.get(Calendar.HOUR_OF_DAY);
            minutos=c.get(Calendar.MINUTE);

            TimePickerDialog timePickerDialog = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
                @Override
                public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                    textViewHora.setText(hourOfDay+":"+minute);
                }
            },hora,minutos,false);
            timePickerDialog.show();
        }
        if(v==textViewDataNotificacao){
            final Calendar c = Calendar.getInstance();
            dia = c.get(Calendar.DAY_OF_MONTH);
            mes = c.get(Calendar.MONTH);
            ano = c.get(Calendar.YEAR);

            DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                    textViewDataNotificacao.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                }
            }
                    , dia, mes, ano);
            datePickerDialog.show();

        }
        if(v== textViewHoraNotificacao){
            final Calendar c = Calendar.getInstance();
            hora = c.get(Calendar.HOUR_OF_DAY);
            minutos = c.get(Calendar.MINUTE);

            TimePickerDialog timePickerDialog = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
                @Override
                public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                    textViewHoraNotificacao.setText(hourOfDay + ":" + minute);
                }
            }, hora, minutos, false);
            timePickerDialog.show();

        }
        if(v==salvar){
            textViewTitulo = findViewById(R.id.titulo_editar);
            textViewDescricao = findViewById(R.id.descricao_editar);
            textViewData = findViewById(R.id.viewData_editar);
            textViewHora = findViewById(R.id.viewHora_editar);
            textViewDataNotificacao = findViewById(R.id.viewData_notificacao_editar);
            textViewHoraNotificacao = findViewById(R.id.viewHora_notificacao_editar);
            luminoso = findViewById(R.id.switch_luminoso_editar);
            sonoro = findViewById(R.id.switch_sonoro_editar);
            mensagem = findViewById(R.id.switch_mensagem_editar);
            textoMensagem = findViewById(R.id.mensagem_notificacao_editar);

            avaliacao.setTitulo(textViewTitulo.getText().toString());
            avaliacao.setDescricao(textViewDescricao.getText().toString());
            avaliacao.setDataDaAvaliacao(textViewData.getText().toString());
            avaliacao.setHoraDaAvaliacao(textViewHora.getText().toString());

            notificacao.setData(textViewDataNotificacao.getText().toString());
            notificacao.setHora(textViewHoraNotificacao.getText().toString());
            if(luminoso.isChecked()){
                notificacao.setTipoNotifi(1);
            } else if(sonoro.isChecked()){
                notificacao.setTipoNotifi(2);
            } else if(mensagem.isChecked()){
                notificacao.setTipoNotifi(3);
                notificacao.setMenssagem(textoMensagem.getText().toString());
            }

            try {

                db.updateAvaliacao(avaliacao, notificacao);

                Toast.makeText(EditarAvaliacaoActivity.this, "Salvo com sucesso", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(EditarAvaliacaoActivity.this, MainActivity.class);
                startActivity(intent);
                finish();

            }catch (Exception e){
                Toast.makeText(EditarAvaliacaoActivity.this, "Não foi possível salvar", Toast.LENGTH_SHORT).show();
            }

        }

        if(v==cancelar){
            finish();

        }

    }

}
