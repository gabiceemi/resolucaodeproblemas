package br.edu.unipampa.appavaliacoes.Controller;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;
import android.widget.Toast;

import br.edu.unipampa.appavaliacoes.Model.Notificacao;
import br.edu.unipampa.appavaliacoes.Persistencia.DataBasePersistencia;
import br.edu.unipampa.appavaliacoes.R;

public class TipoNotificacao extends AppCompatActivity implements View.OnClickListener {

    public Button confirmar;
    public Button voltar;
    public Switch sonoro;
    public Switch luminoso;
    public Switch mensagem;
    public String tipo;
    DataBasePersistencia dataBasePersistencia;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tipo_notificacao);

        confirmar = findViewById(R.id.btn_confirmar);
        voltar = findViewById(R.id.btn_voltar);
        sonoro = findViewById(R.id.switch_sonoro);
        luminoso = findViewById(R.id.switch_luminoso);
        mensagem = findViewById(R.id.switch_mensagem);

        confirmar.setOnClickListener(this);
        voltar.setOnClickListener(this);


        this.dataBasePersistencia = DataBasePersistencia.getInstance(this);
    }

    public void onClick(View v) {
        if(v==confirmar){
            Notificacao notificacao = new Notificacao();
            String t = tipo.toString();
            notificacao.setTipoNotifi(t);

            Context contexto = getApplicationContext();
            String texto = "tipo de notificação salva";
            int duracao = Toast.LENGTH_SHORT;

            Toast toast = Toast.makeText(contexto, texto,duracao);
            toast.show();
            Toast.makeText(getApplicationContext(), "tipo de notificação salva", Toast.LENGTH_SHORT).show();

            setContentView(R.layout.activity_adicionar_avaliacao);
        }
        if(v==voltar){
            Intent intent = new Intent(TipoNotificacao.this,
                    AdicionarAvaliacaoActivity.class);
            startActivity(intent);
            finish();

        }

    }

}
