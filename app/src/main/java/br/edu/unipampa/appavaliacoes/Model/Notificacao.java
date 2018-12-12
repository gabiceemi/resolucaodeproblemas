package br.edu.unipampa.appavaliacoes.Model;

		import java.util.ArrayList;

public class Notificacao {

	private long id;
	private String data;
	private String hora;
	private int tipoNotifi;
	private String mensagem;

	public Notificacao(){

	}

	public Notificacao(int idNotificacao, String dataNotificacao, String horaNotificacao, int idTipoNotificao) {

		this.data = "";
		this.hora = "00:00";
		this.tipoNotifi = 0;
	}


    public String getMenssagem() {
        return mensagem;
    }

    public void setMenssagem(String menssagem) {
        this.mensagem = menssagem;
    }

    public void setId(long id) {

        this.id = id;
    }

    public  Notificacao(int id, String data, String hora, int tipoNotifi, String mensagem) {

        this.id = id;
        this.data = data;
        this.hora = hora;
        this.tipoNotifi = tipoNotifi;
        this.mensagem = mensagem;
    }
    public  Notificacao(int id, String dataNotificacao, String horaNotificacao, String mensagemNotificao, int tipoNotifi) {

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

	public int getTipoNotifi()
	{
		return tipoNotifi;
	}

	public void setTipoNotifi(int tipoNotifi) {

		this.tipoNotifi = tipoNotifi;
	}
}
