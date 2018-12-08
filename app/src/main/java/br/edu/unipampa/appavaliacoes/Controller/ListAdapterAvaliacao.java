package br.edu.unipampa.appavaliacoes.Controller;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import br.edu.unipampa.appavaliacoes.Model.Avaliacao;
import br.edu.unipampa.appavaliacoes.R;

class ListAdapterAvaliacao extends ArrayAdapter<Avaliacao> implements AdapterView.OnItemClickListener{
    private final Context context;

    public ListAdapterAvaliacao(Context context, ArrayList<Avaliacao> avaliacoes) {
        super(context,0,avaliacoes);
        this.context = context;
        ArrayList<Avaliacao> avaliacao1 = avaliacoes;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Avaliacao avaliacaoPosicao = this.getItem(position);

        convertView = LayoutInflater.from(this.context).inflate(R.layout.activity_list_adapter_avalicoes , null);

        TextView textViewData = (TextView) convertView.findViewById(R.id.dataAvaliacao);
        textViewData.setText(avaliacaoPosicao.getDataDaAvaliacao());
        TextView textViewHora = (TextView) convertView.findViewById(R.id.horaAvaliacao);
        textViewHora.setText(avaliacaoPosicao.getHoraDaAvaliacao());
        TextView textViewTitulo = (TextView) convertView.findViewById(R.id.tituloAvaliacao);
        if(avaliacaoPosicao.getTitulo().length() > 27) {
            textViewTitulo.setText(avaliacaoPosicao.getTitulo().substring(0, 27) + "...");
        } else {
            textViewTitulo.setText(avaliacaoPosicao.getTitulo());
        }

        return convertView;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(context, EditarAvaliacaoActivity.class);
        context.startActivity(intent);
    }
}
