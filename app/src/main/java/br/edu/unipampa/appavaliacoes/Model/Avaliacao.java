package br.edu.unipampa.appavaliacoes.Model;

import android.widget.EditText;
import android.widget.TextView;

import java.io.Serializable;

import br.edu.unipampa.appavaliacoes.Model.Notificacao;

public class Avaliacao implements Serializable {

	private int id;
	private String titulo;
	private String descricao;
	private String dataDaAvaliacao;
	private String horaDaAvaliacao;
	private Notificacao notificacao;

	public Avaliacao() {
		this.titulo = "";
		this.descricao = "";
		this.dataDaAvaliacao = "";
		this.horaDaAvaliacao = "00:00";
	}

	public boolean isValida() {
        return !titulo.isEmpty() && !dataDaAvaliacao.isEmpty() &&
                !horaDaAvaliacao.isEmpty();

	}

    //Organiza as avaliações
    /*public static void sort(List<Avaliacao> listaAvaliacoes) {
        Collections.sort(listaAvaliacoes, new Comparator<Avaliacao>() {
            @Override
            public int compare(Avaliacao o1, Avaliacao o2) {
                int tempo1 = TempoUtils.extractValorNumericoDeDia(o1.getDataDaAvaliacao());
                int tempo2 = TempoUtils.extractValorNumericoDeDia(o2.getDataDaAvaliacao());
                if (tempo1 < tempo2) {   // ordena por dia
                    return -1;
                } else if (tempo1 > tempo2) {
                    return 1;
                } else { // se dia igual ordena por horá início
                    tempo1 = TempoUtils.extractTotalMinutos(o1.getHoraDaAvaliacao());
                    tempo2 = TempoUtils.extractTotalMinutos(o2.getHoraDaAvaliacao());
                    if (tempo1 < tempo2) {
                        return -1;
                    } else if (tempo1 > tempo2) {
                        return 1;
                    }
                    }
                }
            }
        });*/

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

	public String getDataDaAvaliacao() {
		return dataDaAvaliacao;
	}

	public void setDataDaAvaliacao(String dataDaAvaliacao) {
		this.dataDaAvaliacao = dataDaAvaliacao;
	}

	public String getHoraDaAvaliacao() {
		return horaDaAvaliacao;
	}

	public void setHoraDaAvaliacao(String horaDaAvaliacao) {
		this.horaDaAvaliacao = horaDaAvaliacao;
	}

	public Notificacao getNotificacao() {
		return notificacao;
	}

	public void setNotificacao(Notificacao notificacao) {
		this.notificacao = notificacao;
	}

}
