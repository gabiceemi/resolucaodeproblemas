package br.edu.unipampa.appavaliacoes.Service;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class NotificacaoService  extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		Toast.makeText(context, "Você tem uma avaliação!!", Toast.LENGTH_LONG).show();
	}
}
