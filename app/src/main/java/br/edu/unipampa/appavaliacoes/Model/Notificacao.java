package br.edu.unipampa.appavaliacoes.Model;

		import java.util.ArrayList;

public class Notificacao {

	private String data;
	private String hora;
	private String tipoNotifi;

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

	public boolean getTipoNotifi() {
		return true;
	}

	public void setTipoNotifi(String tipoNotifi) {
		this.tipoNotifi = tipoNotifi;
	}
}
