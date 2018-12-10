package br.edu.unipampa.appavaliacoes.Model;

		import java.util.ArrayList;

public class Notificacao {

	private long id;
	private String data;
	private String hora;
	private String tipoNotifi;

	public Notificacao() {

		this.data = "";
		this.hora = "00:00";
		this.tipoNotifi = "";
	}


    public  Notificacao(int id, String data, String hora, String tipoNotifi) {

        this.id = id;
        this.data = data;
        this.hora = hora;
        this.tipoNotifi = tipoNotifi;
    }
    public  Notificacao(int id, String tipoNotifi) {

        this.id = id;
        this.tipoNotifi = tipoNotifi;
    }

	private ArrayList<Notificacao> notificacoes;


	public int getId() {
		return (int) id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public boolean isValida() {

		return false;
	};

	public String getData() {

		return data;
	}

	public void setData(String data) {

		this.data = data;
	}

	public String getHora()
	{
		return hora;
	}

	public void setHora(String hora)
	{
		this.hora = hora;
	}

	public String getTipoNotifi()
	{
		return tipoNotifi;
	}

	public void setTipoNotifi(String tipoNotifi) {

		this.tipoNotifi = tipoNotifi;
	}
}
