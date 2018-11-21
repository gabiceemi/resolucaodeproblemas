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

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getHora() {
		return hora;
	}

	public void setHora(String hora) {
		this.hora = hora;
	}

	public boolean isAvisoSonoro() {
		return avisoSonoro;
	}

	public void setAvisoSonoro(boolean avisoSonoro) {
		this.avisoSonoro = avisoSonoro;
	}

	public boolean isAvisoLuminoso() {
		return avisoLuminoso;
	}

	public void setAvisoLuminoso(boolean avisoLuminoso) {
		this.avisoLuminoso = avisoLuminoso;
	}

	public boolean isAvisoMensagem() {
		return avisoMensagem;
	}

	public void setAvisoMensagem(boolean avisoMensagem) {
		this.avisoMensagem = avisoMensagem;
	}
}
