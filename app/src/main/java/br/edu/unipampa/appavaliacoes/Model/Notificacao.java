package br.edu.unipampa.appavaliacoes.Model;

import java.util.ArrayList;

public class Notificacao {

	private String data;
	private String hora;
	private boolean avisoSonoro;
	private boolean avisoLuminoso;
	private boolean avisoMensagem;
	private ArrayList<Notificacao> notificacoes;

	public boolean isValida() {
		return false;
	}

}
